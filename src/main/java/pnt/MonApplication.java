package pnt;

import controleur.Controleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vues.IHMManager;

import java.io.IOException;

public class MonApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Controleur controleur = new Controleur(new IHMManager(stage));
        controleur.run();

    }


    public static void main(String[] args) {
        launch();
    }

}