
package com.example.dramaclubpointsapp;

public class User {

    public String username;
    public double points;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, double points) {
        this.username = username;
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    public String getUsername(){
        return username;
    }
}