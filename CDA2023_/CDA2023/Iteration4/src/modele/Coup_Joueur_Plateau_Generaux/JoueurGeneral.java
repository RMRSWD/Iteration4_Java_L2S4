package modele.Coup_Joueur_Plateau_Generaux;

public abstract class JoueurGeneral {
    public String nomJoueur;
    public int nbPartiesGagnees;
    public int nbPartiesEgaux;

    public JoueurGeneral(String nomJoueur) {
        this.nomJoueur = nomJoueur;
        this.nbPartiesGagnees = 0;
        this.nbPartiesEgaux = 0;
    }
    public JoueurGeneral() {
    }
    public String getNomJ() {
        return nomJoueur;
}
    public void casEgaux() {
        nbPartiesEgaux++;
    }
    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }
    public void gagnerPartie() {
        nbPartiesGagnees++;
    }
    public int getNbPartiesEgaux() {
        return nbPartiesEgaux;
    }

    public abstract String getCouleur();

    public abstract String getNoir();

    public abstract String getBlanc();



}



