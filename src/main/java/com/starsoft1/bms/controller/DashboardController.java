package com.starsoft1.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.starsoft1.bms.model.UserModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpServletRequest request) {
	    // セッションを取得
	    HttpSession session = request.getSession();
	    UserModel user = (UserModel) session.getAttribute("user");

	    if (user == null) {
	        // ログインしていない場合の処理を記述（例: ログインページにリダイレクト）
	        return "redirect:/login";
	    }

	    // ログイン済みの場合、ユーザー情報をモデルに追加
	    model.addAttribute("user", user);
	    
	    return "dashboard";
	}

	@PostMapping("/dashboard")
	public String showDashboard() {
		return "dashboard";
	}

	// ログアウト機能
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// セッションを取得
		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションを無効化して削除
			session.invalidate();
		}
		// ログアウト後にリダイレクトするURLを指定する
		return "redirect:/login";
	}

}
