package com.example.koung.healthy.sleep;

public class Sleep {
    private String date;
    private String duration;
    private String resultTime;

    public Sleep() {
    }

    public Sleep(String date, String duration, String resultTime) {
        this.date = date;
        this.duration = duration;
        this.resultTime = resultTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getResultTime() {
        return resultTime;
    }

    public void setResultTime(String resultTime) {
        this.resultTime = resultTime;
    }
}

