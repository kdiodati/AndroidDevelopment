package com.gmail.kdiodati.tideappv1;

//Written by Kyle Diodati July 17 2019

public class TideItem {

    private String highLow = null; //if the tide is High or Low
    private String pred = null; //predicted height of tide (in feet)
    private String date = null; //date of the tide
    private String day = null; //which day that date falls on
    private String time = null; //what time the High/Low is at
    private String highLowTime = null; //combo string of high/Low + time of tide


    //Getters & Setters//

    public String getDateFormatted() {
        return date + ", " + day;
    }


    public void setHighLowTime() {
        highLowTime = highLow + ": " + time;
    }

    public String getHighLowTime() { return highLowTime; }


    public void setDate(String inDate) { this.date = inDate; }

    public String getDate() { return date; }


    //sets day from 3 letter to full word
    public void setDay(String inDay) {
        if (inDay.equalsIgnoreCase("sun")) { day = "Sunday"; }
        else if (inDay.equalsIgnoreCase("mon")) { day = "Monday"; }
        else if (inDay.equalsIgnoreCase("tue")) { day = "Tuesday"; }
        else if (inDay.equalsIgnoreCase("wed")) { day = "Wednesday"; }
        else if (inDay.equalsIgnoreCase("thu")) { day = "Thursday"; }
        else if (inDay.equalsIgnoreCase("fri")) { day = "Friday"; }
        else if (inDay.equalsIgnoreCase("sat")) { day = "Saturday"; }
        else { day = "Error"; } //if no match found error is placed
    }

    public String getDay() { return day; }


    //sets H/L to full High or Low
    public void setHighLow(String hl) {
        if (hl.equals("H")) { highLow = "High"; }
        else if (hl.equals("L")) { highLow = "Low"; }
        else { highLow = "Error"; } //if no match place error
    }

    public String getHighLow() { return highLow; }


    public void setPred(String prediction) { this.pred = prediction; }

    public String getPred() { return pred; }


    public void setTime(String inTime) { this.time = inTime; }

    public String getTime() { return time; }
}