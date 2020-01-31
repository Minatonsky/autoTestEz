package libs;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public int generateRandomNumberBetweenTwoValues(int low, int high) throws SQLException, IOException, ClassNotFoundException {
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return result;
    }




}
