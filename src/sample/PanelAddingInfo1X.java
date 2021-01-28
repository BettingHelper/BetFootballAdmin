package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Collections;

public class PanelAddingInfo1X extends JPanel{
    ArrayList<Match> listOfMatches;
    JScrollPane tablePanel;
    JPanel panelTableWithMatches;
    ArrayList<JTable> tableOfData = new ArrayList<>();
    JTextArea textArea;
    Font font15 = new Font("Text", Font.BOLD, 15);
    Font font18 = new Font("Text", Font.BOLD, 18);

    public PanelAddingInfo1X(){
        this.setLayout(new BorderLayout());

        JPanel panelAdd1xStats = new JPanel(new GridLayout(1, 0, 5, 5));
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        textArea = new JTextArea("Вставьте данные с сайта 1xstavka.\nПрограмма автоматически заполнит данные, если их состав будет полным.");
        textArea.setMargin(new Insets(0, 5, 0, 0));
        scrollPane.add(textArea);
        panelAdd1xStats.add(scrollPane);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textArea.selectAll();
                Font font10 = new Font("", 0, 12);
                textArea.setFont(font10);
                textArea.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        JButton buttonParseStats = new JButton("Добавить!");
        buttonParseStats.setFont(font18);
        panelAdd1xStats.add(buttonParseStats);

        this.add(panelAdd1xStats, BorderLayout.NORTH);

        panelTableWithMatches = new JPanel(new BorderLayout());

        tablePanel = new JScrollPane();

        panelTableWithMatches.add(tablePanel);

        final JButton buttonAddMatches = new JButton("Добавить!");
        buttonAddMatches.setFont(font18);
        panelTableWithMatches.add(buttonAddMatches, BorderLayout.SOUTH);
        this.add(panelTableWithMatches);

        buttonParseStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                listOfMatches = getListOfMatches(text);
                panelTableWithMatches.remove(tablePanel);
                tableOfData.clear();
                tablePanel = buildTableOfMatches();
                panelTableWithMatches.add(tablePanel);
                panelTableWithMatches.revalidate();


            }
        });

        buttonAddMatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMatchesToDataBase(listOfMatches, tableOfData);
            }
        });


    }

    public void addMatchesToDataBase(ArrayList<Match> listOfMatches, ArrayList<JTable> tableOfData){
        //boolean allRefsAreSet = true;
        String text = "";
        ArrayList<Integer> indexList = new ArrayList<>();
        indexList.add(0);
        for (int i=1; i<listOfMatches.size(); i++){
            if (listOfMatches.get(i).league.equals(listOfMatches.get(i-1).league)){
                indexList.add(indexList.get(i-1));
            } else {
                indexList.add(indexList.get(i-1) + 1);
            }
        }

        /*for (int i=0; i<tableOfData.size(); i++){
            for (int j=0; j<tableOfData.get(i).getRowCount(); j++){
                String value = String.valueOf(tableOfData.get(i).getValueAt(j, 4));
                if (value.contains("ыберите") || value.equals(""))
                    allRefsAreSet = false;
            }
        }*/
        int index = 0;
        for (int i=0; i<tableOfData.size(); i++) {
            for (int j = 0; j < tableOfData.get(i).getRowCount(); j++) {
                if (!String.valueOf(tableOfData.get(i).getValueAt(j, 4)).equals("") && !String.valueOf(tableOfData.get(i).getValueAt(j, 4)).contains("ыберите")){
                    Match match = listOfMatches.get(index);
                    match.referee = String.valueOf(tableOfData.get(i).getValueAt(j, 4));
                    match.league = String.valueOf(tableOfData.get(i).getValueAt(j, 1));
                    match.season = Settings.getCurrentSeasonInLeague(match.league);
                    match.homeSecondYellowCards = Integer.parseInt(String.valueOf(tableOfData.get(i).getValueAt(j, 5)));
                    match.awaySecondYellowCards = Integer.parseInt(String.valueOf(tableOfData.get(i).getValueAt(j, 6)));
                    match.homeDirectRedCards = Integer.parseInt(String.valueOf(tableOfData.get(i).getValueAt(j, 7)));
                    match.awayDirectRedCards = Integer.parseInt(String.valueOf(tableOfData.get(i).getValueAt(j, 8)));
                    match.homePen = Integer.parseInt(String.valueOf(tableOfData.get(i).getValueAt(j, 9)));
                    match.awayPen = Integer.parseInt(String.valueOf(tableOfData.get(i).getValueAt(j, 10)));
                    match.URIonWhoscored = "-";

                    String result = "";
                    if (match.isAllData()){
                        Team ht = Team.getTeamFromFileWithSeason(match.homeTeam, match.league, match.season);
                        result = result + ht.addMatch(match);
                        Team at = Team.getTeamFromFileWithSeason(match.awayTeam, match.league, match.season);
                        result = result + at.addMatch(match);
                        if (!result.contains("Повтор")){
                            match.pushMatchToFile(true);      ////////////////////////////////////////////////////////
                            Referee ref = Referee.getRefFromFile(match.referee, match.season, match.league);
                            ref.addMatchToRefStats(match);

                            text += "Матч " + match.title + " обработан успешно!\n";
                            LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + match.title + ");");
                        }
                        if (result.contains("Повтор")){
                            text += "Матч " + match.title + "уже был добавлен в базу ранее!\n";
                        }
                    } else {
                        PopupWindow w = new PopupWindow("Ошибка в данных матча " + match.title);
                        w.setVisible(true);
                    }
                }
                index++;

            }
        }


        textArea.setText(text);


    }

    public ArrayList<Match> getListOfMatches(String text){
        ArrayList<Match> result = new ArrayList<>();
        String[] stats = text.split("\\n");
        int currentIndex = 0;
        ArrayList<String> listOfLeagues = Settings.getListOfLeagueFor1x();
        ArrayList<Integer> leaguesIndexList = new ArrayList<>();

        while (currentIndex < stats.length && !stats[currentIndex].contains("1хСтавка")) {
            if (!League.getLeagueNameFrom1x(stats[currentIndex], listOfLeagues).contains("Not found")) {
                leaguesIndexList.add(currentIndex);
            }
            currentIndex++;
        }
        leaguesIndexList.add(currentIndex);
        for (int i=0; i<leaguesIndexList.size()-1; i++){
            String previousLeague = "";
            String currentLeague;

            int beginIndex = leaguesIndexList.get(i);
            int endIndex = leaguesIndexList.get(i+1);

            for (int j=beginIndex+1; j<endIndex; j++){

                if (stats[j].contains(" - ") && !stats[j].contains("/")){
                    String homeTeam = Team.getNameOfTeam(stats[j].split(" - ")[0], false);
                    String awayTeam = Team.getNameOfTeam(stats[j].split(" - ")[1], false);

                    if (!homeTeam.equals("") && !awayTeam.equals("")){
                        currentLeague = Team.getLeague(homeTeam);
                        if (!previousLeague.equals("")){
                            if (!currentLeague.equals(previousLeague)){
                                break;
                            }
                        }
                        int startMatchIndex = j-1;
                        int endMatchIndex = j;
                        boolean flagFoundEndOfMatch = false;
                        String dateString = stats[j-1].split(" ")[0];
                        while (endMatchIndex < endIndex && !flagFoundEndOfMatch){
                            endMatchIndex++;
                            if (stats[endMatchIndex].contains(dateString)){
                                flagFoundEndOfMatch = true;
                            }
                        }
                        Match match = new Match(startMatchIndex, endMatchIndex-1, stats);
                        result.add(match);
                        j = endMatchIndex;
                        previousLeague = currentLeague;
                    }
                }
            }
        }
        return result;
    }

    public JScrollPane buildTableOfMatches(){
        JPanel result = new JPanel(new VerticalLayout());
        ArrayList<Integer> indexList = new ArrayList<>();
        indexList.add(0);
        for (int i=1; i<listOfMatches.size(); i++){
            if (!listOfMatches.get(i).league.equals(listOfMatches.get(i-1).league)){
                indexList.add(i);
            }
        }
        indexList.add(listOfMatches.size());

        for (int i=0; i<indexList.size()-1; i++){
            String[] colHeads = {"Номер", "Лига", "Дата", "Матч", "Арбитр", "2ЖК хоз.", "2ЖК гос.",
                    "Пр.КК хоз.", "Пр.КК гос.", "Пен.хоз.", "Пен.гос."};
            Object[][] data = new Object[indexList.get(i+1) - indexList.get(i)][colHeads.length];

            int initIndex = 0;
            String[] refList = {"Выберите арбитра"};
            for (int j=indexList.get(i); j<indexList.get(i+1); j++){
                int number = j+1;
                String league = Team.getLeague(listOfMatches.get(j).homeTeam);
                String date = listOfMatches.get(j).date;
                String title = listOfMatches.get(j).homeTeam + " - " + listOfMatches.get(j).awayTeam;

                data[initIndex] = new Object[]{ number, league, date, title, "", "0", "0", "0", "0", "0", "0" };

                String fileRefName = Settings.getPathToDatabase() + Settings.getCurrentSeasonInLeague(listOfMatches.get(j).league) + "/" + listOfMatches.get(j).league +"/Referees/Referees.txt";
                if (!fileRefName.contains("ыберите")) {
                    refList = Referee.getListOfRefs(fileRefName);
                }

                initIndex++;
            }

            tableOfData.add(new JTable(data, colHeads));

            tableOfData.get(i).setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableOfData.get(i).getTableHeader().setFont(font15);
            tableOfData.get(i).setFont(font15);
            tableOfData.get(i).setRowHeight(25);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int j=0; j<colHeads.length; j++){
                tableOfData.get(i).getColumnModel().getColumn(j).setCellRenderer(renderer);
            }


            JComboBox refChooser = new JComboBox(refList);

            TableColumn columnRefs = tableOfData.get(i).getColumnModel().getColumn(4);

            DefaultCellEditor defaultCellEditor=new DefaultCellEditor(refChooser);
            columnRefs.setCellEditor(defaultCellEditor);

            for (int j=5; j<=10; j++){
                JTextField textField = new JTextField("0");
                SelectAllCellEditor cellEditor = new SelectAllCellEditor(textField);

                TableColumn column = tableOfData.get(i).getColumnModel().getColumn(j);
                column.setCellEditor(cellEditor);
            }

            tableOfData.get(i).getColumnModel().getColumn(0).setPreferredWidth(30);
            tableOfData.get(i).getColumnModel().getColumn(3).setPreferredWidth(200);
            tableOfData.get(i).getColumnModel().getColumn(4).setPreferredWidth(150);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(tableOfData.get(i));
            panel.add(tableOfData.get(i).getTableHeader(), BorderLayout.NORTH);

            result.add(panel);
        }
        return new JScrollPane(result);
    }
}

