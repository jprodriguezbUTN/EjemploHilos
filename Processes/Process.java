/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jprod
 */
public class Process {
    protected String nombre;

    public Process(String nombre) {
        this.nombre = nombre;
    }
    
    public void process(){
        for (int i = 1; i <= 15; i++) {
            System.out.println("Proceso "+
                    nombre+ " ejecucion "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
