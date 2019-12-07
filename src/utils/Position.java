package utils;


/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Position{
    private int x;
    private int y;
    
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void moveUp(){
        this.setPosition(this.getX()-1, this.getY());
    }
    public void moveDown(){
        this.setPosition(this.getX()+1, this.getY());
    }
    public void moveRight(){
        this.setPosition(this.getX(), this.getY()+1);
    }
    public void moveLeft(){
        this.setPosition(this.getX(), this.getY()-1);        
    }
}
