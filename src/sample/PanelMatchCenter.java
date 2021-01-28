package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class PanelMatchCenter extends JPanel{
    int WIDTH;
    int DEFWIDTH = 1600;
    double procWIDTH;
    int HEIGHT;
    int DEFHEIGHT = 1000;
    int graphicWidth = 1250;
    int graphicHeight = 410;
    double procHEIGHT;
    JScrollPane scrollPane;
    int dayCode;
    JPanel panelHeader;
    JPanel panelWithJSP;
    JButton buttonLeft;
    JButton buttonRight;
    JLabel labelDate;
    JScrollPane jsp;

    public PanelMatchCenter(int width, int height){
        this.setLayout(new GridLayout(1, 2, 5, 5));
        /*WIDTH = width;
        HEIGHT = height;
        procWIDTH = WIDTH / (double) DEFWIDTH;
        procHEIGHT = HEIGHT / (double) DEFHEIGHT;
        if (procWIDTH == 1)
            this.graphicWidth = 1300;*/

        final JTextArea textArea = new JTextArea("");
        textArea.setFont(new Font("", Font.BOLD, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.add(textArea);
        this.add(scrollPane);

        final JButton button = new JButton("Записать данные");
        button.setFont(new Font("", Font.BOLD, 35));
        this.add(button);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textArea.selectAll();
                button.setText("Записать данные");
            }
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToAdd = textArea.getText().replaceAll("Таблица\n", "").replaceAll("Таблица Live\n", "");
                String[] array = textToAdd.split("\n");

                boolean flagDate = false;
                int index = 0;
                int dayCode = 0;
                while (!flagDate && index < array.length){
                    if (array[index].equals("Расписание") ){  //&& array[index+4].contains("Order by")
                        LocalDate ld = LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(array[index+1].split(" ")[0].split("/")[1]), Integer.parseInt(array[index+1].split(" ")[0].split("/")[0]));
                        if (!ld.isAfter(LocalDate.now())){
                            ld = ld.plusYears(1);
                        }
                        String monthS = String.valueOf(ld.getMonthValue());
                        if (monthS.length()<2){
                            monthS = "0" + monthS;
                        }
                        String dayS = String.valueOf(ld.getDayOfMonth());
                        if (dayS.length()<2){
                            dayS = "0" + dayS;
                        }
                        dayCode = Integer.parseInt(String.valueOf(ld.getYear()) + monthS + dayS);
                        flagDate = true;
                    } else
                        index++;
                }



                ArrayList<String> resultList = new ArrayList<>();
                ArrayList<String> leagueList = new ArrayList<>();
                ArrayList<Integer> leagueListBeginIndex = new ArrayList<>();
                int indexNotFound = 0;

                while (index < array.length-1 || indexNotFound < 20){
                    String leagueName = Settings.getLeagueName(array[index], array[index + 1]);
                    if (!leagueName.equals("")){
                        leagueList.add(leagueName);
                        leagueListBeginIndex.add(index+2);
                        indexNotFound = 0;
                    } else{
                        indexNotFound++;
                    }
                    index++;
                }
                leagueListBeginIndex.add(index);

                for (int i=0; i<leagueList.size();i++){
                    resultList.add(leagueList.get(i));
                    for (int j=leagueListBeginIndex.get(i); j<leagueListBeginIndex.get(i+1); j+=4){
                        if (array[j].contains("Тех. поражение")){
                            j+=2;
                            continue;
                        }
                        String matchTime = array[j];
                        String homeTeam = Team.getNameForMatchCenter(array[j+1], leagueList.get(i));
                        String awayTeam = Team.getNameForMatchCenter(array[j+3], leagueList.get(i));
                        if (!matchTime.equals("") && !homeTeam.equals("") && !awayTeam.equals("")){
                            resultList.add(matchTime + "*" + homeTeam + "*" + awayTeam);
                        } else {
                            break;
                        }
                    }
                }

                for (int i=0; i<resultList.size(); i++){
                    if (resultList.get(i).contains("Перенесен")){
                        resultList.remove(i);
                        i--;
                    }
                }


                if (resultList.size() == 0){
                    resultList.add("No matches!");
                }

                FileWriter fr = null;
                BufferedWriter br = null;
                File file = new File("tmp/" + String.valueOf(dayCode) + ".txt");

                try {
                    //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
                    fr = new FileWriter(file,true);
                    br = new BufferedWriter(fr);
                    //теперь мы можем использовать метод write или метод append
                    for (int i=0; i<resultList.size(); i++){
                        br.write(resultList.get(i));
                        br.newLine();
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally{
                    try {
                        br.close();
                        fr.close();
                        FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/matchCenter/" + String.valueOf(dayCode) + ".txt", file.getPath());
                        button.setText("Запись " + String.valueOf(dayCode) + ".txt произведена!");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

        /*button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
                String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                if (day.length() < 2)
                    day = "0" + day;
                String month = String.valueOf(c.get(Calendar.MONTH)+1);
                if (month.length() < 2)
                    month = "0" + month;
                String year = String.valueOf(c.get(Calendar.YEAR));

//                int dayCode = Settings.getDayCode("Jan 1 2020");
                int dayCode = Integer.parseInt(year + month + day);

                String textToAdd = textArea.getText();
                textToAdd = textToAdd.replaceAll("Stream\n", "");

                String[] array = textToAdd.split("\n");

                boolean flagDate = false;
                int index = 0;
                while (!flagDate && index < array.length){
                    if (array[index].contains("Football Live Scores") ){  //&& array[index+4].contains("Order by")
                        String dateS;

                        try {
                            dateS = array[index+3].split(",")[1].trim();
                        }
                        catch (IndexOutOfBoundsException e1){
                            dateS = array[index+2].split(",")[1].trim();
                        }

                        dayCode = Settings.getDayCode(dateS);
                        flagDate = true;
                    } else
                        index++;
                }
                ArrayList<String> resultList = new ArrayList<>();
                String errors = "";
                boolean flagDetailed = false;

                while (index < array.length){
                    if (array[index].contains("Detailed coverage") && Settings.getLeagueName(array[index])){

                        String league = Settings.getNameOfLeagueFromWS(array[index]);
                        resultList.add(league);
                        index++;
                        *//*boolean flagEurocups = false;
                        if (array[index].contains("Europe"))
                            flagEurocups = true;*//*
                        boolean flag = true;
                        while (flag){  //!array[index].contains("Detailed")|| !array[index].contains(":")
                            if (array[index].equals("vs")){
                                String time = array[index-2]; // .split("\t")[2];
                                String homeTeam = array[index-1]; //.split("\t")[5];
                                String awayTeam = array[index+1]; //.split("\t")[1];
                                if (Team.getNameForMatchCenter(homeTeam, league).equals(""))
                                    break;
                                resultList.add(time + "*" + homeTeam + "*" + awayTeam);
                            }
                            if (array[index].contains("Detailed") ){
                                flag = false;
                                index --;
                                break;
                            }

                            index += 1;
                        }
                    } else
                        index++;
                }
                if (resultList.size() == 0){
                    resultList.add("No matches!");
                }

                FileWriter fr = null;
                BufferedWriter br = null;
                File file = new File("tmp/" + String.valueOf(dayCode) + ".txt");

                try {
                    //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
                    fr = new FileWriter(file,true);
                    br = new BufferedWriter(fr);
                    //теперь мы можем использовать метод write или метод append
                    for (int i=0; i<resultList.size(); i++){
                        br.write(resultList.get(i));
                        br.newLine();
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally{
                    try {
                        br.close();
                        fr.close();
                        FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/football/matchCenter/" + String.valueOf(dayCode) + ".txt", file.getPath());
                        button.setText("Запись " + String.valueOf(dayCode) + ".txt произведена!");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });*/





    }

}



