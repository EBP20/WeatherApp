package ir.eatandroid.weather.views.adapters.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.eatandroid.weather.api.Urls;
import ir.eatandroid.weather.datetools.DateUtil;
import ir.eatandroid.weather.models.obj.daily.List;
import ir.eatandroid.weather.views.holders.daily.ListRowHolder;
import ir.oxima.weatherapp.R;


public class ListAdapter extends RecyclerView.Adapter<ListRowHolder> {

    private ArrayList<List> feedList;
    private View view;
    private Context context;
    private float max_temp;
    private float min_temp;
    private ListenerDailyAdapter listenerDailyAdapter;

    public ListAdapter(Context context, ArrayList<List> list) {
        this.feedList = list;
        this.context = context;
        max_temp = calcuteMaxTemp(feedList);
        min_temp = calcuteMin(feedList) ;
    }
    public interface ListenerDailyAdapter{
        void onClickDailyItem(List list);
    }
    public ListAdapter setListener(ListenerDailyAdapter listener){
        this.listenerDailyAdapter=listener;
        return this;
    }

    private float calcuteMaxTemp(ArrayList<List> listArrayList) {
        if(listArrayList == null || listArrayList.size() == 0 ){
            return 0;
        }
        float max = 0;
        for(List list:listArrayList){
            if(list.getTemp().getMax()>max){
                max = list.getTemp().getMax();
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
            if(list.getTemp().getMax()<min){
                min = list.getTemp().getMax();
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
//        feedListRowHolder.verticalProgressCell.setProgress(list.getTemp().getMax() );

        feedListRowHolder.pc_chart.setYmax(max_temp);
        feedListRowHolder.pc_chart.setY(list.getTemp().getMax());
        feedListRowHolder.pc_chart.setMinY(min_temp);

        String dateTime = DateUtil.getDateTime(String.valueOf(list.getDate()),"dd-MM-yyyy");
        feedListRowHolder.txt_label.setText(DateUtil.getStringDayOfWeek(dateTime,"dd-MM-yyyy",false));
        Glide.with(context)
                .load(String.format(list.getWeather() == null ? "" : Urls.API_ICON, list.getWeather().get(0).getIcon()))
                .into(feedListRowHolder.img_icon);
        feedListRowHolder.layout_sup_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listenerDailyAdapter != null){
                    listenerDailyAdapter.onClickDailyItem(feedList.get(position));
                }
            }
        });
//        switch (list.getWeather().get(0).getMain()){
//            case "Clear" :
//                feedListRowHolder.layout_sup_item.setBackgroundResource(R.mipmap.background_clear);
//            case "Rain" :
//                feedListRowHolder.layout_sup_item.setBackgroundResource(R.mipmap.background_rainy);
//        }


    }

    @Override
    public int getItemCount() {
        return (feedList == null ? 0 :  feedList.size());
    }
}
