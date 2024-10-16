/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search;

import ObserverPattern.Observable;
import ObserverPattern.Observer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jprod
 */
public class Searcher extends Thread implements Observable {
    private String id;
    private Observer observer;

    public void setId(String id) {
        this.id = id;
    }
    
    
    public void run(){
        search();
    }
    
    public void search(){
        String filePath= System.getProperty("user.dir")+"\\Extract\\Padron_Completo.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 7) {
                    String id = data[0];                    
                    String name = data[5];
                    String firstName = data[6];
                    String lastName = data[7];
                    String fullName = name.trim() + " " + firstName.trim() + " " + lastName.trim();
                    notifyObserver(fullName);
                    Thread.sleep((long) 0.5);
                    if (this.id.equals(id))return;
                } else {
                    notifyObserver("Formato incorrecto en la línea: " + line);
                }
            }
        } catch (IOException e) {
            notifyObserver("Error al leer el archivo: " + e.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
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
