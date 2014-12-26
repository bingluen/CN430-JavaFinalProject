package net.ericksonjuang;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Erickson on 2014/12/25.
 */
public class fileIO {
    private static final String FILE_DATA = "yyyyMMddHHmmss";
    private static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    private static final String[] playerString = {"\u9ed1\u68cb", "\u767d\u68cb", "\u5e73\u624b"};
    public static void outputResult(Board board, int winner, String name)
    {
        Calendar timer = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FILE_DATA);
        String outputFileName = dateFormat.format(timer.getTime()) + ".txt";
        File outputFile = new File(outputFileName);
        try{
            if(!outputFile.exists())
                outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            SimpleDateFormat timeFormat = new SimpleDateFormat(DATE_TIME);
            String recordTime = timeFormat.format(timer.getTime());
            FileWriter fw = new FileWriter(outputFileName);
            fw.write("Winner: "+ playerString[winner] + " - " + name + "\r\n");
            fw.write("Record Time: " + recordTime + "\r\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
