package sample;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// определяем корневой элемент
@XmlRootElement(name = "Match")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"homeTeam", "awayTeam", "homeScore", "awayScore", "homeScore1T", "awayScore1T", "homeScore2T", "awayScore2T",
        "homeBallPossession", "awayBallPossession", "homeBallPossession1T", "awayBallPossession1T", "homeBallPossession2T", "awayBallPossession2T",
        "homeCorners", "awayCorners", "homeCorners1T", "awayCorners1T", "homeCorners2T", "awayCorners2T",
        "homeYellowCards", "awayYellowCards", "homeYellowCards1T", "awayYellowCards1T", "homeYellowCards2T", "awayYellowCards2T",
        "homeRedCards", "awayRedCards", "homeShots", "awayShots", "homeShots1T", "awayShots1T", "homeShots2T", "awayShots2T",
        "homeShotsOnTarget", "awayShotsOnTarget", "homeShotsOnTarget1T", "awayShotsOnTarget1T", "homeShotsOnTarget2T", "awayShotsOnTarget2T",
        "homeShotsOffTarget", "awayShotsOffTarget", "homeShotsOffTarget1T", "awayShotsOffTarget1T", "homeShotsOffTarget2T", "awayShotsOffTarget2T",
        "homeBlockedShots", "awayBlockedShots", "homeBlockedShots1T", "awayBlockedShots1T", "homeBlockedShots2T", "awayBlockedShots2T",
        "homeOffsides", "awayOffsides", "homeOffsides1T", "awayOffsides1T", "homeOffsides2T", "awayOffsides2T",
        "homeFouls", "awayFouls", "homeFouls1T", "awayFouls1T", "homeFouls2T", "awayFouls2T",
        "homeShotsOnPost", "awayShotsOnPost", "homeSaves", "awaySaves", "homeSaves1T", "awaySaves1T", "homeSaves2T", "awaySaves2T",
        "homeDribbles", "awayDribbles", "homeDribbles1T", "awayDribbles1T", "homeDribbles2T", "awayDribbles2T",
        "homeAerialsWon", "awayAerialsWon", "homeAerialsWon1T", "awayAerialsWon1T", "homeAerialsWon2T", "awayAerialsWon2T",
        "homeClearances", "awayClearances", "homeClearances1T", "awayClearances1T", "homeClearances2T", "awayClearances2T",
        "homeInterceptions", "awayInterceptions", "homeInterceptions1T", "awayInterceptions1T", "homeInterceptions2T", "awayInterceptions2T",
        "homeTackles", "awayTackles", "homeTackles1T", "awayTackles1T", "homeTackles2T", "awayTackles2T",
        "homeDispossessed", "awayDispossessed", "homeDispossessed1T", "awayDispossessed1T", "homeDispossessed2T", "awayDispossessed2T",
        "homeKeyPasses", "awayKeyPasses", "homeKeyPasses1T", "awayKeyPasses1T", "homeKeyPasses2T", "awayKeyPasses2T",
        "homePasses", "awayPasses", "homePasses1T", "awayPasses1T", "homePasses2T", "awayPasses2T",
        "homeThrowIns", "awayThrowIns", "homeThrowIns1T", "awayThrowIns1T", "homeThrowIns2T", "awayThrowIns2T",
        "homeGoalKicks", "awayGoalKicks", "homeGoalKicks1T", "awayGoalKicks1T", "homeGoalKicks2T", "awayGoalKicks2T",
        "homePassesSuccessfully", "awayPassesSuccessfully", "homePassesSuccessfully1T", "awayPassesSuccessfully1T", "homePassesSuccessfully2T", "awayPassesSuccessfully2T",
        "referee", "homeXG", "awayXG", "homeSecondYellowCards", "awaySecondYellowCards", "homeDirectRedCards", "awayDirectRedCards",
        "homeOGScored", "awayOGScored", "homePen", "awayPen", "homePenScored", "awayPenScored", "URIonWhoscored",
        "homeGoalsBy15minutes", "awayGoalsBy15minutes", "firstYCMinute", "lastYCMinute", "firstYCTeam", "lastYCTeam",
        "date", "league", "isWhoScoredStats", "title", "season"})

