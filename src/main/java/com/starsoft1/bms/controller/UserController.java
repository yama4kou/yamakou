package com.starsoft1.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.starsoft1.bms.dao.UserDAO;
import com.starsoft1.bms.model.UserModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private UserDAO userDAO;

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@PostMapping("/userEdit")
	public String userEdit(@ModelAttribute("user") UserModel userModel, BindingResult bindingResult,
			Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		user.setPassword(""); // 初期値を空文字列に設定する
		user.setConfirmPassword(""); // 初期値を空文字列に設定する
		
		model.addAttribute("user", user);

		return "userEdit";
	}


	@GetMapping("/userEdit")
	public String userEdit() {
		return "userEdit";
	}

	@PostMapping("/userEditComplete")
	public String userEditComplete(@ModelAttribute("user") UserModel newUser, BindingResult bindingResult,
			Model model, HttpServletRequest request) {
		// パスワードとパスワード確認の一致を検証する
		if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword", "error.user", "パスワードが一致しません");
			return "userEdit";
		}

		// エラーチェック
		if (bindingResult.hasErrors()) {
			return "userEdit";
		}


		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// ユーザー情報をデータベースに保存
		userDAO.updateUser(user, newUser);

		model.addAttribute("user", user);

		//sessionnに登録し直し
		session.setAttribute("user", newUser);

		return "userEditComplete";
	}

	@GetMapping("/userEditComplete")
	public String userEditComplete() {
		return "login";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@ModelAttribute("user") UserModel passwordConfirm, BindingResult bindingResult,
	        Model model, HttpServletRequest request) {

	    HttpSession session = request.getSession();
	    UserModel user = (UserModel) session.getAttribute("user");
	    model.addAttribute("user", user);

//	    // パスワードの確認
//	    if (!passwordConfirm.getPassword().equals(user.getPassword())) {
//	        bindingResult.rejectValue("confirmPassword", "error.user", "パスワードが一致しません");
//	        return "userDelete";
//	    }
//
//	    // エラーチェック
//	    if (bindingResult.hasErrors()) {
//	        return "userDelete";
//	    }

	    

	    return "userDelete";
	}
	
	@PostMapping("/userDeleteComplete")
	public String userDeleteComplete(@ModelAttribute("user") UserModel user, BindingResult bindingResult, Model model) {
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword", "error.user", "パスワードが一致しません");
			return "userDelete";
		}
		
		// エラーチェック
		if (bindingResult.hasErrors()) {
			return "userDelete";
		}
		
		// ユーザー削除処理
	    userDAO.deleteUser(user);
	    // ここに削除処理のコードを追加します
	    
		model.addAttribute("user", user);
		
		return "userDeleteComplete";
	}

}
