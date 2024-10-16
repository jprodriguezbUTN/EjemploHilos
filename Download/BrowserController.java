/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Download;

import ObserverPattern.Observer;


/**
 *
 * @author jprod
 */
public class BrowserController implements Observer {
    private Browser model;
    private FrmBrowser view;

    public BrowserController(FrmBrowser view) {
        model = new Browser();
        model.addObserver(this);
        this.view = view;
    }
    
    public void download(String link,String fileName){
        model.setLink(link);
        model.setFileName(fileName);
        model.download();
    }
    
    public void update(Object value){
        view.display(value);
    }
    
    
}
