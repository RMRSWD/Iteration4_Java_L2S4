package modele.modele_JeuAwale;

import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;

public class JoueurAwale extends JoueurGeneral {
    private int num_0 = 0;
    private int num_1 = 1;
    private int numDuJoueur;
    public JoueurAwale(){
    }
    public JoueurAwale(String joueur,int num){
        super(joueur);
        this.numDuJoueur = num;
    }
    @Override
    public String getCouleur() {
        return String.valueOf(numDuJoueur);

    }
    @Override
    public String getNoir() {
        return String.valueOf(num_1);

    }
    @Override
    public String getBlanc() {
        return String.valueOf(num_0);
    }

}
