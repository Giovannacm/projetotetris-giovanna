package control;

import elements.Peca;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Consts;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameScreen extends Application {
    private static Pane pane = new Pane();
    private static Scene scene = new Scene(pane, 300 + 250, 540);
    private static Peca peca, proxPeca;
    private static int Gravidade = Consts.Gravidade;
    public static int[][]Tela = new int[10][18];        //Matriz que representa a tela do jogo
    public static int topo = 0;
    public static int pontuacao = 0;
    @Override
    public void start(Stage primaryStage) {
        //Texto para indicar a fase atual
        Text faseTexto = new Text("Fase atual: ");
        faseTexto.setStyle("-fx-font: 20 arial;");
	faseTexto.setX(310);
        faseTexto.setY(20);
        
        //Texto para indicar a pontuação atual
        Text pontuacaoTexto = new Text("Pontuação atual: ");
        pontuacaoTexto.setStyle("-fx-font: 20 arial;");
        pontuacaoTexto.setX(310);
        pontuacaoTexto.setY(70);
        
        //Texto para indicar a pontuação atual
        Text proximaPeca = new Text("Próxima peça: ");
        proximaPeca.setStyle("-fx-font: 20 arial;");
        proximaPeca.setX(310);
        proximaPeca.setY(120);
        
        //Linha para separar a área do jogo com os textos
        Line linha = new Line(300, 0, 300, 800);
        
        //Adicionando os textos na tela
        pane.getChildren().addAll(faseTexto, pontuacaoTexto, linha);
        
        //Criando a tela (javaFX)
        primaryStage.setScene(scene);
	primaryStage.setTitle("TETRIS - Giovanna");
        
        //Inicializando a tela/tabuleiro (matriz) do jogo com 0
        for(int i=0 ; i<10 ; i++)
            for(int j=0 ; j<18 ; j++)
                Tela[i][j]=0;
        
        //Exibindo tudo que foi adicionado anteriormente
        primaryStage.show();
        
        peca = GameController.proximaPeca();                        //Pegando a primeira peça do jogo
        pane.getChildren().addAll(peca.a, peca.b, peca.c, peca.d);
        GameController.moverTeclaPressionada(peca);
        proxPeca = GameController.proximaPeca();                    //Guardando a proxima peça
                
        Timer fall = new Timer();           //Representa o "agendador" de tarefas
	TimerTask task = new TimerTask()    //Representa a tarefa a ser agendada. É uma classe abstrata que implementa a interface Runnable, assim, devemos criar uma sub-classe que implemente o método run()
        {
            public void run() 
            {
                Platform.runLater(new Runnable() 
                {
                    public void run() 
                    {
                        if(GameController.fazerCair(peca)==false)       //Se não for mais possível fazer a peça cair, outra peça é criada
                        {
                            peca = proxPeca;                            //Pegando a próxima peça
                            pane.getChildren().addAll(peca.a, peca.b, peca.c, peca.d);
                            GameController.moverTeclaPressionada(peca);
                            proxPeca = GameController.proximaPeca();    //Guardando a proxima peça para a próxima iteração
                        }
                    }
		});
            }
	};
	fall.schedule(task, 0, 600*Gravidade);  //Definindo o período de execução
                
    }

    public static Scene getScene() {
        return scene;
    }
}
