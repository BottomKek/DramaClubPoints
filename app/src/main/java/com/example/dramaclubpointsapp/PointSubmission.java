package com.example.dramaclubpointsapp;

import android.graphics.Point;

public class PointSubmission {

    private String time;
    private String firstName, lastName;
    private String nameOfProduction;
    private double points;
    private String memes;

    public PointSubmission(String time, String firstName, String lastName, String prod, double points, String memes){
        this.time = time;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameOfProduction = prod;
        this.points = points;
        this.memes = memes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameOfProduction() {
        return nameOfProduction;
    }

    public void setNameOfProduction(String nameOfProduction) {
        this.nameOfProduction = nameOfProduction;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMemes() {
        return memes;
    }

    public void setMemes(String memes) {
        this.memes = memes;
    }
}
