package com.example.new_res;

public class RequestedMember {
    private String email, password, username, type;

    RequestedMember(){}


    //setter methods
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) { this.username = username; }

    public void setType(String type) { this.type = type; }


    //Getter methods
    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public String getType() { return type; }

}
