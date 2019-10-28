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
    public static int[][] Tela = GameScreen.Tela;   //Acesso à tela do jogo para conseguir mudar a posição dos elementos

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
    /**
     * Função responsável por mover a peça para a direita.
     * Inicialmente é verificado se é possível mover cada retangulo para a direita, 
     *      se for, é verificado se não há nenhuma peça nequela posição (o valor da
     *      matriz naquela posição é 0), se for, o a posição de cada retângulo é mudada.
     * @param peca 
     */
    public void moveDireita(Peca peca) 
    {
        //Mover a peça para a direita, implica em mover todos os retangulos pertencentes à ela por meio do incremento da posição x
        if((peca.a.getX() + 1 < 18)&&(peca.b.getX() + 1 < 18)&&(peca.c.getX() + 1 < 18)&&(peca.d.getX() + 1 < 18))
        {
            if((Tela[(int)peca.a.getX() + 1][(int)peca.a.getY()] == 0)&&(Tela[(int)peca.b.getX() + 1][(int)peca.b.getY()] == 0)&&(Tela[(int)peca.c.getX() + 1][(int)peca.c.getY()] == 0)&&(Tela[(int)peca.d.getX() + 1][(int)peca.d.getY()] == 0))
            {
                peca.a.setX(peca.a.getX() + 1);
		peca.b.setX(peca.b.getX() + 1);
		peca.c.setX(peca.c.getX() + 1);
		peca.d.setX(peca.d.getX() + 1);
            }
	}
    }
    public void moveEsquerda(Peca peca) 
    {
        //Mover a peça para a esquerda, implica em mover todos os retangulos pertencentes à ela por meio do decrementos da posição x
        if((peca.a.getX() - 1 >= 0)&&(peca.b.getX() - 1 >= 0)&&(peca.c.getX() - 1 >= 0)&&(peca.d.getX() - 1 >= 0))
        {
            if((Tela[(int)peca.a.getX() - 1][(int)peca.a.getY()] == 0)&&(Tela[(int)peca.b.getX() - 1][(int)peca.b.getY()] == 0)&&(Tela[(int)peca.c.getX() - 1][(int)peca.c.getY()] == 0)&&(Tela[(int)peca.d.getX() - 1][(int)peca.d.getY()] == 0))
            {
                peca.a.setX(peca.a.getX() - 1);
		peca.b.setX(peca.b.getX() - 1);
		peca.c.setX(peca.c.getX() - 1);
		peca.d.setX(peca.d.getX() - 1);
            }
	}
    }
}
