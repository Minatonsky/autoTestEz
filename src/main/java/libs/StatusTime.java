package libs;

import static libs.EStatusTime.*;

public class StatusTime {
    String status1From;
    String status1To;
    String status1Type;
    String status2From;
    String status2To;
    String status2Type;
    int drivingTime;
    int shiftTime;

    public static EStatusTime getStatusTime(int typeTime){
        switch (typeTime){
            case 8: return day8HoursDriving;
            case 10: return day10HoursDriving;
            default: return day4HoursDriving;
        }
    }
}
