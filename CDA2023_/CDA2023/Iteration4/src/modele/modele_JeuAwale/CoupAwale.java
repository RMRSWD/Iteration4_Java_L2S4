package modele.modele_JeuAwale;

import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;

public class CoupAwale extends CoupGeneral {
    public CoupAwale(){
        super();
    }
    public CoupAwale(int num){
        super(num);
    }
    @Override
    public char getLettre() {
        return 0;
    }
}
