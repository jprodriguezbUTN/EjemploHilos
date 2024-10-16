/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Processes;

/**
 *
 * @author jprod
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Process p1= new Process("Guardar");
        p1.process();
        
        BigProcess bp1= new BigProcess("Descargar Imagen ISO");
        bp1.longProcess();
        
        Process p2= new Process("Descargar");
        p2.process();
    }
    
}
