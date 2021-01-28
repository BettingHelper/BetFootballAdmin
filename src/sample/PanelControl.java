package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PanelControl extends JPanel {
    int WIDTH;
    int DEFWIDTH = 1600;
    double procWIDTH;
    int HEIGHT;
    int DEFHEIGHT = 1000;
    double procHEIGHT;
    Settings settings;
    JPanel container;
    final String path = Settings.getPathToDatabase();

    public PanelControl(int width, int height){
        setLayout(new GridLayout(1, 2, 5, 5));

        JPanel leftPanel = new JPanel(new GridLayout(0, 1, 30, 30));
        JPanel rightPanel = new JPanel(new BorderLayout());

        Font font = new Font("", Font.BOLD, 20);

        JPanel header = new JPanel();
        JFileChooser fileChooser = new JFileChooser(path + Settings.getCurrentSeason() + "/leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> leagueList = new ArrayList<>();
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        Collections.sort(leagueList);

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        final JComboBox<String> seasonCB = new JComboBox<>(seasonList);
        seasonCB.setPreferredSize(new Dimension(200, 40));
        header.add(seasonCB);

        String[] listOfLeagues = new String[leagueList.size()];
        for (int i=0; i<listOfLeagues.length;i++)
            listOfLeagues[i] = leagueList.get(i);
        final JComboBox<String> leagueChooser = new JComboBox<>(listOfLeagues);
        leagueChooser.setPreferredSize(new Dimension(200, 40));
        header.add(leagueChooser);

        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        leftPanel.add(header);

        JButton button1 = new JButton("Проверить количество игр каждой команды");
        button1.setFont(font);
        leftPanel.add(button1);

        JButton button2 = new JButton("Проверить количество игр каждого арбитра");
        button2.setFont(font);
        leftPanel.add(button2);

        JButton button3 = new JButton("Вывести недостающие xG");
        button3.setFont(font);
        leftPanel.add(button3);


        add(leftPanel);

        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea textArea = new JTextArea("");
        textArea.setMargin(new Insets(0,5,0,0));
        textArea.setFont(new Font("", Font.BOLD, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.add(textArea);
        rightPanel.add(scrollPane);

        add(rightPanel);



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

                leagueChooser.setFocusable(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                String fileName = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + leagueChooser.getSelectedItem() + "/Referees/Referees.txt";
                String[] list = Main.readTxtFileForRef(fileName);
                String text = "";

                for (int i=1; i<list.length; i++){
                    Referee ref = Referee.downloadRef(list[i], leagueChooser.getSelectedItem().toString(), seasonCB.getSelectedItem().toString().replace("Сезон ", ""));
                    if (ref.matches > 0)
                        text += "№ " + i + ") " + list[i] + ": количество игр = " + ref.matches + "\n";
                }
                textArea.setText(text);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                String fileName = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] list = Main.readTxtFile(fileName);
                String text = "";

                for (int i=1; i<list.length; i++){
                    Selector selector = new Selector();
                    selector.getListOfMatchesForControl(leagueChooser.getSelectedItem().toString(), list[i], "Все игры", seasonCB.getSelectedItem().toString(), "Все");
                    text += "№ " + i + ") " + list[i] + ": количество игр = " + selector.listOfMatchesForControl.size() + "\n";
                }
                textArea.setText(text);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                String fileName = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] list = Main.readTxtFile(fileName);
                String text = "";

                for (int i=1; i<list.length; i++){
                    text += "Команда " + list[i] + ":\n";
                    System.out.println("Команда " + list[i] + ":");
                    Selector selector = new Selector();
                    selector.getListOfMatches(leagueChooser.getSelectedItem().toString(), list[i], "Все игры", seasonCB.getSelectedItem().toString().replace("Сезон ", ""), "Все");
                    selector.getPList(selector.listOfMatches, list[i]);
                    if ((int) Double.parseDouble(selector.pList.get(17).get(1)) == selector.listOfMatches.size()){
                        text += "Недостающих матчей с xG-статистикой нет.\n";
                        System.out.println("Недостающих матчей с xG-статистикой нет.");
                    } else {
                        for (int j=0; j<selector.listOfMatches.size();j++){
                            Match m = selector.listOfMatches.get(j);
                            if (m.homeXG + m.awayXG == 0){
                                text += "Не хватает xG-статистики матча " + m.title + "\n";
                                System.out.println("Не хватает xG-статистики матча " + m.title);
                            }
                        }
                    }


                }
                textArea.setText(text);
            }
        });


    }
}
