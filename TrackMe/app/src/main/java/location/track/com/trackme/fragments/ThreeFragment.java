package location.track.com.trackme.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import location.track.com.trackme.DatabaseModule.Tracks;
import location.track.com.trackme.DatabaseModule.TracksDatabase;
import location.track.com.trackme.R;
import location.track.com.trackme.adapters.TracksSessionAdapter;

/**
 * Created by Farina Ali on 19-12-2017.
 */

public class ThreeFragment extends Fragment {

    private RecyclerView recycler_view;
    private TracksDatabase database;
    private LiveData<List<Tracks>> tracksArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_three_view, null);

        recycler_view = (RecyclerView) v.findViewById(R.id.recycle_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.setHasFixedSize(true);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        database = Room.databaseBuilder(getActivity(), TracksDatabase.class, "Tracks").build();
        setObservable();
//        setTracksData(database.daoAccess().loadAllTracks().getValue());

        return v;
    }

    private void setObservable() {
        LiveData<List<Tracks>> tracksLiveData = database.daoAccess().loadAllTracks();
        tracksLiveData.observe(this, new Observer<List<Tracks>>() {
            @Override
            public void onChanged(@Nullable List<Tracks> tracksList) {
                //Update your UI here.
                if (tracksList != null)
                    setTracksData(tracksList);
            }
        });
    }

    TracksSessionAdapter adapter;

    private void setTracksData(List<Tracks> tracksList) {

        if (adapter == null) {
            adapter = new TracksSessionAdapter(getActivity(), tracksList);
            recycler_view.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
