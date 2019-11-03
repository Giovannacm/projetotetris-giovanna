package utils;

import java.io.File;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Consts {
    public static final int Gravidade = 1;     //Velocidade da peça no jogo
    public static final int TamRec = 30;
    public static final int LMatriz = 10;
    public static final int CMatriz = 18;
    public static final int XMAX = TamRec * LMatriz;
    public static final int YMAX = TamRec * CMatriz;
    
    //constantes anteriores
    public static final int CELL_SIZE = 30;
    public static final int NUM_CELLS = 20;
    
    public static final String IMG_PATH = File.separator+"imgs"+File.separator;
    
    public static final int DELAY_SCREEN_UPDATE = 20;
}
