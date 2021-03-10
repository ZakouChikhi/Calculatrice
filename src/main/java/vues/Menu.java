package vues;

import controleur.Controleur;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Menu implements VueInteractive {
    private Scene scene;
    private Controleur controleur;

    public Scene getScene() {
        return scene;
    }

    public static Menu creerVue() {
        Menu menu = new Menu();
        menu.initialiserVue();
        return menu;
    }

    private void initialiserVue() {
        BorderPane borderPane = new BorderPane();
        Label label = new Label("Ma calculatrice");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(42));
        label.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
//        label.setPrefSize(label.getMaxWidth(),label.getMaxHeight());
        VBox vBox = new VBox();

        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(vBox,Pos.CENTER);

        Button gotoCalculer = new Button("Calculer");

        gotoCalculer.setFont(Font.font(24));
        gotoCalculer.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        Button gotoHistorique = new Button("Historique");
        gotoHistorique.setFont(Font.font(24));
        gotoHistorique.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        vBox.getChildren().addAll(gotoCalculer,gotoHistorique);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);

        EventHandler eventHandlerCalculatrice = e -> {
            controleur.gotoCalculatrice();
        };


        EventHandler eventHandlerHistorique = e -> {
            controleur.gotoHistorique();
        };

        gotoCalculer.setOnAction(eventHandlerCalculatrice);
        gotoHistorique.setOnAction(eventHandlerHistorique);
        borderPane.setCenter(vBox);
        borderPane.setTop(label);
        this.scene = new Scene(borderPane,640,480);
    }




    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}
