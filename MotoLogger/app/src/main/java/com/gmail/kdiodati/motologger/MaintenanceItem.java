package com.gmail.kdiodati.motologger;

//Written by Kyle Diodati July 2019

public class MaintenanceItem {

    private String name = null;
    private String description = null;

    private float lastFix = -1;
    private float interval = -1;
    private float nextFix = -1;

    //Getters and Setters//
    //name
    public void setName(String name_) { this.name = name_; }

    public String getName() { return this.name; }

    //description
    public void setDescription(String desc) { this.description = desc; }

    public String getDescription() { return this.description; }

    //lastFix
    public void setLastFix(float last) { this.lastFix = last; }

    public float getLastFix() { return this.lastFix; }

    //interval
    public void setInterval(float itvl) { this.interval = itvl; }

    public float getInterval() { return this.interval; }

    //nextFix
    public void setNextFix(float next) { this.nextFix = next; }

    public float getNextFix() { return this.nextFix; }

    public void calculateNextFix() { setNextFix( getLastFix() + getInterval() ); }
}
