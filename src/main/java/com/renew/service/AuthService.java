package com.renew.service;

import com.renew.model.User;
import com.renew.dao.UserDAO;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class AuthService {
    private String hashPassword(String password) {
        return BCrypt.withDefaults()
                .hashToString(12, password.toCharArray());
    }

    public boolean signUp(User user) {
        UserDAO dao = new UserDAO();

        if (dao.existsByEmail(user.getEmail())) return false;
        if (dao.existsByUname(user.getUname())) return false;

        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        return dao.createUser(user);
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.verifyer()
                .verify(password.toCharArray(), hashedPassword)
                .verified;
    }

    public User loginByEmail(String email, String password) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserByEmail(email);

        if (user == null) return null;

        if (verifyPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
    public User loginByUname(String uname, String password) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserByUname(uname);

        if (user == null) return null;

        if (verifyPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public boolean editUser(User user) {
        UserDAO dao = new UserDAO();
        User existingEmail = dao.getUserByEmail(user.getEmail());
        if (existingEmail != null && existingEmail.getId() != user.getId()) {
            return false;
        }

        User existingUname = dao.getUserByUname(user.getUname());
        if (existingUname != null && existingUname.getId() != user.getId()) {
            return false;
        }

        return dao.updateUser(user);
    }

    public boolean deleteUser(int id) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserByID(id);
        if (dao.existsByUname(user.getUname()) || dao.existsByEmail(user.getEmail())) {
            return dao.deleteUser(id);
        }
        return false;
    }
}
