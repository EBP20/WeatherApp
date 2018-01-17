package ir.eatandroid.weather.api.retro;

import ir.eatandroid.weather.models.obj.current.CurrentResponse;
import ir.eatandroid.weather.models.obj.daily.DailyResponse;
import ir.eatandroid.weather.models.obj.hourly.HourlyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by elham on 12/13/2017.
 */

public interface ClientService {
    @GET
    Call<CurrentResponse> getCurrentWeather(@Url String url);

    @GET
    Call<DailyResponse> getDailyWeather(@Url String url);

    @GET
    Call<HourlyResponse> getHourlWeather(@Url String url);

}
