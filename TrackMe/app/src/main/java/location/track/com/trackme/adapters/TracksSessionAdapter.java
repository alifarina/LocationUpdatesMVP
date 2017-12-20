package location.track.com.trackme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import location.track.com.trackme.AppUtils;
import location.track.com.trackme.DatabaseModule.Tracks;
import location.track.com.trackme.R;


/**
 * Created by Farina Ali on 23-11-2017.
 */

public class TracksSessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Tracks> myList;
    private Context mContext;

    public TracksSessionAdapter(Context context, List<Tracks> arrayList) {
        myList = arrayList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackViewHolder holder;
        View mainview = LayoutInflater.from(mContext).inflate(R.layout.item_tracks, null);
        holder = new TrackViewHolder(mainview);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TrackViewHolder holder1 = (TrackViewHolder) holder;

        holder1.track_name.setText(myList.get(position).getTrack_name());
        holder1.startTime.setText(AppUtils.getDate(Long.parseLong(myList.get(position).getStart_time()),
                "MM/dd/yyyy HH:mm:ss"));
        holder1.endTime.setText(AppUtils.getDate(Long.parseLong(myList.get(position).getEnd_time()),
                "MM/dd/yyyy HH:mm:ss"));

    }

    @Override
    public int getItemCount() {
        return myList.size() > 0 ? myList.size() : 0;
    }

    private class TrackViewHolder extends RecyclerView.ViewHolder {
        private final TextView endTime;
        private final TextView track_name;
        private TextView startTime;

        public TrackViewHolder(View itemView) {
            super(itemView);
            startTime = (TextView) itemView.findViewById(R.id.startTime);
            endTime = (TextView) itemView.findViewById(R.id.endTime);
            track_name = (TextView) itemView.findViewById(R.id.track_name);
        }
    }
}
