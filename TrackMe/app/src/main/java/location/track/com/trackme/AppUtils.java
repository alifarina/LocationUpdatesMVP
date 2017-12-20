package location.track.com.trackme;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Farina Ali on 15-06-2017.
 */

public class AppUtils {


    public static String getDate(long milliseconds, String pattern) {
        String strDate = "";
        SimpleDateFormat dateFormatObj = new SimpleDateFormat(pattern);
        Date date = new Date(milliseconds);
        strDate = dateFormatObj.format(date);
        Log.e("converted for long ", "" + strDate);
        return strDate;
    }

}
