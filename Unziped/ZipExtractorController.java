/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unziped;

import ObserverPattern.Observer;

/**
 *
 * @author jprod
 */
public class ZipExtractorController implements Observer {
    private ZipExtractor model;
    private FrmZipExtractor view;

    public ZipExtractorController(FrmZipExtractor view) {
        model = new ZipExtractor();
        model.addObserver(this);
        this.view = view;
    }
    
    public void Unzip(String fileName){
        model.setFileName(fileName);
        model.extractZip();
    }
    
    public void update(Object value){
        view.display(value);
    }
}
