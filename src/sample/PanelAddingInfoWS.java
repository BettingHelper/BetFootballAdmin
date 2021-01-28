package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class PanelAddingInfoWS extends JPanel{
    public PanelAddingInfoWS(int width, int height){
        int defHeight = 1000;
        final Font font = new Font("Text", 0, 15);
        String curSeason;
        final String path = Settings.getPathToDatabase();
        this.setLayout(null);

        ////////////////////////////////////////////ОСНОВНАЯ СТАТИСТИКА (НАЧАЛО)
        JPanel addMainStatsPanel = new JPanel();
        addMainStatsPanel.setBorder(
                BorderFactory.createTitledBorder("Добавление основной статистики матча"));
        addMainStatsPanel.setSize((int) (0.5*width), (int) (0.93*height));
        addMainStatsPanel.setLocation(0, 10);
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea textArea = new JTextArea("URL с WhoScored.\nПрограмма автоматически заполнит данные, если их состав будет полным.");
        textArea.setMargin(new Insets(0,5,0,0));
        scrollPane.setSize((int) (0.4875*width), (int) (0.84*height-(defHeight-height)/10)  - 400);
        scrollPane.add(textArea);
        addMainStatsPanel.add(scrollPane);
        JButton buttonAddInfo = new JButton("Добавить!");
        buttonAddInfo.setPreferredSize(new Dimension((int) (0.4875*width), 400 + 45 - (defHeight-height)/40));
        Font fontForButton = new Font("Добавить!", 0, 25);
        buttonAddInfo.setFont(fontForButton);
        addMainStatsPanel.add(buttonAddInfo);
        this.add(addMainStatsPanel);
        ////////////////////////////////////////////ОСНОВНАЯ СТАТИСТИКА  (КОНЕЦ)

        /////////////////////////////////////////////xG - СТАТИСТИКА   (НАЧАЛО)
        JPanel addxGStatsPanel = new JPanel();
        addxGStatsPanel.setBorder(
                BorderFactory.createTitledBorder("Добавление xG-статистики матча"));
        addxGStatsPanel.setSize((int) (0.49375*width), (int) (0.43*height));
        addxGStatsPanel.setLocation((int) (0.5*width), 10);
        addxGStatsPanel.setLayout(null);

        JFileChooser fileChooser = new JFileChooser(path + Settings.getCurrentSeason() + "/leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> leagueList = new ArrayList<>();
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        Collections.sort(leagueList);

        JPanel panelSelectLeague = new JPanel();
        panelSelectLeague.setBorder(
                BorderFactory.createTitledBorder("Выберите сезон и чемпионат"));
        panelSelectLeague.setSize((int) (0.47125*width), 70);
        panelSelectLeague.setLocation(10, 20);

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        final JComboBox<String> seasonCB = new JComboBox<>(seasonList);
        seasonCB.setPreferredSize(new Dimension(200, 40));
        panelSelectLeague.add(seasonCB);

        curSeason = seasonCB.getSelectedItem().toString().replace("Сезон ", "");

        String[] listOfLeagues = new String[leagueList.size()];
        for (int i=0; i<listOfLeagues.length;i++)
            listOfLeagues[i] = leagueList.get(i);
        final JComboBox<String> leagueChooser = new JComboBox<>(listOfLeagues);
        leagueChooser.setPreferredSize(new Dimension(200, 40));
        panelSelectLeague.add(leagueChooser);
        String fileName = path + curSeason + "/leagues/" + leagueChooser.getSelectedItem() +".txt";
        String[] list = Main.readTxtFile(fileName);
        addxGStatsPanel.add(panelSelectLeague);

        /////////////////////////////////////////////Хозяева (начало)
        JPanel homeTeamPanel = new JPanel();
        homeTeamPanel.setBorder(
                BorderFactory.createTitledBorder("Команда хозяев"));
        homeTeamPanel.setSize((int) (0.240625*width), 170);
        homeTeamPanel.setLocation(10, 90);
        final JComboBox<String> homeTeamChooser = new JComboBox<>(list);
        homeTeamChooser.setPreferredSize(new Dimension(200, 40));
        homeTeamChooser.setLocation((int) (0.0625*width), 100);
        homeTeamPanel.add(homeTeamChooser);
        final JTextField editHomeXG = new JTextField("",15);

        editHomeXG.setFont(font);
        editHomeXG.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        editHomeXG.setText("xG хозяев");
        homeTeamPanel.add(editHomeXG);

        addxGStatsPanel.add(homeTeamPanel);
        /////////////////////////////////////////////Хозяева (конец)

        /////////////////////////////////////////////Гости (начало)
        JPanel awayTeamPanel = new JPanel();
        awayTeamPanel.setBorder(
                BorderFactory.createTitledBorder("Команда гостей"));
        awayTeamPanel.setSize((int) (0.240625*width), 170);
        awayTeamPanel.setLocation((int) (0.246875*width), 90);
        final JComboBox<String> awayTeamChooser = new JComboBox<>(list);
        awayTeamChooser.setPreferredSize(new Dimension(200, 40));
        awayTeamChooser.setLocation((int) (0.0625 * width), 100);
        awayTeamPanel.add(awayTeamChooser);
        final JTextField editAwayXG = new JTextField("",15);
        editAwayXG.setFont(font);
        editAwayXG.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        editAwayXG.setText("xG гостей");
        awayTeamPanel.add(editAwayXG);

        addxGStatsPanel.add(awayTeamPanel);
        /////////////////////////////////////////////Гости (конец)

        final JButton buttonAddInfoXG = new JButton("Добавить!");
        buttonAddInfoXG.setFont(fontForButton);
        buttonAddInfoXG.setSize((int) (0.4765 * width), 45 - (defHeight-height)/40);
        buttonAddInfoXG.setLocation(12, 265);
        addxGStatsPanel.add(buttonAddInfoXG);

        this.add(addxGStatsPanel);
        /////////////////////////////////////////////-------------------xG - СТАТИСТИКА

        JPanel leaguesPanel = new JPanel();
        leaguesPanel.setBorder(
                BorderFactory.createTitledBorder("Данные по лигам"));
        leaguesPanel.setSize((int) (0.49375 * width), (int) (0.33 * height));
        leaguesPanel.setLocation((int) (0.5 * width), (int) (0.53 * height) + 10);
        leaguesPanel.setLayout(new VerticalLayout());

        final JButton buttonRewriteLeagues = new JButton("Обработать!");
        buttonRewriteLeagues.setFont(fontForButton);
        leaguesPanel.add(buttonRewriteLeagues);

        ScrollPane scrollPaneByLeagues = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea textAreaByLeagues = new JTextArea("");
        textAreaByLeagues.setMargin(new Insets(0, 5, 0, 0));
        scrollPaneByLeagues.setPreferredSize(new Dimension((int) (0.4875 * width), (int) (0.84 * height - (defHeight - height) / 10) - 400));
        scrollPaneByLeagues.add(textAreaByLeagues);
        leaguesPanel.add(scrollPaneByLeagues);

        this.add(leaguesPanel);

        seasonCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seasonCB.setFocusable(false);
                String league = String.valueOf(leagueChooser.getSelectedItem());

                String pathToLeaguesList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser leftFileChooser = new JFileChooser(pathToLeaguesList);
                String[] leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length+1];
                leftDirectoryList[0] = "Выберите лигу";
                for (int i=1; i<leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(leftDirectoryList);
                leagueChooser.setModel(modelH);

                for (int i=0; i<leftDirectoryList.length; i++){
                    if (leftDirectoryList[i].equals(league))
                        leagueChooser.setSelectedItem(league);
                }


                /*String pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listLeft = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listLeft = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listLeft);
                leftTeamChooser.setModel(modelH);
                leftTeamChooser.setEnabled(false);

                pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Teams/";
                leftFileChooser = new JFileChooser(pathToTeamsList);
                leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length+1];
                leftDirectoryList[0] = "Выберите команду";
                for (int i=1; i<leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i-1].replace(".xml", "");
                for (int i=0; i<leftDirectoryList.length; i++){
                    if (leftDirectoryList[i].equals(team)){
                        leftTeamChooser.setSelectedItem(team);
                        leftTeamChooser.setEnabled(true);
                    }
                }*/
                leagueChooser.setFocusable(true);
            }
        });



        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textArea.setText("");
                Font font10 = new Font("", 0, 12);
                textArea.setFont(font10);
                textArea.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        editHomeXG.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editHomeXG.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        editAwayXG.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editAwayXG.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        /*editAwayPen.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editAwayPen.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        editAwayOG.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editAwayOG.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });*/
        buttonAddInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";

