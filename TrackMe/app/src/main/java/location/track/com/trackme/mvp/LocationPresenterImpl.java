package location.track.com.trackme.mvp;

import android.location.Location;

import location.track.com.trackme.LocationView;

/**
 * Created by infoobjects on 16-06-2017.
 */

public class LocationPresenterImpl implements LocationPresenter {
    LocationView mainView;
    LocationInteractor interactor;

    public LocationPresenterImpl(LocationView mView) {
        mainView = mView;
        interactor = new LocationInteractor(this,mainView.getActivityContext());
        initializeLocationServices();
    }

    public void initializeLocationServices() {
        interactor.createLocationCallback();
        interactor.createLocationRequest();
        interactor.buildLocationSettingsRequest();
    }

    public void startGettingLocation() {
        interactor.startLocationUpdates();
    }

    public void stopLocationUpdate() {
        interactor.stopLocationUpdates();
    }

    @Override
    public void sendLocation(Location mCurrentLocation, String mLastUpdateTime) {
        mainView.updateUI(mCurrentLocation, mLastUpdateTime);
    }

    @Override
    public void setRequestUpdateStatus(boolean b) {
        mainView.setRequestUpdateStatus(b);
    }
}
