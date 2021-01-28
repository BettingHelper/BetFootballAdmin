package sample;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PanelSettings extends JPanel{
    int WIDTH;
    int DEFWIDTH = 1600;
    double procWIDTH;
    int HEIGHT;
    int DEFHEIGHT = 1000;
    double procHEIGHT;
    Settings settings;
    JPanel container;
    final String path = Settings.getPathToDatabase();

    public PanelSettings(int width, int height){
        int defHeight = 1000;
        WIDTH = width;
        HEIGHT = height;
        procWIDTH = WIDTH / (double) DEFWIDTH;
        procHEIGHT = HEIGHT / (double) DEFHEIGHT;
        String curSeason = Settings.getCurrentSeason();

        this.setLayout(null);

        settings = Settings.getSettingsFromFile();

        final JPanel redactorPanel = new JPanel();
        redactorPanel.setBorder(
                BorderFactory.createTitledBorder("Редактирование матча"));
        redactorPanel.setLocation(245, 5);
        redactorPanel.setSize(width - 240 - 15, 670);
        redactorPanel.setLayout(null);

        final Font font = new Font("Text", 0, 15);

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        final JComboBox<String> seasonCB = new JComboBox<>(seasonList);
        seasonCB.setSize(new Dimension((int) (130 * procWIDTH), 30));
        seasonCB.setLocation(8, 20);
        redactorPanel.add(seasonCB);

        JFileChooser fileChooser = new JFileChooser(path + curSeason + "/" + "leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> leagueList = new ArrayList<>();
        leagueList.add("Выберите лигу");
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        String[] listOfLeagues = new String[leagueList.size()];
        for (int i = 0; i < listOfLeagues.length; i++)
            listOfLeagues[i] = leagueList.get(i);
        final JComboBox<String> leagueChooser = new JComboBox<>(listOfLeagues);
        leagueChooser.setSize(new Dimension((int) (150 * procWIDTH), 30));
        leagueChooser.setLocation((int) (145 * procWIDTH), 20);
        redactorPanel.add(leagueChooser);

        String fileNameHome = path + curSeason + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] listHome = {"Хозяева"};
        if (!fileNameHome.contains("ыберите")) {
            listHome = Main.readTxtFile(fileNameHome);
        }
        final JComboBox<String> teamChooserHome = new JComboBox<>(listHome);
        teamChooserHome.setSize(new Dimension((int) (170 * procWIDTH), 30));
        teamChooserHome.setEnabled(false);
        teamChooserHome.setLocation((int) (300 * procWIDTH), 20);
        redactorPanel.add(teamChooserHome);

        String fileNameAway = path + curSeason + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] listAway = {"Гости"};
        if (!fileNameAway.contains("ыберите")) {
            listAway = Main.readTxtFile(fileNameAway);
        }
        final JComboBox<String> teamChooserAway = new JComboBox<>(listAway);
        teamChooserAway.setSize(new Dimension((int) (170 * procWIDTH), 30));
        teamChooserAway.setEnabled(false);
        teamChooserAway.setLocation((int) (475 * procWIDTH), 20);
        redactorPanel.add(teamChooserAway);

        final JButton buttonEditMatch = new JButton("Редактировать!");
        buttonEditMatch.setSize(new Dimension((int) (200 * procWIDTH), 30));
        buttonEditMatch.setLocation((int) (650 * procWIDTH), 20);
        Font fontForButton = new Font("", 0, 25);
        buttonEditMatch.setFont(fontForButton);
        redactorPanel.add(buttonEditMatch);

        final JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder(""));
        container.setLayout(null);
        container.setSize(width - 240 - 25, 610);
        container.setLocation(5, 55);

        redactorPanel.add(container);

        //this.add(redactorPanel); //ПАНЕЛЬ РЕДАКТИРОВАНИЯ МАТЧА ЗДЕСЬ ДОБАВЛЯЕТСЯ НА ФОРМУ

        JPanel databasePathPanel = new JPanel();
        databasePathPanel.setBorder(
                BorderFactory.createTitledBorder("Путь к базе данных"));
        databasePathPanel.setSize((int) (0.5 * width), (int) (0.5 * height));
        databasePathPanel.setLocation(0, 0);
        databasePathPanel.setLayout(null);

        final JFileChooser directoryChooser = new JFileChooser(Settings.getPathToDatabase());
        directoryChooser.setSize(600, 300);
        directoryChooser.setLocation(5, 5);
        databasePathPanel.add(directoryChooser);

        JButton buttonSetPath = new JButton("Сохранить");
        buttonSetPath.setSize(new Dimension((int) (0.4875 * width), 45 - (defHeight - height) / 40));
        buttonSetPath.setLocation(5, 310);
        buttonSetPath.setFont(fontForButton);

        databasePathPanel.add(buttonSetPath);
        this.add(databasePathPanel);

        /*final JButton buttonZipDatabase = new JButton("Архивировать базу");
        buttonZipDatabase.setSize(new Dimension((int) (0.4875 * width), 45 - (defHeight - height) / 40));
        buttonZipDatabase.setLocation(5, 400);
        buttonZipDatabase.setFont(fontForButton);
        this.add(buttonZipDatabase);*/


        final JPanel addRefPanel = new JPanel();
        addRefPanel.setBorder(
                BorderFactory.createTitledBorder("Добавить судью"));
        addRefPanel.setLocation((int) (0.5 * width), 0);
        addRefPanel.setSize((int) (0.5 * width - 5), 60);
        addRefPanel.setLayout(null);

        JFileChooser fileChooserForRef = new JFileChooser(path + curSeason + "/" + "leagues");
        String[] directoryListForRef = fileChooserForRef.getCurrentDirectory().list();
        ArrayList<String> leagueListForRef = new ArrayList<>();
        leagueListForRef.add("Выберите лигу");
        for (String aDirectoryList : directoryList) leagueListForRef.add(aDirectoryList.replace(".txt", ""));
        String[] listOfLeaguesForRef = new String[leagueListForRef.size()];
        for (int i = 0; i < listOfLeaguesForRef.length; i++)
            listOfLeaguesForRef[i] = leagueListForRef.get(i);
        final JComboBox<String> leagueRefChooser = new JComboBox<>(listOfLeaguesForRef);
        leagueRefChooser.setSize(new Dimension((int) (150 * procWIDTH), 30));
        leagueRefChooser.setLocation(5 , 25);
        addRefPanel.add(leagueRefChooser);

        final JTextField refSurname = new JTextField("Фамилия (название файла)");
        refSurname.setFont(font);
        refSurname.setSize(150, 30);
        refSurname.setLocation((int) (160 * procWIDTH), 25);
        addRefPanel.add(refSurname);

        final JTextField refName = new JTextField("ФИ судьи на Whoscored");
        refName.setFont(font);
        refName.setSize(150, 30);
        refName.setLocation((int) (345 * procWIDTH), 25);
        addRefPanel.add(refName);

        JButton buttonAddRef = new JButton("Добавить арбитра!");
        buttonAddRef.setSize(150, 30);
        buttonAddRef.setLocation(500, 25);
        addRefPanel.add(buttonAddRef);

        JPanel optimizePanel = new JPanel();
        optimizePanel.setBorder(
                BorderFactory.createTitledBorder("Оптимизация обновлений"));
        optimizePanel.setSize((int) (0.5 * width), (int) (0.7 * height));
        optimizePanel.setLocation((int) (0.5 * width), 70);
        optimizePanel.setLayout(null);

        final JFileChooser optimizeDirectoryChooser = new JFileChooser(Settings.getPathToDatabase() + "/updates");
        optimizeDirectoryChooser.setSize(600, 300);
        optimizeDirectoryChooser.setLocation(5, 5);
        optimizePanel.add(optimizeDirectoryChooser);

        final JButton buttonOptimize = new JButton("Оптимизировать");
        buttonOptimize.setSize(new Dimension((int) (0.4875 * width), 45 - (defHeight - height) / 40));
        buttonOptimize.setLocation(5, 310);
        buttonOptimize.setFont(fontForButton);

        optimizePanel.add(buttonOptimize);

        this.add(optimizePanel);

        this.add(addRefPanel);

        buttonAddRef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = !leagueRefChooser.getSelectedItem().toString().contains("Выберите") &&
                        (!refName.getText().equals("")) && (!refName.getText().contains("ФИ")) &&
                        (!refSurname.getText().equals("")) && (!refSurname.getText().contains("Фамилия"));
                if (flag){
                    Referee.addRefToDatabase(leagueRefChooser.getSelectedItem().toString(), refSurname.getText(), refName.getText());
                }
            }
        });

        buttonOptimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.optimizeUpdates(String.valueOf(optimizeDirectoryChooser.getSelectedFile()));
                String fileName = optimizeDirectoryChooser.getSelectedFile().getName();
                try {
                    FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/database/updates/"+fileName, String.valueOf(optimizeDirectoryChooser.getSelectedFile()));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                buttonOptimize.setText("Успешно");
                buttonOptimize.setForeground(new Color(0, 230, 46));
            }
        });

        refSurname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                refSurname.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        refName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                refName.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        seasonCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seasonCB.setFocusable(false);
                leagueChooser.setSelectedIndex(0);
                teamChooserHome.setSelectedIndex(0);
                teamChooserHome.setEnabled(false);
                teamChooserAway.setEnabled(false);
                leagueChooser.setFocusable(true);
            }
        });

        leagueChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = leagueChooser.getSelectedIndex();
                String pathToLeaguesList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + "leagues/";
                JFileChooser leftFileChooser = new JFileChooser(pathToLeaguesList);
                String[] leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length+1];
                leftDirectoryList[0] = "Выберите лигу";
                for (int i=1; i<leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(leftDirectoryList);
                leagueChooser.setModel(modelH);
                leagueChooser.setSelectedIndex(index);
                leagueChooser.setFocusable(false);

                teamChooserHome.setEnabled(true);
                teamChooserAway.setEnabled(true);
                String pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listHome = {"Хозяева"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listHome = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listHome);
                teamChooserHome.setModel(modelH);

                teamChooserAway.setEnabled(true);
                pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listAway = {"Гости"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listAway = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listAway);
                teamChooserAway.setModel(modelH);
            }
        });

        teamChooserHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooserHome.setFocusable(false);
            }
        });

        teamChooserAway.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooserAway.setFocusable(false);
            }
        });

        buttonEditMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (container != null) {
                    redactorPanel.remove(container);
                }
                JScrollPane jsp = refreshData((String) leagueChooser.getSelectedItem(),
                        (String) teamChooserHome.getSelectedItem(),
                        (String) teamChooserAway.getSelectedItem(),
                        (String) seasonCB.getSelectedItem()
                );
                container.add(jsp);
                redactorPanel.add(container);
                redactorPanel.revalidate();
                buttonEditMatch.setFocusable(false);
            }
        });

        buttonSetPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = directoryChooser.getCurrentDirectory().toString();
                if (path.contains("database")){
                    path = path + "/";
                } else{
                    String[] list = directoryChooser.getCurrentDirectory().list();
                    for (int i = 0; i<list.length; i++){
                        if (list[i].equals("database")){
                            path = path + "/database/";
                        }
                    }
                }
                Settings.setPathToDatabase(path);
            }
        });

        /*buttonZipDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.compress("database");
                try {
                    FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/databaseCopies/databaseFBH.zip", "database.zip");
                    Settings.setLastUpdate();

                    FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/databaseCopies/lastUpdateFBH.txt", "tmp/lastUpdateFBH.txt");
                    buttonZipDatabase.setText("База скопирована успешно!");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });*/
    }

    public JScrollPane refreshData(String leagueName, final String homeTeam, final String awayTeam, String season){
        JScrollPane scrollPane;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(new Dimension((int) (0.48 * WIDTH), 730));
        panel.setLocation(50, 100);
        season = season.replace("Сезон ", "");
        if ((!leagueName.contains("Выберите"))&&(!homeTeam.contains("Хозяева"))&&(!awayTeam.contains("Гости"))){
            JFileChooser jfc = new JFileChooser(path + season + "/" + Team.getLeague(homeTeam) + "/Matches/");
            String[] list = jfc.getCurrentDirectory().list();
            Match match = null;
            for (int i=0; i<list.length;i++)
                if (list[i].equals(Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + ".xml")){
                    match = Match.getMatchFromFileByName(path + season + "/" + Team.getLeague(homeTeam) + "/Matches/" + Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + ".xml");
                }

            if (match != null){
                String[] colHeads = {homeTeam, "Параметр", awayTeam};
                final String[] params = {"Голы",  "Владение", "Ударов всего", "Удары в створ", "Угловые", "Офсайды", "Фолы",
                        "Желтые карточки", "ЖК --> КК", "Прямые КК", "xG", "Проб. пенальти", "Автоголы", };
                Object[][] data = {
                        {match.homeScore, params[0], match.awayScore},
                        {match.homeBallPossession, params[1], match.awayBallPossession},
                        {match.homeShots, params[2], match.awayShots},
                        {match.homeShotsOnTarget, params[3], match.awayShotsOnTarget},
                        {match.homeCorners, params[4], match.awayCorners},
                        {match.homeOffsides, params[5], match.awayOffsides},
                        {match.homeFouls, params[6], match.awayFouls},
                        {match.homeYellowCards, params[7], match.awayYellowCards},
                        {match.homeSecondYellowCards, params[8], match.awaySecondYellowCards},
                        {match.homeDirectRedCards, params[9], match.awayDirectRedCards},
                        {match.homeXG, params[10], match.awayXG},
                        {match.homePen, params[11], match.awayPen},
                        {match.homeOGScored, params[12], match.awayOGScored}
                };

                JTable table = new JTable(data, colHeads);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.isCellEditable(1,1);
                Font font = new Font("Arial", Font.BOLD, 15);
                table.getTableHeader().setFont(font);
                table.setFont(font);
                table.getColumnModel().getColumn(0).setPreferredWidth(150);
                table.getColumnModel().getColumn(1).setPreferredWidth(150);
                table.getColumnModel().getColumn(2).setPreferredWidth(150);
                table.setRowHeight(25);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                JPanel tablePanel = new JPanel();
                tablePanel.setLayout(new BorderLayout());
                tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
                tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

                tablePanel.setSize(453, 350);
                tablePanel.setLocation(5, 5);
                panel.add(tablePanel);

                final JLabel labelRef = new JLabel("Арбитр матча - ");
                labelRef.setLocation(10, 360);
                labelRef.setSize(150, 30);
                labelRef.setFont(font);
                panel.add(labelRef);

                String fileRefName = path + leagueName +"/Referees/Referees.txt";
                String[] listForCB = Main.readTxtFileForRef(fileRefName);
                final JComboBox<String> refChooser = new JComboBox<>(listForCB);
                refChooser.setSize(new Dimension((int) (150 * procWIDTH), 30));
                refChooser.setLocation((int) (150*procWIDTH), 360);
                String resfList = path + season + "/" + leagueName + "/Referees/Referees.txt";
                String[] listRight = {"Выберите судью"};
                if (!resfList.contains("ыберите")) {
                    listRight = Main.readTxtFileForRef(resfList);
                }
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(listRight);
                refChooser.setModel(modelH);
                int indexOfRef = 0;
                for (int i=1; i<listRight.length; i++){
                    if (listRight[i].equals(match.referee))
                        indexOfRef = i;
                }
                refChooser.setSelectedIndex(indexOfRef);
                panel.add(refChooser);

            } else {
                String labelText = "Матч не найден!";
                final JLabel label = new JLabel(labelText);
                label.setLocation(10, 0);
                label.setSize(new Dimension((int) (0.995 * WIDTH) - 30, 25));
                Font fontLabel = new Font("Arial", Font.BOLD, 15);
                label.setFont(fontLabel);
                panel.add(label);
            }
        } else {
            final JLabel label = new JLabel("Задайте условия все условия поиска. Лига и/или команды не заданы.");
            label.setLocation(10, 0);
            label.setSize(new Dimension((int) (0.995 * WIDTH) - 30, 25));
            Font fontLabel = new Font("Arial", Font.BOLD, 15);
            label.setFont(fontLabel);
            panel.add(label);
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setSize(new Dimension((int) (0.98 * WIDTH), (int) (0.92 * HEIGHT)));
        scrollPane.setLocation(0,0);
        scrollPane.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );

        return scrollPane;
    }
}