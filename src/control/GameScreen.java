package control;

import elements.Peca;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameScreen extends Application {
    private static Pane pane = new Pane();
    private static Scene scene = new Scene(pane, 550, 800);
    public static int[][]Tela = new int[10][18];        //Matriz que representa a tela do jogo
    public static int topo = 0;
    public static int pontuacao = 0;
    @Override
    public void start(Stage primaryStage) {
        //Texto para indicar a fase atual
        Text faseTexto = new Text("Fase atual: ");
        faseTexto.setStyle("-fx-font: 20 arial;");
	faseTexto.setX(320);
        faseTexto.setY(80);
        
        //Texto para indicar a pontuação atual
        Text pontuacaoTexto = new Text("Pontuação atual: ");
        pontuacaoTexto.setStyle("-fx-font: 20 arial;");
        pontuacaoTexto.setX(320);
        pontuacaoTexto.setY(50);
        
        //Linha para separar a área do jogo com os textos
        Line linha = new Line(300, 0, 300, 800);
        
        //Adicionando os textos na tela
        pane.getChildren().addAll(faseTexto, pontuacaoTexto, linha);
        
        //Criando a tela (javaFX)
        primaryStage.setScene(scene);
	primaryStage.setTitle("TETRIS");
        
        //Inicializando a tela/tabuleiro (matriz) do jogo com 0
        for(int i=0 ; i<10 ; i++)
            for(int j=0 ; j<18 ; j++)
                Tela[i][j]=0;
        
        //Exibindo tudo que foi adicionado anteriormente
        primaryStage.show();
        
        Peca peca = GameController.proximaPeca();
        pane.getChildren().addAll(peca.a, peca.b, peca.c, peca.d);
        GameController.moverTeclaPressionada(peca);
    }

    public static Scene getScene() {
        return scene;
    }
}
