package ir.eatandroid.weather.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import ir.eatandroid.weather.api.Urls;
import ir.eatandroid.weather.api.retro.ClientService;
import ir.eatandroid.weather.api.retro.ServiceGenerator;
import ir.eatandroid.weather.datetools.DateUtil;
import ir.eatandroid.weather.models.obj.current.CurrentResponse;
import ir.eatandroid.weather.views.fragments.TemperatureFragment;
import ir.oxima.weatherapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchActivity extends BaseActivity implements View.OnClickListener{

    private TemperatureFragment temperatureFragment;
    private static final String TAG = "LaunchActivity";
    private ProgressBar prg;
    private AppBarLayout appbar;
    private TextView txt_city;
    private TextView txt_main;
    private TextView txt_temp;
    private TextView txt_date;
    private ImageView img_icon;
    private ImageView img_more_option;
    private Call<CurrentResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();
        getWeather();
    }

    private void initViews() {
        prg = findViewById(R.id.prg);
        appbar = findViewById(R.id.appbar);
        txt_city = findViewById(R.id.txt_city);
        txt_main = findViewById(R.id.txt_main);
        txt_temp = findViewById(R.id.txt_temp);
        txt_date = findViewById(R.id.txt_date);
        txt_date = findViewById(R.id.txt_date);
        img_icon = findViewById(R.id.img_icon);
        img_more_option = findViewById(R.id.img_more_option);

        img_more_option.setOnClickListener(this);

    }

    private void showLoading() {
        prg.setVisibility(View.VISIBLE);
        appbar.setVisibility(View.GONE);
    }

    private void hideLoading() {
        prg.setVisibility(View.GONE);
        appbar.setVisibility(View.VISIBLE);
    }

    public void replaceFragment(Fragment fragment, String tag, int container) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment, tag);
        fragmentTransaction.commit();
    }

    private void getWeather() {
        showLoading();
        ClientService service = ServiceGenerator.createService(ClientService.class);

        call = service.getCurrentWeather(String.format(Urls.API_CURRENT_WEATHER,112931, "metric", getResources().getText(R.string.api_key)));
        call.enqueue(new Callback<CurrentResponse>() {
            @Override
            public void onResponse(Call<CurrentResponse> call, Response<CurrentResponse> response) {
                hideLoading();
                initParam(response.body());
                temperatureFragment = TemperatureFragment.instance();
                replaceFragment(temperatureFragment, "TemperatureFragment", R.id.container);

            }

            @Override
            public void onFailure(Call<CurrentResponse> call, Throwable t) {
                hideLoading();
                needShowAlertDialog(t.getMessage());
            }
        });

    }

    private void initParam(CurrentResponse body) {
        if (body == null) {
            return;
        }

        txt_city.setText(body.getName());
        txt_main.setText(body.getWeather() == null ? "" : body.getWeather().get(0).getMain());
        txt_temp.setText(body.getMain() == null ? "" : String.valueOf(body.getMain().getTemp()));


        txt_date.setText(DateUtil.getLongStringDateFromMilis(String.valueOf(body.getDate()),"dd-MM-yyyy 'at' hh:mm",false));
        Glide.with(this)
                .load(String.format(body.getWeather() == null ? "" : Urls.API_ICON, body.getWeather().get(0).getIcon()))
                .into(img_icon);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            this.finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_more_option:
                break;
        }
    }
}
