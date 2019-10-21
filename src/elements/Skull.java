package elements;

import utils.Drawing;
import java.awt.Graphics;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Skull extends Element{
    public static final int TIMER_FIRE = 40;
    private int contIntervals;
    
    
    public Skull(String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.contIntervals = 0;
    }

    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

        this.contIntervals++;
        if(this.contIntervals == TIMER_FIRE){
            this.contIntervals = 0;
            Fire f = new Fire("fire.png");
            f.setPosition(pos.getX(),pos.getY()+1);
            Drawing.getGameScreen().addElement(f);
        }
    }    
}
