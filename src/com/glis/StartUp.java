package com.glis;

import com.glis.domain.GenericWordProvider;
import com.glis.gui.HangmanFrameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Glis
 */
public class StartUp  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new HangmanFrameController(new GenericWordProvider()));
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
