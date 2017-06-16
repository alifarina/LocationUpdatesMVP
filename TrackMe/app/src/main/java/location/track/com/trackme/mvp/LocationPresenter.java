package location.track.com.trackme.mvp;

import android.content.Context;
import android.location.Location;

import location.track.com.trackme.LocationView;

/**
 * Created by infoobjects on 16-06-2017.
 */

public interface LocationPresenter {


    void sendLocation(Location mCurrentLocation, String mLastUpdateTime);

    void setRequestUpdateStatus(boolean b);

}
