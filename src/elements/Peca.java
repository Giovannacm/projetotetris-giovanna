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
    private String nome;            //Nome da variável de acordo com o seu formato
    private int posX;               //Variável que armazena a posição (no eixo x) da peça na metriz do jogo
    private int posY;               //Variável que armazena a posição (no eixo y) da peça na metriz do jogo

    public Peca(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String nome, Color cor) {  //Construtor para a peca
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.nome = nome;
        switch (nome) {     //Mudança da cor da Peca de acordo com o seu formato, no caso, o nome
            case "I":
                cor = Color.LIGHTBLUE;
                break;
            case "O":
                cor = Color.YELLOW;
                break;
            case "T":
                cor = Color.PURPLE;
                break;
            case "S":
                cor = Color.GREEN;
                break;
            case "Z":
                cor = Color.RED;
                break;
            case "J":
                cor = Color.DARKBLUE;
                break;
            case "L":
                cor = Color.ORANGE;
        }
        this.a.setFill(cor);  //Set na cor de cada retangulo
        this.b.setFill(cor);
        this.c.setFill(cor);
        this.d.setFill(cor);
    }
    //Métodos para acessar/mudar a posição do elemento;
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
