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
public class Componente {       //Classe que será utilizada para representar cada elemento da peça
    private Rectangle r;        //Possui um retangulo (JavaFx)
    private boolean fixa;       //E uma variável booleana que indica se esse componente é fixo ou não (dessa forma, conseguimos diferenciar peca=false e obstaculo=true)
    
    public Componente(int largura, int altura, boolean fixa)    //Construtor para o componente
    {
        this.r = new Rectangle(largura, altura);
        this.fixa = fixa;
    }
    //Métodos de acesso/modificação dos atributos privados da classe, assim como as propriedades do objeto do tipo Rectangle (JavaFx)
    public Rectangle getR() 
    {
        return r;
    }
    public void setR(Rectangle r) 
    {
        this.r = r;
    }
    public boolean isFixa() 
    {
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
    public int getX()
    {
        return((int)this.r.getX());
    }
    public int getY()
    {
        return((int)this.r.getY());
    }
    public void mudaCor(Paint cor)  //Mudando a cor do retangulo a partir de uma função do javaFx
    {
        this.r.setFill(cor);
    }
}
