/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import ObserverPattern.*;

/**
 *
 * @author jprod
 */
public class Browser extends Thread implements Observable{
    private String link;
    private String fileName;
    private Observer observer;

    public void setLink(String link) {
        this.link = link;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
        
    public void download(){
        try {
            notifyObserver("Conectando...");
            HttpURLConnection connection=establishConnection(link);
            if (connection==null) return;
            
            int fileSize = connection.getContentLength();
            
            InputStream input = new BufferedInputStream(connection.getInputStream());
            FileOutputStream output = new FileOutputStream(getDestination(fileName));
            
            byte[] buffer = new byte[1024];
            int bytesSize = 0;
            long totalBytesRead = 0;
            notifyObserver("Iniciando Descarga...");
            while ((bytesSize = input.read(buffer)) != -1) {
                totalBytesRead += bytesSize;
                output.write(buffer, 0, bytesSize);
                calculatePercentage(totalBytesRead, fileSize);
            }
            input.close();
            output.close();
            notifyObserver("Descarga completada.");
        } catch (IOException e) {
            notifyObserver("Error durante la descarga: " + e.getMessage());
        }
    }
    
    private HttpURLConnection establishConnection(String link) throws IOException{
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();            
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            notifyObserver("Error al conectar: " + responseCode);
            return null;
        }
        return connection;
    }
    
    private String getDestination(String fileName){
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + "/" + fileName;
    }
    
    public void calculatePercentage(long totalBytesRead,int fileSize){
        int porcentaje = (int)((totalBytesRead * 100) / (double)fileSize);
        notifyObserver(porcentaje);
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer=observer;
    }

    @Override
    public void notifyObserver(Object value) {
        observer.update(value);
    }
}
