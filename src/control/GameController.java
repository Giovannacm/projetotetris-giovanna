package control;

import elements.Element;
import elements.Lolo;
import elements.Peca;
import utils.Consts;
import java.awt.Graphics;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameController {
    //Uso das constantes da classe Consts
    private static int XMAX = Consts.XMAX;
    private static int YMAX = Consts.YMAX;
    private static int TamRec = Consts.TamRec;
    private static int[][]Tela = GameScreen.Tela;
     
    
    /**
     * Função responsável por gerar uma peça aleatória (de acordo com o valorAreatorio) e colocá-la na parte de cima da tela centrada no meio.
     *  Inicialmente é criado um retangulo com o tamanho especificado na constante.
     *  Depois é analisado o valorAleatorio, e de acordo com esse valor, cada retangulo é posicionado no meio de acordo com a peça que sejá gerada.
     * @return peca que foi criada
     */
    public static Peca proximaPeca()
    {        
        int valorAleatorio = (int) (Math.random() * 6);     //Pegando um valor aleatório (0 à 6) para determinar qual das 7 peça serão colocadas no jogo
	String nome = "";
	Rectangle a = new Rectangle(TamRec-1, TamRec-1);
        Rectangle b = new Rectangle(TamRec-1, TamRec-1); 
        Rectangle c = new Rectangle(TamRec-1, TamRec-1);
        Rectangle d = new Rectangle(TamRec-1, TamRec-1);
        switch(valorAleatorio)
        {
            case(0):
                a.setX(XMAX / 2 - TamRec - TamRec);
		b.setX(XMAX / 2 - TamRec);
		c.setX(XMAX / 2);
		d.setX(XMAX / 2 + TamRec);
		nome = "I";
                break;
            case(1):
                a.setX(XMAX / 2 - TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 - TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2);
                d.setY(TamRec);
                nome = "O";
                break;
            case(2):
                a.setX(XMAX / 2 - TamRec);
		b.setX(XMAX / 2);
		c.setX(XMAX / 2);
		c.setY(TamRec);
		d.setX(XMAX / 2 + TamRec);
		nome = "T";
                break;
            case(3):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 - TamRec);
                d.setY(TamRec);
                nome = "S";
                break;
            case(4):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 + TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec + TamRec);
                d.setY(TamRec);
                nome = "Z";
                break;
            case(5):
                a.setX(XMAX / 2 - TamRec);
                b.setX(XMAX / 2 - TamRec);
                b.setY(TamRec);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec);
                d.setY(TamRec);
                nome = "J";
                break;
            case(6):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2 - TamRec);
                b.setY(TamRec);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec);
                d.setY(TamRec);
                nome = "L";
                break;
        }
	return new Peca(a, b, c, d, nome);
    }
    
    
    /**
     * Função de utiliza o javaFx para realizar a leitura do teclado
     *  de acordo com a tecla lida, uma função é chamada.
     * @param peca 
     */
    public static void moverTeclaPressionada(Peca peca)
    {
        Scene cenaJogo = GameScreen.getScene();
        cenaJogo.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        moveDireita(peca);
                        break;
                    case LEFT:
                        moveEsquerda(peca);
                        break;
                    //case DOWN:
                    //    moveBaixo(peca);
                    //break;
                    //case SPACE:
                    //    rotaciona(peca);
                    //    break;
		}
            }
	});
    }
    
    
    /**
     * Função responsável por mover a peça para a direita.
     * Inicialmente é verificado se é possível mover cada retangulo para a direita, 
     *      se for, é verificado se não há nenhuma peça nequela posição (o valor da
     *      matriz naquela posição é 0), se for, o a posição de cada retângulo é mudada.
     * @param peca 
     */
    public static void moveDireita(Peca peca) 
    {
        //Mover a peça para a direita, implica em mover todos os retangulos pertencentes à ela por meio do incremento da posição x
        if(movimentoValidoD(peca.a) && movimentoValidoD(peca.b)&&movimentoValidoD(peca.c)&&movimentoValidoD(peca.d))
        {
            peca.a.setX(peca.a.getX() + TamRec);
            peca.b.setX(peca.b.getX() + TamRec);
            peca.c.setX(peca.c.getX() + TamRec);
            peca.d.setX(peca.d.getX() + TamRec);
        }
    }
    public static boolean movimentoValidoD(Rectangle r)
    {
        if((r.getX() + TamRec <= XMAX - TamRec)&&((Tela[(int)r.getX()/TamRec + 1][(int)r.getY()/TamRec] == 0)))
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * Função responsável por mover a peça para a esquerda.
     * Inicialmente é verificado se é possível mover cada retangulo para a esquerda, 
     *      se for, é verificado se não há nenhuma peça nequela posição (o valor da
     *      matriz naquela posição é 0), se for, o a posição de cada retângulo é mudada.
     * @param peca 
     */
    public static void moveEsquerda(Peca peca) 
    {
        //Mover a peça para a esquerda, implica em mover todos os retangulos pertencentes à ela por meio do decrementos da posição x
        if(movimentoValidoE(peca.a) && movimentoValidoE(peca.b)&&movimentoValidoE(peca.c)&&movimentoValidoE(peca.d))
        {
            peca.a.setX(peca.a.getX() - TamRec);
            peca.b.setX(peca.b.getX() - TamRec);
            peca.c.setX(peca.c.getX() - TamRec);
            peca.d.setX(peca.d.getX() - TamRec);
        }
    }
    public static boolean movimentoValidoE(Rectangle r)
    {
        if((r.getX() - TamRec >= 0)&&(Tela[(int)r.getX()/TamRec - 1][(int)r.getY()/TamRec] == 0))
        {
            return true;
        }
        return false;
    }
     
    
    /**
     * 
     * @param peca 
     */
    private static void moveBaixo(Peca peca) 
    {
        
    }
    
    
    /**
     * 
     * @param peca 
     */
    private static void rotaciona(Peca peca)
    {
        
    }
}
    /*
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;
        
        Lolo lLolo = (Lolo)e.get(0);
        if (!isValidPosition(e, lLolo)) {
            lLolo.backToLastPosition();
            
            return;
        }
        
        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(lLolo.overlap(eTemp))
                if(eTemp.isMortal())
                    e.remove(eTemp);
        }
    }
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
}*/
