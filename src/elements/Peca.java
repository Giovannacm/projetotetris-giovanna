/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author giova
 */
public class Peca {                 //Criando a Classe que repesenta os elementos do Tetris, através da biblioteca gráfica JavaFX
    private Rectangle a, b, c, d;   //Cada objeto do tipo Rectangle é um componente da peça, dessa forma, todas as pessas possuem 4 retangulos, onde diferem apenas na disposição desses elementos
    private Color cor;              //Variável que armazena a cor da peça
    private String nome;

    public Peca(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String nome, Color cor) {  //Construtor para a peca
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.nome = nome;
        switch (nome) {     //Mudança da cor da Peca de acordo com o seu formato, no caso, o nome
            case "j":
                cor = Color.BLUE;
                break;
            case "l":
                cor = Color.YELLOW;
                break;
            case "o":
                cor = Color.RED;
                break;
            case "s":
                cor = Color.GREEN;
                break;
            case "t":
                cor = Color.GREY;
                break;
            case "z":
                cor = Color.WHITE;
                break;
            case "i":
                cor = Color.PINK;
        }
        this.a.setFill(cor);  //Set na cor de cada retangulo
        this.b.setFill(cor);
        this.c.setFill(cor);
        this.d.setFill(cor);
    }
}
