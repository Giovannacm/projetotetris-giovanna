/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import control.GameScreen;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author giova
 */
public class Peca {                     //Criando a Classe que repesenta os elementos do Tetris, através da biblioteca gráfica JavaFX
    private Componente a, b, c, d;      //Cada objeto do tipo Componente é um componente da peça, dessa forma, todas as peças possuem 4 retangulos, onde diferem apenas na disposição desses elementos
    private char nome;                  //Nome da variável de acordo com o seu formato

    public Peca(Componente a, Componente b, Componente c, Componente d, char nome) //Construtor para a peca
    {  
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.nome = nome;
    }
    public Componente getA() 
    {
        return a;
    }
    public Componente getB() 
    {
        return b;
    }
    public Componente getC() 
    {
        return c;
    }
    public Componente getD() 
    {
        return d;
    }
    public char getNome() {
        return nome;
    }
} 