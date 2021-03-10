package controleur.ordres;

public class Ordre {

    public enum OrdreType {SHOW_MENU, SHOW_CALCULATRICE,SHOW_HISTO, NEW_OPERATION, ERREUR_OPERATION_MAL_FORMEE, NEW_OPERANDE,ERREUR_OPERATION_NON_SUPPORTEE
    };
    private OrdreType type;

    public Ordre(OrdreType type) {
        this.type = type;
    }

    public OrdreType getType() {
        return type;
    }

}
