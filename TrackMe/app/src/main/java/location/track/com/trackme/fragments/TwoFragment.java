package location.track.com.trackme.fragments;

import android.arch.persistence.room.Room;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import location.track.com.trackme.DatabaseModule.Tracks;
import location.track.com.trackme.DatabaseModule.TracksDatabase;
import location.track.com.trackme.R;
import location.track.com.trackme.StepCountModule.StepDetector;
import location.track.com.trackme.StepCountModule.StepListener;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Farina Ali on 19-12-2017.
 */

public class TwoFragment extends Fragment implements SensorEventListener, StepListener {

    private SensorManager sensorManager;
    private StepDetector simpleStepDetector;
    private Sensor accel;
    private TextView tv_display;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private TracksDatabase database;

    private String startTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_two_view, null);
        tv_display = (TextView) v.findViewById(R.id.tv_display);

        // Get an instance of the SensorManager
        getSensorManager();
        startTime = System.currentTimeMillis() + "";
        database = Room.databaseBuilder(getActivity(), TracksDatabase.class, "Tracks").build();

        new TracksDataManager().execute();

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void getSensorManager() {
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        tv_display.setText(TEXT_NUM_STEPS + numSteps);

        if (numSteps == 50) {
            //insert record in DB
            new TracksDataManager().execute();
            startTime = System.currentTimeMillis() + "";
            numSteps=0;
        }
    }


    class TracksDataManager extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Tracks tracks = new Tracks();
            tracks.setTrack_name("track_" + System.currentTimeMillis());
            tracks.setStart_time(startTime);
            tracks.setEnd_time(System.currentTimeMillis() + "");
            database.daoAccess().insertTrack(tracks);
            return null;
        }
    }
}
