package com.tanda.hackathon.acss.axis.Models;

/**
 * Created by kean on 7/29/17.
 */

public class User {
    private String username;
    private String password;

    public User () {
        username = "testusername";
        password = "testpassword";
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
