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
        GameScreen screen = new GameScreen();
        screen.start(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

