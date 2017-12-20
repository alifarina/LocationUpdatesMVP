package location.track.com.trackme.DatabaseModule;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Farina Ali on 20-12-2017.
 */

@Database(entities = {Tracks.class}, version = 2,exportSchema = false)
public abstract class TracksDatabase extends RoomDatabase {

     public abstract TrackInfoDao daoAccess();
}
