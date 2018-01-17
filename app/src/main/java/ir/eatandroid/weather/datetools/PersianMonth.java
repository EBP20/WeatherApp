package ir.eatandroid.weather.datetools;

/**
 * Created by elham on 12/13/2017.
 */

public enum PersianMonth {
    Farvardin,
    Ordibehesht,
    Khordad,
    Tir,
    Mordad,
    Shahrivar,
    Mehr,
    Aban,
    Azar,
    Dey,
    Bahman,
    Esfand;

    public int getValue()
    {
        return this.ordinal();
    }

    public static PersianMonth forValue(int value)
    {
        return values()[value];
    }
}
