package controleur;

import controleur.ordres.Ordre;
import vues.EcouteurOrdre;

public interface LanceurOrdre {
    void abonnement(EcouteurOrdre o, Ordre.OrdreType... types);

    void fireOrdre(Ordre ordre);
}
