package com.renew.service;

import com.renew.model.Reminder;
import com.renew.dao.ReminderDAO;

import java.util.List;

public class ReminderService {
    ReminderDAO dao = new ReminderDAO();
    public boolean addReminder(Reminder reminder) {
        return dao.createReminder(reminder);
    }

    public boolean editReminder(Reminder reminder) {
        return dao.updateReminder(reminder);
    }

    public boolean deleteReminder(int id, int userId) {
        return dao.deleteReminder(id, userId);
    }

    public Reminder getReminderById(int id) {
        return dao.getReminderById(id);
    }

    public List<Reminder> getRemindersByUserId(int userId) {
        return dao.getRemindersByUserId(userId);
    }
}
