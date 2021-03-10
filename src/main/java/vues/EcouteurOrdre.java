package vues;

import controleur.LanceurOrdre;
import controleur.ordres.Ordre;

public interface EcouteurOrdre {

    void setAbonnements(LanceurOrdre controleur);
    void broadCast(Ordre ordre);
}
