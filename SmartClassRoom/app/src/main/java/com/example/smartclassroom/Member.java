package com.example.smartclassroom;

public class Member {
    private String email, user, status, pass, room;

    Member() {}


    //Getter method
    public String getEmail() {
        return email;
    }
    public String getUser() {
        return user;
    }
    public String getStatus() { return status; }
    public String getPass() { return pass; }

    public String getRoom() {
        return room;
    }
    //Setter method

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setStatus(String status) { this.status = status; }
    public void setPass(String pass) { this.pass = pass; }

    public void setRoom(String room) {
        this.room = room;
    }
}
