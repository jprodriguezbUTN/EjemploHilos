/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Synchronized;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author jprod
 */
public class TaskQueue {
    private final Queue<String> tasks = new LinkedList<>();

    public /*synchronized*/ void addTask(String task) {
        tasks.add(task);
        System.out.println("Nueva tarea :" + task);
        //notify();
    }

    public /*synchronized*/ String getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " esperando nuevas tareas ...");
            //wait();
        }
        String task = tasks.poll();
        System.out.println(Thread.currentThread().getName() + " obteniendo tarea: " + task);
        return task;
    }
}
