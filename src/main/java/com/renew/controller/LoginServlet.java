package com.renew.controller;

import com.renew.model.User;
import com.renew.service.AuthService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String loginId = req.getParameter("loginId");
        String password = req.getParameter("password");

        User user = authService.loginByEmail(loginId, password);
        if (user == null) {
            user = authService.loginByUname(loginId, password);
        }

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());

            res.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
