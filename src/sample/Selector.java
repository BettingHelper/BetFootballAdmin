package sample;

import java.util.ArrayList;

public class Selector {
    public String teamName;
    public ArrayList<Match> listOfMatches;
    public ArrayList<String> listOfMatchesForControl;
    public ArrayList<String> listForConfrontation;
    public ArrayList<ArrayList<String>> pList;
    public ArrayList<ArrayList<String>> refList;
    static double xG_of_Penalty = 0.75;
    static double xG_of_ownGoal = 0.3;
    public String[] params = {"Победы", "Ничьи", "Поражения", "Очки", "Голы", "xG", "Владение", "Удары", "Удары в створ",
            "Удары мимо", "Угловые", "Офсайды", "Блокировано ударов", "Фолы", "Желтые", "Красные", "Форма", "xGMatches", "xWins", "xDraws", "xLoses", "xPoints",
            "Голы 1 тайм", "Голы 2 тайм", "Голы 0-15", "Голы 16-30", "Голы 31-45'+", "Голы 46-60", "Голы 61-75", "Голы 76-90'+",
            "Владение 1 тайм", "Владение 2 тайм", "Угловые 1 тайм", "Угловые 2 тайм", "ЖК 1 тайм", "ЖК 2 тайм",
            "Удары 1 тайм", "Удары 2 тайм", "Удары в створ 1 тайм", "Удары в створ 2 тайм", "Удары мимо 1 тайм", "Удары мимо 2 тайм",
            "Блок.ударов 1 тайм", "Блок.ударов 2 тайм", "Офсайды 1 тайм", "Офсайды 2 тайм", "Фолы 1 тайм", "Фолы 2 тайм",
            "Дриблинг 1 тайм", "Дриблинг 2 тайм", "Возд.един. 1 тайм", "Возд.един. 2 тайм", "Сэйвы 1 тайм", "Сэйвы 2 тайм",
            "Выносы 1 тайм", "Выносы 2 тайм", "Перехваты 1 тайм", "Перехваты 2 тайм", "Отборы 1 тайм", "Отборы 2 тайм",
            "Точные передачи 1 тайм", "Точные передачи 2 тайм",  "Процент точности передач 1 тайм", "Процент точности передач 2 тайм",
            "Ключевые передачи 1 тайм", "Ключевые передачи 2 тайм", "Потери 1 тайм", "Потери 2 тайм", "Дриблинг", "Возд.един."};
    public String[] refParams = {"Желтые карточки (все)", "Желтые карточки 1 тайм ", "Желтые карточки 2 тайм ","ЖК --> КК", "Прямые КК",
            "Фолы", "Фолы 1 тайм", "Фолы 2 тайм", "Назначенные пенальти", "СКО по ЖК", "СКО по фолам",
            "Корреляция фолов и ЖК", "Корреляция форы фолов и  форы ЖК"};

    public Selector(){
        pList = new ArrayList<>();
        refList = new ArrayList<>();
        listOfMatches = new ArrayList<>();
        listOfMatchesForControl = new ArrayList<>();
        listForConfrontation = new ArrayList<>();
    }

