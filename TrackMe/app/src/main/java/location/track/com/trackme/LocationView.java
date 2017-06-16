package location.track.com.trackme;

import android.content.Context;
import android.location.Location;

/**
 * Created by infoobjects on 16-06-2017.
 */

public interface LocationView {

    Context getActivityContext();

    void setRequestUpdateStatus(boolean b);

    void updateUI(Location mCurrentLocation, String mLastUpdateTime);
}
