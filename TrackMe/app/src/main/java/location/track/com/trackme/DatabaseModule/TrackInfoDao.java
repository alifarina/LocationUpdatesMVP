package location.track.com.trackme.DatabaseModule;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farina Ali on 20-12-2017.
 */

@Dao
public interface TrackInfoDao {

    @Insert
    void insertTrack(Tracks tracks);

    @Query("SELECT * FROM tracks")
    LiveData<List<Tracks>> loadAllTracks();
}
