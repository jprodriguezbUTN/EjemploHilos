/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Synchronized;

/**
 *
 * @author jprod
 */
public class Worker extends Thread {
    private final TaskQueue taskQueue;

    public Worker(TaskQueue taskQueue, String name) {
        super(name);
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                
                String task = taskQueue.getTask();
                
                System.out.println(getName() + " trabajando en la tarea: " + task);
                int time = (int)((Math.random()*9000)+1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " Se interumpio.");
        }
    }
}
