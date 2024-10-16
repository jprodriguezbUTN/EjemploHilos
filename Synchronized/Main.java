/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Synchronized;

/**
 *
 * @author jprod
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();

        Worker worker1 = new Worker(taskQueue, "Trabajador 1");
        Worker worker2 = new Worker(taskQueue, "Trabajador 2");

        worker1.start();
        worker2.start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    taskQueue.addTask("Tarea " + i);
                    int time = (int)((Math.random()*9000)+1000);
                    Thread.sleep(time); // Espera antes de añadir otra tarea
                }
            } catch (InterruptedException e) {
                System.out.println("Agregar tarea se interumpio.");
            }
        }).start();
    }
}