    public void getListOfMatches(String leagueName, String teamName, String allOrHomeOrAway, String season, String lastOrFullSeason){
        this.teamName = teamName;
        if ((!leagueName.contains("Выберите"))&&(!teamName.contains("Выберите"))){

            Team team = Team.getTeamFromFileWithSeason(teamName, leagueName, season);

            if (lastOrFullSeason.contains("Последние")){
                int count = Integer.parseInt(lastOrFullSeason.split(" ")[1]);
                int index = team.matchList.size()-1;
                while (count>0&&index>=0){
                    if (allOrHomeOrAway.contains("Все")){
                        listOfMatches.add(0, Match.downloadMatch(season, leagueName, team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("Дома") && team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                        listOfMatches.add(0, Match.downloadMatch(season, leagueName, team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("На выезде") && team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                        listOfMatches.add(0, Match.downloadMatch(season, leagueName, team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    index --;
                }
            } else {
                if (allOrHomeOrAway.contains("Все")){
                    for (int index=0; index<team.matchList.size(); index++){
                        listOfMatches.add(Match.downloadMatch(season, leagueName, team.matchList.get(index)+ ".xml"));
                    }
                }
                if (allOrHomeOrAway.contains("Дома")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.downloadMatch(season, leagueName, team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
                if (allOrHomeOrAway.contains("На выезде")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.downloadMatch(season, leagueName, team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
            }
        }
    }

    public void getListOfMatchesForControl(String leagueName, String teamName, String allOrHomeOrAway, String season, String lastOrFullSeason){
        this.teamName = teamName;
        if ((!leagueName.contains("Выберите"))&&(!teamName.contains("Выберите"))){

            Team team = Team.downloadTeam(teamName, leagueName, season.replace("Сезон ", ""));

            if (lastOrFullSeason.contains("Последние")){
                int count = Integer.parseInt(lastOrFullSeason.split(" ")[1]);
                int index = team.matchList.size()-1;
                while (count>0&&index>=0){
                    if (allOrHomeOrAway.contains("Все")){
                        listOfMatchesForControl.add(0, team.matchList.get(index));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("Дома") && team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                        listOfMatchesForControl.add(0, team.matchList.get(index));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("На выезде") && team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                        listOfMatchesForControl.add(0, team.matchList.get(index));
                        count--;
                    }
                    index --;
                }
            } else {
                if (allOrHomeOrAway.contains("Все")){
                    for (int index=0; index<team.matchList.size(); index++){
                        listOfMatchesForControl.add(team.matchList.get(index));
                    }
                }
                if (allOrHomeOrAway.contains("Дома")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                            listOfMatchesForControl.add(team.matchList.get(index));
                        }
                    }
                }
                if (allOrHomeOrAway.contains("На выезде")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                            listOfMatchesForControl.add(team.matchList.get(index));
                        }
                    }
                }
            }
        }
    }

    public void getPList(ArrayList<Match> listOfMatches, String teamName){
        if (listOfMatches.size()>0){
            double[][] paramsD = new double[params.length][2];
            String forma = "";
            for (int i=0; i<listOfMatches.size(); i++){
                Match m = listOfMatches.get(i);
                if (teamName.equals(m.homeTeam)){
                    if (m.homeScore == m.awayScore){
                        paramsD[1][0] += 1;
                        paramsD[1][1] += 1;
                        paramsD[3][0] += 1;
                        paramsD[3][1] += 1;
                        forma = forma + "D";
                    }
                    else if (m.homeScore > m.awayScore){
                        paramsD[0][0] += 1;
                        paramsD[2][1] += 1;
                        paramsD[3][0] += 3;
                        forma = forma + "W";
                    }
                    else {
                        paramsD[2][0] += 1;
                        paramsD[0][1] += 1;
                        paramsD[3][1] += 3;
                        forma = forma + "L";
                    }

                    paramsD[4][0] += m.homeScore;
                    paramsD[4][1] += m.awayScore;
                    paramsD[5][0] += m.homeXG;
                    paramsD[5][1] += m.awayXG;
                    paramsD[6][0] += m.homeBallPossession;
                    paramsD[6][1] += m.awayBallPossession;
                    paramsD[7][0] += m.homeShots;
                    paramsD[7][1] += m.awayShots;
                    paramsD[8][0] += m.homeShotsOnTarget;
                    paramsD[8][1] += m.awayShotsOnTarget;
                    paramsD[9][0] += m.homeShotsOffTarget;
                    paramsD[9][1] += m.awayShotsOffTarget;
                    paramsD[10][0] += m.homeCorners;
                    paramsD[10][1] += m.awayCorners;
                    paramsD[11][0] += m.homeOffsides;
                    paramsD[11][1] += m.awayOffsides;
                    paramsD[12][0] += m.homeBlockedShots;
                    paramsD[12][1] += m.awayBlockedShots;
                    paramsD[13][0] += m.homeFouls;
                    paramsD[13][1] += m.awayFouls;
                    paramsD[14][0] += m.homeYellowCards;
                    paramsD[14][1] += m.awayYellowCards;
                    paramsD[15][0] += m.homeRedCards;
                    paramsD[15][1] += m.awayRedCards;

                    paramsD[22][0] += m.homeScore1T;
                    paramsD[22][1] += m.awayScore1T;
                    paramsD[23][0] += m.homeScore2T;
                    paramsD[23][1] += m.awayScore2T;
                    paramsD[24][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[0]);
                    paramsD[24][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[0]);
                    paramsD[25][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[1]);
                    paramsD[25][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[1]);
                    paramsD[26][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[2]);
                    paramsD[26][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[2]);
                    paramsD[27][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[3]);
                    paramsD[27][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[3]);
                    paramsD[28][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[4]);
                    paramsD[28][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[4]);
                    paramsD[29][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[5]);
                    paramsD[29][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[5]);

                    paramsD[30][0] += m.homeBallPossession1T;
                    paramsD[30][1] += m.awayBallPossession1T;
                    paramsD[31][0] += m.homeBallPossession2T;
                    paramsD[31][1] += m.awayBallPossession2T;
                    paramsD[32][0] += m.homeCorners1T;
                    paramsD[32][1] += m.awayCorners1T;
                    paramsD[33][0] += m.homeCorners2T;
                    paramsD[33][1] += m.awayCorners2T;
                    paramsD[34][0] += m.homeYellowCards1T;
                    paramsD[34][1] += m.awayYellowCards1T;
                    paramsD[35][0] += m.homeYellowCards2T;
                    paramsD[35][1] += m.awayYellowCards2T;
                    paramsD[36][0] += m.homeShots1T;
                    paramsD[36][1] += m.awayShots1T;
                    paramsD[37][0] += m.homeShots2T;
                    paramsD[37][1] += m.awayShots2T;
                    paramsD[38][0] += m.homeShotsOnTarget1T;
                    paramsD[38][1] += m.awayShotsOnTarget1T;
                    paramsD[39][0] += m.homeShotsOnTarget2T;
                    paramsD[39][1] += m.awayShotsOnTarget2T;
                    paramsD[40][0] += m.homeShotsOffTarget1T;
                    paramsD[40][1] += m.awayShotsOffTarget1T;
                    paramsD[41][0] += m.homeShotsOffTarget2T;
                    paramsD[41][1] += m.awayShotsOffTarget2T;
                    paramsD[42][0] += m.homeBlockedShots1T;
                    paramsD[42][1] += m.awayBlockedShots1T;
                    paramsD[43][0] += m.homeBlockedShots2T;
                    paramsD[43][1] += m.awayBlockedShots2T;
                    paramsD[44][0] += m.homeOffsides1T;
                    paramsD[44][1] += m.awayOffsides1T;
                    paramsD[45][0] += m.homeOffsides2T;
                    paramsD[45][1] += m.awayOffsides2T;
                    paramsD[46][0] += m.homeFouls1T;
                    paramsD[46][1] += m.awayFouls1T;
                    paramsD[47][0] += m.homeFouls2T;
                    paramsD[47][1] += m.awayFouls2T;
                    paramsD[48][0] += m.homeDribbles1T;
                    paramsD[48][1] += m.awayDribbles1T;
                    paramsD[49][0] += m.homeDribbles2T;
                    paramsD[49][1] += m.awayDribbles2T;
                    paramsD[50][0] += m.homeAerialsWon1T;
                    paramsD[50][1] += m.awayAerialsWon1T;
                    paramsD[51][0] += m.homeAerialsWon2T;
                    paramsD[51][1] += m.awayAerialsWon2T;
                    paramsD[52][0] += m.homeSaves1T;
                    paramsD[52][1] += m.awaySaves1T;
                    paramsD[53][0] += m.homeSaves2T;
                    paramsD[53][1] += m.awaySaves2T;
                    paramsD[54][0] += m.homeClearances1T;
                    paramsD[54][1] += m.awayClearances1T;
                    paramsD[55][0] += m.homeClearances2T;
                    paramsD[55][1] += m.awayClearances2T;
                    paramsD[56][0] += m.homeInterceptions1T;
                    paramsD[56][1] += m.awayInterceptions1T;
                    paramsD[57][0] += m.homeInterceptions2T;
                    paramsD[57][1] += m.awayInterceptions2T;
                    paramsD[58][0] += m.homeTackles1T;
                    paramsD[58][1] += m.awayTackles1T;
                    paramsD[59][0] += m.homeTackles2T;
                    paramsD[59][1] += m.awayTackles2T;
                    paramsD[60][0] += m.homePassesSuccessfully1T;
                    paramsD[60][1] += m.awayPassesSuccessfully1T;
                    paramsD[61][0] += m.homePassesSuccessfully2T;
                    paramsD[61][1] += m.awayPassesSuccessfully2T;
                    paramsD[62][0] += Team.roundResult(100 * m.homePassesSuccessfully1T / (double) m.homePasses , 0);
                    paramsD[62][1] += Team.roundResult(100 * m.awayPassesSuccessfully1T / (double) m.awayPasses , 0);
                    paramsD[63][0] += Team.roundResult(100 * m.homePassesSuccessfully2T / (double) m.homePasses , 0);
                    paramsD[63][1] += Team.roundResult(100 * m.awayPassesSuccessfully2T / (double) m.awayPasses , 0);
                    paramsD[64][0] += m.homeKeyPasses1T;
                    paramsD[64][1] += m.awayKeyPasses1T;
                    paramsD[65][0] += m.homeKeyPasses2T;
                    paramsD[65][1] += m.awayKeyPasses2T;
                    paramsD[66][0] += m.homeDispossessed1T;
                    paramsD[66][1] += m.awayDispossessed1T;
                    paramsD[67][0] += m.homeDispossessed2T;
                    paramsD[67][1] += m.awayDispossessed2T;
                    paramsD[68][0] += m.homeDribbles;
                    paramsD[68][1] += m.awayDribbles;
                    paramsD[69][0] += m.homeAerialsWon;
                    paramsD[69][1] += m.awayAerialsWon;

                    if (!((m.homeXG==0)&&(m.awayXG==0))){
                        paramsD[17][0] += 1;
                        double[] xWxDxL = Team.getXWinXDrawXLose(m.homeXG - m.awayXG);
                        paramsD[18][0] += xWxDxL[0];
                        paramsD[19][0] += xWxDxL[1];
                        paramsD[20][0] += xWxDxL[2];
                        paramsD[21][0] += Team.roundResult(3*xWxDxL[0] + xWxDxL[1],2);
                    }

                } else if (teamName.equals(m.awayTeam)){
                    if (m.awayScore == m.homeScore){
                        paramsD[1][0] += 1;
                        paramsD[1][1] += 1;
                        paramsD[3][0] += 1;
                        paramsD[3][1] += 1;
                        forma = forma + "D";
                    }
                    else if (m.awayScore > m.homeScore){
                        paramsD[0][0] += 1;
                        paramsD[2][1] += 1;
                        paramsD[3][0] += 3;
                        forma = forma + "W";
                    }
                    else {
                        paramsD[2][0] += 1;
                        paramsD[0][1] += 1;
                        paramsD[3][1] += 3;
                        forma = forma + "L";
                    }

                    paramsD[4][1] += m.homeScore;
                    paramsD[4][0] += m.awayScore;
                    paramsD[5][1] += m.homeXG;
                    paramsD[5][0] += m.awayXG;
                    paramsD[6][1] += m.homeBallPossession;
                    paramsD[6][0] += m.awayBallPossession;
                    paramsD[7][1] += m.homeShots;
                    paramsD[7][0] += m.awayShots;
                    paramsD[8][1] += m.homeShotsOnTarget;
                    paramsD[8][0] += m.awayShotsOnTarget;
                    paramsD[9][1] += m.homeShotsOffTarget;
                    paramsD[9][0] += m.awayShotsOffTarget;
                    paramsD[10][1] += m.homeCorners;
                    paramsD[10][0] += m.awayCorners;
                    paramsD[11][1] += m.homeOffsides;
                    paramsD[11][0] += m.awayOffsides;
                    paramsD[12][1] += m.homeBlockedShots;
                    paramsD[12][0] += m.awayBlockedShots;
                    paramsD[13][1] += m.homeFouls;
                    paramsD[13][0] += m.awayFouls;
                    paramsD[14][1] += m.homeYellowCards;
                    paramsD[14][0] += m.awayYellowCards;
                    paramsD[15][1] += m.homeRedCards;
                    paramsD[15][0] += m.awayRedCards;

                    paramsD[22][0] += m.awayScore1T;
                    paramsD[22][1] += m.homeScore1T;
                    paramsD[23][0] += m.awayScore2T;
                    paramsD[23][1] += m.homeScore2T;
                    paramsD[24][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[0]);
                    paramsD[24][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[0]);
                    paramsD[25][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[1]);
                    paramsD[25][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[1]);
                    paramsD[26][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[2]);
                    paramsD[26][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[2]);
                    paramsD[27][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[3]);
                    paramsD[27][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[3]);
                    paramsD[28][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[4]);
                    paramsD[28][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[4]);
                    paramsD[29][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[5]);
                    paramsD[29][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[5]);

                    paramsD[30][1] += m.homeBallPossession1T;
                    paramsD[30][0] += m.awayBallPossession1T;
                    paramsD[31][1] += m.homeBallPossession2T;
                    paramsD[31][0] += m.awayBallPossession2T;
                    paramsD[32][1] += m.homeCorners1T;
                    paramsD[32][0] += m.awayCorners1T;
                    paramsD[33][1] += m.homeCorners2T;
                    paramsD[33][0] += m.awayCorners2T;
                    paramsD[34][1] += m.homeYellowCards1T;
                    paramsD[34][0] += m.awayYellowCards1T;
                    paramsD[35][1] += m.homeYellowCards2T;
                    paramsD[35][0] += m.awayYellowCards2T;
                    paramsD[36][1] += m.homeShots1T;
                    paramsD[36][0] += m.awayShots1T;
                    paramsD[37][1] += m.homeShots2T;
                    paramsD[37][0] += m.awayShots2T;
                    paramsD[38][1] += m.homeShotsOnTarget1T;
                    paramsD[38][0] += m.awayShotsOnTarget1T;
                    paramsD[39][1] += m.homeShotsOnTarget2T;
                    paramsD[39][0] += m.awayShotsOnTarget2T;
                    paramsD[40][1] += m.homeShotsOffTarget1T;
                    paramsD[40][0] += m.awayShotsOffTarget1T;
                    paramsD[41][1] += m.homeShotsOffTarget2T;
                    paramsD[41][0] += m.awayShotsOffTarget2T;
                    paramsD[42][1] += m.homeBlockedShots1T;
                    paramsD[42][0] += m.awayBlockedShots1T;
                    paramsD[43][1] += m.homeBlockedShots2T;
                    paramsD[43][0] += m.awayBlockedShots2T;
                    paramsD[44][1] += m.homeOffsides1T;
                    paramsD[44][0] += m.awayOffsides1T;
                    paramsD[45][1] += m.homeOffsides2T;
                    paramsD[45][0] += m.awayOffsides2T;
                    paramsD[46][1] += m.homeFouls1T;
                    paramsD[46][0] += m.awayFouls1T;
                    paramsD[47][1] += m.homeFouls2T;
                    paramsD[47][0] += m.awayFouls2T;
                    paramsD[48][1] += m.homeDribbles1T;
                    paramsD[48][0] += m.awayDribbles1T;
                    paramsD[49][1] += m.homeDribbles2T;
                    paramsD[49][0] += m.awayDribbles2T;
                    paramsD[50][1] += m.homeAerialsWon1T;
                    paramsD[50][0] += m.awayAerialsWon1T;
                    paramsD[51][1] += m.homeAerialsWon2T;
                    paramsD[51][0] += m.awayAerialsWon2T;
                    paramsD[52][1] += m.homeSaves1T;
                    paramsD[52][0] += m.awaySaves1T;
                    paramsD[53][1] += m.homeSaves2T;
                    paramsD[53][0] += m.awaySaves2T;
                    paramsD[54][1] += m.homeClearances1T;
                    paramsD[54][0] += m.awayClearances1T;
                    paramsD[55][1] += m.homeClearances2T;
                    paramsD[55][0] += m.awayClearances2T;
                    paramsD[56][1] += m.homeInterceptions1T;
                    paramsD[56][0] += m.awayInterceptions1T;
                    paramsD[57][1] += m.homeInterceptions2T;
                    paramsD[57][0] += m.awayInterceptions2T;
                    paramsD[58][1] += m.homeTackles1T;
                    paramsD[58][0] += m.awayTackles1T;
                    paramsD[59][1] += m.homeTackles2T;
                    paramsD[59][0] += m.awayTackles2T;
                    paramsD[60][1] += m.homePassesSuccessfully1T;
                    paramsD[60][0] += m.awayPassesSuccessfully1T;
                    paramsD[61][1] += m.homePassesSuccessfully2T;
                    paramsD[61][0] += m.awayPassesSuccessfully2T;
                    paramsD[62][1] += Team.roundResult(100 * m.homePassesSuccessfully1T / (double) m.homePasses , 0);
                    paramsD[62][0] += Team.roundResult(100 * m.awayPassesSuccessfully1T / (double) m.awayPasses , 0);
                    paramsD[63][1] += Team.roundResult(100 * m.homePassesSuccessfully2T / (double) m.homePasses , 0);
                    paramsD[63][0] += Team.roundResult(100 * m.awayPassesSuccessfully2T / (double) m.awayPasses , 0);
                    paramsD[64][1] += m.homeKeyPasses1T;
                    paramsD[64][0] += m.awayKeyPasses1T;
                    paramsD[65][1] += m.homeKeyPasses2T;
                    paramsD[65][0] += m.awayKeyPasses2T;
                    paramsD[66][1] += m.homeDispossessed1T;
                    paramsD[66][0] += m.awayDispossessed1T;
                    paramsD[67][1] += m.homeDispossessed2T;
                    paramsD[67][0] += m.awayDispossessed2T;
                    paramsD[68][1] += m.homeDribbles;
                    paramsD[68][0] += m.awayDribbles;
                    paramsD[69][1] += m.homeAerialsWon;
                    paramsD[69][0] += m.awayAerialsWon;

                    if (!((m.homeXG==0)&&(m.awayXG==0))){
                        paramsD[17][0] += 1;
                        double[] xWxDxL = Team.getXWinXDrawXLose(m.awayXG - m.homeXG);
                        paramsD[18][0] += xWxDxL[0];
                        paramsD[19][0] += xWxDxL[1];
                        paramsD[20][0] += xWxDxL[2];
                        paramsD[21][0] += Team.roundResult(3*xWxDxL[0] + xWxDxL[1],2);
                    }
                }
            }
            for (int i=0; i<paramsD.length; i++){
                ArrayList<String> parametr = new ArrayList<>();
                if (i>3&&i<16) {
                    paramsD[i][0] = Team.roundResult(paramsD[i][0] / listOfMatches.size(), 2);
                    paramsD[i][1] = Team.roundResult(paramsD[i][1] / listOfMatches.size(), 2);
                }
                parametr.add(params[i]);
                parametr.add(String.valueOf(Team.roundResult(paramsD[i][0],2)));
                parametr.add(String.valueOf(Team.roundResult(paramsD[i][1],2)));
                pList.add(parametr);
            }
            ArrayList<String> f = new ArrayList<>();
            if (forma.length()>20){
                forma = forma.substring(forma.length()-20,forma.length());
            }
            f.add("Форма");
            f.add(forma);
            f.add(forma);
            pList.set(16,f);
        }
    }

    public void getRefListOfMatches(String leagueName, String refName, String season, String lastOrFullSeason){
        if ((!leagueName.contains("Выберите"))&&(!refName.contains("Выберите"))){

            String path = "database/" + season + "/" + leagueName + "/Matches/";
            Referee ref = Referee.getRefFromFile(refName, season, leagueName);

            if (lastOrFullSeason.contains("Последние")){
                int count = Integer.parseInt(lastOrFullSeason.split(" ")[1]);
                int index = ref.matchList.size()-1;
                while (count>0&&index>=0){
                    listOfMatches.add(0, Match.getMatchFromFileByName(path + ref.matchList.get(index)+ ".xml"));
                    count--;
                    index --;
                }
            } else {
                for (int index=0; index<ref.matchList.size(); index++)
                    listOfMatches.add(Match.getMatchFromFileByName(path + ref.matchList.get(index)+ ".xml"));
            }

            double[][] paramsD = new double[refParams.length][3];
            for (int i=0; i<listOfMatches.size(); i++){
                paramsD[0][0] += listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards;
                paramsD[0][1] += listOfMatches.get(i).homeYellowCards ;
                paramsD[0][2] += listOfMatches.get(i).awayYellowCards;

                paramsD[1][0] += listOfMatches.get(i).homeYellowCards1T + listOfMatches.get(i).awayYellowCards1T;
                paramsD[1][1] += listOfMatches.get(i).homeYellowCards1T ;
                paramsD[1][2] += listOfMatches.get(i).awayYellowCards1T;

                paramsD[2][0] += listOfMatches.get(i).homeYellowCards2T + listOfMatches.get(i).awayYellowCards2T;
                paramsD[2][1] += listOfMatches.get(i).homeYellowCards2T ;
                paramsD[2][2] += listOfMatches.get(i).awayYellowCards2T;

                paramsD[3][0] += listOfMatches.get(i).homeSecondYellowCards + listOfMatches.get(i).awaySecondYellowCards;
                paramsD[3][1] += listOfMatches.get(i).homeSecondYellowCards;
                paramsD[3][2] += listOfMatches.get(i).awaySecondYellowCards;

                paramsD[4][0] += listOfMatches.get(i).homeDirectRedCards + listOfMatches.get(i).awayDirectRedCards;
                paramsD[4][1] += listOfMatches.get(i).homeDirectRedCards;
                paramsD[4][2] += listOfMatches.get(i).awayDirectRedCards;

                paramsD[5][0] += listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls;
                paramsD[5][1] += listOfMatches.get(i).homeFouls;
                paramsD[5][2] += listOfMatches.get(i).awayFouls;

                paramsD[6][0] += listOfMatches.get(i).homeFouls1T + listOfMatches.get(i).awayFouls1T;
                paramsD[6][1] += listOfMatches.get(i).homeFouls1T;
                paramsD[6][2] += listOfMatches.get(i).awayFouls1T;

                paramsD[7][0] += listOfMatches.get(i).homeFouls2T + listOfMatches.get(i).awayFouls2T;
                paramsD[7][1] += listOfMatches.get(i).homeFouls2T;
                paramsD[7][2] += listOfMatches.get(i).awayFouls2T;

                paramsD[8][0] += listOfMatches.get(i).homePen + listOfMatches.get(i).awayPen;
                paramsD[8][1] += listOfMatches.get(i).homePen;
                paramsD[8][2] += listOfMatches.get(i).awayPen;
            }

            double dispF1 = 0;
            double dispF2 = 0;
            double dispF3 = 0;
            double dispYC1 = 0;
            double dispYC2 = 0;
            double dispYC3 = 0;
            for (int i=0; i<listOfMatches.size(); i++){
                dispF1 += Math.pow( (listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls - paramsD[5][0]/ (double) listOfMatches.size()) , 2);
                dispF2 += Math.pow( (listOfMatches.get(i).homeFouls - paramsD[5][1]/ (double) listOfMatches.size()) , 2);
                dispF3 += Math.pow( (listOfMatches.get(i).awayFouls - paramsD[5][2]/ (double) listOfMatches.size()) , 2);
                dispYC1 += Math.pow( (listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards - paramsD[0][0]/ (double) listOfMatches.size()) , 2);
                dispYC2 += Math.pow( (listOfMatches.get(i).homeYellowCards - paramsD[0][1]/ (double) listOfMatches.size()) , 2);
                dispYC3 += Math.pow( (listOfMatches.get(i).awayYellowCards - paramsD[0][2]/ (double) listOfMatches.size()) , 2);
            }

            paramsD[9][0] = Math.sqrt(dispYC1 / (double) listOfMatches.size());
            paramsD[9][1] = Math.sqrt(dispYC2 / (double) listOfMatches.size());
            paramsD[9][2] = Math.sqrt(dispYC3 / (double) listOfMatches.size());
            paramsD[10][0] = Math.sqrt(dispF1 / (double) listOfMatches.size());
            paramsD[10][1] = Math.sqrt(dispF2 / (double) listOfMatches.size());
            paramsD[10][2] = Math.sqrt(dispF3 / (double) listOfMatches.size());


            double MO_Fouls = paramsD[5][0]/ (double) listOfMatches.size();
            double MO_FoulsHT = paramsD[5][1]/ (double) listOfMatches.size();
            double MO_FoulsAT = paramsD[5][2]/ (double) listOfMatches.size();
            double MO_FoulsDiff = (paramsD[5][1] - paramsD[5][2]) / (double) listOfMatches.size();
            double MO_YC = paramsD[0][0]/ (double) listOfMatches.size();
            double MO_YCHT = paramsD[0][1]/(double) listOfMatches.size();
            double MO_YCAT = paramsD[0][2]/(double) listOfMatches.size();
            double MO_YCDiff = (paramsD[0][1] - paramsD[0][2]) / (double) listOfMatches.size();

            double verhSumm = 0;
            double nizSumm1 = 0;
            double nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls - MO_Fouls)*(listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards - MO_YC);
                nizSumm1 += Math.pow( listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls - MO_Fouls , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards - MO_YC , 2);
            }
            paramsD[11][0] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

            verhSumm = 0;
            nizSumm1 = 0;
            nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).homeFouls - MO_FoulsHT)*(listOfMatches.get(i).homeYellowCards - MO_YCHT);
                nizSumm1 += Math.pow( listOfMatches.get(i).homeFouls - MO_FoulsHT , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).homeYellowCards - MO_YCHT , 2);
            }
            paramsD[11][1] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

            verhSumm = 0;
            nizSumm1 = 0;
            nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).awayFouls - MO_FoulsAT)*(listOfMatches.get(i).awayYellowCards - MO_YCAT);
                nizSumm1 += Math.pow( listOfMatches.get(i).awayFouls - MO_FoulsAT , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).awayYellowCards - MO_YCAT , 2);
            }
            paramsD[11][2] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

            verhSumm = 0;
            nizSumm1 = 0;
            nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO_FoulsDiff)*(listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO_YCDiff);
                nizSumm1 += Math.pow( listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO_FoulsDiff , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO_YCDiff , 2);
            }
            paramsD[12][0] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);



            for (int i=0; i<paramsD.length; i++){
                ArrayList<String> parametr = new ArrayList<>();
                parametr.add(refParams[i]);
                parametr.add(String.valueOf(Team.roundResult(paramsD[i][0],2)));
                parametr.add(String.valueOf(Team.roundResult(paramsD[i][1],2)));
                parametr.add(String.valueOf(Team.roundResult(paramsD[i][2],2)));
                refList.add(parametr);
            }
        }
    }

}
