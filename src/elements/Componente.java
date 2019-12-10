/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 * 
 * Reescrita da classe Element do template. Não é abstrata pois essa classe é um "molde" para cada elemento de uma peça do jogo (cada peça terá 4 Componentes), logo será instanciada.
 * Além disso, 1 Componente representa um obstáculo (acredito que não seria necessário usar uma herança de implementação para criar apenas uma classe Obstáculo), é passado true para o atributo fixo (ver método adicionaObstaculos no GameController).
 * Como o atributo r (Rectangle do JavaFx) já possui sua posição, a classe Position do template não foi utilizada, uma vez que já está sendo utilizado as propriedades desse objeto do JavaFx.
 * Os métodos de manupulação de sua posição (x e y) já estão incluidos nessa classe.
 */
public class Componente {       //Classe que será utilizada para representar cada elemento da peça (Reescrita da classe Element)
    private Rectangle r;        //Possui um retangulo (JavaFx)
    private boolean fixo;       //E uma variável booleana que indica se esse componente é fixo ou não (dessa forma, conseguimos diferenciar peca=false e obstaculo=true)
    
    public Componente(int largura, int altura, boolean fixo)    //Construtor para o componente
    {
        this.r = new Rectangle(largura, altura);    //Instanciando um objeto Rectangle (JavaFX)
        this.fixo = fixo;
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
    public boolean isFixo() 
    {
        return fixo;
    }
    public void setX(int x)
    {
        this.r.setX(x);
    }
    public int getX()
    {
        return((int)this.r.getX());
    }
    public void setY(int y)
    {
        this.r.setY(y);
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
