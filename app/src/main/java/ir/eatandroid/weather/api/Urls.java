package ir.eatandroid.weather.api;

/**
 * Created by elham on 12/13/2017.
 */

public class Urls {

    /**
     * JSON format is used by default. To get data in XML format just set up mode = xml.
     * for example : http://api.openweathermap.org/data/2.5/weather?q=London&mode=xml
     */

    /**
     * you can search by "City Id" for this :
     * id = city ID
     * for example : http://api.openweathermap.org/data/2.5/forecast?id=524901
     */

    /**
     * you can search by "Geographic Coordinates" for this :
     * lat = latitude
     * lon = longitude
     * for example : http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139
     */

    /**
     * You can use lang parameter to get the output in your language.
     * lang = Language Code , {farsi = fa , English = en}
     * for example : http://api.openweathermap.org/data/2.5/forecast/daily?id=524901&lang=zh_cn
     */

    /**
     * Standard, metric, and imperial units are available.
     * For temperature in Fahrenheit use units=imperial
     * For temperature in Celsius use units=metric
     * Temperature in Kelvin is used by default, no need to use units parameter in API call
     * for example : http://api.openweathermap.org/data/2.5/find?q=London&units=metric
     */

    public static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_DAILY_WEATHER = "forecast/daily?id=%1$s&units=%2$s&appid=%3$s";
    public static final String API_HOURLY_WEATHER = "forecast?id=%1$s&units=%2$s&appid=%3$s";
    public static final String API_CURRENT_WEATHER = "weather?id=%1$s&units=%2$s&appid=%3$s";
    public static final String API_ICON = "http://openweathermap.org/img/w/%1$s.png";
}

