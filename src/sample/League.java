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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

// определяем корневой элемент
@XmlRootElement(name = "League")
// определяем последовательность тегов в XML
@XmlType(propOrder = { "leagueName", "season", "fileName", "matchesPlayed", "matchesToAddingInStat", "goalsScored", "goalsScored1T", "goalsScored2T",
        "homeGoals", "awayGoals", "homeGoals1T", "awayGoals1T", "homeGoals2T", "awayGoals2T", "goalsScoredBy15Min",
        "homeGoalsBy15Min", "awayGoalsBy15Min", "homeXG", "awayXG", "homeBallPossession", "awayBallPossession",
        "homeCorners", "awayCorners", "homeCorners1T", "awayCorners1T", "homeCorners2T", "awayCorners2T",
        "homeYellowCards", "awayYellowCards", "homeYellowCards1T", "awayYellowCards1T", "homeYellowCards2T", "awayYellowCards2T",
        "homeRedCards", "awayRedCards", "homeShots", "awayShots", "homeShotsOnTarget", "awayShotsOnTarget", "homeShotsOffTarget", "awayShotsOffTarget",
        "homeBlockedShots", "awayBlockedShots", "homeOffsides", "awayOffsides", "homeFouls", "awayFouls", "homeShotsOnPost", "awayShotsOnPost",
        "homeSaves", "awaySaves", "homeDribbles", "awayDribbles", "homeAerialsWon", "awayAerialsWon", "homeClearances", "awayClearances",
        "homeInterceptions", "awayInterceptions", "homeDispossessed", "awayDispossessed", "homeTackles", "awayTackles", "homePasses", "awayPasses",
        "homeKeyPasses", "awayKeyPasses", "homePassesSuccessfully", "awayPassesSuccessfully", "homeSecondYellowCards", "awaySecondYellowCards",
        "homeDirectRedCards", "awayDirectRedCards", "homeOGScored", "awayOGScored", "homePen", "awayPen", "homePenScored", "awayPenScored",
        "g_homeWin_draw_awayWin", "g_homeWin_draw_awayWin_1T", "g_homeWin_draw_awayWin_2T", "g_OZ", "g_totals", "g_totals_1T", "g_totals_2T",
        "g_goalsInBothTimes", "g_firstTimeMoreGoals", "g_secondTimeMoreGoals",
        "c_homeWin_draw_awayWin","c_homeWin_draw_awayWin_1T","c_homeWin_draw_awayWin_2T","c_totals","c_totals_1T","c_totals_2T","c_firstTimeMoreCorners","c_secondTimeMoreCorners",
        "yc_homeWin_draw_awayWin","yc_homeWin_draw_awayWin_1T","yc_homeWin_draw_awayWin_2T","yc_totals","yc_totals_1T","yc_totals_2T","yc_firstTimeMoreYC","yc_secondTimeMoreYC",
        "tournamentTable", "overallStatsTable", "homeStatsTable", "awayStatsTable"
       })

public class League {
    public String leagueName;
    public String season;
    public String fileName;
    public int matchesPlayed;
    public ArrayList<String> matchesToAddingInStat;

    // голы и xG
    public int goalsScored;
    public int goalsScored1T;
    public int goalsScored2T;
    public int homeGoals;
    public int awayGoals;
    public int homeGoals1T;
    public int awayGoals1T;
    public int homeGoals2T;
    public int awayGoals2T;
    public String goalsScoredBy15Min;
    public String homeGoalsBy15Min;
    public String awayGoalsBy15Min;
    public double homeXG;
    public double awayXG;

    // остальная статистика
    public int homeBallPossession;
    public int awayBallPossession;
    public int homeCorners;
    public int awayCorners;
    public int homeCorners1T;
    public int awayCorners1T;
    public int homeCorners2T;
    public int awayCorners2T;
    public int homeYellowCards;
    public int awayYellowCards;
    public int homeYellowCards1T;
    public int awayYellowCards1T;
    public int homeYellowCards2T;
    public int awayYellowCards2T;
    public int homeRedCards;
    public int awayRedCards;
    public int homeShots;
    public int awayShots;
    public int homeShotsOnTarget;
    public int awayShotsOnTarget;
    public int homeShotsOffTarget;
    public int awayShotsOffTarget;
    public int homeBlockedShots;
    public int awayBlockedShots;
    public int homeOffsides;
    public int awayOffsides;
    public int homeFouls;
    public int awayFouls;
    public int homeShotsOnPost;
    public int awayShotsOnPost;
    public int homeSaves;
    public int awaySaves;
    public int homeDribbles;
    public int awayDribbles;
    public int homeAerialsWon;
    public int awayAerialsWon;
    public int homeClearances;
    public int awayClearances;
    public int homeInterceptions;
    public int awayInterceptions;
    public int homeDispossessed;
    public int awayDispossessed;
    public int homeTackles;
    public int awayTackles;
    public int homePasses;
    public int awayPasses;
    public int homeKeyPasses;
    public int awayKeyPasses;
    public int homePassesSuccessfully;
    public int awayPassesSuccessfully;
    public int homeSecondYellowCards;
    public int awaySecondYellowCards;
    public int homeDirectRedCards;
    public int awayDirectRedCards;
    public int homeOGScored;
    public int awayOGScored;
    public int homePen;
    public int awayPen;
    public int homePenScored;
    public int awayPenScored;

    // показатели по лиге (голы)
    public String g_homeWin_draw_awayWin;
    public String g_homeWin_draw_awayWin_1T;
    public String g_homeWin_draw_awayWin_2T;
    public int g_OZ;
    public String g_totals;
    public String g_totals_1T;
    public String g_totals_2T;
    public int g_goalsInBothTimes;
    public int g_firstTimeMoreGoals;
    public int g_secondTimeMoreGoals;

    // показатели по лиге (угловые)
    public String c_homeWin_draw_awayWin;
    public String c_homeWin_draw_awayWin_1T;
    public String c_homeWin_draw_awayWin_2T;
    public String c_totals;
    public String c_totals_1T;
    public String c_totals_2T;
    public int c_firstTimeMoreCorners;
    public int c_secondTimeMoreCorners;

    // показатели по лиге (ЖК)
    public String yc_homeWin_draw_awayWin;
    public String yc_homeWin_draw_awayWin_1T;
    public String yc_homeWin_draw_awayWin_2T;
    public String yc_totals;
    public String yc_totals_1T;
    public String yc_totals_2T;
    public int yc_firstTimeMoreYC;
    public int yc_secondTimeMoreYC;

    // турнирная таблица
    public ArrayList<String> tournamentTable;  // Team*matchesPlayed*goalsScored*goalsConceded*goalsDiff*points
    public ArrayList<String> overallStatsTable;
    public ArrayList<String> homeStatsTable;
    public ArrayList<String> awayStatsTable;

    public League(){

    }

    public League(String leagueName, String season) {
        this.leagueName = leagueName;
        this.season = season;
        this.fileName = leagueName + "_" + season + ".xml";
        goalsScoredBy15Min = "0*0*0*0*0*0";
        homeGoalsBy15Min = "0*0*0*0*0*0";
        awayGoalsBy15Min = "0*0*0*0*0*0";
        g_homeWin_draw_awayWin = "0*0*0";
        g_homeWin_draw_awayWin_1T = "0*0*0";
        g_homeWin_draw_awayWin_2T = "0*0*0";
        g_totals = "0*0*0*0*0*0*0";
        g_totals_1T = "0*0*0*0*0*0*0";
        g_totals_2T = "0*0*0*0*0*0*0";
        c_homeWin_draw_awayWin = "0*0*0";
        c_homeWin_draw_awayWin_1T = "0*0*0";
        c_homeWin_draw_awayWin_2T = "0*0*0";
        c_totals = "0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0";
        c_totals_1T = "0*0*0*0*0*0*0*0*0*0*0";
        c_totals_2T = "0*0*0*0*0*0*0*0*0*0*0";

        yc_homeWin_draw_awayWin = "0*0*0";
        yc_homeWin_draw_awayWin_1T = "0*0*0";
        yc_homeWin_draw_awayWin_2T = "0*0*0";
        yc_totals = "0*0*0*0*0*0*0*0*0";
        yc_totals_1T = "0*0*0*0*0*0*0";
        yc_totals_2T = "0*0*0*0*0*0*0";
    }

