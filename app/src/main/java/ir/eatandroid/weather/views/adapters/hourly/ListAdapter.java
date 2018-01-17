package ir.eatandroid.weather.views.adapters.hourly;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.eatandroid.weather.api.Urls;
import ir.eatandroid.weather.models.obj.hourly.List;
import ir.eatandroid.weather.views.holders.hourly.ListRowHolder;
import ir.oxima.weatherapp.R;



public class ListAdapter extends RecyclerView.Adapter<ListRowHolder> {

    private ArrayList<List> feedList;
    private View view;
    private Context context;
    private float max_temp;
    private float min_temp;
    private ListenerHourlyAdapter listenerHourlyAdapter;

    public interface ListenerHourlyAdapter{
        void onClickHourlyItem(List list);
    }
    public ListAdapter setListener(ListenerHourlyAdapter listener){
        this.listenerHourlyAdapter=listener;
        return this;
    }

    public ListAdapter(Context context, ArrayList<List> list) {
        this.feedList = list;
        this.context = context;
        max_temp = calcuteMaxTemp(feedList);
        min_temp = calcuteMin(feedList) ;
    }

    private float calcuteMaxTemp(ArrayList<List> listArrayList) {
        if(listArrayList == null || listArrayList.size() == 0 ){
            return 0;
        }
        float max = 0;
        for(List list:listArrayList){
            if(list.getMain().getTemp()>max){
                max = list.getMain().getTemp();
            }
        }
        return max;
    }

    private float calcuteMin(ArrayList<List> listArrayList) {
        if(listArrayList == null || listArrayList.size() == 0 ){
            return 0;
        }
        float min = 0;
        for(List list:listArrayList){
            if(list.getMain().getTemp()<min){
                min = list.getMain().getTemp();
            }
        }
        return min;
    }

    @Override
    public ListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list, null);
        ListRowHolder mh = new ListRowHolder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(final ListRowHolder feedListRowHolder, final int position) {

        final List list = feedList.get(position);

//        feedListRowHolder.verticalProgressCell.setMaxProgress(max_temp);
//        feedListRowHolder.verticalProgressCell.setMinProgress(min_temp);
//        feedListRowHolder.verticalProgressCell.setProgress(list.getMain().getTemp());

        feedListRowHolder.pc_chart.setYmax(max_temp);
        feedListRowHolder.pc_chart.setY(list.getMain().getTemp());
        feedListRowHolder.pc_chart.setMinY(min_temp);

        String[] time = list.getDtTxt().split(" ")[1].split(":");
        String hour = time[0];
        String min = time[1];
        feedListRowHolder.txt_label.setText(hour + ":" + min);
        Glide.with(context)
                .load(String.format(list.getWeather() == null ? "" : Urls.API_ICON, list.getWeather().get(0).getIcon()))
                .into(feedListRowHolder.img_icon);
        feedListRowHolder.layout_sup_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listenerHourlyAdapter != null){
                    listenerHourlyAdapter.onClickHourlyItem(feedList.get(position));
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return (feedList == null ? 0 :  feedList.size());
    }
}
