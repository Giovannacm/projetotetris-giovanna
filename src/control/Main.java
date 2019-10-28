package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Projeto de POO 2019 mudei essa linha
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */ 
public class Main extends Application{
    private static Pane pane = new Pane();
    private static Scene scene = new Scene(pane, 600, 800);
    @Override
    public void start(Stage primaryStage) {
        Text faseTexto = new Text("Fase atual: ");
        faseTexto.setX(400);
        faseTexto.setY(80);
        Text pontuacaoTexto = new Text("Pontuação atual: ");
        pontuacaoTexto.setX(400);
        pontuacaoTexto.setY(50);
        pane.getChildren().addAll(faseTexto, pontuacaoTexto);      //Adicionando os textos na tela
        primaryStage.setScene(scene);                              //Criando a tela (javaFX)
	primaryStage.setTitle("TETRIS");
	primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
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

