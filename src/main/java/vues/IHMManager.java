package vues;

import controleur.Controleur;
import controleur.LanceurOrdre;
import controleur.ordres.Ordre;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class IHMManager implements VueInteractive, EcouteurOrdre {

    private Calculatrice calculatrice;
    private Historique historique;
    private Menu menu;
    private Stage stage;


    public IHMManager(Stage stage) {
        try {
            this.stage = stage;
            calculatrice = Calculatrice.creerVue();
            menu = Menu.creerVue();
            historique = Historique.creerVue();
        } catch (IOException e) {
            System.err.println("Problème au chargement d'une des deux vues FXML !");
            Platform.exit();
        }
    }


    private void showMenu() {
        this.stage.setScene(menu.getScene());
        this.stage.show();
    }

    private void showCalculatrice() {
        this.stage.setScene(calculatrice.getScene());
        this.stage.show();
    }

    private void showHistorique() {
        this.stage.setScene(historique.getScene());
        this.stage.show();
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.historique.setControleur(controleur);
        this.calculatrice.setControleur(controleur);
        this.menu.setControleur(controleur);
    }

    @Override
    public void setAbonnements(LanceurOrdre lanceurOrdre) {
        lanceurOrdre.abonnement(this, Ordre.OrdreType.SHOW_CALCULATRICE,
                Ordre.OrdreType.SHOW_HISTO, Ordre.OrdreType.SHOW_MENU, Ordre.OrdreType.ERREUR_OPERATION_MAL_FORMEE, Ordre.OrdreType.ERREUR_OPERATION_NON_SUPPORTEE);
        this.historique.setAbonnements(lanceurOrdre);
        this.calculatrice.setAbonnements(lanceurOrdre);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()) {
            case SHOW_MENU: {
                this.showMenu();
                break;
            }
            case SHOW_CALCULATRICE:{
                this.showCalculatrice();
                break;
            }
            case SHOW_HISTO: {
                this.showHistorique();
                break;
            }

            case ERREUR_OPERATION_MAL_FORMEE: {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Opération mal formée !");
                alert.setTitle("Erreur de saisie");
                alert.showAndWait();
                break;
            }

            case ERREUR_OPERATION_NON_SUPPORTEE:
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Opération demandée n'est pas gérée par le modèle !");
                    alert.setTitle("Erreur inattendue !");
                    alert.showAndWait();
                    break;
                }


        }
    }
}
