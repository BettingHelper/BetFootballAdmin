package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;

public class Main extends JFrame { //Наследуя от JFrame, мы получаем всю функциональность окна

    public Main(){
        super("Betting Helper"); //Заголовок окна
        int width = 1600;
        int height = 1000;
        int var = 0;
        if (var == 0){
            width = 1350;
            height = 730;
        }

        /*if (FTPLoader.checkSupportDirectory(Settings.getIp(), Settings.getLogin(), Settings.getPassword())){
            JFrame frameSupport = new JFrame("Внимание");
            JLabel label = new JLabel("Есть обращения в поддержку!");
            label.setFont(new Font("", Font.BOLD, 18));
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frameSupport.add(label);
            frameSupport.pack();
            frameSupport.setVisible(true);
            frameSupport.setLocation(100, 100);
            frameSupport.setAlwaysOnTop(true);

        }*/

        setBounds(10, 5, width, height); //отступы и размеры окна
        super.setResizable(false);
        String tabs[] = {"Добавление в базу c WS", "Добавление в базу c 1X", "Настройки", "Контроль данных", "Учет пользователей", "Матч-центр"};  /*Настройки и редактирование базы*/
        JTabbedPane jtp = new JTabbedPane();


        PanelAddingInfoWS panelAddingInfoWS = new PanelAddingInfoWS(width, height);
        PanelAddingInfo1X panelAddingInfo1X = new PanelAddingInfo1X();
        PanelSettings panelSettings = new PanelSettings(width, height);
        PanelControl panelControl = new PanelControl(width, height);
        PanelUsers panelUsers = new PanelUsers(width, height);
        PanelMatchCenter panelMatchCenter = new PanelMatchCenter(width, height);

        jtp.addTab(tabs[0], panelAddingInfoWS);
        add(jtp);
        jtp.addTab(tabs[1], panelAddingInfo1X);
        add(jtp);
        jtp.addTab(tabs[2], panelSettings);
        add(jtp);
        jtp.addTab(tabs[3], panelControl);
        add(jtp);
        jtp.addTab(tabs[4], panelUsers);
        add(jtp);
        jtp.addTab(tabs[5], panelMatchCenter);
        add(jtp);

        jtp.setSelectedIndex(0);


        /*String path = Settings.getPathToDatabase() + "20-21/SPA.Segunda/Matches";
        String list[] = new File(path).list();

        Referee referee = new Referee("SanchezLopez");

        for (int i=0; i<list.length; i++){
            String absPath = path + "/" + list[i];
            Match m = Match.getMatchFromFileByName(absPath);
            if (m.referee.equals(referee.name)){
                referee.addMatchToRefStats(m);
            }

            int tgfdf = 0;



        }*/

        int ttt = 0;

//        String season1 = "20-21";
       // String season2 = "20";
//        League.resetLeagueInfo("ChampionsLeague", season1);
        //League.resetLeagueInfo("BEL.ProLeague", season1);
//        League.resetLeagueInfo("ENG.Championship", season1);
//        League.resetLeagueInfo("ENG.PrLeague", season1);
//        League.resetLeagueInfo("EuropaLeague", season1);
//        League.resetLeagueInfo("FRA.Ligue1", season1);
        //League.resetLeagueInfo("FRA.Ligue2", season1);
//        League.resetLeagueInfo("GER.1-Bundesliga", season1);
//        League.resetLeagueInfo("GER.2-Bundesliga", season1);
//        League.resetLeagueInfo("ITA.SerieA", season1);
        //League.resetLeagueInfo("ITA.SerieB", season1);
//        League.resetLeagueInfo("NET.Eredivisie", season1);
//        League.resetLeagueInfo("POR.Primeira", season1);
//        League.resetLeagueInfo("RUS.RrLeague", season1);
        //League.resetLeagueInfo("RUS.FNL", season1);
//        League.resetLeagueInfo("SPA.LaLiga", season1);
        //League.resetLeagueInfo("SPA.Segunda", season1);
//        League.resetLeagueInfo("TUR.SuperLig", season1);
        //League.resetLeagueInfo("UKR.PrLeague", season1);
//        League.resetLeagueInfo("CHI.SuperLeague", season2);
//        League.resetLeagueInfo("MLS", season2);




        /*String league = "SPA.LaLiga";
        String season = "20-21";

        //League.setStatsTables(league, season);
        League.resetLeagueInfo(league, season);
        java.awt.Toolkit.getDefaultToolkit().beep();

        int ttt = 0;*/



        /*String season = "19-20"  ;
        String leagueName = "TUR.SuperLig";
        League league = new League(leagueName, season);
        league.pushLeagueToFile();*/

        //int twegwrg = 0;

        /*JFileChooser fileChooser = new JFileChooser(Settings.getPathToDatabase() + season + "/" + leagueName + "/Matches/");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> list = new ArrayList<>();

        for (int i=0; i<directoryList.length;i++){
            String s = directoryList[i].replaceAll(".xml", "");
            list.add(s);
        }
        league.matchesToAddingInStat = list;
        league.fillDataFromAddingList();

        int ttt = 0;
*/
//        WindowWithDiagrams window = new WindowWithDiagrams();


        /*String path = Settings.getPathToDatabase();
        String[] teams = new String[]{
            "AEK_Athens(GRC)",
            "Antwerpen(BEL)",
            "Arsenal(ENG)",
            "AZAlkmaar(NLD)",
            "BayerLeverkusen(DEU)",
            "Benfica(PRT)",
            "Braga(PRT)",
            "Celtic(SCO)",
            "CFRCluj(ROU)",
            "CrvenaZvezda(SRB)",
            "CSKA(RUS)",
            "CSKASofia(BLG)",
            "DinamoZagreb(HRV)",
            "Dundolk(IRE)",
            "Feyenoord(NLD)",
            "Gent(BEL)",
            "Granada(ESP)",
            "HapoelBeerSheva(ISR)",
            "Hoffenheim(DEU)",
            "LASK(AUT)",
            "Lille(FRA)",
            "Lech(POL)",
            "Leicester(ENG)",
            "Ludogorets(BGR)",
            "MacCabiTA(ISR)",
            "Milan(ITA)",
            "Molde(NOR)",
            "Napoli(ITA)",
            "Nice(FRA)",
            "Omonia(CYP)",
            "PAOK(GRC)",
            "PSV(NLD)",
            "Qarabag(AZE)",
            "Rangers(SCO)",
            "RapidWien(AUT)",
            "RealSociedad(ESP)",
            "Rieka(HRV)",
            "Roma(ITA)",
            "Sivasspor(TUR)",
            "Slovan(SVK)",
            "SpartaPrague(CZE)",
            "Standard(BEL)",
            "SlaviaPraha(CZE)",
            "Tottenham(ENG)",
            "Villarreal(ESP)",
            "Wolfsberger(AUT)",
            "YoungBoys(CHE)",
            "Zorya(UKR)",
        };

        String league = "EuropaLeague";
        String season = "20-21";

        for (int i=0; i<teams.length; i++){
            //Referee ref = new Referee(refs[i]);
            //ref.pushRefToFile(league, season);
            Team team = new Team(teams[i]);
            team.pushTeamToFile(league, season);
        }

        String[] refs = new String[]{
                "Al-Hakim",
                "Aytekin",
                "Bastien",
                "Brych",
                "Buquet",
                "Collum",
                "DelCerroGrande",
                "Durieux",
                "Ekberg",
                "Eskas",
                "Gestranius",
                "Glova",
                "Grinfeld",
                "Harvey",
                "Hategan",
                "Jaccottet",
                "Jug",
                "Kabakov",
                "Karasev",
                "Kassai",
                "Kehlet",
                "Kovacs",
                "Kralovec",
                "Kruashvili",
                "Kruzliak",
                "Kuipers",
                "Kulbakov",
                "Madden",
                "MallencoAlberto",
                "ManzanoJesusGil",
                "Marciniak",
                "Massa",
                "MateuLahoz",
                "Mazeika",
                "Mazic",
                "Meshkov",
                "Muntean",
                "Oliver",
                "Pawson",
                "Rocchi",
                "Rumsas",
                "Sidiropoulos",
                "Siebert",
                "Skomina",
                "SoaresDias",
                "Stieler",
                "Taylor",
                "Tierney",
                "Treimanis",
                "Turpin",
                "Vincic",
                "Visser",
                "Zwayer"
        };

        for (int i=0; i<refs.length; i++){
            Referee ref = new Referee(refs[i]);
            ref.pushRefToFile(league, season);
        }

        League.resetLeagueInfo(league, season);*/


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того, чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процессах
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            // ...
            public void windowClosing(WindowEvent event) {
                //Settings.deleteAllFilesFolder("tmp/");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    public static double roundResult(double d, int precise) {
        precise = (int) Math.pow(10,precise);
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }

    void writeFile() throws FileNotFoundException {
        try {
            // отрываем поток для записи
            FileOutputStream fos = new FileOutputStream("");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            // пишем данные
            for (int i = 0; i < 10; i++) {
                String str = String.valueOf(i);
                bw.write(str);
                bw.newLine();
            }
            // закрываем поток
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Конец процедуры записи списка в файл

    public static String[] readTxtFile(String fileName){
        String resultS = "Выберите команду\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return resultS.split("\n");
    }

    public static String[] readTxtFileForRef(String fileName){
        String resultS = "Выберите судью\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return resultS.split("\n");
    }

    public static void setDataForLeague(){
        //String oldSeason = "19-20";
        String newSeason = "20-21";
        String leagueName = "UKR.PrLeague";

        String[] teams = new String[]{
                "Desna",
                "Dnepr1",
                "DynamoKyiv",
                "Ingulets",
                "Kolos",
                "Lviv",
                "Mariupol",
                "Minaj",
                "Olexandria",
                "OlimpikDonetsk",
                "RukhVinniki",
                "Shakhtar",
                "Vorskla",
                "Zorya",

        };

        //JFileChooser fileChooser = new JFileChooser(Settings.getPathToDatabase() + oldSeason + "/" + leagueName + "/Referees/");
        //String[] directoryList = fileChooser.getCurrentDirectory().list();

        String[] refsList = new String[]{
                "AranovskiyEvgeniy",
                "BondarenkoDmitro",
                "BondarIvan",
                "BoykoSergey",
                "DerdoAlexandr",
                "DerevinskiyAlexey",
                "IvanovYuriy",
                "KaziryatskuyMaxim",
                "KopievskiyViktor",
                "KovalenkoAndrey",
                "KozykYaroslav",
                "KrivushkinDmitriy",
                "KutakovDmitriy",
                "PaskhalIgor",
                "RomanovVitaliy",
                "ShurmanDenis",
                "TrukhanovKonstantin",
        };

        Referee.addRefereesTXT(leagueName, newSeason, refsList);

        ArrayList<String> listRefs = new ArrayList<>();

        for (int i=0; i<refsList.length;i++){
            String s = refsList[i].replaceAll(".xml", "");
            if (!s.contains("txt")){
                listRefs.add(s);
            }
        }
        for (int i=0; i<listRefs.size(); i++){
            Referee ref = new Referee(listRefs.get(i));
            ref.pushRefToFile(leagueName, newSeason);
        }

        for (int i=0; i<teams.length; i++){
            Team team = new Team(teams[i]);
            team.pushTeamToFile(leagueName, newSeason);
        }

        League.resetLeagueInfo(leagueName, newSeason);

        int ttt = 0;
    }
}
