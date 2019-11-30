package control;

import elements.Componente;
import elements.Peca;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Consts;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameScreen extends Application {
    private static GameScreen Instance;
    private static Pane pane;
    private static Scene scene;
    private static Peca peca, proxPeca;
    private static double Gravidade;
    private static Componente[][]Tela;        //Matriz que representa a tela do jogo
    private static int topo;
    
    /**
     * Construtor sem parametros para a classe GameScreen.
     * O construtor é privado por conta da implementação do padrão Singleton (garante que uma unica instancia seja feita).
     */
    private GameScreen()
    {
        pane = new Pane();
        scene = new Scene(pane, Consts.XMAX + 250, Consts.YMAX);
        //pane.setStyle("-fx-background-color: Black");
        Gravidade = Consts.Gravidade;
        Tela = new Componente[Consts.LMatriz][Consts.CMatriz];
        topo = 0;
    }
    
    
    /**
     * Função getInstance para implementação do padrão Singleton.
     * @return a única instancia de GameScreen
     */
    public static GameScreen getInstance(){
        if(Instance==null){
            Instance = new GameScreen();
        }
        return(Instance);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //Inicializando a tela/tabuleiro (matriz) do jogo com 0
        for(int i=0 ; i<Consts.LMatriz ; i++)
            for(int j=0 ; j<Consts.CMatriz ; j++)
                Tela[i][j]=null;
        
        GameController.desenhaCenario(primaryStage);
        
        //Exibindo tudo que foi adicionado anteriormente
        primaryStage.show();
        
        peca = GameController.proximaPeca();                        //Pegando a primeira peça do jogo
        pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR());
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
                        topo=GameController.topo();
                        //imprimeMatriz();
                        //System.out.println("Topo: "+topo);
                        imprimeMatriz();
                        if(GameController.fazerCair(peca)==false)       //Se não for mais possível fazer a peça cair, outra peça é criada
                        {
                            peca = proxPeca;                            //Pegando a próxima peça
                            pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR());
                            GameController.moverTeclaPressionada(peca);
                            proxPeca = GameController.proximaPeca();    //Guardando a proxima peça para a próxima iteração
                        }
                        if(GameController.getPontuacao()==1000)
                            Gravidade=Gravidade*1.30;                   //Se chegou em 100 prontos, a velociade aumenta em 30%
                    }
		});
            }
	};
	fall.schedule(task, 0, 600*((int)Gravidade));  //Definindo o período de execução
    }
    
    public static void imprimeMatriz()
    {
        System.out.println("");
        System.out.println("");
        for(int i=0 ; i<Consts.LMatriz ; i++)
        {
            System.out.println("");
            for(int j=0 ; j<Consts.CMatriz ; j++)
            {
                if(Tela[i][j]==null)
                    System.out.print(" 0");
                else
                    System.out.print(" 1");
            }
        }
    }

    //Métodos get e set para os atributos de GameScreen. (São utilizados em GameController para ter acesso aos atributos de GameScreen)
    public static Pane getPane() {
        return pane;
    }

    public static void setPane(Pane pane) {
        GameScreen.pane = pane;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        GameScreen.scene = scene;
    }

    public static Peca getPeca() {
        return peca;
    }

    public static void setPeca(Peca peca) {
        GameScreen.peca = peca;
    }

    public static Peca getProxPeca() {
        return proxPeca;
    }

    public static void setProxPeca(Peca proxPeca) {
        GameScreen.proxPeca = proxPeca;
    }

    public static double getGravidade() {
        return Gravidade;
    }

    public static void setGravidade(int Gravidade) {
        GameScreen.Gravidade = Gravidade;
    }

    public static Componente[][] getTela() {
        return Tela;
    }

    public static void setTela(Componente[][] Tela) {
        GameScreen.Tela = Tela;
    }

    public static int getTopo() {
        return topo;
    }

    public static void setTopo(int topo) {
        GameScreen.topo = topo;
    }

}
