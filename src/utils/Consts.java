package utils;

import java.io.File;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Consts {
    public static final int VELOCIDADE = 1;     //Velocidade da peça no jogo
    public static final int TamRec = 30;
    public static final int XMAX = TamRec * 10;
    public static final int YMAX = TamRec * 18;
    
    
   //Constantes que já estavam no código do material
    public static final int CELL_SIZE = 30;
    public static final int NUM_CELLS = 20;
    
    public static final String IMG_PATH = File.separator+"imgs"+File.separator;
    
    public static final int DELAY_SCREEN_UPDATE = 20;
}
