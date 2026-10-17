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

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    private final AuthService authService = new AuthService();
    private final UserDAO dao = new UserDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session == null || session.getAttribute("userId") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = dao.getUserByID((int) session.getAttribute("userId"));

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(req, res);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session == null || session.getAttribute("userId") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int id = Integer.parseInt(req.getParameter("userId"));
        User user = new User();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String uname = req.getParameter("uname");
        String password = req.getParameter("password");
    }
}
