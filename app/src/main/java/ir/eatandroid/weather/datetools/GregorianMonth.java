package ir.eatandroid.weather.datetools;

/**
 * Created by elham on 12/13/2017.
 */
public enum GregorianMonth {
    Jan,
    Feb,
    Mar,
    Apr,
    May,
    Jun,
    Jul,
    Aug,
    Sep,
    Oct,
    Nov,
    Dec;

    public int getValue()
    {
        return this.ordinal();
    }

    public static GregorianMonth forValue(int value)
    {
        return values()[value];
    }
}
