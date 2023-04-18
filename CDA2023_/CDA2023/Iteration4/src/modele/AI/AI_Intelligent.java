package modele.AI;
import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;
import modele.modele_JeuOthello.CoupOthello;
import modele.modele_JeuOthello.DamierOthello;

import java.util.List;

public class AI_Intelligent  implements AI_Strategie {
    public AI_Intelligent() {
        super();
    }
    //Fonction d'évaluation pour une couleur donnée

    public int evaluationDamier(PlateauGeneral damier_, JoueurGeneral joueur1, JoueurGeneral joueur2, JoueurGeneral joueurCourant, String numJeu) {
        int note = 0;
        //Verifier si le jeu est fini
        //JeuFini retourne true s'il reste encore le coup à faire
        //Ici, quand l'ordinateur faire sa partie, il reste encore des coups à faire. Donc on met not pour continuer d'evaluation
        if (!damier_.JeuFini(joueur1, joueur2)) {
            String vainqueur = damier_.JeuTermine(joueur1.getNomJ(), joueur2.getNomJ());
            if (vainqueur.equals(joueurCourant.getNomJ())) {
                return 1000;
            } else {
                return -1000;
            }
        }
        if (numJeu.equals("1")) {
            for (int i = 0; i < damier_.getLigne(); i++) {
                for (int j = 0; j < damier_.getColonne(); j++) {
                    String couleurJoueurCourant = damier_.getInit()[i][j];
                    //vérifier la posion dans un coin
                    if (couleurJoueurCourant.equals(joueurCourant.getCouleur())) {


                        if (
                                (i == 0 && j == 0) ||
                                        (i == 0 && j == damier_.getColonne() - 1) ||
                                        (i == damier_.getLigne() - 1 && j == 0) ||
                                        (i == damier_.getLigne() - 1 && j == damier_.getColonne() - 1)
                        ) {
                            note += 11;
//                        verifie si la position est au bord
                        } else if (
                                i == 0 || i == damier_.getLigne() - 1 || j == 0 || j == damier_.getColonne() - 1
                        ) {
                            note += 6;
//                        verifie si la position est autre ailleurs
                        } else {
                            note += 1;
                        }


                    }
                }
            }
        }
//La partie d’évaluation la différence rentre le nombre de graines du grenier de l’IA et le nombre de graines du grenier de son adversaire Awale
        else {
            String[][] plateau = damier_.getInit();
            int grainesGrenierJ1 = Integer.parseInt(plateau[Integer.parseInt(joueur1.getCouleur())][0]);
            int grainesGrenierJ2 = Integer.parseInt(plateau[Integer.parseInt(joueur2.getCouleur())][6]);
            if(joueurCourant.getNomJ().equals(joueur1.getNomJ())){
                note = grainesGrenierJ1 - grainesGrenierJ2;
            }
            else{
                note = grainesGrenierJ2 - grainesGrenierJ1;
            }
        }
        return note;
    }
    public int Minimax(PlateauGeneral damier_, int profondeur, boolean isMaximizingPlayer, JoueurGeneral j1, JoueurGeneral j2, JoueurGeneral joueurCourant, String numJeu) {
        if (profondeur == 0 || damier_.JeuFini(j1, j2)) {
            return evaluationDamier(damier_, j1, j2, joueurCourant, numJeu);
        }
        if (isMaximizingPlayer) {
            int meilleurNote = Integer.MIN_VALUE;
            List<CoupGeneral> coupsPossibles = damier_.prendTousLesCoupsPossible(joueurCourant);
            for (CoupGeneral list : coupsPossibles) {
                DamierOthello new_Damier = new DamierOthello(damier_.getInit());
                CoupOthello new_Coup = new CoupOthello(list.getNumLigne(), list.getLettre());
                new_Damier.effectuerCoup(new_Coup, joueurCourant);
                int note = Minimax(new_Damier, profondeur - 1, false, j1, j2, joueurCourant, numJeu);
                meilleurNote = Math.max(meilleurNote, note);
            }
            return meilleurNote;
        } else {
            int meilleurNote = Integer.MAX_VALUE;
            JoueurGeneral Adversaire = joueurCourant == j1 ? j2 : j1;
            List<CoupGeneral> coupsPossibles_ = damier_.prendTousLesCoupsPossible(Adversaire);
            for (CoupGeneral list : coupsPossibles_) {
                DamierOthello new_Damier = new DamierOthello(damier_.getInit());
                CoupOthello new_Coup = new CoupOthello(list.getNumLigne(), list.getLettre());
                new_Damier.effectuerCoup(new_Coup, joueurCourant);
                int note = Minimax(new_Damier, profondeur - 1, true, j1, j2, joueurCourant, numJeu);
                meilleurNote = Math.min(meilleurNote, note);
            }
            return meilleurNote;
        }
    }
    public CoupGeneral ChercherMeilleurCoup(PlateauGeneral damier_, JoueurGeneral j1, JoueurGeneral j2, JoueurGeneral joueurCourant, int profondeur, String numJeu) {
        List<CoupGeneral> possibleCoups = damier_.prendTousLesCoupsPossible(joueurCourant);
        if (possibleCoups.isEmpty()) {
            return null;
        }
        CoupGeneral meilleurCoup = possibleCoups.get(0);//prendre le coup à la première position dans List
        int meilleurValeur = Integer.MIN_VALUE;
        for (CoupGeneral coup : possibleCoups) {
            int valueur = Minimax(damier_, profondeur - 1, false, j1, j2, joueurCourant, numJeu);
            if (valueur > meilleurValeur) {
                meilleurValeur = valueur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }
    @Override
    public CoupGeneral CoupAI(PlateauGeneral damier, JoueurGeneral joueur1, JoueurGeneral joueur2, JoueurGeneral joueurCourant, String niveau, String numJeu) {
        CoupGeneral coupGeneral ;
        if(niveau.equals("3")){
            coupGeneral = ChercherMeilleurCoup(damier, joueur1, joueur2, joueurCourant, 2, numJeu);
        }
        else if(niveau.equals("4")){
            coupGeneral = ChercherMeilleurCoup(damier, joueur1, joueur2, joueurCourant, 4, numJeu);
        }
        else {
            coupGeneral = ChercherMeilleurCoup(damier, joueur1, joueur2, joueurCourant, 5, numJeu);
        }
        return coupGeneral;
    }

}





