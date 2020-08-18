package libs;

public enum ECycleRules {

// CYCLE ID 0
    USA_70hr_8days(8, 70, 14, 11, 34, 999, 10, 8),
    USA_70hr_8days_Cargo2(8, 70, 15, 10, 34, 999, 10, 999),
    USA_70hr_8days_Cargo3(8, 70, 14, 11, 24, 999, 10, 8),
    USA_70hr_8days_Cargo4(8, 70, 14, 11, 34, 999, 10, 999),

// CYCLE ID 1
    USA_60hr_7days(7, 60, 14, 11, 34, 999, 10, 8),
    USA_60hr_7days_Cargo2(7, 60, 15, 10, 34, 999, 10, 999),
    USA_60hr_7days_Cargo3(7, 60, 14, 11, 24, 999, 10, 8),
    USA_60hr_7days_Cargo4(7, 60, 14, 11, 34, 999, 10, 999),

// CYCLE ID 2
    Alaska_70hr_7days(7, 70, 20, 15, 34, 999, 10, 8),
    Alaska_70hr_7days_Cargo3(7, 70, 20, 15, 24, 999, 10, 8),
    Alaska_70hr_7days_Cargo4(7, 70, 20, 15, 24, 999, 10, 999),

//    CYCLE ID 3
    Alaska_80hr_8days(8, 80, 20, 15, 34, 999, 10, 8),
    Alaska_80hr_8days_Cargo3(8, 80, 20, 15, 24, 999, 10, 8),
    Alaska_80hr_8days_Cargo4(8, 80, 20, 15, 34, 999, 10, 999),

//    CYCLE ID 4
    Canada_70hr_7days(7, 70, 16, 13, 36, 14, 8, 999),

//    CYCLE ID 5
    Canada_120hr_14days(14, 120, 16, 13, 72, 14, 8, 999),

//    CYCLE ID 6
    Texas70hr_7days(7, 70, 15, 12, 34, 999, 8, 8),
    Texas70hr_7days_Cargo3(7, 70, 15, 12, 24, 999, 8, 8),
    Texas70hr_7days_Cargo4(7, 70, 15, 12, 34, 999, 8, 999),

//    CYCLE ID 7
    California_80hr_7days(8, 80, 16, 14, 34, 999, 8, 8),
    California_80hr_7days_Cargo2(8, 80, 15, 12, 34, 999, 8, 8),
    California_80hr_7days_Cargo3(8, 80, 16, 14, 24, 999, 8, 8),
    California_80hr_7days_Cargo4(8, 80, 16, 14, 34, 999, 8, 999),

//    CYCLE ID 8
    CanadaNorth_60_80_7(7, 80, 20, 15, 36, 18, 8, 999);


    private int days;
    private int cycleHours;
    private int shiftHours;
    private int driveHours;
    private int restartHours;
    private int shiftWorkHours;
    private int shiftRestartHours;
    private int breakHours;




    ECycleRules(int days, int cycleHours, int shiftHours, int driveHours, int restartHours, int shiftWorkHours, int shiftRestartHours, int breakHours) {
        this.days = days;
        this.cycleHours = cycleHours;
        this.shiftHours = shiftHours;
        this.driveHours = driveHours;
        this.restartHours = restartHours;
        this.shiftWorkHours = shiftWorkHours;
        this.shiftRestartHours = shiftRestartHours;
        this.breakHours = breakHours;
    }

    public int getDays() {
        return days;
    }

    public int getCycleHours() {
        return cycleHours;
    }

    public int getShiftHours() {
        return shiftHours;
    }

    public int getDriveHours() {
        return driveHours;
    }

    public int getRestartHours() {
        return restartHours;
    }

    public int getShiftWorkHours() {
        return shiftWorkHours;
    }

    public int getShiftRestartHours() {
        return shiftRestartHours;
    }

    public int getBreakHours() {
        return breakHours;
    }
}
