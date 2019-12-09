package control;

import elements.Componente;
import elements.Peca;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Consts;
import utils.Drawing;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameScreen extends Application {
    private static GameScreen Instance;         //Atributo para o padrão singleton
    private static Pane pane;                   //Pane do JavaFx
    private static Scene scene;                 //Scene do JavaFx
    private static Peca peca, proxPeca;         //peca armazena a peça atual e proxPeca armazena a próxima peça do jogo
    private static Componente[][]Tela;          //Matriz lógica de componentes que representa a tela do jogo
    private static Timer timer;                 //Representa o "agendador" de tarefas (JavaFx)

    /**
     * Construtor sem parametros para a classe GameScreen.
     * O construtor é privado por conta da implementação do padrão Singleton (garante que uma unica instancia seja feita).
     */
    private GameScreen()
    {
        pane = new Pane();
        scene = new Scene(pane, Consts.XMAX + 250, Consts.YMAX);
        pane.setStyle("-fx-background-color: White");
        Tela = new Componente[Consts.LMatriz][Consts.CMatriz];
        timer = new Timer();
    }
    
    
    /**
     * Método getInstance para implementação do padrão Singleton.
     * @return a única instancia de GameScreen
     */
    public static GameScreen getInstance()
    {
        if(Instance==null){
            Instance = new GameScreen();
        }
        return(Instance);
    }
    
    
    /**
     * Método start da aplicação com JavaFx.
     * Chama o método da classe Drawing para exibir o menu
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage)
    {
        Drawing.menu(primaryStage, timer, scene, pane); //Exibindo o menu
    } 
    
    
    /**
     * Método reponsável pelo jogo.
     * Recebe como parâmetro uma variável booleana, indicando se a fase será ou não com obstáculo. Caso seja, o método adicionarObstáculos (do GameController) é chamado.
     * @param comObstaculo
     * @param primaryStage 
     */
    public static void tetris(boolean comObstaculo, Stage primaryStage)
    { 
        if(comObstaculo)    //Verificando se a fase é com obstáculo para adicionar obstáculo
            GameController.adicionaObstaculos(new Random().nextInt(3)+1);   //A quantidade de obstáculos será um valor randomico de 1 - 3
        peca = GameController.proximaPeca();                                //Pegando a primeira peça do jogo
        pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR()); //Adicionando no pane
        GameController.moverTeclaPressionada(peca);                 //Chamando o método do GameController responsável por ler o teclado e por meio dessa leitura verificar o que será feito com a peça
        proxPeca = GameController.proximaPeca();                    //Guardando a proxima peça
        Drawing.mostraProximaPeca(proxPeca, scene, pane);                                //Chamando o método para mostrar a proxima peca
	TimerTask task = new TimerTask()    //Representa a tarefa a ser agendada. É uma classe abstrata que implementa a interface Runnable, assim, devemos criar uma sub-classe que implemente o método run()
        {
            public void run() 
            {
                Platform.runLater(new Runnable() 
                {
                    public void run() 
                    {
                        if(GameController.fazerCair(peca)==false && GameController.topo()!=0)       //Se não for mais possível fazer a peça cair, outra peça é criada
                        {
                            peca = proxPeca;                            //Pegando a próxima peça
                            pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR());  //Adicionando no pane
                            GameController.moverTeclaPressionada(peca); //Verficando o que será feito com a peça
                            proxPeca = GameController.proximaPeca();    //Guardando a proxima peça para a próxima iteração
                            Drawing.mostraProximaPeca(proxPeca, scene, pane);                //Chamando o método para mostrar a proxima peça
                        }
                        if(GameController.topo()==0)    //Se o topo for 0, o jogo acaba
                        {
                            timer.cancel(); // Encerrando o timer, descartando qualquer tarefa agendada no momento
                            timer.purge();  // Remove todas as tarefas canceladas da fila de tarefas deste timer
                            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);   //Criando uma caixa de alerta para mostrar ao usuário o final do jogo
                            dialogoErro.getDialogPane().setStyle("-fx-background-color: white");
                            dialogoErro.setTitle("TETRIS - Alerta");
                            dialogoErro.setHeaderText(null);
                            dialogoErro.setContentText("FIM DE JOGO!");
                            dialogoErro.showAndWait().ifPresent(response -> {   //Quando o usuário clicar em OK, fecha a tela
                                if (response == ButtonType.OK) {
                                    primaryStage.close();
                                }
                            });
                        }
                    }
		});
            }
	};
	timer.schedule(task, 0, 600*((int)GameController.getGravidade()));  //Definindo o período de execução de acordo com a gravidade
    }
        
    
    /**
     * Método para inicialização da matriz que irá armazenar os componentes (método utilizado no Drawing)
     */
    public static void inicializaMatriz()
    {
        for(int i=0 ; i<Consts.LMatriz ; i++)
            for(int j=0 ; j<Consts.CMatriz ; j++)
                Tela[i][j]=null;
    }
    
    //Métodos get e set para alguns atributos de GameScreen. (São utilizados em GameController para ter acesso aos atributos de GameScreen)
    public static Pane getPane() 
    {
        return pane;
    }
    public static Scene getScene() 
    {
        return scene;
    }
    public static Componente[][] getTela() 
    {
        return Tela;
    }
}
