package libs;

public enum EStatusTime {
    day4HoursDriving("08:00:00 AM", "10:00:00 AM", "Dr", "01:00:00 PM", "03:00:00 PM", "Dr", 14400, 25200),
    day8HoursDriving("06:00:00 AM", "10:00:00 AM", "Dr", "01:00:00 PM", "05:00:00 PM", "Dr", 28400, 39600),
    day10HoursDriving("06:00:00 AM", "11:00:00 AM", "Dr", "01:00:00 PM", "06:00:00 PM", "Dr", 360000, 43200);

    private String status1From;
    private String status1To;
    private String status1Type;
    private String status2From;
    private String status2To;
    private String status2Type;
    private int drivingTime;
    private int shiftTime;

    public String getStatus1From() {
        return status1From;
    }

    public String getStatus1To() {
        return status1To;
    }

    public String getStatus1Type() {
        return status1Type;
    }

    public String getStatus2From() {
        return status2From;
    }

    public String getStatus2To() {
        return status2To;
    }

    public String getStatus2Type() {
        return status2Type;
    }

    public int getDrivingTime() {
        return drivingTime;
    }

    public int getShiftTime() {
        return shiftTime;
    }

    EStatusTime(String status1From, String status1To, String status1Type, String status2From, String status2To, String status2Type, int drivingTime, int shiftTime){
        this.status1From = status1From;
        this.status1To = status1To;
        this.status1Type = status1Type;
        this.status2From = status2From;
        this.status2To = status2To;
        this.status2Type = status2Type;
        this.drivingTime = drivingTime;
        this.shiftTime = shiftTime;

    }
}
