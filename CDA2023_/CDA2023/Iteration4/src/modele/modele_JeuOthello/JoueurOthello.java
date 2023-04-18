package modele.modele_JeuOthello;

import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;

public class JoueurOthello extends JoueurGeneral {
    private String noir = "⚫";
    private String blanc = "⚪";
    private String couleur;
    public JoueurOthello(){
        super();
    }
    public JoueurOthello(String joueur, String couleur){
        super(joueur);
        this.couleur = couleur;
    }
    public String getCouleur(){
        return couleur;
    }
    public String getNoir() {
        return noir;
    }
    public String getBlanc() {
        return blanc;
    }
}
