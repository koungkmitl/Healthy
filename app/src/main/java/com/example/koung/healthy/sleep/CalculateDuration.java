package com.example.koung.healthy.sleep;

class CalculateDuration {
    private String hour;
    private String minute;

    public CalculateDuration() {
    }

    public String calculate(String sleep,
                            String wake) {
        String[] _sleepArray = sleep.split(":");
        String[] _wakeArray = wake.split(":");

        String hour;
        String minute;

        int intSleep = (Integer.parseInt(_sleepArray[0]) * 3600) + (Integer.parseInt(_sleepArray[1]) * 60);
        int intWake = (Integer.parseInt(_wakeArray[0]) * 3600) + (Integer.parseInt(_wakeArray[1]) * 60);

        if ( intSleep > intWake ) {
            hour = String.valueOf(Math.round(86400 - (intSleep - intWake)) / 3600);
            minute = String.valueOf(Math.round((86400 - (intSleep - intWake)) % 3600) / 60);
            return hour + ":" + minute;
        }

        hour = String.valueOf(Math.round(86400 - (intWake - intSleep)) / 3600);
        minute = String.valueOf(Math.round((86400 - (intWake - intSleep)) % 3600) / 60);
        return hour + ":" + minute;
    }
}
