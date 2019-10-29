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
public class Peca {                 //Criando a Classe que repesenta os elementos do Tetris, através da biblioteca gráfica JavaFX
    public Rectangle a, b, c, d;   //Cada objeto do tipo Rectangle é um componente da peça, dessa forma, todas as pessas possuem 4 retangulos, onde diferem apenas na disposição desses elementos
    private Color cor;              //Variável que armazena a cor da peça
    public String nome;            //Nome da variável de acordo com o seu formato

    public Peca(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String nome) {  //Construtor para a peca
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.nome = nome;
        switch (nome) {     //Mudança da cor da Peca de acordo com o seu formato, no caso, o nome
            case "I":
                this.cor = Color.LIGHTBLUE;
                break;
            case "O":
                this.cor = Color.YELLOW;
                break;
            case "T":
                this.cor = Color.PURPLE;
                break;
            case "S":
                this.cor = Color.GREEN;
                break;
            case "Z":
                this.cor = Color.RED;
                break;
            case "J":
                this.cor = Color.DARKBLUE;
                break;
            case "L":
                this.cor = Color.ORANGE;
        }
        this.a.setFill(this.cor);  //Set na cor de cada retangulo
        this.b.setFill(this.cor);
        this.c.setFill(this.cor);
        this.d.setFill(this.cor);
    }
}
