package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.tabbedui.VerticalLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WindowWithDiagrams extends JFrame{

    public WindowWithDiagrams(){
        super("Диаграммы сравнения лиг");
        this.setLayout(new BorderLayout());
        this.setLocation(10, 10);
        this.setMinimumSize(new Dimension(400, 400));

        JScrollPane jsp;
        JPanel diagramContainer = getDiagrams();

        jsp = new JScrollPane(diagramContainer);
        jsp.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );

        this.add(jsp);

        this.setSize(1300,800);
        this.setVisible(true);
//        this.setAlwaysOnTop(true);
    }

    public JPanel getDiagrams(){
        JPanel result = new JPanel(new VerticalLayout());
        int diagramWidth = 290;
        int diagramHeight = 700;
        String headerText = "";

        JLabel jtf = new JLabel(headerText);
        jtf.setFont(new Font("", 0, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        result.add(jtf);

        ArrayList<League> listOfLeagues1 = new ArrayList<>();
        ArrayList<League> listOfLeagues2 = new ArrayList<>();
        ArrayList<League> listOfLeagues3 = new ArrayList<>();
        listOfLeagues1.add(League.getLeagueFromFile("ENG.Championship", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("ENG.PrLeague", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("FRA.Ligue1", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("GER.1-Bundesliga", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("GER.2-Bundesliga", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("ITA.SerieA", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("NET.Eredivisie", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("POR.Primeira", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("RUS.RrLeague", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("SPA.LaLiga", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("TUR.SuperLig", "16-17"));
        listOfLeagues1.add(League.getLeagueFromFile("CHI.SuperLeague", "17"));
        listOfLeagues1.add(League.getLeagueFromFile("MLS", "17"));
        listOfLeagues1.add(League.getLeagueFromFile("BRA.SerieA", "17"));

        listOfLeagues2.add(League.getLeagueFromFile("ENG.Championship", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("ENG.PrLeague", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("FRA.Ligue1", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("GER.1-Bundesliga", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("GER.2-Bundesliga", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("ITA.SerieA", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("NET.Eredivisie", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("POR.Primeira", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("RUS.RrLeague", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("SPA.LaLiga", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("TUR.SuperLig", "17-18"));
        listOfLeagues2.add(League.getLeagueFromFile("CHI.SuperLeague", "18"));
        listOfLeagues2.add(League.getLeagueFromFile("MLS", "18"));
        listOfLeagues2.add(League.getLeagueFromFile("BRA.SerieA", "18"));

        listOfLeagues3.add(League.getLeagueFromFile("ENG.Championship", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("ENG.PrLeague", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("FRA.Ligue1", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("GER.1-Bundesliga", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("GER.2-Bundesliga", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("ITA.SerieA", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("NET.Eredivisie", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("POR.Primeira", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("RUS.RrLeague", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("SPA.LaLiga", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("TUR.SuperLig", "18-19"));
        listOfLeagues3.add(League.getLeagueFromFile("CHI.SuperLeague", "19"));
        listOfLeagues3.add(League.getLeagueFromFile("MLS", "19"));
        listOfLeagues3.add(League.getLeagueFromFile("BRA.SerieA", "19"));

        ArrayList<String> listOfDiagram = new ArrayList<>();
        listOfDiagram.add("Голов за матч (сред.)");
        listOfDiagram.add("% голов хозяев от общего количества голов");
        listOfDiagram.add("% голов за 1 тайм от общего количества голов");
        listOfDiagram.add("% голов хозяев от общего количества голов (1 тайм)");
        listOfDiagram.add("% голов за 2 тайм от общего количества голов");
        listOfDiagram.add("% голов хозяев от общего количества голов (2 тайм)");
        listOfDiagram.add("% голов 1 тайме от общего числа голов");
        listOfDiagram.add("% побед хозяев");
        listOfDiagram.add("% побед ничьих");
        listOfDiagram.add("% побед гостей");
        listOfDiagram.add("% побед хозяев в 1 тайме");
        listOfDiagram.add("% побед ничьих в 1 тайме");
        listOfDiagram.add("% побед гостей в 1 тайме");
        listOfDiagram.add("% побед хозяев во 2 тайме");
        listOfDiagram.add("% побед ничьих во 2 тайме");
        listOfDiagram.add("% побед гостей во 2 тайме");
        listOfDiagram.add("% голов в 0-15' от всех голов");
        listOfDiagram.add("% голов в 16-30' от всех голов");
        listOfDiagram.add("% голов в 31-45+' от всех голов");
        listOfDiagram.add("% голов в 46-60' от всех голов");
        listOfDiagram.add("% голов в 61-75' от всех голов");
        listOfDiagram.add("% голов в 75-90+' от всех голов");
        listOfDiagram.add("% матчей, сыгранных на 'Обе забьют'");
        listOfDiagram.add("% матчей, в которых 1Т > 2T (по голам)");
        listOfDiagram.add("% матчей, в которых 1Т = 2T (по голам)");
        listOfDiagram.add("% матчей, в которых 1Т < 2T (по голам)");
        listOfDiagram.add("% матчей, сыгранных на ТБ(2.5)");



        for (int k=0; k<listOfDiagram.size(); k++){
            DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();

            for (int i=0; i<listOfLeagues1.size(); i++){
                League l1 = listOfLeagues1.get(i);
                League l2 = listOfLeagues2.get(i);
                League l3 = listOfLeagues3.get(i);
                double value1 = 0;
                double value2 = 0;
                double value3 = 0;
                switch (k){
                    case 0:{
                        value1 = roundResult((l1.homeGoals + l1.awayGoals) / (double) l1.matchesPlayed , 2);
                        value2 = roundResult((l2.homeGoals + l2.awayGoals) / (double) l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult((l3.homeGoals + l3.awayGoals) / (double) l3.matchesPlayed , 2);
                        break;
                    }
                    case 1:{
                        value1 = roundResult(100*l1.homeGoals / (double) (l1.homeGoals + l1.awayGoals) , 2);
                        value2 = roundResult(100*l2.homeGoals / (double) (l2.homeGoals + l2.awayGoals) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*l3.homeGoals / (double) (l3.homeGoals + l3.awayGoals) , 2);
                        break;
                    }
                    case 2:{
                        value1 = roundResult(100 * (l1.homeGoals1T + l1.awayGoals1T) / (double) (l1.homeGoals + l1.awayGoals) , 2);
                        value2 = roundResult(100 * (l2.homeGoals1T + l2.awayGoals1T) / (double) (l2.homeGoals + l2.awayGoals) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100 * (l3.homeGoals1T + l3.awayGoals1T) / (double) (l3.homeGoals + l3.awayGoals) , 2);
                        break;
                    }
                    case 3:{
                        value1 = roundResult(100*l1.homeGoals1T / (double) (l1.homeGoals1T + l1.awayGoals1T) , 2);
                        value2 = roundResult(100*l2.homeGoals1T / (double) (l2.homeGoals1T + l2.awayGoals1T) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*l3.homeGoals1T / (double) (l3.homeGoals1T + l3.awayGoals1T) , 2);
                        break;
                    }
                    case 4:{
                        value1 = roundResult(100 * (l1.homeGoals2T + l1.awayGoals2T) / (double) (l1.homeGoals + l1.awayGoals) , 2);
                        value2 = roundResult(100 * (l2.homeGoals2T + l2.awayGoals2T) / (double) (l2.homeGoals + l2.awayGoals) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100 * (l3.homeGoals2T + l3.awayGoals2T) / (double) (l3.homeGoals + l3.awayGoals) , 2);
                        break;
                    }
                    case 5:{
                        value1 = roundResult(100*l1.homeGoals2T / (double) (l1.homeGoals2T + l1.awayGoals2T) , 2);
                        value2 = roundResult(100*l2.homeGoals2T / (double) (l2.homeGoals2T + l2.awayGoals2T) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*l3.homeGoals2T / (double) (l3.homeGoals2T + l3.awayGoals2T) , 2);
                        break;
                    }
                    case 6:{
                        value1 = roundResult(100*(l1.homeGoals1T + l1.awayGoals1T) / (double) (l1.homeGoals + l1.awayGoals) , 2);
                        value2 = roundResult(100*(l2.homeGoals1T + l2.awayGoals1T) / (double) (l2.homeGoals + l2.awayGoals) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*(l3.homeGoals1T + l3.awayGoals1T) / (double) (l3.homeGoals + l3.awayGoals) , 2);
                        break;
                    }
                    case 7:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin.split("\\*")[0]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin.split("\\*")[0]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin.split("\\*")[0]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 8:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin.split("\\*")[1]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin.split("\\*")[1]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin.split("\\*")[1]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 9:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin.split("\\*")[2]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin.split("\\*")[2]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin.split("\\*")[2]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 10:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin_1T.split("\\*")[0]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin_1T.split("\\*")[0]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin_1T.split("\\*")[0]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 11:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin_1T.split("\\*")[1]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin_1T.split("\\*")[1]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin_1T.split("\\*")[1]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 12:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin_1T.split("\\*")[2]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin_1T.split("\\*")[2]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin_1T.split("\\*")[2]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 13:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin_2T.split("\\*")[0]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin_2T.split("\\*")[0]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin_2T.split("\\*")[0]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 14:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin_2T.split("\\*")[1]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin_2T.split("\\*")[1]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin_2T.split("\\*")[1]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 15:{
                        value1 = roundResult(100*Double.parseDouble(l1.g_homeWin_draw_awayWin_2T.split("\\*")[2]) / l1.matchesPlayed , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.g_homeWin_draw_awayWin_2T.split("\\*")[2]) / l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.g_homeWin_draw_awayWin_2T.split("\\*")[2]) / l3.matchesPlayed , 2);
                        break;
                    }
                    case 16:{
                        value1 = roundResult(100*Double.parseDouble(l1.goalsScoredBy15Min.split("\\*")[0]) / (double) (l1.homeGoals + l1.awayGoals) , 2);
                        value2 = roundResult(100*Double.parseDouble(l2.goalsScoredBy15Min.split("\\*")[0]) / (double) (l2.homeGoals + l2.awayGoals) , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.goalsScoredBy15Min.split("\\*")[0]) / (double) (l3.homeGoals + l3.awayGoals) , 2);
                        break;
                    }
                    case 17:{
                        value1 = roundResult(100*Double.parseDouble(l1.goalsScoredBy15Min.split("\\*")[1]) / (double) (l1.homeGoals + l1.awayGoals), 2);
                        value2 = roundResult(100*Double.parseDouble(l2.goalsScoredBy15Min.split("\\*")[1]) / (double) (l2.homeGoals + l2.awayGoals), 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.goalsScoredBy15Min.split("\\*")[1]) / (double) (l3.homeGoals + l3.awayGoals), 2);
                        break;
                    }
                    case 18:{
                        value1 = roundResult(100*Double.parseDouble(l1.goalsScoredBy15Min.split("\\*")[2]) / (double) (l1.homeGoals + l1.awayGoals), 2);
                        value2 = roundResult(100*Double.parseDouble(l2.goalsScoredBy15Min.split("\\*")[2]) / (double) (l2.homeGoals + l2.awayGoals), 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.goalsScoredBy15Min.split("\\*")[2]) / (double) (l3.homeGoals + l3.awayGoals), 2);
                        break;
                    }
                    case 19:{
                        value1 = roundResult(100*Double.parseDouble(l1.goalsScoredBy15Min.split("\\*")[3]) / (double) (l1.homeGoals + l1.awayGoals), 2);
                        value2 = roundResult(100*Double.parseDouble(l2.goalsScoredBy15Min.split("\\*")[3]) / (double) (l2.homeGoals + l2.awayGoals), 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.goalsScoredBy15Min.split("\\*")[3]) / (double) (l3.homeGoals + l3.awayGoals), 2);
                        break;
                    }
                    case 20:{
                        value1 = roundResult(100*Double.parseDouble(l1.goalsScoredBy15Min.split("\\*")[4]) / (double) (l1.homeGoals + l1.awayGoals), 2);
                        value2 = roundResult(100*Double.parseDouble(l2.goalsScoredBy15Min.split("\\*")[4]) / (double) (l2.homeGoals + l2.awayGoals), 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.goalsScoredBy15Min.split("\\*")[4]) / (double) (l3.homeGoals + l3.awayGoals), 2);
                        break;
                    }
                    case 21:{
                        value1 = roundResult(100*Double.parseDouble(l1.goalsScoredBy15Min.split("\\*")[5]) / (double) (l1.homeGoals + l1.awayGoals), 2);
                        value2 = roundResult(100*Double.parseDouble(l2.goalsScoredBy15Min.split("\\*")[5]) / (double) (l2.homeGoals + l2.awayGoals), 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100*Double.parseDouble(l3.goalsScoredBy15Min.split("\\*")[5]) / (double) (l3.homeGoals + l3.awayGoals), 2);
                        break;
                    }
                    case 22:{
                        value1 = roundResult(100* l1.g_OZ / (double) l1.matchesPlayed , 2);
                        value2 = roundResult(100* l2.g_OZ / (double) l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100* l3.g_OZ / (double) l3.matchesPlayed , 2);
                        break;
                    }
                    case 23:{
                        value1 = roundResult(100* l1.g_firstTimeMoreGoals / (double) l1.matchesPlayed , 2);
                        value2 = roundResult(100* l2.g_firstTimeMoreGoals / (double) l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100* l3.g_firstTimeMoreGoals / (double) l3.matchesPlayed , 2);
                        break;
                    }
                    case 24:{
                        value1 = roundResult(100* (l1.matchesPlayed - l1.g_secondTimeMoreGoals - l1.g_firstTimeMoreGoals) / (double) l1.matchesPlayed , 2);
                        value2 = roundResult(100* (l2.matchesPlayed - l2.g_secondTimeMoreGoals - l2.g_firstTimeMoreGoals) / (double) l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100* (l3.matchesPlayed - l3.g_secondTimeMoreGoals - l3.g_firstTimeMoreGoals) / (double) l3.matchesPlayed , 2);
                        break;
                    }
                    case 25:{
                        value1 = roundResult(100* l1.g_secondTimeMoreGoals / (double) l1.matchesPlayed , 2);
                        value2 = roundResult(100* l2.g_secondTimeMoreGoals / (double) l2.matchesPlayed , 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100* l3.g_secondTimeMoreGoals / (double) l3.matchesPlayed , 2);
                        break;
                    }
                    case 26:{
                        double tm1 = Double.parseDouble(l1.g_totals.split("\\*")[0]) + Double.parseDouble(l1.g_totals.split("\\*")[1]) + Double.parseDouble(l1.g_totals.split("\\*")[2]);
                        double tm2 = Double.parseDouble(l2.g_totals.split("\\*")[0]) + Double.parseDouble(l2.g_totals.split("\\*")[1]) + Double.parseDouble(l2.g_totals.split("\\*")[2]);
                        double tm3 = Double.parseDouble(l3.g_totals.split("\\*")[0]) + Double.parseDouble(l3.g_totals.split("\\*")[1]) + Double.parseDouble(l3.g_totals.split("\\*")[2]);


                        value1 = roundResult(100 * (l1.matchesPlayed - tm1) / (double) l1.matchesPlayed, 2);
                        value2 = roundResult(100 * (l2.matchesPlayed - tm2) / (double) l2.matchesPlayed, 2);
                        if (!l3.leagueName.contains("BRA.SerieA"))
                            value3 = roundResult(100 * (l3.matchesPlayed - tm3) / (double) l3.matchesPlayed, 2);
                        break;
                    }
                    case 27:{

                        break;
                    }
                    case 28:{

                        break;
                    }




                }

                categoryDataset.addValue( value1, l1.leagueName, "Сезон 16-17 и 17");
                categoryDataset.addValue( value2, l2.leagueName, "Сезон 17-18 и 18");
                categoryDataset.addValue( value3, l3.leagueName, "Сезон 18-19 и 19");
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    listOfDiagram.get(k) + "  ", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

            // Определение фона диаграммы
            chart.setBackgroundPaint(new Color(238, 238, 238));
            chart.getTitle().setFont(new Font("", 0, 18));
            chart.getTitle().setMargin(10,0,0,0);
            // Настройка plot'а
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(new Color(238, 238, 238));

            plot.setDomainGridlinePaint(Color.black);
            plot.setRangeGridlinePaint(Color.black);
//        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
//        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            BarRenderer renderer = (BarRenderer)plot.getRenderer();
            renderer.setItemMargin(0.02);
            SubCategoryAxis subCategoryAxis = new SubCategoryAxis("");
            subCategoryAxis.setCategoryMargin(0.15);

            ChartPanel cp = new ChartPanel(chart);
            cp.setPreferredSize(new Dimension(diagramWidth, diagramHeight));
            result.add(cp);
        }




        /*String series1 = "1-ый тайм";
        String series2 = "2-ой тайм";
        // column keys...
        String category1 = "Забито" ;
        String category2 = "Пропущено" ;
        String category3 = "Разница" ;
        String category4 = "Тотал" ;

        categoryDataset.addValue(0, series1, category1);
        categoryDataset.addValue(0, series2, category1);

        categoryDataset.addValue(0, series1, category2);
        categoryDataset.addValue(0, series2, category2);

        categoryDataset.addValue(0 - 0, series1, category3);
        categoryDataset.addValue(0 - 0, series2, category3);

        categoryDataset.addValue(0 + 0, series1, category4);
        categoryDataset.addValue(0 + 0, series2, category4);

        JFreeChart chartByTimes = ChartFactory.createBarChart(
                "Голы по таймам", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartByTimes.setBackgroundPaint(new Color(238, 238, 238));
        chartByTimes.getTitle().setFont(new Font("", 0, 18));
        chartByTimes.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        CategoryPlot plotByTimes = chartByTimes.getCategoryPlot();
        plotByTimes.setBackgroundPaint(new Color(238, 238, 238));
        plotByTimes.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotByTimes.getRenderer().setSeriesPaint(1, new Color(40, 40, 255 ));

        plotByTimes.setDomainGridlinePaint(Color.black);
        plotByTimes.setRangeGridlinePaint(Color.black);
        NumberAxis axis = (NumberAxis) plotByTimes.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer)plotByTimes.getRenderer();
        renderer.setItemMargin(0.02);
        SubCategoryAxis subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        categoryDataset = new DefaultCategoryDataset();
        // row keys...
        series1 = "0' - 15'";
        series2 = "16' - 30'";
        String series3 = "31' - 45+'";
        String series4 = "46' - 60'";
        String series5 = "61' - 75'";
        String series6 = "76' - 90+'";
        // column keys...
        category1 = "Забито" ;
        category2 = "Пропущено" ;
        category3 = "Разница" ;
        category4 = "Тотал" ;

        categoryDataset.addValue( 0, series1, category1);
        categoryDataset.addValue( 0, series2, category1);
        categoryDataset.addValue( 0, series3, category1);
        categoryDataset.addValue( 0, series4, category1);
        categoryDataset.addValue( 0, series5, category1);
        categoryDataset.addValue( 0, series6, category1);

        categoryDataset.addValue( 0, series1, category2);
        categoryDataset.addValue( 0, series2, category2);
        categoryDataset.addValue( 0, series3, category2);
        categoryDataset.addValue( 0, series4, category2);
        categoryDataset.addValue( 0, series5, category2);
        categoryDataset.addValue( 0, series6, category2);

        categoryDataset.addValue( 0, series1, category3);
        categoryDataset.addValue( 0, series2, category3);
        categoryDataset.addValue( 0, series3, category3);
        categoryDataset.addValue( 0, series4, category3);
        categoryDataset.addValue( 0, series5, category3);
        categoryDataset.addValue( 0, series6, category3);

        categoryDataset.addValue( 0, series1, category4);
        categoryDataset.addValue( 0, series2, category4);
        categoryDataset.addValue( 0, series3, category4);
        categoryDataset.addValue( 0, series4, category4);
        categoryDataset.addValue( 0, series5, category4);
        categoryDataset.addValue( 0, series6, category4);

        JFreeChart chartBy15Min = ChartFactory.createBarChart(
                "Голы по 15-минуткам", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartBy15Min.setBackgroundPaint(new Color(238, 238, 238));
        chartBy15Min.getTitle().setFont(new Font("", 0, 18));
        chartBy15Min.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        CategoryPlot plotBy15Min = chartBy15Min.getCategoryPlot();
        plotBy15Min.setBackgroundPaint(new Color(238, 238, 238));
        plotBy15Min.setDomainGridlinePaint(Color.black);
        plotBy15Min.setRangeGridlinePaint(Color.black);
        plotBy15Min.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotBy15Min.getRenderer().setSeriesPaint(1, new Color(40, 40, 255 ));
        plotBy15Min.getRenderer().setSeriesPaint(2, new Color(0, 140, 20 ));
        plotBy15Min.getRenderer().setSeriesPaint(3, new Color(242, 120, 21 ));
        plotBy15Min.getRenderer().setSeriesPaint(4, new Color(187,  46, 230));
        plotBy15Min.getRenderer().setSeriesPaint(5, new Color(30 , 200, 230));
        axis = (NumberAxis) plotBy15Min.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        renderer = (BarRenderer)plotBy15Min.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.1);

        ChartPanel cp = new ChartPanel(chartByTimes);
        cp.setPreferredSize(new Dimension(diagramWidth, 300));
        result.add(cp);

        cp = new ChartPanel(chartBy15Min);
        cp.setPreferredSize(new Dimension(diagramWidth, 300));
        result.add(cp);*/



        return result;
    }

    public static double roundResult(double d, int precise) {
        precise = (int) Math.pow(10,precise);
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }


}