//                Match match = new Match(textArea.getText());

                String fromBuffer = "";
                try {
                    fromBuffer = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException e1) {
                    e1.printStackTrace();
                    textArea.setText(e1.toString());
                } catch (IOException e2) {
                    e2.printStackTrace();
                    textArea.setText(e2.toString());
                }
                Match match = new Match(fromBuffer);

                if (match.isAllData()){
                    Team ht = Team.getTeamFromFileWithSeason(match.homeTeam, match.league, match.season);
                    result = result + ht.addMatch(match);
                    Team at = Team.getTeamFromFileWithSeason(match.awayTeam, match.league, match.season);
                    result = result + at.addMatch(match);
                    if (!result.contains("Повтор")){
                        match.pushMatchToFile(true);      ////////////////////////////////////////////////////////
                        Referee ref = Referee.getRefFromFile(match.referee, match.season, match.league);
                        ref.addMatchToRefStats(match);

                        textArea.setText("Матч " + match.title + "\n обработан успешно!");
                        Font fontForText = new Font("", 0, 25);
                        textArea.setFont(fontForText);
                        textArea.setForeground(Color.black);
                        LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + match.title + ");");
                    }
                    if (result.contains("Повтор")){
                        Font fontForText = new Font("", 0, 25);
                        textArea.setFont(fontForText);
                        textArea.setText("Этот матч уже был добавлен в базу ранее!");
                        textArea.setForeground(Color.red);
                    }
                } else {
                    PopupWindow w = new PopupWindow("Ошибка в данных матча " + match.title);
                    w.setVisible(true);
                }

            }
        });

        buttonAddInfoXG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean flagCorrectData = true;
                Boolean flagMatch = false;
                Boolean flagAlreadyAdded = false;
                String directoryName = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + leagueChooser.getSelectedItem() + "/Matches";
                String homeTeamName = (String) homeTeamChooser.getSelectedItem();
                String awayTeamName = (String) awayTeamChooser.getSelectedItem();
                Double homeXG = 0.0;
                Double awayXG = 0.0;

                if ((homeTeamName.equals("Выберите команду")) || (awayTeamName.equals("Выберите команду")) || homeTeamName.equals(awayTeamName))
                    flagCorrectData = false;
                try {
                    homeXG = Double.valueOf(editHomeXG.getText());
                    awayXG = Double.valueOf(editAwayXG.getText());
                } catch (Exception exception) {
                    flagCorrectData = false;
                }
                if (flagCorrectData) {
                    String matchName = Team.getShortName(homeTeamName) + Team.getShortName(awayTeamName);
                    JFileChooser fileChooser = new JFileChooser(directoryName);
                    boolean flag = false;
                    for (int i = 0; i < fileChooser.getCurrentDirectory().list().length; i++) {
                        if (fileChooser.getCurrentDirectory().list()[i].contains(matchName) && !flag) {
                            flagMatch = true;
                            Match match = Match.getMatchFromFileByName(fileChooser.getCurrentDirectory() + "/" + fileChooser.getCurrentDirectory().list()[i]);
                            if ((match.homeXG == 0) && (match.awayXG == 0)) {
                                match.homeXG = homeXG;
                                match.awayXG = awayXG;
                                match.pushMatchToFile(false);
                                //String path1 = "database/" + curSeason + "/" + Team.getLeague(homeTeamName) + "/Matches/" + fileChooser.getCurrentDirectory().list()[i] + ".xml";
                                //LogWriter.writeUpdates("download=" + path1);
                                flag = true;
                            } else flagAlreadyAdded = true;
                        }
                    }
                }

                if (!flagMatch) {
                    buttonAddInfoXG.setText("По данному матчу нет основной статистики!");
                    buttonAddInfoXG.setForeground(Color.red);
                }
                if (!flagCorrectData) {
                    buttonAddInfoXG.setText("Ошибка в данных!");
                    buttonAddInfoXG.setForeground(Color.red);
                }
                if (flagCorrectData && flagMatch && !flagAlreadyAdded) {
                    buttonAddInfoXG.setText("Данные успешно добавлены!");
                    buttonAddInfoXG.setForeground(new Color(0, 230, 46));
                    String matchTitle = Team.getShortName(homeTeamName) + Team.getShortName(awayTeamName);
                    LogWriter.writeLog("Added match xG-statistics - " + homeTeamName + " vs " + awayTeamName + "   (" + matchTitle + ");");
                }
                if (flagAlreadyAdded) {
                    buttonAddInfoXG.setText("Этот матч уже есть в базе!");
                    buttonAddInfoXG.setForeground(Color.red);
                }
                editHomeXG.setText("xG хозяев");
                editAwayXG.setText("xG гостей");
            }
        });

        leagueChooser.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String fileName = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() +".txt";
                buttonAddInfoXG.setText("Добавить!");
                buttonAddInfoXG.setForeground(Color.black);
                String[] list = Main.readTxtFile(fileName);
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(list);
                homeTeamChooser.setModel(modelH);
                DefaultComboBoxModel modelA = new DefaultComboBoxModel(list);
                awayTeamChooser.setModel(modelA);
            }
        });

        homeTeamChooser.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buttonAddInfoXG.setText("Добавить!");
                buttonAddInfoXG.setForeground(Color.black);
                editHomeXG.setText("xG хозяев");

            }
        });

        awayTeamChooser.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buttonAddInfoXG.setText("Добавить!");
                buttonAddInfoXG.setForeground(Color.black);
                editAwayXG.setText("xG гостей");

            }
        });

        buttonRewriteLeagues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathToLeaguesInfo = Settings.getPathToDatabase().replace("database", "leaguesInfo");
                String[] list = new JFileChooser(pathToLeaguesInfo).getCurrentDirectory().list();
                String resultOfFilling;
//                String seasonToRewrite1 = Settings.getCurrentSeason();
                String seasonToRewrite1 = "20-21";
//                Calendar c = Calendar.getInstance();
//                String seasonToRewrite2 = String.valueOf(c.get(Calendar.YEAR));
                String seasonToRewrite2 = "20";

                for (int i=0; i<list.length; i++){
                    if (list[i].contains("_" + seasonToRewrite1) || list[i].contains("_" + seasonToRewrite2 + ".")){
                        League league = League.getLeagueFromFileByName(pathToLeaguesInfo + list[i]);
                        resultOfFilling = league.fillDataFromAddingList();
                        textAreaByLeagues.setText( textAreaByLeagues.getText() + resultOfFilling);
                    }

                }
            }
        });


    }

    public static int findIndexOfParameter(String parameter, String[] stats){
        int result = -1;

        for (int i = 0; i< stats.length; i++){
            if (stats[i].equals(parameter))
                result = i;
        }
        return  result;
    }


}

