package com.renew.controller;

import com.renew.service.ReminderService;
import com.renew.model.Reminder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/addReminder")
public class AddReminderServlet extends HttpServlet {
    private final ReminderService reminderService = new ReminderService();

    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/addReminder.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int userId = (int) session.getAttribute("userId");
        String name = req.getParameter("reminderName");
        String notes = req.getParameter("reminderNotes");
        String dateTime = req.getParameter("reminderDateTime");

        Reminder reminder = new Reminder();
        reminder.setUserId(userId);
        reminder.setName(name);
        reminder.setNotes(notes);
        reminder.setDateTime(LocalDateTime.parse(dateTime));

        if (reminderService.addReminder(reminder)) {
            res.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            req.setAttribute(
                    "error",
                    "There was an error."
            );

            req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);
        }

    }
}
