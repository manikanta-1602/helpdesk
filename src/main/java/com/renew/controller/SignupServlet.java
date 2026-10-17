package com.renew.controller;

import com.renew.service.AuthService;
import com.renew.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private final AuthService as = new AuthService();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String uname = req.getParameter("uname");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUname(uname);
        user.setPassword(password);

        boolean success = as.signUp(user);

        if (success) {
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute(
                    "error",
                    "Username or email already exists."
            );

            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, res);
        }
    }
}
