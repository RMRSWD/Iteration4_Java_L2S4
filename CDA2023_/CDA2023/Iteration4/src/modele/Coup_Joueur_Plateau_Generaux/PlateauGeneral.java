package modele.Coup_Joueur_Plateau_Generaux;

import java.util.List;
public abstract class PlateauGeneral{
    public int ligne_;
    public int colonne_;
    public String[][] init;

    public PlateauGeneral(int line, int colonne ){
        this.ligne_ = line;
        this.colonne_ = colonne;
        this.init = new String[ligne_][colonne_];
    }
    public PlateauGeneral() {
    }

    public abstract void  creerUnDamier();
    public abstract String toString();

    public String[][] getInit() {
        return this.init;
    }
    public int getLigne() {
        return ligne_;
}
    public int getColonne() {
        return colonne_;
    }

    public abstract boolean verifierCoup(CoupGeneral new_coup, JoueurGeneral joueurCourant);
    public abstract void effectuerCoup(CoupGeneral new_coup, JoueurGeneral joueurCourant);

    public abstract boolean JeuFini(JoueurGeneral joueur1, JoueurGeneral joueur2);

   public abstract String JeuTermine(String j1, String j2);
    public abstract List<CoupGeneral> prendTousLesCoupsPossible(JoueurGeneral joueur);

    public abstract int compterPionNoir();

    public abstract int compterPionBlanc();
}
