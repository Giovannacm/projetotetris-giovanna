package control;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Consts;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 */
public class Main extends Application{
   @Override
    public void start(Stage primaryStage) {
        /*primaryStage.setTitle("Menu - Tetris");
        Button botaoIniciar = new Button();
        botaoIniciar.setLayoutX(200);
        botaoIniciar.setLayoutY(200);
        botaoIniciar.setText("Iniciar");
        botaoIniciar.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) {
                GameScreen screen = GameScreen.getInstance();
                screen.start(primaryStage);
            }
        });
        Pane pane = new Pane();
        pane.getChildren().add(botaoIniciar);
        primaryStage.setScene(new Scene(pane, Consts.XMAX + 250, Consts.YMAX));
        primaryStage.show();*/
        
        GameScreen screen = GameScreen.getInstance();
        screen.start(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