public class Match {
    public String homeTeam;  //+++
    public String awayTeam;  //+++
    public int homeScore;  //+++
    public int awayScore;  //+++
    public int homeScore1T;  //+++
    public int awayScore1T;  //+++
    public int homeScore2T;  //+++
    public int awayScore2T;  //+++
    public int homeBallPossession;  //+++
    public int awayBallPossession;  //+++
    public int homeBallPossession1T;  //+++
    public int awayBallPossession1T;  //+++
    public int homeBallPossession2T;  //+++
    public int awayBallPossession2T;  //+++
    public int homeCorners;  //+++
    public int awayCorners;  //+++
    public int homeCorners1T;  //+++
    public int awayCorners1T;  //+++
    public int homeCorners2T;  //+++
    public int awayCorners2T;  //+++
    public int homeYellowCards; //+++
    public int awayYellowCards; //+++
    public int homeYellowCards1T;   //+++
    public int awayYellowCards1T;   //+++
    public int homeYellowCards2T;   //+++
    public int awayYellowCards2T;   //+++
    public int homeRedCards;    //+++
    public int awayRedCards;    //+++
    public int homeShots;         //+++
    public int awayShots;         //+++
    public int homeShots1T;         //+++
    public int awayShots1T;         //+++
    public int homeShots2T;         //+++
    public int awayShots2T;         //+++
    public int homeShotsOnTarget;    //+++
    public int awayShotsOnTarget;    //+++
    public int homeShotsOnTarget1T;    //+++
    public int awayShotsOnTarget1T;    //+++
    public int homeShotsOnTarget2T;    //+++
    public int awayShotsOnTarget2T;    //+++
    public int homeShotsOffTarget;    //+++
    public int awayShotsOffTarget;    //+++
    public int homeShotsOffTarget1T;    //+++
    public int awayShotsOffTarget1T;    //+++
    public int homeShotsOffTarget2T;    //+++
    public int awayShotsOffTarget2T;    //+++
    public int homeBlockedShots;        //+++
    public int awayBlockedShots;        //+++
    public int homeBlockedShots1T;      //+++
    public int awayBlockedShots1T;      //+++
    public int homeBlockedShots2T;      //+++
    public int awayBlockedShots2T;      //+++
    public int homeOffsides;          //+++
    public int awayOffsides;          //+++
    public int homeOffsides1T;          //+++
    public int awayOffsides1T;          //+++
    public int homeOffsides2T;          //+++
    public int awayOffsides2T;          //+++
    public int homeFouls;              //+++
    public int awayFouls;              //+++
    public int homeFouls1T;          //+++
    public int awayFouls1T;          //+++
    public int homeFouls2T;            //+++
    public int awayFouls2T;            //+++
    public int homeShotsOnPost;            //+++
    public int awayShotsOnPost;
    public int homeSaves;                   //+++
    public int awaySaves;                   //+++
    public int homeSaves1T;               //+++
    public int awaySaves1T;               //+++
    public int homeSaves2T;                 //+++
    public int awaySaves2T;                 //+++
    public int homeDribbles;                   //+++
    public int awayDribbles;                   //+++
    public int homeDribbles1T;               //+++
    public int awayDribbles1T;               //+++
    public int homeDribbles2T;                 //+++
    public int awayDribbles2T;                 //+++
    public int homeAerialsWon;              //+++
    public int awayAerialsWon;              //+++
    public int homeAerialsWon1T;                //+++
    public int awayAerialsWon1T;                //+++
    public int homeAerialsWon2T;                //+++
    public int awayAerialsWon2T;                //+++
    public int homeClearances;              //+++
    public int awayClearances;              //+++
    public int homeClearances1T;                //+++
    public int awayClearances1T;                //+++
    public int homeClearances2T;                //+++
    public int awayClearances2T;                //+++
    public int homeInterceptions;              //+++
    public int awayInterceptions;              //+++
    public int homeInterceptions1T;                //+++
    public int awayInterceptions1T;                //+++
    public int homeInterceptions2T;                //+++
    public int awayInterceptions2T;                //+++
    public int homeDispossessed;              //+++
    public int awayDispossessed;              //+++
    public int homeDispossessed1T;                //+++
    public int awayDispossessed1T;                //+++
    public int homeDispossessed2T;                //+++
    public int awayDispossessed2T;                //+++
    public int homeTackles;              //+++
    public int awayTackles;              //+++
    public int homeTackles1T;                //+++
    public int awayTackles1T;                //+++
    public int homeTackles2T;                //+++
    public int awayTackles2T;                //+++
    public int homePasses;              //+++
    public int awayPasses;              //+++
    public int homePasses1T;                //+++
    public int awayPasses1T;                //+++
    public int homePasses2T;                //+++
    public int awayPasses2T;                //+++
    public int homeThrowIns;              //+++
    public int awayThrowIns;              //+++
    public int homeThrowIns1T;                //+++
    public int awayThrowIns1T;                //+++
    public int homeThrowIns2T;                //+++
    public int awayThrowIns2T;                //+++
    public int homeGoalKicks;              //+++
    public int awayGoalKicks;              //+++
    public int homeGoalKicks1T;                //+++
    public int awayGoalKicks1T;                //+++
    public int homeGoalKicks2T;                //+++
    public int awayGoalKicks2T;                //+++
    public int homeKeyPasses;              //+++
    public int awayKeyPasses;              //+++
    public int homeKeyPasses1T;                //+++
    public int awayKeyPasses1T;                //+++
    public int homeKeyPasses2T;                //+++
    public int awayKeyPasses2T;                //+++
    public int homePassesSuccessfully;              //+++
    public int awayPassesSuccessfully;              //+++
    public int homePassesSuccessfully1T;                //+++
    public int awayPassesSuccessfully1T;                //+++
    public int homePassesSuccessfully2T;                //+++
    public int awayPassesSuccessfully2T;                //+++
    public String referee;              //+++
    public double homeXG;               //+++
    public double awayXG;               //+++
    public int homeSecondYellowCards;               //+++
    public int awaySecondYellowCards;               //+++
    public int homeDirectRedCards;              //+++
    public int awayDirectRedCards;              //+++
    public int homeOGScored;                //+++
    public int awayOGScored;                //+++
    public int homePen;             //+++
    public int awayPen;             //+++
    public int homePenScored;             //+++
    public int awayPenScored;             //+++
    public String URIonWhoscored;               //+++
    public String homeGoalsBy15minutes;               //+++
    public String awayGoalsBy15minutes;               //+++
    public int firstYCMinute;             //+++
    public int lastYCMinute;             //+++
    public String firstYCTeam = "";               //+++
    public String lastYCTeam = "";               //+++
    public String date;             //+++
    public String league;               //+++
    public boolean isWhoScoredStats;               //+++
    public String season;               //+++
    public String title;               //+++

