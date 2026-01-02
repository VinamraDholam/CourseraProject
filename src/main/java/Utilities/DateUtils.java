package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getTimeStamp() {
        // Format: dd-MM-yyyy_HH-mm-ss
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        return sdf.format(new Date());
    }

}
