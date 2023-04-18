package modele.modele_JeuOthello;

import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;
import java.util.ArrayList;
import java.util.List;

public class DamierOthello extends PlateauGeneral {
    private JoueurOthello couleur;

    public DamierOthello(int ligne, int colonne) {
        super(ligne,colonne);
        couleur = new JoueurOthello();
    }

    public DamierOthello(String[][] init_){
        super();
        this.init = init_;
    }
    public void creerUnDamier() {
        for (int i = 0; i < ligne_; i++) {
            for (int j = 0; j < colonne_; j++) {
                this.init[i][j] = "";
            }
        }
        init[3][3] = couleur.getBlanc();
        init[3][4] = couleur.getNoir();
        init[4][3] = couleur.getNoir();
        init[4][4] = couleur.getBlanc();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s = new StringBuilder("   A B C D  E F G H\n");
        for (int i = 0; i < ligne_; i++) {
            s.append((i + 1)).append(" ");
            for (int j = 0; j < colonne_; j++) {
                if (init[i][j].equals(couleur.getNoir()) ) {
                    s.append(couleur.getNoir());
                } else if (init[i][j].equals(couleur.getBlanc())) {
                    s.append(couleur.getBlanc());
                } else {
                    s.append("\uD83D\uDFE9").append(init[i][j]);
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    /*
    explique la méthode verifierCoup:
    cette méthode permet virifier un coup que l'utilisateur saisit et retourne true ou false.
    elle prend deux attribut new_coup et joueurCourant, pour l'attribut new_coup, nous utilisons plys un array "listeCharLettre8"
    pour transformer une lettre à un numéro, ce numéro correspond à la colonne dans le damier.
    Après, else vérifie un coup qui respecte la règle du jeu ou pas, pour faire ça on suivit les étapes suivantes:
    1: (line 72) vérifie la position effecte le coup est vide, si pas vite on retourne false et demande le joueur re-saisir un coup valide
    2: (line 77) vérifie les positions autour de la position d'utilisateur selon 8 direction:
        (-1,0) : vertical vers haut
        (1,0) : vertical vers bas
        (0,-1) : horizontal vers gauche
        (0,1) : horizontal vers droite
        (-1,-1) : diagonal vers haut gauche
        (-1,1) : diagonal vers haut droite
        (1,-1) : diagonal vers bas gauche
        (1,1) : diagonal vers bas droite
     r = ligne
     c = colonne
     si r < 8 et c < 8 && init[r][c] != vide && init[r][c] != couleur joueur courant -> true
     sinon continuer vérifier les directions restes
    3: (lingne 87) si la condition 'while' valide, on continue vérifier s'il y a encore couleur = couleur d'adversaire selon la direction courant.
    On vérifie jusqu’à ce qu’une couleur = couleur du joueur actuel. Quitter la boucle 'while' et changer dans l’autre direction
    */
    public boolean verifierCoup(CoupGeneral new_coup, JoueurGeneral joueurCourant) {
        int ligne = new_coup.getNumLigne() - 1;
        char lettre = new_coup.getLettre();
        int col = 0;
        boolean coupValide = false;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        char[] listeCharLettre8 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (int i = 0; i < listeCharLettre8.length; i++) {
            if (lettre == listeCharLettre8[i]) {
                col = i;
            }
        }
        if (getInit()[ligne][col].equals("")) {//si la position entrer = vide
            for (int[] dir : directions) {
                int r = ligne + dir[0];
                int c = col + dir[1];
                while (r >= 0 && r < this.ligne_ && c >= 0 && c < this.colonne_ && !this.getInit()[r][c].equals("") && !this.getInit()[r][c].equals(joueurCourant.getCouleur())) {//vérifier les pions à coté la position le pion d'utilisateur
                    r += dir[0];
                    c += dir[1];
                    if (r >= 0 && r < this.ligne_ && c >= 0 && c < this.colonne_ && !this.getInit()[r][c].equals("") && this.getInit()[r][c].equals(joueurCourant.getCouleur())) {
                        coupValide = true;
                    }
                }
            }
        } else {
            return coupValide;
        }
        return coupValide;
    }
    public boolean JeuFini(JoueurGeneral joueur1, JoueurGeneral joueur2) {
        for (int i = 0; i < this.ligne_; i++) {
            for (int j = 0; j < this.colonne_; j++) {
                CoupOthello finiTest = new CoupOthello(i + 1, (char) ('A' + j));//'A'=65
                if (this.getInit()[i][j].equals("")) {
                    if (verifierCoup(finiTest, joueur1) || verifierCoup(finiTest, joueur2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void effectuerCoup(CoupGeneral new_coup, JoueurGeneral joueurCourant) {
        int ligne = new_coup.getNumLigne() - 1;
        char lettre = new_coup.getLettre();
        int col = 0;
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        char[] listeCharLettre8 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (int i = 0; i < listeCharLettre8.length; i++) {
            if (lettre == listeCharLettre8[i]) {
                col = i;
            }
        }
        if (getInit()[ligne][col].equals("")) {//si la position entrer = vide (c'est a dire il y a une couleur sur cette position)
            for (int[] dir : directions) {
                int r = ligne + dir[0];
                int c = col + dir[1];
                while (r >= 0 && r < this.ligne_ && c >= 0 && c < this.colonne_ && !this.getInit()[r][c].equals("") && !this.getInit()[r][c].equals(joueurCourant.getCouleur())) {//vérifier les pions à coté la position le pion d'utilisateur
                    r += dir[0];
                    c += dir[1];
                    if (r >= 0 && r < this.ligne_ && c >= 0 && c < this.colonne_ && !this.getInit()[r][c].equals("") && this.getInit()[r][c].equals(joueurCourant.getCouleur())) {//verifier les pions pour effectuer le coup
                        this.getInit()[ligne][col] = joueurCourant.getCouleur();
                        r = ligne + dir[0];
                        c = col + dir[1];
                        while (this.getInit()[r][c] != joueurCourant.getCouleur()) {
                            this.getInit()[r][c] = joueurCourant.getCouleur();
                            r += dir[0];
                            c += dir[1];
                        }
                    }
                }
            }
        }
    }
    public int compterPionNoir() {
        int pionNoir = 0;
        for (int i = 0; i < this.ligne_; i++) {
            for (int j = 0; j < this.colonne_; j++) {
                if (getInit()[i][j] == couleur.getNoir()) {
                    pionNoir++;
                }
            }
        }
        return pionNoir;
    }

    public int compterPionBlanc() {
        int pionBlanc = 0;
        for (int i = 0; i < this.ligne_; i++) {
            for (int j = 0; j < this.colonne_; j++) {
                if (getInit()[i][j] == couleur.getBlanc()) {
                    pionBlanc++;
                }
            }
        }
        return pionBlanc;
    }
    public String JeuTermine(String j1, String j2){
        int pionNoir = compterPionNoir();
        int pionBlanc = compterPionBlanc();
        if(pionNoir - pionBlanc > 0){
            return j1;
        }
        else if( pionNoir - pionBlanc < 0){
            return j2;
        }
        else{
            return "ex aequo";
        }
    }
    //prendre tous les coups possibles AI
    public List<CoupGeneral> prendTousLesCoupsPossible(JoueurGeneral joueur) {
        List<CoupGeneral> possibleCoups = new ArrayList<>();
        for (int ligne = 0; ligne < this.ligne_; ligne++) {
            for (int colonne = 0; colonne < this.colonne_; colonne++) {
                if (this.getInit()[ligne][colonne].equals("")) {
                    CoupOthello creerCoup = new CoupOthello(ligne + 1, (char) ('A' + colonne));//'A'=65
                    if (verifierCoup(creerCoup, joueur)) {
                        possibleCoups.add(creerCoup);
                    }
                }
            }
        }
        return possibleCoups;
    }

}