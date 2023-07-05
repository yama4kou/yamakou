package com.starsoft1.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.starsoft1.bms.dao.UserDAO;
import com.starsoft1.bms.model.UserModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private UserDAO userDAO;

    public LoginController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserModel user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // ログイン成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // セッションにユーザー情報を格納
            
            return "redirect:/dashboard"; // ダッシュボードページへリダイレクト
        } else {
            // ログイン失敗
            model.addAttribute("error", "メールアドレスまたはパスワードが正しくありません");
            return "login";
        }
    }
}
