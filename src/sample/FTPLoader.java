package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPLoader {

    public static void uploadFile(String hostAddress, String log, String password, String pathOnServer, String pathToFile) throws FileNotFoundException {
        FTPClient fClient = new FTPClient();
        FileInputStream fInput = new FileInputStream(pathToFile);
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            fClient.storeFile(pathOnServer, fInput);
            fClient.logout();
            fClient.disconnect();
            fInput.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static String downloadFile(String hostAddress, String log, String password, String pathOnServer, String pathToSave) throws FileNotFoundException {
        String result;
        FTPClient fClient = new FTPClient();
        FileOutputStream fOutput = new FileOutputStream(pathToSave);
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            fClient.retrieveFile(pathOnServer, fOutput);
            fClient.logout();
            fClient.disconnect();
            fOutput.close();
            result = "Success";
        } catch (IOException ex) {
            System.err.println(ex);
            result = ex.toString().split("Exception: ")[1];
        }
        return result;
    }

    public static boolean checkSupportDirectory(String hostAddress, String log, String password){
        boolean result = false;
        FTPClient fClient = new FTPClient();

        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            FTPFile[] ftpFiles = fClient.listFiles("/data/football/support/");
            if (ftpFiles.length > 0){
                result = true;
            }
            fClient.logout();
            fClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
