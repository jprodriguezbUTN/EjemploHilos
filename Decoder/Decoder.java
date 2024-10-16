/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Decoder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jprod
 */
public class Decoder {
    FrmDecoder frm;
    private int dig1;
    private int dig2;
    private int dig3;
    private int dig4;
    private int dig5;

    public Decoder(FrmDecoder frm) {
        this.frm = frm;
        this.dig1=1;
        this.dig2=5;
        this.dig3=1;
        this.dig4=0;
        this.dig5=4;
    }
    
    public void decifrar(){
        int d1,d2,d3,d4,d5;
        do{
            d1=(int) (Math.random()*10);
            d2=(int) (Math.random()*10);
            d3=(int) (Math.random()*10);
            d4=(int) (Math.random()*10);
            d5=(int) (Math.random()*10);
            frm.display(d1, d2, d3, d4, d5);
        }while(d1!=dig1 ||d2!=dig2 ||d3!=dig3||d4!=dig4||d5!=dig5);

    }
}
