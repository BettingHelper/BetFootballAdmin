package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

// определяем корневой элемент
@XmlRootElement(name = "Settings")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"showList"})

public class Settings {
    public ArrayList<Boolean> showList;

    public Settings(){
    }

    public Settings(ArrayList<Boolean> arrayList) {
        this.showList = arrayList;
    }

    public static Settings getSettingsFromFile(){
        String fileName = "settings/graphicSettings.xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Settings) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void pushSettingsToFile(){
        String fileName = "settings/graphicSettings.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Settings.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void optimizeUpdates(String fileName){
        ArrayList<String> list = new ArrayList<>();
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                list.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        int index = 0;
        while (index < list.size()){
            for (int i=index+1; i<list.size(); i++){
                if (list.get(i).equals(list.get(index))){
                    list.remove(i);
                    i--;
                }
            }
            index++;
        }

        File file = new File("tmp/newFile.txt");
        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            //теперь мы можем использовать метод write или метод append
            for (int i=0; i<list.size(); i++){
                br.write(list.get(i));
                br.newLine();
            }


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

        try {
            Files.move(Paths.get("tmp/newFile.txt"), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int ttt = 0;

    }

    public static String getPathToDatabase(){
        String result = "";
        try {
            File fileDir = new File("settings/pathToDatabase.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void setPathToDatabase(String path){
        File file = new File("settings/pathToDatabase.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(path);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getCurrentSeason(){
        String result = "";
        try {
            String path = getPathToDatabase();
            File fileDir = new File(path + "seasons.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            result = in.readLine();
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static ArrayList<String> getListOfSeasons(){
        ArrayList<String> result = new ArrayList<>();
        try {
            String path = getPathToDatabase();
            File fileDir = new File(path + "seasons.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getIp(){
        return "31.31.198.216";
    }

    public static String getLogin(){
        return "u0536356";
    }

    public static String getPassword(){
        return "Ds7!meBI";
    }

    public static String getSerialKey() throws Exception{
        String line;
        String serial = "";
        Process process = Runtime.getRuntime().exec("cmd /c wmic diskdrive get serialnumber");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(process.getInputStream()) );
        while ((line = in.readLine()) != null) {
            if ((!line.contains("erial"))&&(!line.equals("")))
                serial = serial + line;
        }
        in.close();
        return serial.trim();
    }

    public static ArrayList<ArrayList<String>> getUsersList(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        try {
            FTPLoader.downloadFile(getIp(), getLogin(), getPassword(), "/data/users.txt", "tmp/users.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            File fileDir = new File("tmp/users.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (!str.equals("")){
                    ArrayList<String> record = new ArrayList<>();
                    for (int i=0; i<str.split("=").length;i++)
                        record.add(str.split("=")[i]);
                    result.add(record);
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static void compress(String dirName){
        ZipOutputStream zos;
        Path sourceDir = Paths.get(dirName);

        try {
            String zipFileName = dirName.concat(".zip");
            zos = new ZipOutputStream(new FileOutputStream(zipFileName));

            Files.walkFileTree(sourceDir, new ZipDir(zos, sourceDir));

            zos.close();
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex);
        }
    }

    public static void setLastUpdate(){
        File file = new File("tmp/lastUpdateFBH.txt");

        try(FileWriter writer = new FileWriter(file, false)){
            String result = "";
            File fileDir = new File(Settings.getPathToDatabase() + "updates/_listOfUpdates.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = str;
            }
            in.close();
            writer.write(result);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getDateString(String s){
        String result = s.substring(0,3);
        switch (s.substring(4,7)){
            case "Jan":{
                result += "01."; break;
            }
            case "Feb":{
                result += "02."; break;
            }
            case "Mar":{
                result += "03."; break;
            }
            case "Apr":{
                result += "04."; break;
            }
            case "May":{
                result += "05."; break;
            }
            case "Jun":{
                result += "06."; break;
            }
            case "Jul":{
                result += "07."; break;
            }
            case "Aug":{
                result += "08."; break;
            }
            case "Sep":{
                result += "09."; break;
            }
            case "Okt":{
                result += "10."; break;
            }
            case "Nov":{
                result += "11."; break;
            }
            case "Dec":{
                result += "12."; break;
            }
        }
        result += s.substring(10,12);
        return result;
    }

    public static int getDayCode(String s){
        String resultS = s.split(" ")[2];
        switch (s.split(" ")[0]){
            case "Jan":{
                resultS += "01"; break;
            }
            case "Feb":{
                resultS += "02"; break;
            }
            case "Mar":{
                resultS += "03"; break;
            }
            case "Apr":{
                resultS += "04"; break;
            }
            case "May":{
                resultS += "05"; break;
            }
            case "Jun":{
                resultS += "06"; break;
            }
            case "Jul":{
                resultS += "07"; break;
            }
            case "Aug":{
                resultS += "08"; break;
            }
            case "Sep":{
                resultS += "09"; break;
            }
            case "Okt":{
                resultS += "10"; break;
            }
            case "Oct":{
                resultS += "10"; break;
            }
            case "Nov":{
                resultS += "11"; break;
            }
            case "Dec":{
                resultS += "12"; break;
            }
        }
        String day = s.split(" ")[1];
        if (day.length() < 2)
            day = "0" + day;

        resultS += day;
        return Integer.parseInt(resultS);
    }

    public static int getNextDayCode(int todayCode){
        int res = todayCode;
        int currentDay = Integer.parseInt(String.valueOf(todayCode).substring(6,8));
        int currentMonth = Integer.parseInt(String.valueOf(todayCode).substring(4,6));
        int currentYear = Integer.parseInt(String.valueOf(todayCode).substring(0,4));

        if (currentDay+1 <= getLastDayInMonth(currentMonth, currentYear))
            res++;
        else {
            currentDay = 1;
            if (currentMonth < 12)
                currentMonth++;
            else{
                currentMonth = 1;
                currentYear++;
            }
            res = currentYear*10000 + currentMonth*100 + currentDay;
        }
        return  res;
    }

    public static int getPreviousDayCode(int todayCode){
        int res = todayCode;
        int currentDay = Integer.parseInt(String.valueOf(todayCode).substring(6,8));
        int currentMonth = Integer.parseInt(String.valueOf(todayCode).substring(4,6));
        int currentYear = Integer.parseInt(String.valueOf(todayCode).substring(0,4));

        if (currentDay-1 > 0)
            res--;
        else {
            if (currentMonth-1 > 0)
                currentMonth--;
            else {
                currentMonth = 12;
                currentYear--;
            }
            res = currentYear*10000 + currentMonth*100 + currentDay;
        }
        return  res;
    }

    public static int getLastDayInMonth(int month, int year){
        switch (month){
            case 1: {
                return 31;
            }
            case 2: {
                if (year % 4 == 0)
                    return 29;
                else
                    return 28;
            }
            case 3: {
                return 31;
            }
            case 4: {
                return 30;
            }
            case 5: {
                return 31;
            }
            case 6: {
                return 30;
            }
            case 7: {
                return 31;
            }
            case 8: {
                return 31;
            }
            case 9: {
                return 30;
            }
            case 10: {
                return 31;
            }
            case 11: {
                return 30;
            }
            case 12: {
                return 31;
            }

        }
        return 0;
    }

    public static int getNumberOfTeamsInLeague(String leagueName){
        int result = 0;
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(leagueName)){
                    return Integer.parseInt(str.split("=")[1]);
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getNameOfLeagueFromWS(String leagueName){
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[3].equals(leagueName)){
                    return str.split("=")[0];
                }
                if (leagueName.contains("China - Super League")){
                    return "CHI.SuperLeague";
                }
                if (leagueName.contains("USA - Major League Soccer Grp.")){
                    return "MLS";
                }
                if (leagueName.contains("Europa League")){
                    return "EuropaLeague";
                }
                if (leagueName.contains("Champions League")){
                    return "ChampionsLeague";
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return "";
    }


    public static String getLeagueName(String country, String leagueName){
        String result = "";
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[3].equals(country + "*" + leagueName)){
                    in.close();
                    return str.split("=")[0];
                }
            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static boolean isFavoriteLeague(String leagueName){
        boolean result = false;
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[3].equals(leagueName))
                    return true;
                if (leagueName.contains("China - Super League")){
                    return true;
                }
                if (leagueName.contains("USA - Major League Soccer"))
                    return true;
                if (leagueName.contains("Europe - Champions League"))
                    return true;
                if (leagueName.contains("Europe - Europa League"))
                    return true;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getCurrentSeasonInLeague(String leagueName){
        String result = "";
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(leagueName)){
                    return str.split("=")[2];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String[] getActionsList(){
        return new String[]{"Выберите действие", "Продлить подписку", "Закончить подписку", "Возобновить подписку", "Изменить данные"};
    }

    public static void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile() && !myFile.getName().contains("users")) myFile.delete();

    }

    public static ArrayList<String> getListForMatchCenter(String fullFileName){
        ArrayList result = new ArrayList();
        try {
            File fileDir = new File(fullFileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static ArrayList<String> getListOfLeagueFor1x(){
        ArrayList<String> result = new ArrayList<>();
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[4].equals("1xstavka"))
                    result.add(str.split("=")[5]);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static boolean isLeagueWhoscored(String leagueName){
        boolean result = false;
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(leagueName) && str.split("=")[4].equals("whoscored"))
                    result = true;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }
}