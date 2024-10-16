/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search;

import ObserverPattern.Observer;

/**
 *
 * @author jprod
 */
public class SearcherController implements Observer {
    private Searcher model;
    private FrmSearcher view;

    public SearcherController(FrmSearcher view) {
        this.view = view;
    }
    
    public void search(String id){
        model = new Searcher();
        model.addObserver(this);
        model.setId(id);
        model.start();
    }
    
    public void update(Object value){
        view.display(value);
    }
}
