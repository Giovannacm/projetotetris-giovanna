package utils;

import control.GameController;
import control.GameScreen;
import elements.Componente;
import elements.Peca;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Drawing {
    private static Text pontuacaoTexto = new Text("Pontuação: " + Integer.toString(GameController.getPontuacao())); //Esse texto irá mudar toda vez que a pontuação for alterada
    private static Text faseTexto = new Text("Fase: ");             //Esse texto irá mudar futuramente dependendo da fase escolhida
    private static Peca auxiliar = null;                            //Peca auxiliar é utilizada para mostrar a próxima peca na tela
    
    /**
     * Método responsável por desenhar os elementos do cenário.
     * Além de colocar os textos de pontuaçãofase, atual e próxima peça, adiciona as linhas para desenhar a matriz e um botão para sair do jogo.
     * @param primaryStage 
     * @param timer 
     * @param scene 
     * @param pane 
     */
    public static void desenhaCenario(Stage primaryStage, Timer timer, Scene scene, Pane pane)
    {
        //Texto para indicar a fase atual
        faseTexto.setStyle("-fx-font-size: 22;");
	faseTexto.setX(310);
        faseTexto.setY(30);
        
        //Texto para indicar a pontuação atual
        pontuacaoTexto.setStyle("-fx-font-size: 22;");
        pontuacaoTexto.setX(310);
        pontuacaoTexto.setY(90);
        
        //Texto para indicar a pontuação atual
        Text proxPeca = new Text("Próxima peca: ");
        proxPeca.setStyle("-fx-font-size: 22;");
        proxPeca.setX(310);
        proxPeca.setY(150);
                
        //Botão para sair do jogo
        Button sair = new Button("Sair");
        sair.setLayoutX(390);
        sair.setLayoutY(390);
        sair.setStyle("-fx-base: white; -fx-font-size: 20;");
        sair.setFocusTraversable(false);
        sair.setOnAction(new EventHandler<ActionEvent>()   //Adicionando um evento no botão para sair do jogo
        {
            @Override
            public void handle(ActionEvent event) 
            {
                timer.cancel();         //Encerrando o timer, descartando qualquer tarefa agendada no momento
                timer.purge();          //Remove todas as tarefas canceladas da fila de tarefas deste timer
                primaryStage.close();   //Fechando o stage
            }
        });
        
        //Botão para voltar ao menu do jogo
        Button voltar = new Button("Voltar");
        voltar.setLayoutX(380);
        voltar.setLayoutY(450);
        voltar.setStyle("-fx-base: white; -fx-font-size: 20;");
        voltar.setFocusTraversable(false);
        voltar.setOnAction(new EventHandler<ActionEvent>()   //Adicionando um evento no botão para voltar ao menu do jogo
        {
            @Override
            public void handle(ActionEvent event) 
            {
                pane.getChildren().clear();
                menu(primaryStage, timer, scene, pane);
            }
        });
        
        
        //Adicionando os textos e o botao na tela
        pane.getChildren().addAll(faseTexto, pontuacaoTexto, proxPeca, sair, voltar);
        
        desenhaLinhas(primaryStage, scene, pane);
        
        //Criando a tela (javaFX)
        primaryStage.setScene(scene);
	primaryStage.setTitle("TETRIS - Giovanna");
    }
    
   
    /**
     * Método para desenhar as linhas do senário
     * @param primaryStage 
     * @param scene 
     * @param pane 
     */
    public static void desenhaLinhas(Stage primaryStage, Scene scene, Pane pane)
    {
        int i;
        Line linha;
        for(i=0 ; i<Consts.CMatriz ; i++)   //Linhas verticais
        {
            linha = new Line(Consts.TamRec*(i), 0, Consts.TamRec*(i), Consts.YMAX);
            linha.setStroke(Color.GHOSTWHITE);
            pane.getChildren().addAll(linha);
        }
        linha = new Line(Consts.TamRec*(i), 0, Consts.TamRec*(i), Consts.YMAX);  //Linha para separação do texto da cena do jogo
        linha.setStroke(Color.BLACK);
        pane.getChildren().addAll(linha);
        for(i=0 ; i<=Consts.LMatriz ; i++)  //Linhas horizontais
        {
            linha = new Line(0, Consts.TamRec*(i), Consts.XMAX, Consts.TamRec*(i));
            linha.setStroke(Color.GHOSTWHITE);
            pane.getChildren().addAll(linha);
        }
    }
    
    
    /**
     * Método para mostrar a próxima peça na tela.
     * É passado como parâmetro a proxima peça que será exibida e é utilizado a peça auxiliar.
     * @param proxima 
     * @param scene 
     * @param pane 
     */
    public static void mostraProximaPeca(Peca proxima, Scene scene, Pane pane)
    {
        if(auxiliar!=null)       //Já existe uma próxima peça que está sendo apresentada ao usuário, então removemos para adicionar a próxima
        {
            pane.getChildren().remove(auxiliar.getA().getR());
            pane.getChildren().remove(auxiliar.getB().getR());
            pane.getChildren().remove(auxiliar.getC().getR());
            pane.getChildren().remove(auxiliar.getD().getR());
        }
        Componente a = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);   //Instanciando novos componentes
        Componente b = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);
        Componente c = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);
        Componente d = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);    
        a.setX(proxima.getA().getX() + Consts.TamRec*9); //Atualizando o valor desses componentes de acordo com a proxima peça a ser apresentada (é somado uma constande para ficar no canto da tela)
	b.setX(proxima.getB().getX() + Consts.TamRec*9);
	c.setX(proxima.getC().getX() + Consts.TamRec*9);
	d.setX(proxima.getD().getX() + Consts.TamRec*9);
        a.setY(proxima.getA().getY() + Consts.TamRec*6);
	b.setY(proxima.getB().getY() + Consts.TamRec*6);
	c.setY(proxima.getC().getY() + Consts.TamRec*6);
	d.setY(proxima.getD().getY() + Consts.TamRec*6);
        a.mudaCor(proxima.getA().getR().getFill()); //Mudando a cor de acordo com a proxima peca
        b.mudaCor(proxima.getB().getR().getFill());
        c.mudaCor(proxima.getC().getR().getFill());
        d.mudaCor(proxima.getD().getR().getFill());
        auxiliar = new Peca(a, b, c, d, proxima.getNome());  //Instanciando uma nova peça de acordo com esses componentes
        pane.getChildren().addAll(auxiliar.getA().getR(), auxiliar.getB().getR(), auxiliar.getC().getR(), auxiliar.getD().getR());  //Acionando essa cópia da próxima peça no pane
    }
    
    
    /**
     * Método para exibir o menu inicial do jogo para selecionar a fase.
     * Inicialmente é apresentado um menu para o usuário selecionar a fase (com ou sem obstáculo).
     * Após a escolha da fase, todos os itens anteriores são excluídos da tela (pane) e o texto da fase é alterado de modo a indicar a fase atual para o usuário.
     * Além disso, o método tetris é chamado com o parâmetro false caso a fase seja sem obstáculo, e com true caso contrário.
     * @param primaryStage
     * @param timer
     * @param scene
     * @param pane 
     */
    public static void menu(Stage primaryStage, Timer timer, Scene scene, Pane pane)
    {
        primaryStage.setTitle("Menu - Tetris");
        GameScreen.inicializaMatriz();                     //Inicializando a tela/tabuleiro (matriz) do jogo com null
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
                desenhaCenario(primaryStage, timer, scene, pane);
                GameScreen.tetris(false, primaryStage);
                pane.getChildren().remove(faseNormal);  //Removendo os botoes
                pane.getChildren().remove(faseObstaculo);
                setFaseTexto("Fase: sem obstáculos");    //Mudando o texto da fase atual
            }
        });
        faseObstaculo.setOnAction(new EventHandler<ActionEvent>()   //Adicionando um evento no botão de fase com obstaculo
        {
            @Override
            public void handle(ActionEvent event) {     //Quando ele for pressionado a fase com obstaculo será apresentada
                desenhaCenario(primaryStage, timer, scene, pane);
                GameScreen.tetris(true, primaryStage);
                pane.getChildren().remove(faseNormal);  //Removendo os botoes
                pane.getChildren().remove(faseObstaculo);
                setFaseTexto("Fase: com obstáculos");    //Mudando o texto da fase atual
            }
        });
        pane.getChildren().add(faseNormal);     //Adicionando os botoes
        pane.getChildren().add(faseObstaculo);  //Adicionando os botoes
        primaryStage.setScene(scene);           //Sando set no scene do stage
        primaryStage.show();    //Exibindo tudo que foi adicionado anteriormente
    }
    
    
    //Métodos para acesso/modificação dos textos (utilizados nas outras classes)
    public static void setFaseTexto(String text) 
    {
        faseTexto.setText(text);
    }
    public static Text getPontuacaoTexto() {
        return pontuacaoTexto;
    }
}
