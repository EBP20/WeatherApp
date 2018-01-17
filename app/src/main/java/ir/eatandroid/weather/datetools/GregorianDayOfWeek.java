package ir.eatandroid.weather.datetools;

/**
 * Created by elham on 12/13/2017.
 */

public enum GregorianDayOfWeek {
    Sun,
    Mon,
    Tue,
    Wed,
    Thu,
    Fri,
    Sat;

    public int getValue()
    {
        return this.ordinal();
    }

    public static GregorianDayOfWeek forValue(int value)
    {
        return values()[value];
    }
}
