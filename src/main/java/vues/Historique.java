package vues;

import controleur.Controleur;
import controleur.LanceurOrdre;
import controleur.ordres.Ordre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import modele.calcul.OperationHistorisee;

import java.io.IOException;

public class Historique implements VueInteractive, EcouteurOrdre {

    @FXML
    private TextArea histo;


    @FXML
    private BorderPane borderPane;

    private Scene scene;
    private Controleur controleur;

    private void initialiserVue() {
        this.scene = new Scene(borderPane,640,480);
    }

    public Scene getScene() {
        return scene;
    }


    public static Historique creerVue() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Historique.class.getResource("historique.fxml"));

        Parent main = fxmlLoader.load();
        Historique resultat = fxmlLoader.getController();
        resultat.initialiserVue();
        return resultat;
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NEW_OPERATION);
    }

    @Override
    public void broadCast(Ordre ordre) {
        if (ordre.getType()== Ordre.OrdreType.NEW_OPERATION) {
            String resultat = "";
            for (OperationHistorisee op : this.controleur.getOperationsHistorisees()) {
                resultat += op.getOperande1()+ " "+ op.getOperation() + " "+ op.getOperande2()+ " = " +
                        op.getResultat()+"\n";
            }
            this.histo.setText(resultat);
        }
    }

    public void gotomenu(ActionEvent actionEvent) {
        controleur.gotoMenu();
    }
}