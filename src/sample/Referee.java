package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Collections;

// определяем корневой элемент
@XmlRootElement(name = "Referee")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"name", "matches", "expandedMatches", "homeWins", "draws", "awayWins",
        "totalYC", "homeYC", "awayYC", "totalSecondYC", "homeSecondYC", "awaySecondYC",
        "totalDirectRC", "homeDirectRC", "awayDirectRC", "totalPen", "homePen", "awayPen",
        "totalFouls", "homeFouls", "awayFouls", "matchList"})

public class Referee {

    public String name;
    public int matches;
    public int expandedMatches;
    public int homeWins;
    public int draws;
    public int awayWins;
    public int totalYC;
    public int homeYC;
    public int awayYC;
    public int totalSecondYC;
    public int homeSecondYC;
    public int awaySecondYC;
    public int totalDirectRC;
    public int homeDirectRC;
    public int awayDirectRC;
    public int totalPen;
    public int homePen;
    public int awayPen;
    public int totalFouls;
    public int homeFouls;
    public int awayFouls;
    //======================= Список матчей
    public ArrayList<String> matchList;

    public Referee() {
        this.matchList = new ArrayList<>();
    }

    public Referee(String name) {
        this.name = name;
        this.matches = 0;
        this.expandedMatches = 0;
        this.homeWins = 0;
        this.draws = 0;
        this.awayWins = 0;
        this.totalYC = 0;
        this.homeYC = 0;
        this.awayYC = 0;
        this.totalSecondYC = 0;
        this.homeSecondYC = 0;
        this.awaySecondYC = 0;
        this.totalDirectRC = 0;
        this.homeDirectRC = 0;
        this.awayDirectRC = 0;
        this.totalPen = 0;
        this.homePen = 0;
        this.awayPen = 0;
        this.totalFouls = 0;
        this.homeFouls = 0;
        this.awayFouls = 0;

        this.matchList = new ArrayList<>();
    }

    public static Referee getRefFromFile(String refName, String season, String league){
        String fileName = Settings.getPathToDatabase() + season + "/" + league + "/Referees/" + refName+".xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Referee.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Referee) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Referee downloadRef(String refName, String league, String season){
        String path = "tmp/" + refName+".xml";
        Referee ref = null;
        try {
            FTPLoader.downloadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + season + "/" + league + "/Referees/" + refName+".xml", path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Referee.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            ref = (Referee) un.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return ref;
    }

    public void pushRefToFile(String league, String season){
        String fileName = Settings.getPathToDatabase() + season + "/" + league + "/Referees/" + this.name + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Referee.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + season + "/" + league + "/Referees/" + this.name + ".xml" , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void addMatchToRefStats(Match match){
        boolean flag = false;
        if (match.referee == null)
            flag = true;
        else
        if (match.referee.equals(""))
            flag = true;

        if (!flag){
            this.matches += 1;
            this.expandedMatches += 1;
            if (match.homeYellowCards > match.awayYellowCards)
                this.homeWins += 1;
            if (match.homeYellowCards == match.awayYellowCards)
                this.draws += 1;
            if (match.homeYellowCards < match.awayYellowCards)
                this.awayWins += 1;

            this.totalYC += match.homeYellowCards + match.awayYellowCards;
            this.homeYC += match.homeYellowCards;
            this.awayYC += match.awayYellowCards;
            this.totalSecondYC += match.homeSecondYellowCards + match.awaySecondYellowCards;
            this.homeSecondYC += match.homeSecondYellowCards;
            this.awaySecondYC += match.awaySecondYellowCards;
            this.totalDirectRC += match.homeDirectRedCards + match.awayDirectRedCards;
            this.homeDirectRC += match.homeDirectRedCards;
            this.awayDirectRC += match.awayDirectRedCards;
            this.totalPen += match.homePen + match.awayPen;
            this.homePen += match.homePen;
            this.awayPen += match.awayPen;
            this.totalFouls += match.homeFouls + match.awayFouls;
            this.homeFouls += match.homeFouls;
            this.awayFouls += match.awayFouls;
            this.matchList.add(match.title);

            this.pushRefToFile(match.league, match.season);
        }
    }

    public static String getNameByWhoscoredName(String name){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Referees.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[1].equals(name)){
                    result = str.split("=")[0];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void addRefereesTXT(String league, String season, String[] list){
        File file = new File(Settings.getPathToDatabase() + season + "/" + league + "/Referees/Referees.txt");
        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            //теперь мы можем использовать метод write или метод append
            for (String s : list){
                br.write(s);
                br.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                br.close();
                fr.close();
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + season + "/" + league + "/Referees/Referees.txt", file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addRefToDatabase(String league, String surname, String name){
        File file = new File(Settings.getPathToDatabase() + "Referees.txt");
        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            //теперь мы можем использовать метод write или метод append
            br.write(surname + "=" + name + "=" + league);
            br.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                br.close();
                fr.close();
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/Referees.txt", file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        file = new File(Settings.getPathToDatabase() + "/" + Settings.getCurrentSeason() + "/" + league + "/Referees/Referees.txt");
        fr = null;
        br = null;
        ArrayList<String> listOfRefs = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                listOfRefs.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        listOfRefs.add(surname);
        for(int i = 0; i < listOfRefs.size()-1; i++){
            for (int j=i+1; j<listOfRefs.size(); j++){
                if(listOfRefs.get(i).equals(listOfRefs.get(j)))
                    listOfRefs.remove(i);
                j--;
            }
        }

        Collections.sort(listOfRefs);
        //file.delete();

        try {
            //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            //теперь мы можем использовать метод write или метод append
            for (int i=0; i<listOfRefs.size(); i++){
                br.write(listOfRefs.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                br.close();
                fr.close();
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + Settings.getCurrentSeason() + "/" + league + "/Referees/Referees.txt", file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Referee ref = new Referee(surname);
        ref.pushRefToFile(league, Settings.getCurrentSeason());

        String path1 = "database/" + Settings.getCurrentSeason() + "/" + league + "/Referees/" + surname + ".xml";
        String path2 = "database/Referees.txt";
        String path3 = "database/" + Settings.getCurrentSeason() + "/" + league + "/Referees/Referees.txt";
        LogWriter.writeUpdates(path1);
        LogWriter.writeUpdates(path2);
        LogWriter.writeUpdates(path3);

        LogWriter.writeLog(path1);
        LogWriter.writeLog(path2);
        LogWriter.writeLog(path3);


    }

    public static String getLeague(String name){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Referees.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(name)){
                    result = str.split("=")[2];
                    break;
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String[] getListOfRefs(String fileName){
        String resultS = "Выберите арбитра\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return resultS.split("\n");
    }
}