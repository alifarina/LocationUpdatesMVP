package location.track.com.trackme.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.Date;

import location.track.com.trackme.LocationView;
import location.track.com.trackme.R;
import location.track.com.trackme.mvp.LocationPresenterImpl;

import static android.content.ContentValues.TAG;

/**
 * Created by Farina Ali on 19-12-2017.
 */

public class OneFragment extends Fragment implements LocationView {


    private TextView tv_location;
    private Button btn_location;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private boolean mRequestingLocationUpdates;

    //Our fragments presenter
    private LocationPresenterImpl presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_one_view, null);

        initViews(v);

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                presenter.startGettingLocation();
            }
        });

        presenter = new LocationPresenterImpl(this);

//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
//        mSettingsClient = LocationServices.getSettingsClient(getActivity());

//        createLocationCallback();
//        createLocationRequest();
//        buildLocationSettingsRequest();
        presenter.initializeLocationServices();

        return v;
    }

    private void initViews(View v) {
        tv_location = (TextView) v.findViewById(R.id.tv_display);
        btn_location = (Button) v.findViewById(R.id.show_loc_btn);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            stopLocationUpdates();
        }
    }

    private void stopLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            Log.d(TAG, "stopLocationUpdates: updates never requested, no-op.");
            return;
        }

        presenter.stopLocationUpdate();
    }


    @Override
    public Activity getActivityContext() {
        return getActivity();
    }

    @Override
    public void setRequestUpdateStatus(boolean b) {
        mRequestingLocationUpdates = b;

    }

    @Override
    public void updateUI(Location mCurrentLocation, String mLastUpdateTime) {
        tv_location.setText("Longitude-- " + mCurrentLocation.getLongitude() + " Latitude-- " + mCurrentLocation.getLatitude());
    }
}