    public Match(){
    }

    public Match(String URI){
        /*System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        org.jsoup.nodes.Document doc = null;
        try{
            doc = Jsoup.connect(URI).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isEurocup = false;
        if (URI.contains("UEFA-Europa-League") || URI.contains("UEFA-Champions-League"))
            isEurocup = true;

        String s = doc.toString().split("matchCentreData = ")[1].split("</script>")[0] + "}}";*/

        String whoscored = "https" + URI.split("<link rel=")[1].split("<title>")[0].split("https")[1].split(">")[0];
        whoscored = whoscored.replace("\"", "");

        String s = URI.split("matchCentreData = ")[1].split("</script>")[0] + "}}";



        URIonWhoscored = whoscored;
        boolean isEurocup = false;

        boolean ligueWasFound = false;
        if (URIonWhoscored.contains("Europa-League")){
            league = "EuropaLeague";
            isEurocup = true;
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Champions-League")){
            league = "ChampionsLeague";
            isEurocup = true;
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("England-Premier-League")){
            league = "ENG.PrLeague";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("England-Championship")){
            league = "ENG.Championship";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("France-Ligue-1")){
            league = "FRA.Ligue1";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Germany-Bundesliga-20")){
            league = "GER.1-Bundesliga";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Germany-Bundesliga-II")){
            league = "GER.2-Bundesliga";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Italy-Serie-A")){
            league = "ITA.SerieA";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Netherlands-Eredivisie")){
            league = "NET.Eredivisie";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Portugal-Liga-NOS")){
            league = "POR.Primeira";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Russia-Premier-League")){
            league = "RUS.RrLeague";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Spain-LaLiga")){
            league = "SPA.LaLiga";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Turkey-Super-Lig")){
            league = "TUR.SuperLig";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Brazil-Brasileirão")){
            league = "BRA.SerieA";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("China-Super-league")){
            league = "CHI.SuperLeague";
            ligueWasFound = true;
        }
        if ( URIonWhoscored.contains("Major-League-Soccer")){
            league = "MLS";
            ligueWasFound = true;
        }

        if (!ligueWasFound) {
            league = Team.getLeague(homeTeam);
        }

        String refS = "";
        try{
            refS = s.split("\"referee\":")[1].split(",\"weatherCode\"")[0];
        } catch (Exception ignored){

        }
        String htScoreS = s.split("\"htScore\":\"")[1].split("\",\"ftScore\"")[0];
        String fullScoreS = s.split("\"ftScore\":\"")[1].split("\",\"etScore\"")[0];
        String homeTeamStatS = s.split("\"home\":")[1].split(",\"away\":")[0];
        String awayTeamStatS = s.split("\"away\":")[1].split(",\"maxMinute\":")[0];
        String minutes = s.split("\"periodEndMinutes\":")[1].split(",\"commonEvents")[0];
        String dateS = s.split("\"startTime\":\"")[1].split("T")[0];
        String incidentHTS = homeTeamStatS.split("\"incidentEvents\":")[1].split(",\"shotZones\"")[0];
        String incidentATS = awayTeamStatS.split("\"incidentEvents\":")[1].split(",\"shotZones\"")[0];

        String eventsS = s.split("\"events\":")[1].split(",\"timeoutInSeconds\"")[0];
        ArrayList<Object> events = null;

        JSONParser parser = new JSONParser();
        JSONObject ref = null;
        JSONObject homeTeamS = null;
        JSONObject awayTeamS = null;
        JSONObject minute = null;
        ArrayList<Object> incidentHT = null;
        ArrayList<Object> incidentAT = null;

        try {
            if (!refS.equals(""))
            ref = (JSONObject) parser.parse(refS);
            homeTeamS = (JSONObject) parser.parse(homeTeamStatS);
            awayTeamS = (JSONObject) parser.parse(awayTeamStatS);
            minute = (JSONObject) parser.parse(minutes);
            incidentHT = (ArrayList) parser.parse(incidentHTS);
            incidentAT = (ArrayList) parser.parse(incidentATS);

            events = (ArrayList) parser.parse(eventsS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String homeTeamID = homeTeamStatS.split("teamId\":")[1].split(",")[0];

        for (int i=0; i<events.size(); i++) {
            String type = ((JSONObject) events.get(i)).get("qualifiers").toString();
            if (type.contains("GoalKick")){
                if (((JSONObject) events.get(i)).get("teamId").toString().equals(homeTeamID)){
                    homeGoalKicks++;
                    if (((JSONObject) events.get(i)).get("period").toString().contains("FirstHalf")){
                        homeGoalKicks1T++;
                    }
                    if (((JSONObject) events.get(i)).get("period").toString().contains("SecondHalf")){
                        homeGoalKicks2T++;
                    }
                } else {
                    awayGoalKicks++;
                    if (((JSONObject) events.get(i)).get("period").toString().contains("FirstHalf")){
                        awayGoalKicks1T++;
                    }
                    if (((JSONObject) events.get(i)).get("period").toString().contains("SecondHalf")){
                        awayGoalKicks2T++;
                    }
                }
            }
        }

        int firstMinuteOf2ndTime = Integer.parseInt(String.valueOf(minute.get("1"))) + 1;

        homeTeam = Team.getNameOfTeam(String.valueOf(homeTeamS.get("name")), isEurocup);
        awayTeam = Team.getNameOfTeam(String.valueOf(awayTeamS.get("name")), isEurocup);
        homeScore = Integer.parseInt(fullScoreS.split(" : ")[0]);
        awayScore = Integer.parseInt(fullScoreS.split(" : ")[1]);
        homeScore1T = Integer.parseInt(htScoreS.split(" : ")[0]);
        awayScore1T = Integer.parseInt(htScoreS.split(" : ")[1]);
        homeScore2T = homeScore - homeScore1T;
        awayScore2T = awayScore - awayScore1T;

        Object[] minParameterHT = null;
        Object[] parameterHT = null;
        Object[] minParameterAT = null;
        Object[] parameterAT = null;

        double htPar;
        double htPar1T;
        double htPar2T;
        double atPar;
        double atPar1T;
        double atPar2T;

        String[] titles = {"possession", "cornersTotal", "shotsTotal", "shotsOnTarget", "shotsOffTarget", "shotsBlocked",
                "offsidesCaught", "foulsCommited", "shotsOnPost", "dribblesWon", "aerialsWon", "clearances", "interceptions",
                "passesTotal", "passesAccurate", "passesKey", "tackleSuccessful", "dispossessed", "throwInsTotal"};

        for (int index=0; index<titles.length; index++){
            if (homeTeamStatS.contains(titles[index])){
                minParameterHT = ((JSONObject) ((JSONObject) homeTeamS.get("stats")).get(titles[index])).keySet().toArray();
                parameterHT = ((JSONObject) ((JSONObject) homeTeamS.get("stats")).get(titles[index])).values().toArray();
            }
            if (awayTeamStatS.contains(titles[index])){
                minParameterAT = ((JSONObject) ((JSONObject) awayTeamS.get("stats")).get(titles[index])).keySet().toArray();
                parameterAT = ((JSONObject) ((JSONObject) awayTeamS.get("stats")).get(titles[index])).values().toArray();
            }

            htPar = 0;
            htPar1T = 0;
            htPar2T = 0;
            atPar = 0;
            atPar1T = 0;
            atPar2T = 0;
            if (homeTeamStatS.contains(titles[index])){
                for (int i=0; i< minParameterHT.length; i++){
                    if (Double.parseDouble(String.valueOf(minParameterHT[i])) < firstMinuteOf2ndTime)
                        htPar1T += Double.parseDouble(String.valueOf(parameterHT[i]));
                    else
                        htPar2T += Double.parseDouble(String.valueOf(parameterHT[i]));
                    htPar += Double.parseDouble(String.valueOf(parameterHT[i]));
                }
            }
            if (awayTeamStatS.contains(titles[index])){
                for (int i=0; i< minParameterAT.length; i++){
                    if (Double.parseDouble(String.valueOf(minParameterAT[i])) < firstMinuteOf2ndTime)
                        atPar1T += Double.parseDouble(String.valueOf(parameterAT[i]));
                    else
                        atPar2T += Double.parseDouble(String.valueOf(parameterAT[i]));
                    atPar += Double.parseDouble(String.valueOf(parameterAT[i]));
                }
            }
            switch (titles[index]){
                case "possession":{
                    homeBallPossession = (int) Team.roundResult(100 * htPar / (htPar + atPar) , 0);
                    awayBallPossession = (int) Team.roundResult(100 * atPar / (htPar + atPar) , 0);
                    homeBallPossession1T = (int) Team.roundResult(100 * htPar1T / (htPar1T + atPar1T) , 0);
                    awayBallPossession1T = (int) Team.roundResult(100 * atPar1T / (htPar1T + atPar1T) , 0);
                    homeBallPossession2T = (int) Team.roundResult(100 * htPar2T / (htPar2T + atPar2T) , 0);
                    awayBallPossession2T = (int) Team.roundResult(100 * atPar2T / (htPar2T + atPar2T) , 0);
                    break;
                }
                case "cornersTotal":{
                    homeCorners = (int) htPar;
                    awayCorners = (int) atPar;
                    homeCorners1T = (int) htPar1T;
                    awayCorners1T = (int) atPar1T;
                    homeCorners2T = (int) htPar2T;
                    awayCorners2T = (int) atPar2T;
                    break;
                }
                case "shotsTotal":{
                    homeShots = (int) htPar;
                    awayShots = (int) atPar;
                    homeShots1T = (int) htPar1T;
                    awayShots1T = (int) atPar1T;
                    homeShots2T = (int) htPar2T;
                    awayShots2T = (int) atPar2T;
                    break;
                }
                case "shotsOnTarget":{
                    homeShotsOnTarget = (int) htPar;
                    awayShotsOnTarget = (int) atPar;
                    homeShotsOnTarget1T = (int) htPar1T;
                    awayShotsOnTarget1T = (int) atPar1T;
                    homeShotsOnTarget2T = (int) htPar2T;
                    awayShotsOnTarget2T = (int) atPar2T;
                    break;
                }
                case "shotsOffTarget":{
                    homeShotsOffTarget = (int) htPar;
                    awayShotsOffTarget = (int) atPar;
                    homeShotsOffTarget1T = (int) htPar1T;
                    awayShotsOffTarget1T = (int) atPar1T;
                    homeShotsOffTarget2T = (int) htPar2T;
                    awayShotsOffTarget2T = (int) atPar2T;
                    break;
                }
                case "shotsBlocked":{
                    homeBlockedShots = (int) htPar;
                    awayBlockedShots = (int) atPar;
                    homeBlockedShots1T = (int) htPar1T;
                    awayBlockedShots1T = (int) atPar1T;
                    homeBlockedShots2T = (int) htPar2T;
                    awayBlockedShots2T = (int) atPar2T;
                    break;
                }
                case "offsidesCaught":{
                    homeOffsides = (int) htPar;
                    awayOffsides = (int) atPar;
                    homeOffsides1T = (int) htPar1T;
                    awayOffsides1T = (int) atPar1T;
                    homeOffsides2T = (int) htPar2T;
                    awayOffsides2T = (int) atPar2T;
                    break;
                }
                case "foulsCommited":{
                    homeFouls = (int) htPar;
                    awayFouls = (int) atPar;
                    homeFouls1T = (int) htPar1T;
                    awayFouls1T = (int) atPar1T;
                    homeFouls2T = (int) htPar2T;
                    awayFouls2T = (int) atPar2T;
                    break;
                }
                case "shotsOnPost":{
                    homeShotsOnPost = (int) htPar;
                    awayShotsOnPost = (int) atPar;
                    break;
                }
                case "dribblesWon":{
                    homeDribbles = (int) htPar;
                    awayDribbles = (int) atPar;
                    homeDribbles1T = (int) htPar1T;
                    awayDribbles1T = (int) atPar1T;
                    homeDribbles2T = (int) htPar2T;
                    awayDribbles2T = (int) atPar2T;
                    break;
                }
                case "aerialsWon":{
                    homeAerialsWon = (int) htPar;
                    awayAerialsWon = (int) atPar;
                    homeAerialsWon1T = (int) htPar1T;
                    awayAerialsWon1T = (int) atPar1T;
                    homeAerialsWon2T = (int) htPar2T;
                    awayAerialsWon2T = (int) atPar2T;
                    break;
                }
                case "clearances":{
                    homeClearances = (int) htPar;
                    awayClearances = (int) atPar;
                    homeClearances1T = (int) htPar1T;
                    awayClearances1T = (int) atPar1T;
                    homeClearances2T = (int) htPar2T;
                    awayClearances2T = (int) atPar2T;
                    break;
                }
                case "interceptions":{
                    homeInterceptions = (int) htPar;
                    awayInterceptions = (int) atPar;
                    homeInterceptions1T = (int) htPar1T;
                    awayInterceptions1T = (int) atPar1T;
                    homeInterceptions2T = (int) htPar2T;
                    awayInterceptions2T = (int) atPar2T;
                    break;
                }
                case "passesTotal":{
                    homePasses = (int) htPar;
                    awayPasses = (int) atPar;
                    homePasses1T = (int) htPar1T;
                    awayPasses1T = (int) atPar1T;
                    homePasses2T = (int) htPar2T;
                    awayPasses2T = (int) atPar2T;
                    break;
                }
                case "passesAccurate":{
                    homePassesSuccessfully = (int) htPar;
                    awayPassesSuccessfully = (int) atPar;
                    homePassesSuccessfully1T = (int) htPar1T;
                    awayPassesSuccessfully1T = (int) atPar1T;
                    homePassesSuccessfully2T = (int) htPar2T;
                    awayPassesSuccessfully2T = (int) atPar2T;
                    break;
                }
                case "passesKey":{
                    homeKeyPasses = (int) htPar;
                    awayKeyPasses = (int) atPar;
                    homeKeyPasses1T = (int) htPar1T;
                    awayKeyPasses1T = (int) atPar1T;
                    homeKeyPasses2T = (int) htPar2T;
                    awayKeyPasses2T = (int) atPar2T;
                    break;
                }
                case "tackleSuccessful":{
                    homeTackles = (int) htPar;
                    awayTackles = (int) atPar;
                    homeTackles1T = (int) htPar1T;
                    awayTackles1T = (int) atPar1T;
                    homeTackles2T = (int) htPar2T;
                    awayTackles2T = (int) atPar2T;
                    break;
                }
                case "dispossessed":{
                    homeDispossessed = (int) htPar;
                    awayDispossessed = (int) atPar;
                    homeDispossessed1T = (int) htPar1T;
                    awayDispossessed1T = (int) atPar1T;
                    homeDispossessed2T = (int) htPar2T;
                    awayDispossessed2T = (int) atPar2T;
                    break;
                }
                case "throwInsTotal":{
                    homeThrowIns = (int) htPar;
                    awayThrowIns = (int) atPar;
                    homeThrowIns1T = (int) htPar1T;
                    awayThrowIns1T = (int) atPar1T;
                    homeThrowIns2T = (int) htPar2T;
                    awayThrowIns2T = (int) atPar2T;
                    break;
                }
            }

        }

        int[] goalMinutesHT = {0, 0, 0, 0, 0, 0};
        int[] goalMinutesAT = {0, 0, 0, 0, 0, 0};
        firstYCMinute = 200;
        lastYCMinute = -1;

        for (int i=0; i<incidentHT.size(); i++){
            String type = (String) ((JSONObject) (((JSONObject) incidentHT.get(i)).get("type"))).get("displayName");

            switch (type){
                case "Goal":{
                    int minuteOfGoal = Integer.parseInt((((JSONObject) incidentHT.get(i)).get("expandedMinute")).toString());
                    String qualifiers = (((JSONObject) incidentHT.get(i)).get("qualifiers")).toString();
                    if (qualifiers.contains("displayName\":\"OwnGoal")){
                        awayOGScored ++;
                        if (minuteOfGoal < 16)
                            goalMinutesAT[0] ++;
                        if (minuteOfGoal >= 16 && minuteOfGoal <= 30)
                            goalMinutesAT[1] ++;
                        if (minuteOfGoal >= 31 && minuteOfGoal < firstMinuteOf2ndTime)
                            goalMinutesAT[2] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime && minuteOfGoal <= firstMinuteOf2ndTime+15)
                            goalMinutesAT[3] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+16 && minuteOfGoal <= firstMinuteOf2ndTime+30)
                            goalMinutesAT[4] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+31)
                            goalMinutesAT[5] ++;
                    } else{
                        if (qualifiers.contains("displayName\":\"Penalty")){
                            homePen ++;
                            homePenScored ++;
                        }
                        if (minuteOfGoal < 16)
                            goalMinutesHT[0] ++;
                        if (minuteOfGoal >= 16 && minuteOfGoal <= 30)
                            goalMinutesHT[1] ++;
                        if (minuteOfGoal >= 31 && minuteOfGoal < firstMinuteOf2ndTime)
                            goalMinutesHT[2] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime && minuteOfGoal <= firstMinuteOf2ndTime+15)
                            goalMinutesHT[3] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+16 && minuteOfGoal <= firstMinuteOf2ndTime+30)
                            goalMinutesHT[4] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+31)
                            goalMinutesHT[5] ++;
                    }
                    break;
                }
                case "Card":{
                    String cardType = "";
                    try {
                        cardType = (String) ((JSONObject) (((JSONObject) incidentHT.get(i)).get("cardType"))).get("displayName");
                    } catch (Exception ignored){

                    }
                    if (cardType.equals("SecondYellow") && ((JSONObject) incidentHT.get(i)).get("playerId") != null){
                        homeSecondYellowCards ++;
                        homeRedCards ++;
                    }
                    if (cardType.equals("Red") && ((JSONObject) incidentHT.get(i)).get("playerId") != null){
                        homeDirectRedCards ++;
                        homeRedCards++;
                    }
                    if (cardType.equals("Yellow") && ((JSONObject) incidentHT.get(i)).get("playerId") != null){
                        homeYellowCards++;
                        int minuteOfCard = Integer.parseInt((((JSONObject) incidentHT.get(i)).get("expandedMinute")).toString());
                        if (minuteOfCard < firstMinuteOf2ndTime)
                            homeYellowCards1T ++;
                        else
                            homeYellowCards2T ++;

                        int realMinute = minuteOfCard + 1;
                        if (minuteOfCard > firstMinuteOf2ndTime)
                            realMinute = minuteOfCard + 45 - firstMinuteOf2ndTime + 1;

                        if (firstYCMinute == 200){
                            firstYCMinute = realMinute;
                            if (realMinute > 45 && realMinute < firstMinuteOf2ndTime)
                                firstYCMinute = 45;
                            firstYCTeam = homeTeam;
                        }
                        lastYCMinute = realMinute;
                        lastYCTeam = homeTeam;
                    }

                    break;
                }
                case "SavedShot":{
                    String qualifiers = (((JSONObject) incidentHT.get(i)).get("qualifiers")).toString();
                    if (qualifiers.contains("displayName\":\"Penalty"))
                        homePen ++;

                    break;
                }
            }
        }
        for (int i=0; i<incidentAT.size(); i++){
            String type = (String) ((JSONObject) (((JSONObject) incidentAT.get(i)).get("type"))).get("displayName");

            switch (type){
                case "Goal":{
                    int minuteOfGoal = Integer.parseInt((((JSONObject) incidentAT.get(i)).get("expandedMinute")).toString());
                    String qualifiers = (((JSONObject) incidentAT.get(i)).get("qualifiers")).toString();
                    if (qualifiers.contains("displayName\":\"OwnGoal")){
                        homeOGScored ++;
                        if (minuteOfGoal < 16)
                            goalMinutesHT[0] ++;
                        if (minuteOfGoal >= 16 && minuteOfGoal <= 30)
                            goalMinutesHT[1] ++;
                        if (minuteOfGoal >= 31 && minuteOfGoal < firstMinuteOf2ndTime)
                            goalMinutesHT[2] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime && minuteOfGoal <= firstMinuteOf2ndTime+15)
                            goalMinutesHT[3] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+16 && minuteOfGoal <= firstMinuteOf2ndTime+30)
                            goalMinutesHT[4] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+31)
                            goalMinutesHT[5] ++;
                    } else{
                        if (qualifiers.contains("displayName\":\"Penalty")){
                            awayPen ++;
                            awayPenScored ++;
                        }
                        if (minuteOfGoal < 16)
                            goalMinutesAT[0] ++;
                        if (minuteOfGoal >= 16 && minuteOfGoal <= 30)
                            goalMinutesAT[1] ++;
                        if (minuteOfGoal >= 31 && minuteOfGoal < firstMinuteOf2ndTime)
                            goalMinutesAT[2] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime && minuteOfGoal <= firstMinuteOf2ndTime+15)
                            goalMinutesAT[3] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+16 && minuteOfGoal <= firstMinuteOf2ndTime+30)
                            goalMinutesAT[4] ++;
                        if (minuteOfGoal >= firstMinuteOf2ndTime+31)
                            goalMinutesAT[5] ++;
                    }
                    break;
                }
                case "Card":{
                    String cardType = (String) ((JSONObject) (((JSONObject) incidentAT.get(i)).get("cardType"))).get("displayName");
                    if (cardType.equals("SecondYellow") && ((JSONObject) incidentAT.get(i)).get("playerId") != null){
                        awaySecondYellowCards ++;
                        awayRedCards ++;
                    }
                    if (cardType.equals("Red") && ((JSONObject) incidentAT.get(i)).get("playerId") != null){
                        awayDirectRedCards ++;
                        awayRedCards++;
                    }
                    if (cardType.equals("Yellow") && ((JSONObject) incidentAT.get(i)).get("playerId") != null){
                        awayYellowCards++;
                        int minuteOfCard = Integer.parseInt((((JSONObject) incidentAT.get(i)).get("expandedMinute")).toString());
                        if (minuteOfCard < firstMinuteOf2ndTime)
                            awayYellowCards1T ++;
                        else
                            awayYellowCards2T ++;
                        int realMinute = minuteOfCard + 1;
                        if (minuteOfCard > firstMinuteOf2ndTime)
                            realMinute = minuteOfCard + 45 - firstMinuteOf2ndTime + 1;

                        if (firstYCMinute == 200 || firstYCMinute > realMinute){
                            firstYCMinute = realMinute;
                            if (realMinute > 45 && realMinute < firstMinuteOf2ndTime)
                                firstYCMinute = 45;
                            firstYCTeam = awayTeam;
                        }
                        if (lastYCMinute == -1 || lastYCMinute < realMinute){
                            lastYCMinute = realMinute;
                            lastYCTeam = awayTeam;
                        }


                    }

                    break;
                }
                case "SavedShot":{
                    String qualifiers = (((JSONObject) incidentAT.get(i)).get("qualifiers")).toString();
                    if (qualifiers.contains("displayName\":\"Penalty"))
                        awayPen ++;
                    break;
                }
            }
        }
        if (homeYellowCards2T + awayYellowCards2T == 0 && lastYCMinute > 45)
            lastYCMinute = 45;
        if (lastYCMinute > 90)
            lastYCMinute = 90;

        homeGoalsBy15minutes = String.valueOf(goalMinutesHT[0]) + "*" + String.valueOf(goalMinutesHT[1])
                + "*" + String.valueOf(goalMinutesHT[2]) + "*" + String.valueOf(goalMinutesHT[3])
                + "*" + String.valueOf(goalMinutesHT[4]) + "*" + String.valueOf(goalMinutesHT[5]);
        awayGoalsBy15minutes = String.valueOf(goalMinutesAT[0]) + "*" + String.valueOf(goalMinutesAT[1])
                + "*" + String.valueOf(goalMinutesAT[2]) + "*" + String.valueOf(goalMinutesAT[3])
                + "*" + String.valueOf(goalMinutesAT[4]) + "*" + String.valueOf(goalMinutesAT[5]);

        int numberOfGK_HT = homeTeamStatS.split("totalSaves\":\\{").length - 1;
        while (numberOfGK_HT > 0){
            String[] savesHT = homeTeamStatS.split("totalSaves\":\\{")[1].split("\\}")[0].split(",");
            for (int k=0; k<savesHT.length; k++){
                homeSaves += (int) Double.parseDouble(savesHT[k].split(":")[1].split("\"")[0]);
                if (Integer.parseInt(savesHT[k].split("\":")[0].split("\"")[1]) < firstMinuteOf2ndTime)
                    homeSaves1T += (int) Double.parseDouble(savesHT[k].split(":")[1].split("\"")[0]);
                else
                    homeSaves2T += (int) Double.parseDouble(savesHT[k].split(":")[1].split("\"")[0]);
            }
            homeTeamStatS = homeTeamStatS.replaceFirst("totalSaves", "");
            numberOfGK_HT --;
        }

        int numberOfGK_AT = awayTeamStatS.split("totalSaves\":\\{").length - 1;
        while (numberOfGK_AT > 0){
            String[] savesAT = awayTeamStatS.split("totalSaves\":\\{")[1].split("\\}")[0].split(",");
            for (int k=0; k<savesAT.length; k++){
                awaySaves += (int) Double.parseDouble(savesAT[k].split(":")[1].split("\"")[0]);
                if (Integer.parseInt(savesAT[k].split("\":")[0].split("\"")[1]) < firstMinuteOf2ndTime)
                    awaySaves1T += (int) Double.parseDouble(savesAT[k].split(":")[1].split("\"")[0]);
                else
                    awaySaves2T += (int) Double.parseDouble(savesAT[k].split(":")[1].split("\"")[0]);
            }
            awayTeamStatS = awayTeamStatS.replaceFirst("totalSaves", "");
            numberOfGK_AT --;
        }


        if (ref != null)
            referee = Referee.getNameByWhoscoredName(String.valueOf(ref.get("name")));
        date = dateS.split("-")[2] + "." + dateS.split("-")[1] + "." + dateS.split("-")[0];