    public String fillDataFromAddingList(){
        String result = leagueName + ":\n";
        boolean flagError = false;
        if (matchesToAddingInStat == null ){
            result += "Необработанных матчей нет. \n";
            return result;
        }

        while (matchesToAddingInStat.size() > 0 && !flagError) {
            Match match = Match.getMatchFromFileByName(Settings.getPathToDatabase() + season + "/" + leagueName + "/Matches/" + matchesToAddingInStat.get(0) + ".xml");
            if (match != null){
                matchesPlayed ++;
                goalsScored += match.homeScore + match.awayScore;
                goalsScored1T += match.homeScore1T + match.awayScore1T;
                goalsScored2T += match.homeScore2T + match.awayScore2T;
                homeGoals += match.homeScore;
                awayGoals += match.awayScore;
                homeGoals1T += match.homeScore1T;
                awayGoals1T += match.awayScore1T;
                homeGoals2T += match.homeScore2T;
                awayGoals2T += match.awayScore2T;

                String[] arrGoals = goalsScoredBy15Min.split("\\*");
                String[] arrGoalsHT = homeGoalsBy15Min.split("\\*");
                String[] arrGoalsAT = awayGoalsBy15Min.split("\\*");
                if (Settings.isLeagueWhoscored(match.league)){
                    for (int i=0;i<arrGoals.length;i++){
                        arrGoals[i] = String.valueOf( Integer.parseInt(goalsScoredBy15Min.split("\\*")[i]) + Integer.parseInt(match.homeGoalsBy15minutes.split("\\*")[i]) + Integer.parseInt(match.awayGoalsBy15minutes.split("\\*")[i]));
                        arrGoalsHT[i] = String.valueOf( Integer.parseInt(homeGoalsBy15Min.split("\\*")[i]) + Integer.parseInt(match.homeGoalsBy15minutes.split("\\*")[i]));
                        arrGoalsAT[i] = String.valueOf( Integer.parseInt(awayGoalsBy15Min.split("\\*")[i]) + Integer.parseInt(match.awayGoalsBy15minutes.split("\\*")[i]));
                    }
                    goalsScoredBy15Min = String.valueOf(arrGoals[0]) + "*" + String.valueOf(arrGoals[1])
                            + "*" + String.valueOf(arrGoals[2]) + "*" + String.valueOf(arrGoals[3])
                            + "*" + String.valueOf(arrGoals[4]) + "*" + String.valueOf(arrGoals[5]);
                    homeGoalsBy15Min = String.valueOf(arrGoalsHT[0]) + "*" + String.valueOf(arrGoalsHT[1])
                            + "*" + String.valueOf(arrGoalsHT[2]) + "*" + String.valueOf(arrGoalsHT[3])
                            + "*" + String.valueOf(arrGoalsHT[4]) + "*" + String.valueOf(arrGoalsHT[5]);
                    awayGoalsBy15Min = String.valueOf(arrGoalsAT[0]) + "*" + String.valueOf(arrGoalsAT[1])
                            + "*" + String.valueOf(arrGoalsAT[2]) + "*" + String.valueOf(arrGoalsAT[3])
                            + "*" + String.valueOf(arrGoalsAT[4]) + "*" + String.valueOf(arrGoalsAT[5]);
                } else {
                    goalsScoredBy15Min = "0*0*0*0*0*0";
                    homeGoalsBy15Min = "0*0*0*0*0*0";
                    awayGoalsBy15Min = "0*0*0*0*0*0";
                }

                homeXG += match.homeXG;
                awayXG += match.awayXG;

                homeBallPossession += match.homeBallPossession;
                awayBallPossession += match.awayBallPossession;
                homeCorners += match.homeCorners;
                awayCorners += match.awayCorners;
                homeCorners1T += match.homeCorners1T;
                awayCorners1T += match.awayCorners1T;
                homeCorners2T += match.homeCorners2T;
                awayCorners2T += match.awayCorners2T;
                homeYellowCards += match.homeYellowCards;
                awayYellowCards += match.awayYellowCards;
                homeYellowCards1T += match.homeYellowCards1T;
                awayYellowCards1T += match.awayYellowCards1T;
                homeYellowCards2T += match.homeYellowCards2T;
                awayYellowCards2T += match.awayYellowCards2T;
                homeRedCards += match.homeRedCards;
                awayRedCards += match.awayRedCards;
                homeShots += match.homeShots;
                awayShots += match.awayShots;
                homeShotsOnTarget += match.homeShotsOnTarget;
                awayShotsOnTarget += match.awayShotsOnTarget;
                homeShotsOffTarget += match.homeShotsOffTarget;
                awayShotsOffTarget += match.awayShotsOffTarget;
                homeBlockedShots += match.homeBlockedShots;
                awayBlockedShots += match.awayBlockedShots;
                homeOffsides += match.homeOffsides;
                awayOffsides += match.awayOffsides;
                homeFouls += match.homeFouls;
                awayFouls += match.awayFouls;
                homeShotsOnPost += match.homeShotsOnPost;
                awayShotsOnPost += match.awayShotsOnPost;
                homeSaves += match.homeSaves;
                awaySaves += match.awaySaves;
                homeDribbles += match.homeDribbles;
                awayDribbles += match.awayDribbles;
                homeAerialsWon += match.homeAerialsWon;
                awayAerialsWon += match.awayAerialsWon;
                homeClearances += match.homeClearances;
                awayClearances += match.awayClearances;
                homeInterceptions += match.homeInterceptions;
                awayInterceptions += match.awayInterceptions;
                homeDispossessed += match.homeDispossessed;
                awayDispossessed += match.awayDispossessed;
                homeTackles += match.homeTackles;
                awayTackles += match.awayTackles;
                homePasses += match.homePasses;
                awayPasses += match.awayPasses;
                homeKeyPasses += match.homeKeyPasses;
                awayKeyPasses += match.awayKeyPasses;
                homePassesSuccessfully += match.homePassesSuccessfully;
                awayPassesSuccessfully += match.awayPassesSuccessfully;
                homeSecondYellowCards += match.homeSecondYellowCards;
                awaySecondYellowCards += match.awaySecondYellowCards;
                homeDirectRedCards += match.homeDirectRedCards;
                awayDirectRedCards += match.awayDirectRedCards;
                homeOGScored += match.homeOGScored;
                awayOGScored += match.awayOGScored;
                homePen += match.homePen;
                awayPen += match.awayPen;
                homePenScored += match.homePenScored;
                awayPenScored += match.awayPenScored;

                int homeWin = Integer.parseInt(g_homeWin_draw_awayWin.split("\\*")[0]);
                int draw = Integer.parseInt(g_homeWin_draw_awayWin.split("\\*")[1]);
                int awayWin = Integer.parseInt(g_homeWin_draw_awayWin.split("\\*")[2]);

                if (match.homeScore == match.awayScore){
                    draw++;
                } else {
                    if (match.homeScore > match.awayScore)
                        homeWin ++;
                    else
                        awayWin ++;
                }

                g_homeWin_draw_awayWin = String.valueOf(homeWin) + "*" + String.valueOf(draw) + "*" + String.valueOf(awayWin);

                int homeWin1T = Integer.parseInt(g_homeWin_draw_awayWin_1T.split("\\*")[0]);
                int draw1T = Integer.parseInt(g_homeWin_draw_awayWin_1T.split("\\*")[1]);
                int awayWin1T = Integer.parseInt(g_homeWin_draw_awayWin_1T.split("\\*")[2]);

                if (match.homeScore1T == match.awayScore1T){
                    draw1T++;
                } else {
                    if (match.homeScore1T > match.awayScore1T)
                        homeWin1T ++;
                    else
                        awayWin1T ++;
                }

                g_homeWin_draw_awayWin_1T = String.valueOf(homeWin1T) + "*" + String.valueOf(draw1T) + "*" + String.valueOf(awayWin1T);

                int homeWin2T = Integer.parseInt(g_homeWin_draw_awayWin_2T.split("\\*")[0]);
                int draw2T = Integer.parseInt(g_homeWin_draw_awayWin_2T.split("\\*")[1]);
                int awayWin2T = Integer.parseInt(g_homeWin_draw_awayWin_2T.split("\\*")[2]);

                if (match.homeScore2T == match.awayScore2T){
                    draw2T++;
                } else {
                    if (match.homeScore2T > match.awayScore2T)
                        homeWin2T ++;
                    else
                        awayWin2T ++;
                }

                g_homeWin_draw_awayWin_2T = String.valueOf(homeWin2T) + "*" + String.valueOf(draw2T) + "*" + String.valueOf(awayWin2T);

                if (match.homeScore * match.awayScore > 0)
                    g_OZ ++;


                String[] totals = g_totals.split("\\*");
                if (match.homeScore + match.awayScore < 6)
                    totals[match.homeScore + match.awayScore] = String.valueOf(Integer.parseInt(totals[match.homeScore + match.awayScore]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                g_totals = String.join("*", totals);

                totals = g_totals_1T.split("\\*");
                if (match.homeScore1T + match.awayScore1T < 6)
                    totals[match.homeScore1T + match.awayScore1T] = String.valueOf(Integer.parseInt(totals[match.homeScore1T + match.awayScore1T]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                g_totals_1T = String.join("*", totals);

                totals = g_totals_2T.split("\\*");
                if (match.homeScore2T + match.awayScore2T < 6)
                    totals[match.homeScore2T + match.awayScore2T] = String.valueOf(Integer.parseInt(totals[match.homeScore2T + match.awayScore2T]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                g_totals_2T = String.join("*", totals);

                if ( (match.homeScore1T+match.awayScore1T)*(match.homeScore2T+match.awayScore2T) > 0 )
                    g_goalsInBothTimes ++;

                if ( (match.homeScore1T+match.awayScore1T) > (match.homeScore2T+match.awayScore2T) )
                    g_firstTimeMoreGoals ++;
                if ( (match.homeScore1T+match.awayScore1T) < (match.homeScore2T+match.awayScore2T) )
                    g_secondTimeMoreGoals ++;

                homeWin = Integer.parseInt(c_homeWin_draw_awayWin.split("\\*")[0]);
                draw = Integer.parseInt(c_homeWin_draw_awayWin.split("\\*")[1]);
                awayWin = Integer.parseInt(c_homeWin_draw_awayWin.split("\\*")[2]);

                if (match.homeCorners == match.awayCorners){
                    draw++;
                } else {
                    if (match.homeCorners > match.awayCorners)
                        homeWin ++;
                    else
                        awayWin ++;
                }

                c_homeWin_draw_awayWin = String.valueOf(homeWin) + "*" + String.valueOf(draw) + "*" + String.valueOf(awayWin);

                homeWin1T = Integer.parseInt(c_homeWin_draw_awayWin_1T.split("\\*")[0]);
                draw1T = Integer.parseInt(c_homeWin_draw_awayWin_1T.split("\\*")[1]);
                awayWin1T = Integer.parseInt(c_homeWin_draw_awayWin_1T.split("\\*")[2]);

                if (match.homeCorners1T == match.awayCorners1T){
                    draw1T++;
                } else {
                    if (match.homeCorners1T > match.awayCorners1T)
                        homeWin1T ++;
                    else
                        awayWin1T ++;
                }

                c_homeWin_draw_awayWin_1T = String.valueOf(homeWin1T) + "*" + String.valueOf(draw1T) + "*" + String.valueOf(awayWin1T);

                homeWin2T = Integer.parseInt(c_homeWin_draw_awayWin_2T.split("\\*")[0]);
                draw2T = Integer.parseInt(c_homeWin_draw_awayWin_2T.split("\\*")[1]);
                awayWin2T = Integer.parseInt(c_homeWin_draw_awayWin_2T.split("\\*")[2]);

                if (match.homeCorners2T == match.awayCorners2T){
                    draw2T++;
                } else {
                    if (match.homeCorners2T > match.awayCorners2T)
                        homeWin2T ++;
                    else
                        awayWin2T ++;
                }

                c_homeWin_draw_awayWin_2T = String.valueOf(homeWin2T) + "*" + String.valueOf(draw2T) + "*" + String.valueOf(awayWin2T);

                totals = c_totals.split("\\*");
                if (match.homeCorners + match.awayCorners < 16)
                    totals[match.homeCorners + match.awayCorners] = String.valueOf(Integer.parseInt(totals[match.homeCorners + match.awayCorners]) + 1);
                else
                    totals[16] = String.valueOf(Integer.parseInt(totals[16]) + 1);

                c_totals = String.join("*", totals);

                totals = c_totals_1T.split("\\*");
                if (match.homeCorners1T + match.awayCorners1T < 10)
                    totals[match.homeCorners1T + match.awayCorners1T] = String.valueOf(Integer.parseInt(totals[match.homeCorners1T + match.awayCorners1T]) + 1);
                else
                    totals[10] = String.valueOf(Integer.parseInt(totals[10]) + 1);

                c_totals_1T = String.join("*", totals);

                totals = c_totals_2T.split("\\*");
                if (match.homeCorners2T + match.awayCorners2T < 10)
                    totals[match.homeCorners2T + match.awayCorners2T] = String.valueOf(Integer.parseInt(totals[match.homeCorners2T + match.awayCorners2T]) + 1);
                else
                    totals[10] = String.valueOf(Integer.parseInt(totals[10]) + 1);

                c_totals_2T = String.join("*", totals);

                if ( (match.homeCorners1T+match.awayCorners1T) > (match.homeCorners2T+match.awayCorners2T) )
                    c_firstTimeMoreCorners ++;
                if ( (match.homeCorners1T+match.awayCorners1T) < (match.homeCorners2T+match.awayCorners2T) )
                    c_secondTimeMoreCorners ++;

                homeWin = Integer.parseInt(yc_homeWin_draw_awayWin.split("\\*")[0]);
                draw = Integer.parseInt(yc_homeWin_draw_awayWin.split("\\*")[1]);
                awayWin = Integer.parseInt(yc_homeWin_draw_awayWin.split("\\*")[2]);

                if (match.homeYellowCards == match.awayYellowCards){
                    draw++;
                } else {
                    if (match.homeYellowCards > match.awayYellowCards)
                        homeWin ++;
                    else
                        awayWin ++;
                }

                yc_homeWin_draw_awayWin = String.valueOf(homeWin) + "*" + String.valueOf(draw) + "*" + String.valueOf(awayWin);

                homeWin1T = Integer.parseInt(yc_homeWin_draw_awayWin_1T.split("\\*")[0]);
                draw1T = Integer.parseInt(yc_homeWin_draw_awayWin_1T.split("\\*")[1]);
                awayWin1T = Integer.parseInt(yc_homeWin_draw_awayWin_1T.split("\\*")[2]);

                if (match.homeYellowCards1T == match.awayYellowCards1T){
                    draw1T++;
                } else {
                    if (match.homeYellowCards1T > match.awayYellowCards1T)
                        homeWin1T ++;
                    else
                        awayWin1T ++;
                }

                yc_homeWin_draw_awayWin_1T = String.valueOf(homeWin1T) + "*" + String.valueOf(draw1T) + "*" + String.valueOf(awayWin1T);

                homeWin2T = Integer.parseInt(yc_homeWin_draw_awayWin_2T.split("\\*")[0]);
                draw2T = Integer.parseInt(yc_homeWin_draw_awayWin_2T.split("\\*")[1]);
                awayWin2T = Integer.parseInt(yc_homeWin_draw_awayWin_2T.split("\\*")[2]);

                if (match.homeYellowCards2T == match.awayYellowCards2T){
                    draw2T++;
                } else {
                    if (match.homeYellowCards2T > match.awayYellowCards2T)
                        homeWin2T ++;
                    else
                        awayWin2T ++;
                }

                yc_homeWin_draw_awayWin_2T = String.valueOf(homeWin2T) + "*" + String.valueOf(draw2T) + "*" + String.valueOf(awayWin2T);

                totals = yc_totals.split("\\*");
                if (match.homeYellowCards + match.awayYellowCards < 8)
                    totals[match.homeYellowCards + match.awayYellowCards] = String.valueOf(Integer.parseInt(totals[match.homeYellowCards + match.awayYellowCards]) + 1);
                else
                    totals[8] = String.valueOf(Integer.parseInt(totals[8]) + 1);

                yc_totals = String.join("*", totals);

                totals = yc_totals_1T.split("\\*");
                if (match.homeYellowCards1T + match.awayYellowCards1T < 6)
                    totals[match.homeYellowCards1T + match.awayYellowCards1T] = String.valueOf(Integer.parseInt(totals[match.homeYellowCards1T + match.awayYellowCards1T]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                yc_totals_1T = String.join("*", totals);

                totals = yc_totals_2T.split("\\*");
                if (match.homeYellowCards2T + match.awayYellowCards2T < 6)
                    totals[match.homeYellowCards2T + match.awayYellowCards2T] = String.valueOf(Integer.parseInt(totals[match.homeYellowCards2T + match.awayYellowCards2T]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                yc_totals_2T = String.join("*", totals);

                if ( (match.homeYellowCards1T+match.awayYellowCards1T) > (match.homeYellowCards2T+match.awayYellowCards2T) )
                    yc_firstTimeMoreYC ++;
                if ( (match.homeYellowCards1T+match.awayYellowCards1T) < (match.homeYellowCards2T+match.awayYellowCards2T) )
                    yc_secondTimeMoreYC ++;

                matchesToAddingInStat.remove(0);
                rewriteTournamentTable(match);
                rewriteStatsTables(match);
                System.out.println(matchesPlayed + ") Матч " + match.title + " добавлен успешно!");
                result += "Матч " + match.title + " добавлен успешно!\n";
            } else {
                flagError = true;
            }
        }
        pushLeagueToFile();
        return result;
    }

    public void pushLeagueToFile(){
        String path = Settings.getPathToDatabase().replace("database", "leaguesInfo");
        String fileName = path + "/" + this.fileName;
        try {
            JAXBContext context = JAXBContext.newInstance(League.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/leaguesInfo/" + this.fileName , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static League getLeagueFromFile(String leagueName, String season){
        String path = Settings.getPathToDatabase().replace("database", "leaguesInfo");
        String fileName = path + "/" + leagueName + "_" + season + ".xml";
        return getLeagueFromFileByName(fileName);
    }

    public static String getLeagueNameFrom1x(String str, ArrayList<String> listOfLeagues){
        String result = "Not found";

        for (String s : listOfLeagues){
            if (str.equals(s)){
                return s;
            }
        }
        return  result;
    }

    public static League getLeagueFromFileByName(String fileName){
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (League) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMatchToList(String leagueName, String season, String matchTitle){
        League league = League.getLeagueFromFile(leagueName, season);
        if (league.matchesToAddingInStat == null)
            league.matchesToAddingInStat = new ArrayList<>();
        league.matchesToAddingInStat.add(matchTitle);
        league.pushLeagueToFile();
    }

    public static void resetLeagueInfo(String leagueName, String season){
        String path = Settings.getPathToDatabase() + season + "/" + leagueName;


        League league = new League(leagueName, season);

        ArrayList<String> tt = new ArrayList<>();
        ArrayList<String> tt2 = new ArrayList<>();
        String[] teamsList = new File(path + "/Teams").list();
        for (String s:teamsList){
            tt.add(s.replaceAll(".xml", "") + "*0*0*0*0*0");
            tt2.add(s.replaceAll(".xml", "") + "*0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0" +
                    "*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0" +
                    "*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0");
        }
        league.tournamentTable = tt;
        league.matchesToAddingInStat = new ArrayList<>();
        league.overallStatsTable = (ArrayList<String>) tt2.clone();
        league.homeStatsTable = (ArrayList<String>) tt2.clone();
        league.awayStatsTable = (ArrayList<String>) tt2.clone();

        String[] matchesList = new File(path + "/Matches").list();
        for (int i=0; i<matchesList.length; i++){
            System.out.println(i+1 + ") Матч: " + matchesList[i]);
            league.matchesToAddingInStat.add(matchesList[i].replace(".xml",""));
        }
        league.fillDataFromAddingList();
    }

    public void rewriteTournamentTable(Match match){
        int htIndex = 0;
        int atIndex = 0;

        for (int i=0; i<tournamentTable.size(); i++){
            if (tournamentTable.get(i).startsWith(match.homeTeam))
                htIndex = i;
            if (tournamentTable.get(i).startsWith(match.awayTeam))
                atIndex = i;
        }

        int htPoints = 0;
        int atPoints = 0;
        if (match.homeScore > match.awayScore)
            htPoints = 3;
        if (match.homeScore == match.awayScore){
            htPoints = 1;
            atPoints = 1;
        }
        if (match.homeScore < match.awayScore)
            atPoints = 3;

        String newRecordForHomeTeam = match.homeTeam + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[2]) + match.homeScore) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[3]) + match.awayScore) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[4]) + match.homeScore - match.awayScore) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[5]) + htPoints);

        String newRecordForAwayTeam = match.awayTeam + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[2]) + match.awayScore) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[3]) + match.homeScore) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[4]) + match.awayScore - match.homeScore) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[5]) + atPoints);

        tournamentTable.set(htIndex, newRecordForHomeTeam);
        tournamentTable.set(atIndex, newRecordForAwayTeam);

        Collections.sort(tournamentTable, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Integer.parseInt(o1.split("\\*")[5]) > Integer.parseInt(o2.split("\\*")[5]))
                    return -1;
                else{
                    if (Integer.parseInt(o1.split("\\*")[5]) == Integer.parseInt(o2.split("\\*")[5]))
                        return 0;
                    else
                        return 1;
                }
            }
        });
        pushLeagueToFile();
    }

    public static String getPositionInLeague(String teamName, ArrayList<String> tournamentTable){
        String result;
        int localIndex = 0;
        int upperPlace;
        int downPlace;
        int localPoints = 0;

        for (int i=0; i<tournamentTable.size(); i++){
            if (tournamentTable.get(i).startsWith(teamName)){
                localIndex = i;
                localPoints = Integer.parseInt(tournamentTable.get(localIndex).split("\\*")[5]);
            }
        }

        upperPlace = localIndex;
        downPlace = localIndex;
        for (int i=0; i<localIndex; i++){
            if (tournamentTable.get(i).split("\\*")[5].equals(String.valueOf(localPoints))){
                upperPlace = i;
                break;
            }
        }
        for (int i=localIndex; i<tournamentTable.size(); i++){
            if (tournamentTable.get(i).split("\\*")[5].equals(String.valueOf(localPoints))){
                downPlace = i;
            }
        }

        if (upperPlace == localIndex && localIndex == downPlace){
            result = String.valueOf(localIndex+1);
        } else {
            result = String.valueOf(1 + Math.min(upperPlace, localIndex)) + "-" + String.valueOf(1 + Math.max(localIndex, downPlace));
        }

        return result;
    }

    public static void setTournamentTable(String leagueName, String season){
        String nameOfFile = "leaguesInfo/" + leagueName + "_" + season + ".xml";
        String path = "database/" + season + "/" + leagueName;

        League league = null;

        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            league =  (League) un.unmarshal(new File(nameOfFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        ArrayList<String> tt = new ArrayList<>();
        String[] teamsList = new File(path + "/Teams").list();
        for (String s:teamsList){
            tt.add(s + "*0*0*0*0*0");
        }
        league.tournamentTable = tt;

        String[] matchesList = new File(path + "/Matches").list();
        for (int i=0; i<matchesList.length; i++){
            Match match = Match.getMatchFromFileByName(path + "/Matches/" + matchesList[i]);
            System.out.println(i+1 + ") Матч: " + matchesList[i]);
            league.rewriteTournamentTable(match);
        }
    }

    public static void setStatsTables(String leagueName, String season){
        String nameOfFile = Settings.getPathToDatabase().replace("database/", "leaguesInfo/") + leagueName + "_" + season + ".xml";
        String path = Settings.getPathToDatabase() + season + "/" + leagueName;

        League league = null;

        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            league =  (League) un.unmarshal(new File(nameOfFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        ArrayList<String> tt = new ArrayList<>();
        String[] teamsList = new File(path + "/Teams").list();
        for (String s:teamsList){
            tt.add(s + "*0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0" +
                    "*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0");
        }
        league.overallStatsTable = (ArrayList<String>) tt.clone();
        league.homeStatsTable = (ArrayList<String>) tt.clone();
        league.awayStatsTable = (ArrayList<String>) tt.clone();

        tt.clear();

        String[] matchesList = new File(path + "/Matches").list();
        for (int i=0; i<matchesList.length; i++){
            Match match = Match.getMatchFromFileByName(path + "/Matches/" + matchesList[i]);
            System.out.println(i+1 + ") Матч: " + matchesList[i]);
            league.rewriteStatsTables(match);
        }
    }

    public void rewriteStatsTables(Match match){
        int htIndex = 0;
        int atIndex = 0;

        for (int i=0; i<overallStatsTable.size(); i++){
            if (overallStatsTable.get(i).startsWith(match.homeTeam))
                htIndex = i;
            if (overallStatsTable.get(i).startsWith(match.awayTeam))
                atIndex = i;
        }

        //        "Голы", "Голы в 1-ом тайме", "Голы во 2-ом тайме", "xG", "Владение",
        //        "Ударов всего", "Удары в створ", "Удары мимо", "Угловые", "Угловые в 1-ом тайме", "Угловые во 2-ом тайме",
        //        "Офсайды", "Блокировано ударов", "Фолы", "Желтые карточки", "Желтые карточки в 1-ом тайме", "Желтые карточки во 2-ом тайме"
        //        "Ауты", "Ауты в 1-ом тайме", "Ауты во 2-ом тайме", "Удары от ворот", "Удары от ворот в 1-ом тайме", "Удары от ворот во 2-ом тайме"

        // свои _ чужие _ разница _ тотал

        String newRecordForHomeTeam = match.homeTeam + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[0]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[1]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[2]) + (match.homeScore - match.awayScore)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[3]) + (match.homeScore + match.awayScore)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[0]) +  match.homeScore1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[1]) +  match.awayScore1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[2]) + (match.homeScore1T - match.awayScore1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[3]) + (match.homeScore1T + match.awayScore1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[0]) +  match.homeScore2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[1]) +  match.awayScore2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[2]) + (match.homeScore2T - match.awayScore2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[3]) + (match.homeScore2T + match.awayScore2T)) + "*"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[0]) +  match.homeXG) + "_"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[1]) +  match.awayXG) + "_"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[2]) + (match.homeXG - match.awayXG)) + "_"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[3]) + (match.homeXG + match.awayXG)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[0]) +  match.homeBallPossession) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[1]) +  match.awayBallPossession) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[2]) + (match.homeBallPossession - match.awayBallPossession)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[3]) + (match.homeBallPossession + match.awayBallPossession)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[0]) +  match.homeShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[1]) +  match.awayShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[2]) + (match.homeShots - match.awayShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[3]) + (match.homeShots + match.awayShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[0]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[1]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[2]) + (match.homeShotsOnTarget - match.awayShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[3]) + (match.homeShotsOnTarget + match.awayShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[0]) +  match.homeShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[1]) +  match.awayShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[2]) + (match.homeShotsOffTarget - match.awayShotsOffTarget)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[3]) + (match.homeShotsOffTarget + match.awayShotsOffTarget)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[0]) +  match.homeCorners) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[1]) +  match.awayCorners) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[2]) + (match.homeCorners - match.awayCorners)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[3]) + (match.homeCorners + match.awayCorners)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[11].split("_")[0]) +  match.homeCorners1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[11].split("_")[1]) +  match.awayCorners1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[11].split("_")[2]) + (match.homeCorners1T - match.awayCorners1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[11].split("_")[3]) + (match.homeCorners1T + match.awayCorners1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[0]) +  match.homeCorners2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[1]) +  match.awayCorners2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[2]) + (match.homeCorners2T - match.awayCorners2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[3]) + (match.homeCorners2T + match.awayCorners2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[0]) +  match.homeOffsides) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[1]) +  match.awayOffsides) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[2]) + (match.homeOffsides - match.awayOffsides)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[3]) + (match.homeOffsides + match.awayOffsides)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[0]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[1]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[2]) + (match.homeBlockedShots - match.awayBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[3]) + (match.homeBlockedShots + match.awayBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[0]) +  match.homeFouls) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[1]) +  match.awayFouls) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[2]) + (match.homeFouls - match.awayFouls)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[3]) + (match.homeFouls + match.awayFouls)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[0]) +  match.homeYellowCards) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[1]) +  match.awayYellowCards) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[2]) + (match.homeYellowCards - match.awayYellowCards)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[3]) + (match.homeYellowCards + match.awayYellowCards)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[0]) +  match.homeYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[1]) +  match.awayYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[2]) + (match.homeYellowCards1T - match.awayYellowCards1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[3]) + (match.homeYellowCards1T + match.awayYellowCards1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[0]) +  match.homeYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[1]) +  match.awayYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[2]) + (match.homeYellowCards2T - match.awayYellowCards2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[3]) + (match.homeYellowCards2T + match.awayYellowCards2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[0]) +  match.homeThrowIns) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[1]) +  match.awayThrowIns) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[2]) + (match.homeThrowIns - match.awayThrowIns)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[3]) + (match.homeThrowIns + match.awayThrowIns)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[0]) +  match.homeThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[1]) +  match.awayThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[2]) + (match.homeThrowIns1T - match.awayThrowIns1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[3]) + (match.homeThrowIns1T + match.awayThrowIns1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[21].split("_")[0]) +  match.homeThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[21].split("_")[1]) +  match.awayThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[21].split("_")[2]) + (match.homeThrowIns2T - match.awayThrowIns2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[21].split("_")[3]) + (match.homeThrowIns2T + match.awayThrowIns2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[22].split("_")[0]) +  match.homeGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[22].split("_")[1]) +  match.awayGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[22].split("_")[2]) + (match.homeGoalKicks - match.awayGoalKicks)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[22].split("_")[3]) + (match.homeGoalKicks + match.awayGoalKicks)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[23].split("_")[0]) +  match.homeGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[23].split("_")[1]) +  match.awayGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[23].split("_")[2]) + (match.homeGoalKicks1T - match.awayGoalKicks1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[23].split("_")[3]) + (match.homeGoalKicks1T + match.awayGoalKicks1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[24].split("_")[0]) +  match.homeGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[24].split("_")[1]) +  match.awayGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[24].split("_")[2]) + (match.homeGoalKicks2T - match.awayGoalKicks2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[24].split("_")[3]) + (match.homeGoalKicks2T + match.awayGoalKicks2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[25].split("_")[0]) +  match.homeShots1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[25].split("_")[1]) +  match.awayShots1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[25].split("_")[2]) + (match.homeShots1T - match.awayShots1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[25].split("_")[3]) + (match.homeShots1T + match.awayShots1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[26].split("_")[0]) +  match.homeShots2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[26].split("_")[1]) +  match.awayShots2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[26].split("_")[2]) + (match.homeShots2T - match.awayShots2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[26].split("_")[3]) + (match.homeShots2T + match.awayShots2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[27].split("_")[0]) +  match.homeShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[27].split("_")[1]) +  match.awayShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[27].split("_")[2]) + (match.homeShotsOnTarget1T - match.awayShotsOnTarget1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[27].split("_")[3]) + (match.homeShotsOnTarget1T + match.awayShotsOnTarget1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[28].split("_")[0]) +  match.homeShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[28].split("_")[1]) +  match.awayShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[28].split("_")[2]) + (match.homeShotsOnTarget2T - match.awayShotsOnTarget2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[28].split("_")[3]) + (match.homeShotsOnTarget2T + match.awayShotsOnTarget2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[29].split("_")[0]) +  match.homeOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[29].split("_")[1]) +  match.awayOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[29].split("_")[2]) + (match.homeOffsides1T - match.awayOffsides1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[29].split("_")[3]) + (match.homeOffsides1T + match.awayOffsides1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[30].split("_")[0]) +  match.homeOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[30].split("_")[1]) +  match.awayOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[30].split("_")[2]) + (match.homeOffsides2T - match.awayOffsides2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[30].split("_")[3]) + (match.homeOffsides2T + match.awayOffsides2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[31].split("_")[0]) +  match.homeFouls1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[31].split("_")[1]) +  match.awayFouls1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[31].split("_")[2]) + (match.homeFouls1T - match.awayFouls1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[31].split("_")[3]) + (match.homeFouls1T + match.awayFouls1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[32].split("_")[0]) +  match.homeFouls2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[32].split("_")[1]) +  match.awayFouls2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[32].split("_")[2]) + (match.homeFouls2T - match.awayFouls2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[32].split("_")[3]) + (match.homeFouls2T + match.awayFouls2T));

        String newRecordForAwayTeam = match.awayTeam + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[0]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[1]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[2]) + (match.awayScore - match.homeScore)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[3]) + (match.awayScore + match.homeScore)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[0]) +  match.awayScore1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[1]) +  match.homeScore1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[2]) + (match.awayScore1T - match.homeScore1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[3]) + (match.awayScore1T + match.homeScore1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[0]) +  match.awayScore2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[1]) +  match.homeScore2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[2]) + (match.awayScore2T - match.homeScore2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[3]) + (match.awayScore2T + match.homeScore2T)) + "*"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[0]) +  match.awayXG) + "_"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[1]) +  match.homeXG) + "_"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[2]) + (match.awayXG - match.homeXG)) + "_"
                + String.valueOf(Double.parseDouble(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[3]) + (match.awayXG + match.homeXG)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[0]) +  match.awayBallPossession) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[1]) +  match.homeBallPossession) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[2]) + (match.awayBallPossession - match.homeBallPossession)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[3]) + (match.awayBallPossession + match.homeBallPossession)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[0]) +  match.awayShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[1]) +  match.homeShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[2]) + (match.awayShots - match.homeShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[3]) + (match.awayShots + match.homeShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[0]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[1]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[2]) + (match.awayShotsOnTarget - match.homeShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[3]) + (match.awayShotsOnTarget + match.homeShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[0]) +  match.awayShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[1]) +  match.homeShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[2]) + (match.awayShotsOffTarget - match.homeShotsOffTarget)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[3]) + (match.awayShotsOffTarget + match.homeShotsOffTarget)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[0]) +  match.awayCorners) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[1]) +  match.homeCorners) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[2]) + (match.awayCorners - match.homeCorners)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[3]) + (match.awayCorners + match.homeCorners)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[11].split("_")[0]) +  match.awayCorners1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[11].split("_")[1]) +  match.homeCorners1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[11].split("_")[2]) + (match.awayCorners1T - match.homeCorners1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[11].split("_")[3]) + (match.awayCorners1T + match.homeCorners1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[0]) +  match.awayCorners2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[1]) +  match.homeCorners2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[2]) + (match.awayCorners2T - match.homeCorners2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[3]) + (match.awayCorners2T + match.homeCorners2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[0]) +  match.awayOffsides) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[1]) +  match.homeOffsides) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[2]) + (match.awayOffsides - match.homeOffsides)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[3]) + (match.awayOffsides + match.homeOffsides)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[0]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[1]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[2]) + (match.awayBlockedShots - match.homeBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[3]) + (match.awayBlockedShots + match.homeBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[0]) +  match.awayFouls) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[1]) +  match.homeFouls) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[2]) + (match.awayFouls - match.homeFouls)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[3]) + (match.awayFouls + match.homeFouls)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[0]) +  match.awayYellowCards) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[1]) +  match.homeYellowCards) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[2]) + (match.awayYellowCards - match.homeYellowCards)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[3]) + (match.awayYellowCards + match.homeYellowCards)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[0]) +  match.awayYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[1]) +  match.homeYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[2]) + (match.awayYellowCards1T - match.homeYellowCards1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[3]) + (match.awayYellowCards1T + match.homeYellowCards1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[0]) +  match.awayYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[1]) +  match.homeYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[2]) + (match.awayYellowCards2T - match.homeYellowCards2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[3]) + (match.awayYellowCards2T + match.homeYellowCards2T) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[0]) +  match.awayThrowIns) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[1]) +  match.homeThrowIns) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[2]) + (match.awayThrowIns - match.homeThrowIns)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[3]) + (match.awayThrowIns + match.homeThrowIns)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[0]) +  match.awayThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[1]) +  match.homeThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[2]) + (match.awayThrowIns1T - match.homeThrowIns1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[3]) + (match.awayThrowIns1T + match.homeThrowIns1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[21].split("_")[0]) +  match.awayThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[21].split("_")[1]) +  match.homeThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[21].split("_")[2]) + (match.awayThrowIns2T - match.homeThrowIns2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[21].split("_")[3]) + (match.awayThrowIns2T + match.homeThrowIns2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[22].split("_")[0]) +  match.awayGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[22].split("_")[1]) +  match.homeGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[22].split("_")[2]) + (match.awayGoalKicks - match.homeGoalKicks)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[22].split("_")[3]) + (match.awayGoalKicks + match.homeGoalKicks)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[23].split("_")[0]) +  match.awayGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[23].split("_")[1]) +  match.homeGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[23].split("_")[2]) + (match.awayGoalKicks1T - match.homeGoalKicks1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[23].split("_")[3]) + (match.awayGoalKicks1T + match.homeGoalKicks1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[24].split("_")[0]) +  match.awayGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[24].split("_")[1]) +  match.homeGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[24].split("_")[2]) + (match.awayGoalKicks2T - match.homeGoalKicks2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[24].split("_")[3]) + (match.awayGoalKicks2T + match.homeGoalKicks2T))) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[25].split("_")[0]) +  match.awayShots1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[25].split("_")[1]) +  match.homeShots1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[25].split("_")[2]) + (match.awayShots1T - match.homeShots1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[25].split("_")[3]) + (match.awayShots1T + match.homeShots1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[26].split("_")[0]) +  match.awayShots2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[26].split("_")[1]) +  match.homeShots2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[26].split("_")[2]) + (match.awayShots2T - match.homeShots2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[26].split("_")[3]) + (match.awayShots2T + match.homeShots2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[27].split("_")[0]) +  match.awayShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[27].split("_")[1]) +  match.homeShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[27].split("_")[2]) + (match.awayShotsOnTarget1T - match.homeShotsOnTarget1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[27].split("_")[3]) + (match.awayShotsOnTarget1T + match.homeShotsOnTarget1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[28].split("_")[0]) +  match.awayShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[28].split("_")[1]) +  match.homeShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[28].split("_")[2]) + (match.awayShotsOnTarget2T - match.homeShotsOnTarget2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[28].split("_")[3]) + (match.awayShotsOnTarget2T + match.homeShotsOnTarget2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[29].split("_")[0]) +  match.awayOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[29].split("_")[1]) +  match.homeOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[29].split("_")[2]) + (match.awayOffsides1T - match.homeOffsides1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[29].split("_")[3]) + (match.awayOffsides1T + match.homeOffsides1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[30].split("_")[0]) +  match.awayOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[30].split("_")[1]) +  match.homeOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[30].split("_")[2]) + (match.awayOffsides2T - match.homeOffsides2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[30].split("_")[3]) + (match.awayOffsides2T + match.homeOffsides2T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[31].split("_")[0]) +  match.awayFouls1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[31].split("_")[1]) +  match.homeFouls1T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[31].split("_")[2]) + (match.awayFouls1T - match.homeFouls1T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[31].split("_")[3]) + (match.awayFouls1T + match.homeFouls1T)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[32].split("_")[0]) +  match.awayFouls2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[32].split("_")[1]) +  match.homeFouls2T) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[32].split("_")[2]) + (match.awayFouls2T - match.homeFouls2T)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[32].split("_")[3]) + (match.awayFouls2T + match.homeFouls2T));

        overallStatsTable.set(htIndex, newRecordForHomeTeam);
        overallStatsTable.set(atIndex, newRecordForAwayTeam);

        String newRecordForHomeTeamInHomeTable = match.homeTeam + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[0]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[1]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[2]) + (match.homeScore - match.awayScore)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[3]) + (match.homeScore + match.awayScore)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[0]) +  match.homeScore1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[1]) +  match.awayScore1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[2]) + (match.homeScore1T - match.awayScore1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[3]) + (match.homeScore1T + match.awayScore1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[0]) +  match.homeScore2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[1]) +  match.awayScore2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[2]) + (match.homeScore2T - match.awayScore2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[3]) + (match.homeScore2T + match.awayScore2T)) + "*"
                + String.valueOf(Double.parseDouble(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[0]) +  match.homeXG) + "_"
                + String.valueOf(Double.parseDouble(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[1]) +  match.awayXG) + "_"
                + String.valueOf(Double.parseDouble(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[2]) + (match.homeXG - match.awayXG)) + "_"
                + String.valueOf(Double.parseDouble(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[3]) + (match.homeXG + match.awayXG)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[0]) +  match.homeBallPossession) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[1]) +  match.awayBallPossession) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[2]) + (match.homeBallPossession - match.awayBallPossession)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[3]) + (match.homeBallPossession + match.awayBallPossession)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[0]) +  match.homeShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[1]) +  match.awayShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[2]) + (match.homeShots - match.awayShots)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[3]) + (match.homeShots + match.awayShots)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[0]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[1]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[2]) + (match.homeShotsOnTarget - match.awayShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[3]) + (match.homeShotsOnTarget + match.awayShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[0]) +  match.homeShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[1]) +  match.awayShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[2]) + (match.homeShotsOffTarget - match.awayShotsOffTarget)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[3]) + (match.homeShotsOffTarget + match.awayShotsOffTarget)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[0]) +  match.homeCorners) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[1]) +  match.awayCorners) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[2]) + (match.homeCorners - match.awayCorners)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[3]) + (match.homeCorners + match.awayCorners)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[11].split("_")[0]) +  match.homeCorners1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[11].split("_")[1]) +  match.awayCorners1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[11].split("_")[2]) + (match.homeCorners1T - match.awayCorners1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[11].split("_")[3]) + (match.homeCorners1T + match.awayCorners1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[0]) +  match.homeCorners2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[1]) +  match.awayCorners2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[2]) + (match.homeCorners2T - match.awayCorners2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[3]) + (match.homeCorners2T + match.awayCorners2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[0]) +  match.homeOffsides) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[1]) +  match.awayOffsides) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[2]) + (match.homeOffsides - match.awayOffsides)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[3]) + (match.homeOffsides + match.awayOffsides)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[0]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[1]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[2]) + (match.homeBlockedShots - match.awayBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[3]) + (match.homeBlockedShots + match.awayBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[0]) +  match.homeFouls) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[1]) +  match.awayFouls) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[2]) + (match.homeFouls - match.awayFouls)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[3]) + (match.homeFouls + match.awayFouls)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[0]) +  match.homeYellowCards) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[1]) +  match.awayYellowCards) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[2]) + (match.homeYellowCards - match.awayYellowCards)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[3]) + (match.homeYellowCards + match.awayYellowCards)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[0]) +  match.homeYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[1]) +  match.awayYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[2]) + (match.homeYellowCards1T - match.awayYellowCards1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[3]) + (match.homeYellowCards1T + match.awayYellowCards1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[0]) +  match.homeYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[1]) +  match.awayYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[2]) + (match.homeYellowCards2T - match.awayYellowCards2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[3]) + (match.homeYellowCards2T + match.awayYellowCards2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[0]) +  match.homeThrowIns) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[1]) +  match.awayThrowIns) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[2]) + (match.homeThrowIns - match.awayThrowIns)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[3]) + (match.homeThrowIns + match.awayThrowIns)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[0]) +  match.homeThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[1]) +  match.awayThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[2]) + (match.homeThrowIns1T - match.awayThrowIns1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[3]) + (match.homeThrowIns1T + match.awayThrowIns1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[21].split("_")[0]) +  match.homeThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[21].split("_")[1]) +  match.awayThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[21].split("_")[2]) + (match.homeThrowIns2T - match.awayThrowIns2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[21].split("_")[3]) + (match.homeThrowIns2T + match.awayThrowIns2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[22].split("_")[0]) +  match.homeGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[22].split("_")[1]) +  match.awayGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[22].split("_")[2]) + (match.homeGoalKicks - match.awayGoalKicks)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[22].split("_")[3]) + (match.homeGoalKicks + match.awayGoalKicks)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[23].split("_")[0]) +  match.homeGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[23].split("_")[1]) +  match.awayGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[23].split("_")[2]) + (match.homeGoalKicks1T - match.awayGoalKicks1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[23].split("_")[3]) + (match.homeGoalKicks1T + match.awayGoalKicks1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[24].split("_")[0]) +  match.homeGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[24].split("_")[1]) +  match.awayGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[24].split("_")[2]) + (match.homeGoalKicks2T - match.awayGoalKicks2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[24].split("_")[3]) + (match.homeGoalKicks2T + match.awayGoalKicks2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[25].split("_")[0]) +  match.homeShots1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[25].split("_")[1]) +  match.awayShots1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[25].split("_")[2]) + (match.homeShots1T - match.awayShots1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[25].split("_")[3]) + (match.homeShots1T + match.awayShots1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[26].split("_")[0]) +  match.homeShots2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[26].split("_")[1]) +  match.awayShots2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[26].split("_")[2]) + (match.homeShots2T - match.awayShots2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[26].split("_")[3]) + (match.homeShots2T + match.awayShots2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[27].split("_")[0]) +  match.homeShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[27].split("_")[1]) +  match.awayShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[27].split("_")[2]) + (match.homeShotsOnTarget1T - match.awayShotsOnTarget1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[27].split("_")[3]) + (match.homeShotsOnTarget1T + match.awayShotsOnTarget1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[28].split("_")[0]) +  match.homeShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[28].split("_")[1]) +  match.awayShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[28].split("_")[2]) + (match.homeShotsOnTarget2T - match.awayShotsOnTarget2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[28].split("_")[3]) + (match.homeShotsOnTarget2T + match.awayShotsOnTarget2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[29].split("_")[0]) +  match.homeOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[29].split("_")[1]) +  match.awayOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[29].split("_")[2]) + (match.homeOffsides1T - match.awayOffsides1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[29].split("_")[3]) + (match.homeOffsides1T + match.awayOffsides1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[30].split("_")[0]) +  match.homeOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[30].split("_")[1]) +  match.awayOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[30].split("_")[2]) + (match.homeOffsides2T - match.awayOffsides2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[30].split("_")[3]) + (match.homeOffsides2T + match.awayOffsides2T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[31].split("_")[0]) +  match.homeFouls1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[31].split("_")[1]) +  match.awayFouls1T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[31].split("_")[2]) + (match.homeFouls1T - match.awayFouls1T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[31].split("_")[3]) + (match.homeFouls1T + match.awayFouls1T)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[32].split("_")[0]) +  match.homeFouls2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[32].split("_")[1]) +  match.awayFouls2T) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[32].split("_")[2]) + (match.homeFouls2T - match.awayFouls2T)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[32].split("_")[3]) + (match.homeFouls2T + match.awayFouls2T));

        String newRecordForAwayTeamInAwayTable = match.awayTeam + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[0]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[1]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[2]) + (match.awayScore - match.homeScore)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[3]) + (match.awayScore + match.homeScore)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[0]) +  match.awayScore1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[1]) +  match.homeScore1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[2]) + (match.awayScore1T - match.homeScore1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[3]) + (match.awayScore1T + match.homeScore1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[0]) +  match.awayScore2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[1]) +  match.homeScore2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[2]) + (match.awayScore2T - match.homeScore2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[3]) + (match.awayScore2T + match.homeScore2T)) + "*"
                + String.valueOf(Double.parseDouble(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[0]) +  match.awayXG) + "_"
                + String.valueOf(Double.parseDouble(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[1]) +  match.homeXG) + "_"
                + String.valueOf(Double.parseDouble(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[2]) + (match.awayXG - match.homeXG)) + "_"
                + String.valueOf(Double.parseDouble(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[3]) + (match.awayXG + match.homeXG)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[0]) +  match.awayBallPossession) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[1]) +  match.homeBallPossession) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[2]) + (match.awayBallPossession - match.homeBallPossession)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[3]) + (match.awayBallPossession + match.homeBallPossession)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[0]) +  match.awayShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[1]) +  match.homeShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[2]) + (match.awayShots - match.homeShots)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[3]) + (match.awayShots + match.homeShots)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[0]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[1]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[2]) + (match.awayShotsOnTarget - match.homeShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[3]) + (match.awayShotsOnTarget + match.homeShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[0]) +  match.awayShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[1]) +  match.homeShotsOffTarget) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[2]) + (match.awayShotsOffTarget - match.homeShotsOffTarget)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[3]) + (match.awayShotsOffTarget + match.homeShotsOffTarget)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[0]) +  match.awayCorners) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[1]) +  match.homeCorners) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[2]) + (match.awayCorners - match.homeCorners)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[3]) + (match.awayCorners + match.homeCorners)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[11].split("_")[0]) +  match.awayCorners1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[11].split("_")[1]) +  match.homeCorners1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[11].split("_")[2]) + (match.awayCorners1T - match.homeCorners1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[11].split("_")[3]) + (match.awayCorners1T + match.homeCorners1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[0]) +  match.awayCorners2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[1]) +  match.homeCorners2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[2]) + (match.awayCorners2T - match.homeCorners2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[3]) + (match.awayCorners2T + match.homeCorners2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[0]) +  match.awayOffsides) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[1]) +  match.homeOffsides) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[2]) + (match.awayOffsides - match.homeOffsides)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[3]) + (match.awayOffsides + match.homeOffsides)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[0]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[1]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[2]) + (match.awayBlockedShots - match.homeBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[3]) + (match.awayBlockedShots + match.homeBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[0]) +  match.awayFouls) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[1]) +  match.homeFouls) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[2]) + (match.awayFouls - match.homeFouls)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[3]) + (match.awayFouls + match.homeFouls)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[0]) +  match.awayYellowCards) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[1]) +  match.homeYellowCards) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[2]) + (match.awayYellowCards - match.homeYellowCards)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[3]) + (match.awayYellowCards + match.homeYellowCards)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[0]) +  match.awayYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[1]) +  match.homeYellowCards1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[2]) + (match.awayYellowCards1T - match.homeYellowCards1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[3]) + (match.awayYellowCards1T + match.homeYellowCards1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[0]) +  match.awayYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[1]) +  match.homeYellowCards2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[2]) + (match.awayYellowCards2T - match.homeYellowCards2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[3]) + (match.awayYellowCards2T + match.homeYellowCards2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[0]) +  match.awayThrowIns) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[1]) +  match.homeThrowIns) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[2]) + (match.awayThrowIns - match.homeThrowIns)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[3]) + (match.awayThrowIns + match.homeThrowIns)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[0]) +  match.awayThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[1]) +  match.homeThrowIns1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[2]) + (match.awayThrowIns1T - match.homeThrowIns1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[3]) + (match.awayThrowIns1T + match.homeThrowIns1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[21].split("_")[0]) +  match.awayThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[21].split("_")[1]) +  match.homeThrowIns2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[21].split("_")[2]) + (match.awayThrowIns2T - match.homeThrowIns2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[21].split("_")[3]) + (match.awayThrowIns2T + match.homeThrowIns2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[22].split("_")[0]) +  match.awayGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[22].split("_")[1]) +  match.homeGoalKicks) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[22].split("_")[2]) + (match.awayGoalKicks - match.homeGoalKicks)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[22].split("_")[3]) + (match.awayGoalKicks + match.homeGoalKicks)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[23].split("_")[0]) +  match.awayGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[23].split("_")[1]) +  match.homeGoalKicks1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[23].split("_")[2]) + (match.awayGoalKicks1T - match.homeGoalKicks1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[23].split("_")[3]) + (match.awayGoalKicks1T + match.homeGoalKicks1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[24].split("_")[0]) +  match.awayGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[24].split("_")[1]) +  match.homeGoalKicks2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[24].split("_")[2]) + (match.awayGoalKicks2T - match.homeGoalKicks2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[24].split("_")[3]) + (match.awayGoalKicks2T + match.homeGoalKicks2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[25].split("_")[0]) +  match.awayShots1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[25].split("_")[1]) +  match.homeShots1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[25].split("_")[2]) + (match.awayShots1T - match.homeShots1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[25].split("_")[3]) + (match.awayShots1T + match.homeShots1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[26].split("_")[0]) +  match.awayShots2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[26].split("_")[1]) +  match.homeShots2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[26].split("_")[2]) + (match.awayShots2T - match.homeShots2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[26].split("_")[3]) + (match.awayShots2T + match.homeShots2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[27].split("_")[0]) +  match.awayShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[27].split("_")[1]) +  match.homeShotsOnTarget1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[27].split("_")[2]) + (match.awayShotsOnTarget1T - match.homeShotsOnTarget1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[27].split("_")[3]) + (match.awayShotsOnTarget1T + match.homeShotsOnTarget1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[28].split("_")[0]) +  match.awayShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[28].split("_")[1]) +  match.homeShotsOnTarget2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[28].split("_")[2]) + (match.awayShotsOnTarget2T - match.homeShotsOnTarget2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[28].split("_")[3]) + (match.awayShotsOnTarget2T + match.homeShotsOnTarget2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[29].split("_")[0]) +  match.awayOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[29].split("_")[1]) +  match.homeOffsides1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[29].split("_")[2]) + (match.awayOffsides1T - match.homeOffsides1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[29].split("_")[3]) + (match.awayOffsides1T + match.homeOffsides1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[30].split("_")[0]) +  match.awayOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[30].split("_")[1]) +  match.homeOffsides2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[30].split("_")[2]) + (match.awayOffsides2T - match.homeOffsides2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[30].split("_")[3]) + (match.awayOffsides2T + match.homeOffsides2T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[31].split("_")[0]) +  match.awayFouls1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[31].split("_")[1]) +  match.homeFouls1T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[31].split("_")[2]) + (match.awayFouls1T - match.homeFouls1T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[31].split("_")[3]) + (match.awayFouls1T + match.homeFouls1T)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[32].split("_")[0]) +  match.awayFouls2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[32].split("_")[1]) +  match.homeFouls2T) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[32].split("_")[2]) + (match.awayFouls2T - match.homeFouls2T)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[32].split("_")[3]) + (match.awayFouls2T + match.homeFouls2T));

        homeStatsTable.set(htIndex, newRecordForHomeTeamInHomeTable);
        awayStatsTable.set(atIndex, newRecordForAwayTeamInAwayTable);

        pushLeagueToFile();
    }
}
