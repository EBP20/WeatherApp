package ir.eatandroid.weather.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import ir.eatandroid.weather.api.Urls;
import ir.eatandroid.weather.api.retro.ClientService;
import ir.eatandroid.weather.api.retro.ServiceGenerator;
import ir.eatandroid.weather.dialog.MyBottomSheetDialogFragment;
import ir.eatandroid.weather.models.obj.daily.DailyResponse;
import ir.eatandroid.weather.models.obj.daily.List;
import ir.eatandroid.weather.models.obj.hourly.HourlyResponse;
import ir.eatandroid.weather.views.adapters.hourly.ListAdapter;
import ir.oxima.weatherapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by elham on 12/13/2017.
 */

public class TemperatureFragment extends Fragment implements View.OnClickListener, ir.eatandroid.weather.views.adapters.daily.ListAdapter.ListenerDailyAdapter,ListAdapter.ListenerHourlyAdapter {

    public ViewGroup view;
    private RecyclerView rcl_hourly;
    private RecyclerView rcl_daily;
    private ClientService service;

    public static TemperatureFragment instance() {
        TemperatureFragment fragment = new TemperatureFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_temperature, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews();
        service = ServiceGenerator.createService(ClientService.class);
        getListHourly();
        getListDaily();
        return view;
    }

    private void getListHourly() {
        Call<HourlyResponse> call = service.getHourlWeather(String.format(Urls.API_HOURLY_WEATHER,112931, "metric", getResources().getText(R.string.api_key)));
        call.enqueue(new Callback<HourlyResponse>() {
            @Override
            public void onResponse(Call<HourlyResponse> call, Response<HourlyResponse> response) {
                if(response == null || response.body() == null || response.body().getList() == null){
                    return;
                }
                showHourlyWeather(response.body());
            }

            @Override
            public void onFailure(Call<HourlyResponse> call, Throwable t) {
            }
        });
    }

    private void getListDaily() {
        Call<DailyResponse> call = service.getDailyWeather(String.format(Urls.API_DAILY_WEATHER,112931, "metric", getResources().getText(R.string.api_key)));
        call.enqueue(new Callback<DailyResponse>() {
            @Override
            public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                if(response == null || response.body() == null || response.body().getList() == null){
                    return;
                }
                showDailyWeather(response.body());
            }

            @Override
            public void onFailure(Call<DailyResponse> call, Throwable t) {
            }
        });
    }

    private void showHourlyWeather(HourlyResponse hourlyResponse) {
        ListAdapter listAdapter = new ListAdapter(getActivity(),hourlyResponse.getList());
        listAdapter.setListener(this);
        rcl_hourly.setAdapter(listAdapter);
    }

    private void showDailyWeather(DailyResponse dailyResponse) {
        ir.eatandroid.weather.views.adapters.daily.ListAdapter listAdapter = new ir.eatandroid.weather.views.adapters.daily.ListAdapter(getActivity(),dailyResponse.getList());
        listAdapter.setListener(this);
        rcl_daily.setAdapter(listAdapter);
    }

    private void initViews() {
        rcl_daily = view.findViewById(R.id.rcl_daily);
        rcl_hourly = view.findViewById(R.id.rcl_hourly);
        rcl_hourly.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcl_daily.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void onClickDailyItem(List list) {
        if(getActivity() == null){
            return;
        }
        getListDaily();
        final MyBottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance(String.valueOf(list.getHumidity()),String.valueOf(list.getPressure()),String.valueOf(list.getSpeed()));
        myBottomSheet.show(getFragmentManager(),myBottomSheet.getTag());

    }

    @Override
    public void onClickHourlyItem(ir.eatandroid.weather.models.obj.hourly.List list) {
        if (getActivity() == null) {
            return;
        }
        getListHourly();
        final MyBottomSheetDialogFragment bottomSheet = MyBottomSheetDialogFragment.newInstance(String.valueOf(list.getMain().getHumidity()), String.valueOf(list.getMain().getPressure()), String.valueOf(list.getMain().getSeaLevel()));
        bottomSheet.show(getFragmentManager(), bottomSheet.getTag());
    }
}
