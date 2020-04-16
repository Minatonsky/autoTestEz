package libs;

import com.mifmif.common.regex.Generex;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Utils {
    private Logger log;

    public Utils(){
        log = Logger.getLogger(getClass());
    }
    /**
     * Taking screenshot into .//target// + pathToScreenShot
     * @param pathToScreenShot
     * @param driver
     */
    public void screenShot(String pathToScreenShot, WebDriver driver){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(pathToScreenShot));
            log.info("ScreenShot: " + pathToScreenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Hard wait
     * @param second
     */
    public static void waitABit(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     * @return
     */
    public static String getDateAndTimeFormated(){

        return getDateAndTime("MM/dd/yyyy HH:mm:ss");
    }
    public static String getDateFormat(){

        return getDateAndTime("yyyy-MM-dd");
    }
    /**
     * Method returned SystemDateAndTime
     * @return
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String dateFormated = dateFormat.format(date);
        System.out.println(dateFormated);
        return dateFormated;
    }
    /**
     * Method get List<ArrayList> and returned Map by key=value
     * @param tempAmountList
     * @return
     */
    public static Map<String, Object> listArrayToMap(List<ArrayList> tempAmountList) {
        List<String> placeHolderList = tempAmountList.get(0);
        List<Object> valueList = tempAmountList.get(1);

        Map<String, Object> result = IntStream.range(0, placeHolderList.size())
                .boxed()
                .collect(toMap(placeHolderList::get, valueList::get));

        return result;

    }
    public static String getDateRandom() {
        int hundredYears = 100 * 365;
        return LocalDate.ofEpochDay(ThreadLocalRandom
                .current().nextInt(-hundredYears, hundredYears)).toString();
    }
    public static int genRandomNumberBetweenTwoValues(int low, int high) throws SQLException, IOException, ClassNotFoundException {
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return result;
    }
    public static String genRandomState(){
        String[] arr={"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV",
                "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY", "AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU",
                "ON", "PE", "QC", "SK", "YT"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }

    public static String genRandomStateName(){
        String[] arr={"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
                "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
                "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
                "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }
    public static String genRandomDataByRegex(String regEx) throws SQLException, IOException, ClassNotFoundException {
        Generex generex = new Generex(regEx);
        String randomStr = generex.random();
        return randomStr;

// generate the second String in lexicographical order that matches the given Regex.
//        String secondString = generex.getMatchedString(2);
//        System.out.println(secondString);

// Generate all String that matches the given Regex.
//        List<String> matchedStrs = generex.getAllMatchedStrings();

// Using Generex iterator
//        Iterator iterator = generex.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
    }
    public static void createAndWriteStringToFile(File file, String data) throws IOException {
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.flush();
        writer.close();
    }
    public static String reedFile(String bearerToken) throws IOException {
        String content;
        content = new String(Files.readAllBytes(Paths.get(bearerToken)));
        return content;
    }
    public static String genRandomEquipmentType(){
        String[] arr={"Property", "Agriculture", "Passenger", "Oil and Gas", "Short-Haul"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }

    public static String genRandomFuelType(){
        String[] arr={"Gasoline", "Diesel", "Gasohol", "Propane", "LNG", "CNG", "Ethanol", "Methanol", "E-85", "M-85", "A55", "Biodiesel"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }

    public static String genRandomCreditCard(){
        String[] arr={"4007000000027", "4111111111111111", "5424000000000015", "4012888818888", "370000000000002", "2223000010309711", "6011000000000012"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }
    public static long getUnitDateTimeFromString(String dateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dateTime);
        long timestamp = date.getTime();
        long droppedMillis = timestamp/1000;
        return droppedMillis;
    }
    public static LocalDateTime getLocalDateTimeFromString(String dateTimeString){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, format);
        return dateTime;
    }

    public static String getStringDateTimeUTC(String format){
        LocalDateTime datetime = LocalDateTime.now(ZoneOffset.UTC);
        String formatted = DateTimeFormatter.ofPattern(format).format(datetime);
        return formatted;
    }
    public static String getCurrentDateTimePlusDays(String format, int days){
        LocalDateTime datetime = LocalDateTime.now(ZoneOffset.UTC).plusDays(days);
        String formatted = DateTimeFormatter.ofPattern(format).format(datetime);
        return formatted;
    }
    public static LocalDateTime getLocalDateTimeUTC(){
        LocalDateTime tempDateTimeUTC = LocalDateTime.now(ZoneOffset.UTC);
        return tempDateTimeUTC;
    }
    public static String startDayPlusHours(int hours){
        LocalDate day = LocalDate.now(ZoneOffset.UTC);
        LocalDateTime startOfDay = day.atStartOfDay().plusHours(hours);
        String formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(startOfDay);
        return formatted;
    }

    public static boolean compareDiffDateTime(LocalDateTime firstDateTime, LocalDateTime secondTime, int countMinutes){
        long diffMinutes = ChronoUnit.MINUTES.between(firstDateTime, secondTime);
        return diffMinutes < countMinutes && diffMinutes > (-countMinutes);
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


}
