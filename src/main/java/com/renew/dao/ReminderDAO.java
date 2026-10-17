package com.renew.dao;
import com.renew.model.Reminder;
import com.renew.utils.DBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ReminderDAO {
    public boolean createReminder(Reminder reminder) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO Reminders (reminderId, userId, name, notes, reminderDate) VALUES (reminders_seq.NEXTVAL, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, reminder.getUserId());
            ps.setString(2, reminder.getName());
            ps.setString(3, reminder.getNotes());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(reminder.getDateTime()));

            if (ps.executeUpdate() > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public Reminder getReminderById(int id) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reminder reminder = null;
        try {
            String query = "SELECT userId, name, notes, reminderDate FROM Reminders WHERE reminderId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                reminder = new Reminder();
                reminder.setId(id);
                reminder.setUserId(rs.getInt("userId"));
                reminder.setName(rs.getString("name"));
                reminder.setNotes(rs.getString("notes"));
                reminder.setDateTime(rs.getTimestamp("reminderDate").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return reminder;
    }

    public List<Reminder> getRemindersByUserId(int userId) {
        List<Reminder> list = new ArrayList<>();
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT reminderId, name, notes, reminderDate FROM Reminders WHERE userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setId(rs.getInt("reminderId"));
                reminder.setUserId(userId);
                reminder.setName(rs.getString("name"));
                reminder.setNotes(rs.getString("notes"));
                reminder.setDateTime(rs.getTimestamp("reminderDate").toLocalDateTime());
                list.add(reminder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

    public boolean updateReminder(Reminder reminder) {
        PreparedStatement ps = null;
        boolean hasField = false;

        StringBuilder query = new StringBuilder("UPDATE Reminders SET ");

        if (reminder.getName() != null) {
            query.append("name = ?");
            hasField = true;
        }

        if (reminder.getNotes() != null) {
            if (hasField) query.append((", "));
            query.append("notes = ?");
            hasField = true;
        }

        if (reminder.getDateTime() != null) {
            if (hasField) query.append((", "));
            query.append("reminderDate = ?");
            hasField = true;
        }

        if (!hasField) return false;

        query.append(" WHERE reminderId = ? AND userId = ?");

        Connection con = DBCon.getDBConnection();

        try {
            ps = con.prepareStatement(query.toString());
            int index = 1;

            if (reminder.getName() != null) {
                ps.setString(index++, reminder.getName());
            }

            if (reminder.getNotes() != null) {
                ps.setString(index++, reminder.getNotes());
            }

            if (reminder.getDateTime() != null) {
                ps.setTimestamp(index++, java.sql.Timestamp.valueOf(reminder.getDateTime()));
            }

            ps.setInt(index++, reminder.getId());

            ps.setInt(index, reminder.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean deleteReminder(int id, int userId) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM Reminders WHERE reminderId = ? AND userId = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
