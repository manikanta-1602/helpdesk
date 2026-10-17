package com.renew.dao;
import com.renew.utils.DBCon;
import com.renew.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean createUser(User user) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        try {

            String query = "INSERT INTO Users (userId, name, email, uname, password_hash) VALUES (users_seq.NEXTVAL, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUname());
            ps.setString(4, user.getPassword());

            if (ps.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public User getUserByID(int id) {
        User user = null;
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT name, email, uname, password_hash FROM Users WHERE userId = (?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setUname(rs.getString("uname"));
                user.setPassword(rs.getString("password_hash"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }

    public User getUserByUname(String uname) {
        User user = null;
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT userId, name, email, password_hash FROM Users WHERE uname = (?)";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setUname(uname);
                user.setPassword(rs.getString("password_hash"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT userId, name, uname, password_hash FROM Users WHERE email = (?)";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(email);
                user.setUname(rs.getString("uname"));
                user.setPassword(rs.getString("password_hash"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }

    public boolean updateUser(User user) {
        PreparedStatement ps = null;
        StringBuilder query = new StringBuilder("UPDATE Users SET ");
        boolean hasField = false;

        if (user.getName() != null) {
            query.append("name = ?");
            hasField = true;
        }

        if (user.getEmail() != null) {
            if (hasField) query.append(", ");
            query.append("email = ?");
            hasField = true;
        }

        if (user.getUname() != null) {
            if (hasField) query.append(", ");
            query.append("uname = ?");
            hasField = true;
        }

        // Nothing to update
        if (!hasField) {
            return false;
        }

        Connection con = DBCon.getDBConnection();

        query.append(" WHERE userId = ?");

        try {
            ps = con.prepareStatement(query.toString());

            int index = 1;

            if (user.getName() != null) {
                ps.setString(index++, user.getName());
            }

            if (user.getEmail() != null) {
                ps.setString(index++, user.getEmail());
            }

            if (user.getUname() != null) {
                ps.setString(index++, user.getUname());
            }

            ps.setInt(index, user.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    public boolean deleteUser(int id) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        try {

            String query = "DELETE FROM Users WHERE userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean existsByEmail(String email) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT 1 FROM Users WHERE email = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
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
    }

    public boolean existsByUname(String uname) {
        Connection con = DBCon.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT 1 FROM Users WHERE uname = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            return rs.next();
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
    }
}
