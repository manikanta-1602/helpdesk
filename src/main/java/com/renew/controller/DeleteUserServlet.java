package com.renew.controller;

import com.renew.service.AuthService;
import com.renew.dao.UserDAO;
import com.renew.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private final AuthService authService = new AuthService();
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("userId");

        if (session == null || session.getAttribute("userId") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (authService.deleteUser(id)) {
            session.invalidate();
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            res.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }
}
