package com.parul.ratelimiter;

import java.util.concurrent.TimeUnit;

/* This class represents time durations at a given unit of
*  time.
*  unit represents the time unit
*  duration represents the duration of the time unit
* */
public class CustomTimeUnit {
    TimeUnit unit;
    long duration;
    long rateInMillis;

    CustomTimeUnit(TimeUnit unit, long duration) {
        this.unit = unit;
        this.duration = duration;
        init();
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void init() {
        TimeUnit milliseconds = TimeUnit.MILLISECONDS;
        rateInMillis = milliseconds.convert(duration, unit);
    }

    /* This method will return the duration in milli seconds based on the unit
    * If duration = 5 and unit is seconds then rateInMillis = 5000 milliseconds*/
    public long getRequestRateInMillis() {
        return rateInMillis;
    }
}
