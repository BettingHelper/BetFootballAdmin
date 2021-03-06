package sample;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class LogWriter {
    public LogWriter(){

    }

    public static void writeLog(String text) {
        File file = new File("logs/log.txt");
        FileWriter fr = null;
        BufferedWriter br = null;

        Calendar c = Calendar.getInstance();

        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(c.get(Calendar.MONTH)+1);
        String year = String.valueOf(c.get(Calendar.YEAR));
        String hour = String.valueOf(c.get(11));
        String minute = String.valueOf(c.get(12));
        String second = String.valueOf(c.get(13));

        String resultS = "";
        if (day.length()==1){
            day = "0" + day;
        }
        if (month.length()==1){
            month = "0" + month;
        }
        if (hour.length()==1){
            hour = "0" + hour;
        }
        if (minute.length()==1){
            minute = "0" + minute;
        }
        if (second.length()==1){
            second = "0" + second;
        }
        resultS += "[" + day + "." + month + "." + year + "   " + hour + ":" + minute + ":" + second + "]   " + text;

        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            br.newLine();
            //теперь мы можем использовать метод write или метод append
            br.write(resultS);

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeUpdates(String text) {
        String fileName = Settings.getPathToDatabase() + "updates/_listOfUpdates.txt";
        String lastUpdate = "";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (!str.equals("\n"))
                    lastUpdate = in.readLine();
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        FileWriter fr = null;
        BufferedWriter br = null;

        File file;
        String pathOnServer = "/data/football/database/updates/";
        file = new File(Settings.getPathToDatabase() + "updates/" + lastUpdate);
        pathOnServer += lastUpdate;
        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            //теперь мы можем использовать метод write или метод append
            br.write("\n");
            br.write(text);
            br.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                br.close();
                fr.close();
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), pathOnServer, file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void refreshUpdatesList(String text) {
        File file = new File(Settings.getPathToDatabase() + "updates/_listOfUpdates.txt" );
        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            //теперь мы можем использовать метод write или метод append
            br.write(text);
            br.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                br.close();
                fr.close();
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/updates/_listOfUpdates.txt", file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
