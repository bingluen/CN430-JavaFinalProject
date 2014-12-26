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
            fw.write("Chess manual: \u9ed1\u68cb\uff0d\u25cf\t\u767d\u68cb\uff0d\u25cb\tEmpty\uff0d\u2573\r\n");
            for(int i = 0; i < 8; i++)
            {
                if(i == 0)
                    fw.write("\u2554\u2550\u2566\u2550\u2566\u2550\u2566\u2550\u2566\u2550\u2566\u2550\u2566\u2550\u2566\u2550\u2557\r\n");
                for(int j = 0; j < 8; j++)
                {
                    if(j == 0)
                        fw.write("\u2551");
                    if(board.getBoard()[i][j] == State.Black)
                        fw.write("\u25cf\u2551");
                    else if (board.getBoard()[i][j] == State.White)
                        fw.write("\u25cb\u2551");
                    else
                        fw.write("\u2573\u2551");
                }
                fw.write("\r\n");
                if(i == 7)
                    fw.write("\u255a\u2550\u2569\u2550\u2569\u2550\u2569\u2550\u2569\u2550\u2569\u2550\u2569\u2550\u2569\u2550\u255d");
                else
                    fw.write("\u2560\u2550\u256c\u2550\u256c\u2550\u256c\u2550\u256c\u2550\u256c\u2550\u256c\u2550\u256c\u2550\u2563\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
