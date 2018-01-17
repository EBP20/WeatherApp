package ir.eatandroid.weather.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.oxima.weatherapp.R;


/**
 * Created by elham on 12/14/2017.
 */

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {


    String humidity;
    String pressure;
    String speed;

    public static MyBottomSheetDialogFragment newInstance(String hum,String pre,String speed) {
        MyBottomSheetDialogFragment f = new MyBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString("stringHum", hum);
        args.putString("stringPre", pre);
        args.putString("stringSpeed", speed);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        humidity = getArguments().getString("stringHum");
        pressure = getArguments().getString("stringPre");
        speed = getArguments().getString("stringSpeed");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_modal, container, false);
        TextView txt_humidity=v.findViewById(R.id.txt_humidity);
        TextView txt_pressure=v.findViewById(R.id.txt_pressure);
        TextView txt_speed=v.findViewById(R.id.txt_speed);
        txt_humidity.setText(humidity);
        txt_pressure.setText(pressure);
        txt_speed.setText(speed);
        return v;
    }

}
