package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;

// определяем корневой элемент
@XmlRootElement(name = "Team")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"teamName", "matches", "refStatMatches", "wins", "draws", "loses", "goalsScored", "goalsConcedered",
        "goalsDifference", "points", "matchList"})

public class Team {
    static double xG_of_Penalty = 0.75;
    static double xG_of_ownGoal = 0.3;

    public String teamName;
    //======================= Основные параметры команды
    public int matches;
    public int refStatMatches;
    public int wins;
    public int draws;
    public int loses;
    public int goalsScored;
    public int goalsConcedered;
    public int goalsDifference;
    public int points;
    //======================= Список матчей
    public ArrayList<String> matchList;

    public Team() {
        this.matchList = new ArrayList<>();
    }

    public Team(String name) {

        this.teamName = name;
        this.matches = 0;
        this.refStatMatches = 0;
        this.wins = 0;
        this.draws = 0;
        this.loses = 0;
        this.goalsScored = 0;
        this.goalsConcedered = 0;
        this.goalsDifference = 0;
        this.points = 0;
        this.matchList = new ArrayList<>();

    }

    public String addMatch(Match match){
        String result = "";
        String type = "home";
        if (this.teamName.equals(match.awayTeam))
            type = "away";
        boolean fileNotFoundFlag = true;
        for (int i=0; i<this.matchList.size(); i++){
            if (this.matchList.get(i).equals(match.title)){
                fileNotFoundFlag = false;
                result = "Повтор";
            }
        }
        if (fileNotFoundFlag){
            this.matchList.add(match.title);
            this.matches ++;
            this.refStatMatches++;
            if (type.equals("home")){
                if (match.homeScore == match.awayScore)
                    this.draws ++;
                else if (match.homeScore > match.awayScore)
                    this.wins ++;
                else this.loses ++;

                this.points = 3*this.wins + this.draws;
                this.goalsScored += match.homeScore;
                this.goalsConcedered += match.awayScore;
                this.goalsDifference += match.homeScore - match.awayScore;

            } else {
                if (match.homeScore == match.awayScore)
                    this.draws ++;
                else if (match.homeScore > match.awayScore)
                    this.loses ++;
                else this.wins ++;

                this.points = 3*this.wins + this.draws;
                this.goalsScored += match.awayScore;
                this.goalsConcedered += match.homeScore;
                this.goalsDifference += match.awayScore - match.homeScore;
            }
            this.pushTeamToFile(match.league, match.season);
            result = "Успех";
        }
        return result;
    }

    public static Team getTeamFromFileWithSeason(String teamName, String league, String season){
        String path = Settings.getPathToDatabase() + season + "/" + league + "/Teams/" + teamName+".xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Team.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Team) un.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Team downloadTeam(String teamName, String league, String season){
        String path = "tmp/" + teamName+".xml";
        Team team = null;
        try {
            FTPLoader.downloadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + season + "/" + league + "/Teams/" + teamName+".xml", path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Team.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            team = (Team) un.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return team;
    }

    public void pushTeamToFile(String league, String season){
        String fileName = Settings.getPathToDatabase() + season + "/" + league + "/Teams/" + this.teamName + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Team.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + season + "/" + league + "/Teams/" + this.teamName + ".xml" , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static double[] getXWinXDrawXLose(double xDiff){
        double[] result = new double[3];

        if (xDiff>=0){
            if (xDiff>1){
                result[0] = 1;
                result[1] = 0;
                result[2] = 0;
            } else {
                result[0] = roundResult((1/(1+Math.exp(5-10*xDiff))-0.0066928509242848554)/0.9866142981514304,3);
                result[1] = roundResult(1 - result[0],3);
                result[2] = 0;
            }
        } else {
            if (xDiff<-1){
                result[0] = 0;
                result[1] = 0;
                result[2] = 1;
            } else {
                result[2] = roundResult((1/(1+Math.exp(5-10*Math.abs(xDiff)))-0.0066928509242848554)/0.9866142981514304,3);
                result[1] = roundResult(1 - result[2],3);
                result[0] = 0;
            }
        }
        return result;
    }

    public static double roundResult(double d, int precise) {
        precise = (int) Math.pow(10,precise);
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }

    public static String getNameOfTeam(String nameOfWhoscoredOr1X, boolean isEurocup){
        String result = "";
        try {
            File fileDir;
            if (isEurocup)
                fileDir = new File(Settings.getPathToDatabase() + "Teams_eurocups.txt");
            else
                fileDir = new File(Settings.getPathToDatabase() + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[1].equals(nameOfWhoscoredOr1X)){
                    result = str.split("=")[0];
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

    public static String getShortName(String teamName){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[2];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getLeague(String teamName){
        String result = "";
        try {
            // РАССКОММЕНТИТЬ getPathToDatabase после отладки
            File fileDir = new File(/*Settings.getPathToDatabase()*/ "database/" + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[3];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getNameForMatchCenter(String teamName, String league){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[8].equals(teamName) && str.split("=")[3].equals(league)){
                    return str.split("=")[0];
                }
            }
            in.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return result;
    }
}