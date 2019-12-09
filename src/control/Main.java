package control;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class Main extends Application{
   @Override
    public void start(Stage primaryStage)               //Método start da aplicação com JavaFx
    {
        GameScreen screen = GameScreen.getInstance();   //Pegando a instancia da classe GameScreen, desenvolvida com o padrão Singleton
        screen.start(primaryStage);                     //Chamada ao método start (JavaFx)
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}

