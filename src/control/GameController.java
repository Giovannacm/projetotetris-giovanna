package control;

import elements.Element;
import elements.Lolo;
import elements.Peca;
import utils.Consts;
import java.awt.Graphics;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameController {
    
    public static Peca proximaPeca()
    {
        int Tam = Consts.TamanhoRec;
        int XMAX = Consts.XMAX;
        
        int valorAleatorio = (int) (Math.random() * 6);     //Pegando um valor aleatório (0 à 6) para determinar qual das 7 peça serão colocadas no jogo
        //System.out.println("Valor obtido: "+valorAleatorio);
	String nome = "";
	Rectangle a = new Rectangle(Tam-1, Tam-1), b = new Rectangle(Tam-1, Tam-1), c = new Rectangle(Tam-1, Tam-1), d = new Rectangle(Tam-1, Tam-1);
        switch(valorAleatorio)
        {
            case(0):
                a.setX(XMAX / 2 - Tam - Tam);
		b.setX(XMAX / 2 - Tam);
		c.setX(XMAX / 2);
		d.setX(XMAX / 2 + Tam);
		nome = "I";
                break;
            case(1):
                a.setX(XMAX / 2 - Tam);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 - Tam);
                c.setY(Tam);
                d.setX(XMAX / 2);
                d.setY(Tam);
                nome = "O";
                break;
            case(2):
                a.setX(XMAX / 2 - Tam);
		b.setX(XMAX / 2);
		c.setX(XMAX / 2);
		c.setY(Tam);
		d.setX(XMAX / 2 + Tam);
		nome = "T";
                break;
            case(3):
                a.setX(XMAX / 2 + Tam);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(Tam);
                d.setX(XMAX / 2 - Tam);
                d.setY(Tam);
                nome = "S";
                break;
            case(4):
                a.setX(XMAX / 2 + Tam);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 + Tam);
                c.setY(Tam);
                d.setX(XMAX / 2 + Tam + Tam);
                d.setY(Tam);
                nome = "Z";
                break;
            case(5):
                a.setX(XMAX / 2 - Tam);
                b.setX(XMAX / 2 - Tam);
                b.setY(Tam);
                c.setX(XMAX / 2);
                c.setY(Tam);
                d.setX(XMAX / 2 + Tam);
                d.setY(Tam);
                nome = "J";
                break;
            case(6):
                a.setX(XMAX / 2 + Tam);
                b.setX(XMAX / 2 - Tam);
                b.setY(Tam);
                c.setX(XMAX / 2);
                c.setY(Tam);
                d.setX(XMAX / 2 + Tam);
                d.setY(Tam);
                nome = "L";
                break;
        }
	return new Peca(a, b, c, d, nome);
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
