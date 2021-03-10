package controleur;

import controleur.ordres.Ordre;
import facade.CalculatriceDynamiqueDuFutur;
import facade.CalculatriceDynamiqueDuFuturImpl;
import modele.calcul.OperationHistorisee;
import modele.exceptions.NonSupporteeException;
import modele.exceptions.OperationMalFormeeException;
import vues.IHMManager;
import vues.EcouteurOrdre;

import java.util.*;

public class Controleur implements LanceurOrdre {


    private IHMManager vue;
    private CalculatriceDynamiqueDuFutur facade;

    private Map<Ordre.OrdreType, Collection<EcouteurOrdre>> composants;
    public Controleur(IHMManager vue) {
        this.vue = vue;
        this.facade = new CalculatriceDynamiqueDuFuturImpl();
        composants = new HashMap<>();
        for (Ordre.OrdreType ordreType : Ordre.OrdreType.values()){
            composants.put(ordreType,new ArrayList<>());
        }
        vue.setControleur(this);
        vue.setAbonnements(this);
    }


    public void run() {
        gotoMenu();
    }

    @Override
    public void abonnement(EcouteurOrdre o, Ordre.OrdreType... types) {
        for (Ordre.OrdreType type : types)
            this.composants.get(type).add(o);
    }

    @Override
    public void fireOrdre(Ordre ordre) {
        for (EcouteurOrdre o : this.composants.get(ordre.getType())) {
            o.broadCast(ordre);
        }
    }


    public void gotoMenu() {
        this.fireOrdre(new Ordre(Ordre.OrdreType.SHOW_MENU));
    }

    public void gotoHistorique() {
        this.fireOrdre(new Ordre(Ordre.OrdreType.SHOW_HISTO));
    }

    public void gotoCalculatrice(){
        this.fireOrdre(new Ordre(Ordre.OrdreType.SHOW_CALCULATRICE));
    }

    private String nombreAAfficher;

    public String getNombreAAfficher() {
        return nombreAAfficher;
    }


    public void inputEquals() {
        try {
            this.facade.inputEqual();
            this.nombreAAfficher = Double.toString(this.facade.getResultatCourant());
            this.fireOrdre(new Ordre(Ordre.OrdreType.NEW_OPERATION));

        } catch (OperationMalFormeeException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_OPERATION_MAL_FORMEE));
        } catch (NonSupporteeException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_OPERATION_NON_SUPPORTEE));
        }
    }

    public void inputDigit(Integer digit) {
        this.facade.inputDigit(digit);
        this.nombreAAfficher = this.facade.getOperandeCourante();
        this.fireOrdre(new Ordre(Ordre.OrdreType.NEW_OPERANDE));

    }

    public void inputSymbol(String symbol) {
        try {
            this.facade.inputOperation(symbol);
            this.nombreAAfficher = Double.toString(facade.getResultatCourant());
            this.fireOrdre(new Ordre(Ordre.OrdreType.NEW_OPERATION));

        } catch (OperationMalFormeeException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_OPERATION_MAL_FORMEE));
        } catch (NonSupporteeException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_OPERATION_NON_SUPPORTEE));
        }
    }

    public Collection<OperationHistorisee> getOperationsHistorisees() {
        return this.facade.getHistorique();
    }
}
