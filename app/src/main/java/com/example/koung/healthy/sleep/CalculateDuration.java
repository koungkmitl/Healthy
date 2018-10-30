package com.example.koung.healthy.sleep;

class CalculateDuration {
    private String sleep;
    private String wake;

    public CalculateDuration(String sleep, String wake) {
        this.sleep = sleep;
        this.wake = wake;
    }

    public CalculateDuration() {
    }

    public String calculate(String sleep,
                            String wake) {
        String[] _sleepArray = sleep.split(":");
        String[] _wakeArray = wake.split(":");

        return "df";
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getWake() {
        return wake;
    }

    public void setWake(String wake) {
        this.wake = wake;
    }
}
