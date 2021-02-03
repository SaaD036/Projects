package com.example.new_res;

public class Restaurants {
    private float rating, user, total_rating;
    private String name, owner, address;
    private int item, id;

    Restaurants(){}



    //Getter method
    public float getRating() { return rating; }
    public float getUser() { return user; }
    public String getName() { return name; }
    public String getOwner() { return owner; }
    public int getItem() { return item; }
    public int getId() { return id; }

    public String getAddress() {
        return address;
    }

    //Setter method
    public void setUser(float user) { this.user = user; }
    public void setTotal_rating(float total_rating) { this.total_rating = total_rating; }
    public void setName(String name) { this.name = name; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setItem(int item) { this.item = item; }
    public void setId(int id) { this.id = id; }
    public void setAddress(String address) { this.address = address; }
}
