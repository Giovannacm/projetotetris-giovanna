/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author giova
 */
public class Componente {
    private Rectangle r;
    private boolean fixa;    //Variável booleana que indica se esse componente é fixo ou não (dessa forma, conseguimos diferencias peca=false e obstaculo=true)
    
    public Componente(int largura, int altura, boolean fixa)
    {
        this.r = new Rectangle(largura, altura);
        this.fixa = fixa;
    }
    public Rectangle getR() 
    {
        return r;
    }
    public void setR(Rectangle r) 
    {
        this.r = r;
    }
    public boolean isFixa() {
        return fixa;
    }
    public void setFixa(boolean fixa) 
    {
        this.fixa = fixa;
    }
    public void setX(int x)
    {
        this.r.setX(x);
    }
    public void setY(int y)
    {
        this.r.setY(y);
    }
    public void mudaCor(Paint cor)
    {
        this.r.setFill(cor);
    }
    public int getX()
    {
        return((int)this.r.getX());
    }
    public int getY()
    {
        return((int)this.r.getY());
    }
}
