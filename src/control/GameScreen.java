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

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 */
public class GameScreen extends Application {
    private static GameScreen Instance;         //Atributo para o padrão singleton
    private static Pane pane;                   //Pane do JavaFx
    private static Scene scene;                 //Scene do JavaFx
    private static Peca peca, proxPeca, aux;    //peca armazena a peça atual, proxPeca armazena a próxima peça do jogo e aux é utilizada para mostrar a próxima peca na tela
    private static Componente[][]Tela;          //Matriz lógica de componentes que representa a tela do jogo
    private static Timer timer;                 //Representa o "agendador" de tarefas (JavaFx)
    private static int TamRec;                  //Tamanho do retangulo

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
        TamRec = Consts.TamRec;
        aux = null;
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
     * Inicialmente é apresentado um menu para o usuário selecionar a fase (com ou sem obstáculo).
     * Após a escolha da fase, todos os itens anteriores são excluídos da tela (pane) e o texto da fase é alterado de modo a indicar a fase atual para o usuário.
     * Além disso, o método tetris é chamado com o parâmetro false caso a fase seja sem obstáculo, e com true caso contrário.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage)
    {
        inicializaMatriz();                     //Inicializando a tela/tabuleiro (matriz) do jogo com null
        primaryStage.setTitle("Menu - Tetris");
        //Menu
        Button faseNormal = new Button();       //Criando um botão para a fase sem obstáculo e mudando suas propriedades
        Button faseObstaculo = new Button();    //Criando um botão para a fase com obstáculo e mudando suas propriedades
        faseNormal.setLayoutX(175);
        faseNormal.setLayoutY(200);
        faseNormal.setText("Fase sem obstáculo");
        faseNormal.setStyle("-fx-base: purple; -fx-font-size: 20; -fx-focus-color: transparent;");
        faseObstaculo.setLayoutX(175);
        faseObstaculo.setLayoutY(250);
        faseObstaculo.setText("Fase com obstáculo");
        faseObstaculo.setStyle("-fx-base: green; -fx-font-size: 20; -fx-focus-color: transparent;");
        faseNormal.setOnAction(new EventHandler<ActionEvent>()  //Adicionando um evento no botão de fase sem obstaculo
        {
            @Override
            public void handle(ActionEvent event) {     //Quando ele for pressionado a fase sem obstaculo será apresentada
                GameController.desenhaCenario(primaryStage, timer);
                tetris(false, primaryStage);
                pane.getChildren().remove(faseNormal);  //Removendo os botoes
                pane.getChildren().remove(faseObstaculo);
                GameController.setFaseTexto("Fase: sem obstáculos");    //Mudando o texto da fase atual
            }
        });
        faseObstaculo.setOnAction(new EventHandler<ActionEvent>()   //Adicionando um evento no botão de fase com obstaculo
        {
            @Override
            public void handle(ActionEvent event) {     //Quando ele for pressionado a fase com obstaculo será apresentada
                GameController.desenhaCenario(primaryStage, timer);
                tetris(true, primaryStage);
                pane.getChildren().remove(faseNormal);  //Removendo os botoes
                pane.getChildren().remove(faseObstaculo);
                GameController.setFaseTexto("Fase: com obstáculos");    //Mudando o texto da fase atual
            }
        });
        pane.getChildren().add(faseNormal);     //Adicionando os botoes
        pane.getChildren().add(faseObstaculo);  //Adicionando os botoes
        primaryStage.setScene(scene);           //Sando set no scene do stage
        primaryStage.show();    //Exibindo tudo que foi adicionado anteriormente
    } 
    
    
    /**
     * Método reponsável pelo jogo.
     * Recebe como parâmetro uma variável booleana, indicando se a fase será ou não com obstáculo. Caso seja, o método adicionarObstáculos (do GameController) é chamado.
     * 
     * @param comObstaculo
     * @param primaryStage 
     */
    public void tetris(boolean comObstaculo, Stage primaryStage)
    {
        if(comObstaculo)    //Verificando se a fase é com obstáculo para adicionar obstáculo
            GameController.adicionaObstaculos(new Random().nextInt(3)+1);   //A quantidade de obstáculos será um valor randomico de 1 - 3
        peca = GameController.proximaPeca();                                //Pegando a primeira peça do jogo
        pane.getChildren().addAll(peca.getA().getR(), peca.getB().getR(), peca.getC().getR(), peca.getD().getR()); //Adicionando no pane
        GameController.moverTeclaPressionada(peca);                 //Chamando o método do GameController responsável por ler o teclado e por meio dessa leitura verificar o que será feito com a peça
        proxPeca = GameController.proximaPeca();                    //Guardando a proxima peça
        mostraProximaPeca(proxPeca);                                //Chamando o método para mostrar a proxima peca
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
                            mostraProximaPeca(proxPeca);                //Chamando o método para mostrar a proxima peça
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
                        if(GameController.getPontuacao()==1000) //Se chegou em 100 prontos, a velociade aumenta em 30%
                            GameController.setGravidade(GameController.getGravidade()*1.30); //Dando set na variável gravidade do GameController
                    }
		});
            }
	};
	timer.schedule(task, 0, 600*(GameController.getGravidade()));  //Definindo o período de execução de acordo com a gravidade
    }
    
    
    /**
     * Método para mostrar a próxima peça na tela.
     * É passado como parâmetro a proxima peça que será exibida e é utilizado a peça auxiliar.
     * @param proxima 
     */
    public void mostraProximaPeca(Peca proxima)
    {
        if(aux!=null)       //Já existe uma próxima peça que está sendo apresentada ao usuário, então removemos para adicionar a próxima
        {
            pane.getChildren().remove(aux.getA().getR());
            pane.getChildren().remove(aux.getB().getR());
            pane.getChildren().remove(aux.getC().getR());
            pane.getChildren().remove(aux.getD().getR());
        }
        Componente a = new Componente(TamRec-1, TamRec-1, false);   //Instanciando novos componentes
        Componente b = new Componente(TamRec-1, TamRec-1, false);
        Componente c = new Componente(TamRec-1, TamRec-1, false);
        Componente d = new Componente(TamRec-1, TamRec-1, false);    
        a.setX(proxima.getA().getX() + TamRec*9); //Atualizando o valor desses componentes de acordo com a proxima peça a ser apresentada (é somado uma constande para ficar no canto da tela)
	b.setX(proxima.getB().getX() + TamRec*9);
	c.setX(proxima.getC().getX() + TamRec*9);
	d.setX(proxima.getD().getX() + TamRec*9);
        a.setY(proxima.getA().getY() + TamRec*6);
	b.setY(proxima.getB().getY() + TamRec*6);
	c.setY(proxima.getC().getY() + TamRec*6);
	d.setY(proxima.getD().getY() + TamRec*6);
        a.mudaCor(proxima.getA().getR().getFill()); //Mudando a cor de acordo com a proxima peca
        b.mudaCor(proxima.getB().getR().getFill());
        c.mudaCor(proxima.getC().getR().getFill());
        d.mudaCor(proxima.getD().getR().getFill());
        aux = new Peca(a, b, c, d, proxima.getNome());  //Instanciando uma nova peça de acordo com esses componentes
        pane.getChildren().addAll(aux.getA().getR(), aux.getB().getR(), aux.getC().getR(), aux.getD().getR());  //Acionando essa cópia da próxima peça no pane
    }
    
    
    /**
     * Método para inicialização da matriz que irá armazenar os componentes
     */
    public void inicializaMatriz()
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
