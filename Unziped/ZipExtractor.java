/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unziped;

import ObserverPattern.Observable;
import ObserverPattern.Observer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author jprod
 */
public class ZipExtractor implements Observable{
    private String fileName;
    private Observer observer;
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void extractZip(){
        String destDir=System.getProperty("user.dir")+"\\Extract\\";
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();

        byte[] buffer = new byte[1024];
        try {
            notifyObserver("Calculando Tamaño de archivos...");
            long totalUncompressedSize = calculateTotalUncompressedSize(fileName);

            long totalBytesRead = 0;

            FileInputStream fis = new FileInputStream(fileName);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDir + File.separator + fileName);
                notifyObserver("Descomprimiendo archivos...");

                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    new File(newFile.getParent()).mkdirs();

                    FileOutputStream fos = new FileOutputStream(newFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        bos.write(buffer, 0, len);
                        totalBytesRead += len;

                        int progress = (int) ((totalBytesRead * 100) / totalUncompressedSize);
                        notifyObserver(progress);
                    }
                    bos.close();
                }
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            fis.close();
            notifyObserver("Extraccion completa.");            
        } catch (IOException e) {
            notifyObserver("Erroe al extraer archivo"); 
        }
    }
    
    private long calculateTotalUncompressedSize(String zipFilePath) throws IOException {
        long totalSize = 0;

        FileInputStream fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            totalSize += zipEntry.getSize();
            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
        fis.close();

        return totalSize;
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
