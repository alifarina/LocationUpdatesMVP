package location.track.com.trackme;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

/**
 * Created by Farina Ali on 16-06-2017.
 */

public interface LocationView {

    Activity getActivityContext();

    void setRequestUpdateStatus(boolean b);

    void updateUI(Location mCurrentLocation, String mLastUpdateTime);
}
