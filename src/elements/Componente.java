/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author giova
 */
public class Componente {
    private Rectangle r;
    private boolean fixa;    //Variável booleana que indica se esse componente é fixo ou não (dessa forma, conseguimos diferencias pea e obstaculo)
    
    public Componente(Rectangle r, boolean fixa)
    {
        this.r=r;
        this.fixa=fixa;
    }
    public Rectangle getR() {
        return r;
    }
    public void setR(Rectangle r) {
        this.r = r;
    }
    public boolean isFixa() {
        return fixa;
    }
    public void setFixa(boolean fixa) {
        this.fixa = fixa;
    }
}
