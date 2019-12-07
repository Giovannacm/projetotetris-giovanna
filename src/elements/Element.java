package elements;

import utils.Position;


/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public abstract class Element{

    protected Position pos;
    protected boolean fixa;       // Se encostar, morre?

    protected Element(String imageName) 
    {
        this.pos = new Position(1, 1);
        this.fixa = false;
    }
    public void moveUp() 
    {
        this.pos.moveUp();
    }
    public void moveDown() 
    {
        this.pos.moveDown();
    }
    public void moveRight() 
    {
        this.pos.moveRight();
    }
    public void moveLeft() 
    {
        this.pos.moveLeft();
    }
}
