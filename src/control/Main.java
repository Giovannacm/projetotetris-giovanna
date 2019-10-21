package control;

/**
 * Projeto de POO 2019 mudei essa linha
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameScreen screen = new GameScreen();
                screen.setVisible(true);
                screen.createBufferStrategy(2);
                screen.go();
            }
        });
    }
}

