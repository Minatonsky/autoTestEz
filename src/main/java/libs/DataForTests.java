package libs;

public class DataForTests {
// type of devices
    public static String IOSXMonthTariffId = "0";
    public static String geometricsMonthTariffId = "9";
    public static String ezHardMonthTariffId = "12";
    public static String oneYearIOSXTariffId = "1";
    public static String twoYearsIOSXTariffId = "2";
    public static String oneYearGeometricsTariffId = "10";
    public static String twoYearsGeometricsTariffId = "11";
    public static String monthEzHardTariffId = "12";
    public static String oneYearEzHardTariffId = "13";
    public static String twoYearsEzHardTariffId = "14";

// strings type users
    public static String carrierIdString = "carrierId";
    public static String fleetString = "fleet";
    public static String userIdString = "userId";

//    type of violations
    public static String MissingSignature = "1";//Not signed logbook on working day || dvir not signed
    public static String DVIR = "2";//No dvir on date when driver was driving || some defects not fixed
    public static String FormManner = "3";//No info about truck/trailer/bol || No info about mileage || no logbook info on driving day
    public static String DrivingTime8 = "4";//start driving with 8 hours violation || driving with 8 hours violation
    public static String DrivingTime11 = "5";//start driving with 11 hours violation || driving with 11 hours violation
    public static String DrivingTime14 = "6";//start driving with 14 hours violation || driving with 14 hours violation
    public static String OverworkedCycle = "7";//start driving with Overworked violation || driving with Overworked cycle
    public static String DrivingTime60_7 = "8";
    public static String Canada10HoursOff = "9";//Canada 10 hours off duty a day violation
    public static String CanadaChangeCycleBreak = "10";//Canada cycle change violation
    public static String RevisedLogsResult = "11";//driver accepted/rejected logs

//    cycle type
    public static int USA_70hr_8days = 0; //numbers['shiftHours'] = 14; numbers['driveHours'] = 11;
    public static int USA_60hr_7days = 1; //numbers['shiftHours'] = 14; numbers['driveHours'] = 11;
    public static int Alaska_70hr_7days = 2; //numbers['shiftHours'] = 20; numbers['driveHours'] = 15;
    public static int Alaska_80hr_8days = 3; //numbers['shiftHours'] = 20; numbers['driveHours'] = 15;
    public static int Canada_70hr_7days = 4; //numbers['shiftHours'] = 16; numbers['driveHours'] = 13;
    public static int Canada_120hr_14days = 5; //numbers['shiftHours'] = 16; numbers['driveHours'] = 13;
    public static int Texas70hr_7days = 6; //numbers['shiftHours'] = 15; numbers['driveHours'] = 12;
    public static int California_80hr_7days = 7; //numbers['shiftHours'] = 16; numbers['driveHours'] = 14;
    public static int CanadaNorth_60_80_7 = 8; //numbers['shiftHours'] = ; numbers['driveHours'] = ;
    public static int Other = 9;

//    CARGO TYPE
    public static int Property = 0;
    public static int Agriculture = 1;
    public static int Passenger = 2;
    public static int OilGas = 3;
    public static int ShortHaul = 4;
}
