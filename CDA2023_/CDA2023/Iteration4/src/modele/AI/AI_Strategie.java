package modele.AI;
import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;

public interface AI_Strategie {

    CoupGeneral CoupAI(PlateauGeneral damier, JoueurGeneral joueur1, JoueurGeneral joueur2, JoueurGeneral joueurCourant, String niveau, String numJeu);
}
