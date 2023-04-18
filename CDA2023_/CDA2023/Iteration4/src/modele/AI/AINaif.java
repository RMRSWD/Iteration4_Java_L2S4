package modele.AI;

import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;

import java.util.List;
import java.util.Random;

public class AINaif implements AI_Strategie {
    public AINaif(){
    }
    public CoupGeneral CoupAI(PlateauGeneral damier, JoueurGeneral joueur1, JoueurGeneral joueur2, JoueurGeneral joueurCourant, String niveau, String numJeu ){
        Random random = new Random();
        CoupGeneral coupAI;
        List <CoupGeneral> tousLesCoupPossibleAI = damier.prendTousLesCoupsPossible(joueurCourant);
        int taille_table = tousLesCoupPossibleAI.size();
        if(taille_table > 0) {
            int randomNumber = random.nextInt(taille_table);
            coupAI = tousLesCoupPossibleAI.get(randomNumber);
            return coupAI;
        }
        else{
            return null;
        }
    }
}
