package ir.eatandroid.weather.views.holders.daily;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ir.eatandroid.weather.components.PointChart;
import ir.eatandroid.weather.components.progresscell.VerticalProgressCell;
import ir.oxima.weatherapp.R;



/**
 * Created by elham on 12/13/2017.
 */


public class ListRowHolder extends RecyclerView.ViewHolder {

    public ViewGroup layout_sup_item;
    public TextView txt_label;
    public ImageView img_icon;
    public PointChart pc_chart;
    public VerticalProgressCell verticalProgressCell;


    public ListRowHolder(View view) {
        super(view);

        this.layout_sup_item =  view.findViewById(R.id.layout_sup_item);
        this.txt_label=  view.findViewById(R.id.txt_label);
        this.img_icon=  view.findViewById(R.id.img_icon);
        this.pc_chart=  view.findViewById(R.id.pc_chart);
//        this.verticalProgressCell=  view.findViewById(R.id.verticalProgressCell);
    }
}
