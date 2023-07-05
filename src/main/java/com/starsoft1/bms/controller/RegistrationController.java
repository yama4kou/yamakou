package com.starsoft1.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.starsoft1.bms.dao.UserDAO;
import com.starsoft1.bms.model.UserModel;

@Controller
public class RegistrationController {
	
    private final UserDAO userDAO;
    
    public RegistrationController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
	@GetMapping("/registrationForm")
	public String showRegistrationForm(Model model) {
		// モデルから「user」という属性を取得します
		UserModel user = (UserModel) model.getAttribute("user");

		// もし「user」属性がnullの場合は、新しいUserModelオブジェクトを作成します
		if (user == null) {
			user = new UserModel();
		}

		// モデルに「user」属性を追加します
		model.addAttribute("user", user);

		return "registrationForm"; // 登録フォームのビュー名を返します
	}


	@PostMapping("/registrationConfirmation")
	public String processRegistrationForm(@ModelAttribute("user") UserModel user, BindingResult bindingResult, Model model) {
		// パスワードとパスワード確認の一致を検証する
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword", "error.user", "パスワードが一致しません");
			return "registrationForm";
		}

		// エラーチェック
		if (bindingResult.hasErrors()) {
			return "registrationForm";
		}
		
		model.addAttribute("user", user);
		
		// ユーザー情報をデータベースに保存
		userDAO.saveUser(user);
		
		return "registrationConfirmation";
	}


	@GetMapping("/registrationConfirmation")
	public String showRegistrationConfirmation(Model model) {
		return "login";
	}
	
	@PostMapping("/registrationComplete")
    public String processRegistrationComplete(Model model) {
        return "registrationComplete"; // 登録完了画面のビュー名を返します
    }

}