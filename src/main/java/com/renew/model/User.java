package com.renew.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String uname;
    private String password_hash;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password_hash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password_hash) {
        this.password_hash = password_hash;
    }
}
