package location.track.com.trackme.DatabaseModule;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Farina Ali on 20-12-2017.
 */

@Entity(tableName = "Tracks")
public class Tracks {

    @PrimaryKey(autoGenerate = true)
    int track_id;


    @ColumnInfo(name = "track_name")
    String track_name;

    @ColumnInfo(name = "start_time")
    String start_time;

    @ColumnInfo(name = "end_time")
    String end_time;

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

}
