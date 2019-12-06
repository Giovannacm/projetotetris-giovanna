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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Consts;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 */
public class GameScreen extends Application {
    private static GameScreen Instance;
    private static Pane pane;
    private static Scene scene;
    private static Peca peca, proxPeca;
    private static double Gravidade;
    private static Componente[][]Tela;        //Matriz que representa a tela do jogo
    
    /**
     * Construtor sem parametros para a classe GameScreen.
     * O construtor é privado por conta da implementação do padrão Singleton (garante que uma unica instancia seja feita).
     */
    private GameScreen()
    {
        pane = new Pane();
        scene = new Scene(pane, Consts.XMAX + 250, Consts.YMAX);
        pane.setStyle("-fx-background-color: White");
        Gravidade = Consts.Gravidade;
        Tela = new Componente[Consts.LMatriz][Consts.CMatriz];
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
        inicializaMatriz();     //Inicializando a tela/tabuleiro (matriz) do jogo com null
        primaryStage.setTitle("Menu - Tetris");
        //Menu
        Button faseNormal = new Button();       //Criando um botão para a fase sem obstáculo e mudando suas propriedades
        Button faseObstaculo = new Button();    //Criando um botão para a fase com obstáculo e mudando suas propriedades
        faseNormal.setLayoutX(175);
        faseNormal.setLayoutY(200);
        faseNormal.setText("Fase sem obstáculo");
        faseNormal.setStyle("-fx-base: purple; -fx-font-size: 20;");
        faseObstaculo.setLayoutX(175);
        faseObstaculo.setLayoutY(250);
        faseObstaculo.setText("Fase com obstáculo");
        faseObstaculo.setStyle("-fx-base: green; -fx-font-size: 20;");
        faseNormal.setOnAction(new EventHandler<ActionEvent>()  //Adicionando um evento no botão de fase sem obstaculo
        {
            @Override
            public void handle(ActionEvent event) {     //Quando ele for pressionado a fase sem obstaculo será apresentada
                GameController.desenhaCenario(primaryStage);
                fase(false, primaryStage);
                pane.getChildren().remove(faseNormal);  //Removendo os botoes
                pane.getChildren().remove(faseObstaculo);
                GameController.setFaseTexto("Fase: sem obstáculos");    //Mudando o texto da fase atual
            }
        });
        faseObstaculo.setOnAction(new EventHandler<ActionEvent>()   //Adicionando um evento no botão de fase com obstaculo
        {
            @Override
            public void handle(ActionEvent event) {     //Quando ele for pressionado a fase com obstaculo será apresentada
                GameController.desenhaCenario(primaryStage);
                fase(true, primaryStage);
                pane.getChildren().remove(faseNormal);  //Removendo os botoes
                pane.getChildren().remove(faseObstaculo);
                GameController.setFaseTexto("Fase: com obstáculos");    //Mudando o texto da fase atual
            }
        });
        pane.getChildren().add(faseNormal);
        pane.getChildren().add(faseObstaculo);
        primaryStage.setScene(scene);
        primaryStage.show();    //Exibindo tudo que foi adicionado anteriormente
    } 
    
    public void fase(boolean comObstaculo, Stage primaryStage)
    {
        if(comObstaculo)    //Verificando se a fase é com obstáculo para adicionar obstáculo
            GameController.adicionaObstaculos(new Random().nextInt(4)+1);   //A quantidade de obstáculos será um valor randomico de 0 - 4
        
        peca = GameController.proximaPeca();                        //Pegando a primeira peça do jogo
        pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR());
        GameController.moverTeclaPressionada(peca);
        proxPeca = GameController.proximaPeca();                    //Guardando a proxima peça
                
        Timer timer = new Timer();           //Representa o "agendador" de tarefas
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
                            pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR());
                            GameController.moverTeclaPressionada(peca);
                            proxPeca = GameController.proximaPeca();    //Guardando a proxima peça para a próxima iteração
                        }
                        if(GameController.topo()==0)    //Se o topo for 0, o jogo acaba
                        {
                            timer.cancel(); // Encerrando o timer, descartando qualquer tarefa agendada no momento
                            timer.purge();  // Remove todas as tarefas canceladas da fila de tarefas deste timer
                            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);   //Criando uma caixa de alerta
                            dialogoErro.getDialogPane().setStyle("-fx-background-color: white");
                            dialogoErro.setTitle("TETRIS - Erro");
                            dialogoErro.setHeaderText(null);
                            dialogoErro.setContentText("FIM DE JOGO!");
                            dialogoErro.showAndWait().ifPresent(response -> {   //Quando o usuário clicar em OK, fecha a tela
                                if (response == ButtonType.OK) {
                                    primaryStage.close();
                                }
                            });
                        }
                        if(GameController.getPontuacao()==1000)
                            Gravidade=Gravidade*1.30;                   //Se chegou em 100 prontos, a velociade aumenta em 30%
                    }
		});
            }
	};
	timer.schedule(task, 0, 600*((int)Gravidade));  //Definindo o período de execução
    }
    
    public void inicializaMatriz()
    {
        for(int i=0 ; i<Consts.LMatriz ; i++)
            for(int j=0 ; j<Consts.CMatriz ; j++)
                Tela[i][j]=null;
    }
    public void imprimeMatriz()
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
}