//        URIonWhoscored = URI;

        String[] seasonArray = URIonWhoscored.split("-");
        boolean flag = false;
        int index = 0;

        while (!flag) {
            int year = 0;
            int nextYear = 0;
            try {
                year = Integer.parseInt(seasonArray[index]);
                if (year < 2000)
                    index += 1;
            } catch (Exception exception) {
                index += 1;
            }
            if (year > 2000){
                flag = true;
                season = String.valueOf(year).replaceFirst("20", "");
                try {
                    nextYear = Integer.parseInt(seasonArray[index+1]);
                } catch (Exception exception) {
                    index += 1;
                }
                if (nextYear>0){
                    season += "-" + String.valueOf(nextYear).replaceFirst("20", "");
                }
            }
        }
        isWhoScoredStats = true;
        title = Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + date;
        homeXG = 0.0;
        awayXG = 0.0;

    }

    public Match(int begin, int end, String[] stats){
        int localIndex = begin;
        LocalDate ldNow = LocalDate.now();
        int year = ldNow.getYear();
        int month = Integer.parseInt(stats[localIndex].split(" ")[0].split("\\.")[1]);
        int day =   Integer.parseInt(stats[localIndex].split(" ")[0].split("\\.")[0]);

        LocalDate dateOfParsing = LocalDate.of(year, month, day);
        if (dateOfParsing.isAfter(LocalDate.now())){
            dateOfParsing.minusYears(1);
        }
        date = dateOfParsing.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        localIndex++;

        homeTeam = Team.getNameOfTeam(stats[localIndex].split(" - ")[0], false);
        awayTeam = Team.getNameOfTeam(stats[localIndex].split(" - ")[1], false);
        title = Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + date;
        league = Team.getLeague(homeTeam);

        localIndex++;

        homeScore = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
        awayScore = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
        homeScore1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
        awayScore1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
        homeScore2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
        awayScore2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
        localIndex++;

        while (localIndex < end){
            if (stats[localIndex].equals("Угловые")){
                localIndex ++;
                homeCorners = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayCorners = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeCorners1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayCorners1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeCorners2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayCorners2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Желтые карточки")){
                localIndex ++;
                homeYellowCards = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayYellowCards = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeYellowCards1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayYellowCards1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeYellowCards2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayYellowCards2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Удары в створ ворот")){
                localIndex ++;
                homeShotsOnTarget = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayShotsOnTarget = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeShotsOnTarget1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayShotsOnTarget1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeShotsOnTarget2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayShotsOnTarget2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Офсайды")){
                localIndex ++;
                homeOffsides = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayOffsides = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeOffsides1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayOffsides1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeOffsides2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayOffsides2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Фолы")){
                localIndex ++;
                homeFouls = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayFouls = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeFouls1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayFouls1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeFouls2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayFouls2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Штанги и перекладины")){
                localIndex ++;
                homeShotsOnPost = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayShotsOnPost = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Процент владения мячом")){
                localIndex ++;
                homeBallPossession = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayBallPossession = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Удары от ворот")){
                localIndex ++;
                homeGoalKicks = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayGoalKicks = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeGoalKicks1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayGoalKicks1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeGoalKicks2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayGoalKicks2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Вброс аутов")){
                localIndex ++;
                homeThrowIns = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayThrowIns = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeThrowIns1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awayThrowIns1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeThrowIns2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awayThrowIns2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Сейвы")){
                localIndex ++;
                homeSaves = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awaySaves = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                homeSaves1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[0]);
                awaySaves1T = Integer.parseInt(stats[localIndex].split(" \\(")[1].split(",")[0].split(":")[1]);
                homeSaves2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[0]);
                awaySaves2T = Integer.parseInt(stats[localIndex].split(",")[1].split("\\)")[0].split(":")[1]);
                localIndex ++;
            }
            if (stats[localIndex].equals("Удары по воротам")){
                localIndex ++;
                homeShots = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[0]);
                awayShots = Integer.parseInt(stats[localIndex].split(" \\(")[0].split(":")[1]);
                localIndex ++;
            }
            homeGoalsBy15minutes = "0*0*0*0*0*0";
            awayGoalsBy15minutes = "0*0*0*0*0*0";

            localIndex++;
        }

    }

    /*public void addXGStats(Match match, double homeXG, double awayXG){
        if (((match.homeXG == 0)&&(match.awayXG == 0))||((match.homeXG == homeXG)&&(match.awayXG == awayXG))){
            match.homeXG = homeXG;
            match.awayXG = awayXG;
            this.pushMatchToFile(false);
        }
    }*/

    public boolean isAllData(){
        boolean result = true;
        if (homeTeam.equals("") || awayTeam.equals("") || referee == null || referee.equals("") || league.equals("") ||
                season.equals("") || date.equals("") || title.equals("") || URIonWhoscored.equals(""))
            result = false;

        return result;
    }

    public void pushMatchToFile(boolean addToLeagueList){
        String fileName = Settings.getPathToDatabase() + this.season + "/" + this.league + "/Matches/" + title + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Match.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            if (addToLeagueList)
                League.addMatchToList(league, season, title);
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + this.season + "/" + this.league + "/Matches/" + title + ".xml" , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Match getMatchFromFileByName(String matchName){
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Match) un.unmarshal(new File(matchName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Match downloadMatch(String season, String league, String matchName){
        try {
            FTPLoader.downloadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/" + season + "/" + league + "/Matches/" + matchName, "tmp/"+matchName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Match) un.unmarshal(new File("tmp/" + matchName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
