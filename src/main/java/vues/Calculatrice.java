package vues;

import controleur.Controleur;
import controleur.LanceurOrdre;
import controleur.ordres.Ordre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Calculatrice implements VueInteractive, EcouteurOrdre {

    private Scene scene;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TextField screen;

    private Controleur controleur;

    @FXML
    private Button menu;

    private void initialiserVue() {
        this.screen.setEditable(false);
        this.scene = new Scene(mainBorderPane,640,480);
    }

    public Scene getScene() {
        return scene;
    }

    public static Calculatrice creerVue() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Historique.class.getResource("calculatrice.fxml"));
        Parent main = fxmlLoader.load();
        Calculatrice resultat = fxmlLoader.getController();
        resultat.initialiserVue();
        return resultat;
    }

    public void inputNumber(ActionEvent actionEvent) {
        this.controleur.inputDigit(Integer.valueOf(((Button)actionEvent.getSource()).getText()));
    }

    public void inputOperation(ActionEvent actionEvent) {
        this.controleur.inputSymbol(((Button)actionEvent.getSource()).getText());
    }

    public void inputEquals(ActionEvent actionEvent) {
        this.controleur.inputEquals();
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void gotomenu(ActionEvent actionEvent) {
        this.controleur.gotoMenu();
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NEW_OPERATION, Ordre.OrdreType.NEW_OPERANDE);
    }

    @Override
    public void broadCast(Ordre ordre) {
        if (ordre.getType() == Ordre.OrdreType.NEW_OPERATION || ordre.getType() == Ordre.OrdreType.NEW_OPERANDE) {
            this.screen.setText(this.controleur.getNombreAAfficher());
        }
    }
}
