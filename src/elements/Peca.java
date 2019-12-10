/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import control.GameController;
import javafx.scene.shape.Rectangle;
import utils.Consts;
import utils.Drawing;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 * 
 * A classe Peca possui varios componentes, onde diferem apenas em suas posições. Como foi dito anteriormente, a classe Componente é um "molde" para cada "quadradinho" que faz parte da peça.s
 */
public class Peca {                     //Criando a classe que repesenta as peças do tetris
    private Componente a, b, c, d;      //Cada objeto do tipo Componente é um elemento da peça, dessa forma, todas as peças possuem 4 componentes, onde diferem apenas na disposição desses elementos
    private char nome;                  //Nome da variável de acordo com o seu formato

    public Peca(Componente a, Componente b, Componente c, Componente d, char nome) //Construtor para a peca
    {  
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.nome = nome;
    }
    
    
    /**
     * Método responsável por mover a peça para a direita.
     * Inicialmente é verificado se é possível mover cada retangulo para a direita a partir de um método do GameController.
     * Se for, a posição de cada retângulo é mudada, somando 1 (TamRec) no x, ou seja andando um retangulo para a direita.
     */
    public void moveDireita() 
    {
        //Mover a peça para a direita, implica em mover todos os retangulos pertencentes à ela por meio do incremento da posição x
        if(GameController.movimentoValidoD(a) && GameController.movimentoValidoD(b) && GameController.movimentoValidoD(c) && GameController.movimentoValidoD(d))
        {
            a.setX(a.getX() + Consts.TamRec);
            b.setX(b.getX() + Consts.TamRec);
            c.setX(c.getX() + Consts.TamRec);
            d.setX(d.getX() + Consts.TamRec);
        }
    }
    
    
    /**
     * Método responsável por mover a peça para a esquerda.
     * Inicialmente é verificado se é possível mover cada retangulo para a esquerda a partir de um método do GameController. 
     * Se for, a posição de cada retângulo é mudada, subtraindo 1 (TamRec) no x, ou seja andando um retangulo para a esquerda.
     */
    public void moveEsquerda() 
    { 
        //Mover a peça para a esquerda, implica em mover todos os retangulos pertencentes à ela por meio do decrementos da posição x
        if(GameController.movimentoValidoE(a) && GameController.movimentoValidoE(b) && GameController.movimentoValidoE(c) && GameController.movimentoValidoE(d))
        {
            a.setX(a.getX() - Consts.TamRec);
            b.setX(b.getX() - Consts.TamRec);
            c.setX(c.getX() - Consts.TamRec);
            d.setX(d.getX() - Consts.TamRec);
        }
    }
    
    
    /**
     * Método responsável por mover a peça para baixo.
     * Inicialmente é verificado se é possível mover cada retangulo para baixo a partir de um método do GameController. 
     * Se for, a posição de cada retângulo é mudada, somando 1 (TamRec) no y, ou seja andando um retangulo para baixo.
     * Além disso, é atualizado a pontuação (do GameController) pois toda vez que uma peça move pra baixo a pontuação é incrementada.
     * Se chegar em 1000 pontos, a velocidade aumenta em 30%.
     */
    public void moveBaixo() 
    {
        if(GameController.movimentoValidoB(a) && GameController.movimentoValidoB(b) && GameController.movimentoValidoB(c) && GameController.movimentoValidoB(d))
        {
            a.setY(a.getY() + Consts.TamRec);
            b.setY(b.getY() + Consts.TamRec);
            c.setY(c.getY() + Consts.TamRec);
            d.setY(d.getY() + Consts.TamRec);
            GameController.setPontuacao(GameController.getPontuacao()+1);   //Incrementando a pontuação
            Drawing.getPontuacaoTexto().setText("Pontuação: " + Integer.toString(GameController.getPontuacao()));   //Alterando o texto da pontuação (da classe Drawing) com o novo valor
            if(GameController.getPontuacao()==100)                               //Se chegou em 100 prontos, a velociade aumenta em 30%
                GameController.setGravidade(GameController.getGravidade()*1.30); //Dando set na variável gravidade do GameController
	}
    }
    
    
    /**
     * Método responsável por rotacionar uma peça.
     * Inicialmente é definido a posição inicial da matriz imaginária utilizada na rotação (onde foi desenvolvido uma transformação linear para isso) a partir do eixo de rotação (componente b de cada peça).
     * Temos que todas as peças rotacionam em torno do retangulo b, dessa forma o ponto inicial da matriz é determinada por meio da posição de b.
     * Após a atribuição de valores para o x e y inicial da matriz, é aplicado um operador linar sobre cada retângulo.
     * Esse operador linear só é aplicado se puder rotacionar todos os outros componentes, isso é verificado por meio de um método do GameController.
     */
    public void rotaciona()
    {	
        //Variáveis que armazenam o valor de x/y inicial da matriz imaginária 4x4 (utilizada na rotação) de acordo com o eixo de rotação. Como todas as pecas rotacionam em torno do componente b, conseguimos identificar o início da matriz imaginária. 
        int xiM = (int) b.getX() - Consts.TamRec;      //Seu x é o x do eixo - 1
        int yiM = (int) b.getY() - 2*Consts.TamRec;    //Seu y é o y do eixo - 2
        if(GameController.podeRotacionar(a.getR(), xiM, yiM)&&GameController.podeRotacionar(b.getR(), xiM, yiM)&&GameController.podeRotacionar(c.getR(), xiM, yiM)&&GameController.podeRotacionar(d.getR(), xiM, yiM))  //Se pode rotacionar todos os retangulos, o operador linear é aplicado em cada componente da peça para rotacionar
        {
            operadorLinear(a.getR(), xiM, yiM);
            operadorLinear(b.getR(), xiM, yiM);
            operadorLinear(c.getR(), xiM, yiM);
            operadorLinear(d.getR(), xiM, yiM);
        }
    }
    
    
    /**
     * Método responsável por aplicar o operador linear desenvolvido para rotacionar a peça.
     * É somado xMatriz e yMatriz em x e y respectivamente, pois estamos trabalhando na tela toda, e não apenas na matriz inaginária onde foi desenvolvido a transformação.
     * @param r         (retangulo onde será aplicado o operador linear)
     * @param xMatriz   (x inicial da matriz imaginária)
     * @param yMatriz   (y inicial da matriz imaginária)
     */
    public void operadorLinear(Rectangle r, int xMatriz, int yMatriz) //Operador linear: T(x,y)=(3-y, x+1)
    {
        int dX = (int)r.getX() - xMatriz;           //dX representa a distancia (com o valor de x) do retangulo até a "matriz" imaginária da rotação
        int dY = (int)r.getY() - yMatriz;           //dY representa a distancia (com o valor de y) do retangulo até a "matriz" imaginária de rotação
        r.setX(xMatriz + 3*Consts.TamRec - dY);            //Aplicando a transformação, ou seja, x=3-y
        r.setY(yMatriz + dX + Consts.TamRec);              //Aplicando a transformação, ou seja, y=x+1
    }
    
    
    //Métodos para acessar os atributos privados da classe
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
    public char getNome() 
    {
        return nome;
    }
} 