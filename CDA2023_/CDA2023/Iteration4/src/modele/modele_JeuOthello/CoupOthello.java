package modele.modele_JeuOthello;

import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;

public class CoupOthello extends CoupGeneral {
    // cette classe crée les pion noirs et blancs
    private char lettre;
    public CoupOthello() {
    }
    public CoupOthello(int numLigne, char lettre){
        super(numLigne);
        this.lettre = lettre;

    }
    public char getLettre() {
        return lettre;
    }

}