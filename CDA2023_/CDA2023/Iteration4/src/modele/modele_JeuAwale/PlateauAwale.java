package modele.modele_JeuAwale;
import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;

import java.util.ArrayList;
import java.util.List;
public class PlateauAwale extends PlateauGeneral {
    String gainInitial = "4";
    private JoueurGeneral joueur;
    public PlateauAwale(int ligne, int colonne) {
        super(ligne,colonne);
        this.joueur = new JoueurAwale();
    }

    @Override
    public void creerUnDamier() {
        for (int i = 0; i < ligne_; i++) {
            for (int j = 0; j < colonne_; j++) {
                this.init[i][j] = gainInitial;
            }
        }
        init[0][0] = String.valueOf(0);
        init[1][6] = String.valueOf(0);
    }

@Override
    public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("                   1        2        3        4        5        6\n");
    s.append("Joueur 2 -> ");
    for (int i = 0; i < ligne_; i++) {
        for (int j = 0; j < colonne_; j++) {
            if (i == 0 && j == 0) {
                s.append(String.format("%2s", this.init[i][j])).append(" ");
            } else if (i == 1 && j == 6) {
                s.append(String.format(" %2s", this.init[i][j])).append(" <- Joueur 1");
            } else {
                s.append(" | ").append(String.format("%2s", this.init[i][j])).append("  | ");
            }
        }
        s.append("\n").append("               ");
    }
    return s.toString();
}

    @Override
    public boolean verifierCoup(CoupGeneral new_coup, JoueurGeneral joueurCourant) {

        int numJoueur = Integer.parseInt(joueurCourant.getCouleur());
        int num_Coup = 0;
        if(numJoueur == 0) {
            num_Coup = new_coup.getNumLigne() ;
        }
        else{
            num_Coup =  new_coup.getNumLigne() - 1;
        }
        int totalGrainesAdversaire = 0;
        int[][] intPlateau = convertStringToIntPlateau(this.getInit());
        if ((numJoueur == 0 && num_Coup == 0) || (numJoueur == 1 && num_Coup == 6)) {
            return false;
        }
        //vérifier si la case contient des graines
        if (getInit()[numJoueur][num_Coup].equals("0")) {
            return false;
        }
        //vérifier si le coup ne laisse pas l'adversaire sans grains
        for (int i = 1; i <= this.colonne_ - 1; i++) {
            totalGrainesAdversaire += intPlateau[1 - numJoueur][i];
        }
        //vérifier si l'adversaire n'a pas de graines, le joueur doit les nourrir avec son coup
        if (totalGrainesAdversaire == 0) {
            int positionFinal = num_Coup + intPlateau[numJoueur][num_Coup];
//            Si la position finale est entre 1 et 6 (inclus), cela signifie que le coup permettra de donner des graines à l'adversaire et est donc valide. Si ce n'est pas le cas, le coup n'est pas valide.
            if (positionFinal >= 1 && positionFinal <= 6) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public int[][] convertStringToIntPlateau(String[][] plateau) {
        int[][] intPlateau = new int[ligne_][colonne_];
        for (int i = 0; i < ligne_; i++) {
            for (int j = 0; j < colonne_; j++) {
                intPlateau[i][j] = Integer.parseInt(plateau[i][j]);
            }
        }
        return intPlateau;
    }
    @Override
    public void effectuerCoup(CoupGeneral new_coup, JoueurGeneral joueurCourant) {
        int[][] intPlateau = convertStringToIntPlateau(this.getInit());
        int ligneJoueur = Integer.parseInt(joueurCourant.getCouleur());
        int colonneCoup = ligneJoueur == 0 ? new_coup.getNumLigne() : new_coup.getNumLigne() - 1;

        int graines = intPlateau[ligneJoueur][colonneCoup];
        intPlateau[ligneJoueur][colonneCoup] = 0;
        int position = colonneCoup;
        int tourComplet = graines > 11 ? 1 : 0;

        /*
         * La boucle while s'execute tant qu'il reste des graines à distribuer
         * */
        while (graines > 0) {
            if (ligneJoueur == 0 && position == 1) {
                ligneJoueur = 1;
                position = 0;
            } else if (ligneJoueur == 1 && position == 5) {
                ligneJoueur = 0;
                position = 6;
            } else if (ligneJoueur == 0) {
                position--;
            } else {
                position++;
            }
            //On vérifie que si un tour complet est effectué, sauter le trou où les graines ont été prises
            if(tourComplet == 1 && ligneJoueur == Integer.parseInt(joueurCourant.getCouleur()) && position == colonneCoup){
                continue;
            }
            intPlateau[ligneJoueur][position]++;
            graines--;
        }
        int grainesCapturees = 0;
        //On vérifie si la dernière graine a été déposée dans le camp adverse
        //On passe au champ adverse pour verifier la capture
        if(ligneJoueur != Integer.parseInt(joueurCourant.getCouleur())) {
            ligneJoueur = 1 - ligneJoueur;
            if(ligneJoueur == 0) {
                while ((position >=0 && position <= 5) && (intPlateau[1 - ligneJoueur][position] == 2 || intPlateau[1 - ligneJoueur][position] == 3)) {
                    grainesCapturees += intPlateau[1 - ligneJoueur][position];
                    intPlateau[1 - ligneJoueur][position] = 0;
// Deplace la position vers le trou précédent
                    position--;

                }
            }
            else{
                while ((position >=1 && position <= 6) && (intPlateau[1 - ligneJoueur][position] == 2 || intPlateau[1 - ligneJoueur][position] == 3)) {
                    grainesCapturees += intPlateau[1 - ligneJoueur][position];
                    intPlateau[1 - ligneJoueur][position] = 0;
// Deplace la position vers le trou  suivant
                    position++;

                }

            }
        }
//        On revient au joueur qui a effectue le coup pour metre à jour les graines capturees
        ligneJoueur = Integer.parseInt(joueurCourant.getCouleur());
        if (ligneJoueur == 0) {
            intPlateau[ligneJoueur][0] += grainesCapturees;
        } else {
            intPlateau[ligneJoueur][6] += grainesCapturees;

        }
        updateStringPlateau(intPlateau);
    }
    private void updateStringPlateau(int[][] intPlateau) {
        for (int i = 0; i < ligne_; i++) {
            for (int j = 0; j < colonne_; j++) {
                this.getInit()[i][j] = Integer.toString(intPlateau[i][j]);
            }
        }
    }
    @Override
    public boolean JeuFini(JoueurGeneral joueur1, JoueurGeneral joueur2) {
        int totalGrainesJoueur0 = 0;
        int totalGraineJoueur1 = 0;

        for (int i = 1; i <= 6; i++) {
            totalGrainesJoueur0 += Integer.parseInt(this.getInit()[0][i]);
        }
        for (int j = 0; j < 6; j++) {
            totalGraineJoueur1 += Integer.parseInt(this.getInit()[1][j]);
        }
        if (totalGrainesJoueur0 == 0 || totalGraineJoueur1 == 0) {
            return false;
        }
        return true;
    }
    @Override
    public String JeuTermine(String j1, String j2) {
        int nbGrainesJoueur1 = compterPionNoir();
        int nbGrainesJoueur2 = compterPionBlanc();
        if(nbGrainesJoueur1 > nbGrainesJoueur2){
            return j1;
        }
        else if(nbGrainesJoueur1 < nbGrainesJoueur2){
            return j2;
        }
        else{
            return "ex aequo";
        }
    }
    @Override
    public List<CoupGeneral> prendTousLesCoupsPossible(JoueurGeneral joueur) {
        List<CoupGeneral> possibleCoups = new ArrayList<>();
        for (int ligne = 0; ligne < this.ligne_; ligne++) {
            if(joueur.getCouleur().equals("0")) {
                for(int colonne = 1; colonne <= this.colonne_-1; colonne++){
                    if (!this.getInit()[ligne][colonne].equals("0")) {
                        CoupAwale creerCoup = new CoupAwale(colonne);
                        if (verifierCoup(creerCoup, joueur)) {
                            possibleCoups.add(creerCoup);
                        }
                    }
                }
                }
            else{
                if(joueur.getCouleur().equals("1")){
                    for(int colonne = 0; colonne < this.colonne_ -2; colonne++){
                        if (!this.getInit()[ligne][colonne].equals("1")) {
                            CoupAwale creerCoup = new CoupAwale(colonne);
                            if (verifierCoup(creerCoup, joueur)) {
                                possibleCoups.add(creerCoup);
                            }
                        }
                    }
                }
            }
        }
        return possibleCoups;
    }
    @Override
    public int compterPionNoir() {
        int [][] plateau = convertStringToIntPlateau(this.getInit());
        return plateau[1][6];

    }
    @Override
    public int compterPionBlanc() {
        int [][] plateau = convertStringToIntPlateau(this.getInit());
        return plateau[0][0];
    }
}
