package controleur;
import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;
import modele.modele_JeuOthello.CoupOthello;
import vue.Ihm;
import java.util.List;
public class Controleur_Othello extends Jeu_UsePatronDeMethode {
    public Controleur_Othello(Ihm ihm, JoueurGeneral joueur1, JoueurGeneral joueur2, JoueurGeneral joueurCourant, JoueurGeneral joueurAdversaire , CoupGeneral coupGeneral, PlateauGeneral plateauGeneral) {
        super(ihm, joueur1, joueur2, joueurCourant, joueurAdversaire , coupGeneral, plateauGeneral);
        ihm.setControleur_Othello(this);
    }
    @Override
    void demandeRejouer() {
        ihm.demandeRejouer(this.joueur1, this.joueur2);
    }
    public String afficherDamier() {
        return plateauGeneral_.toString();
    }
    @Override
    public void afficherPlateau(){
        this.ihm.afficherDamier();
    }
    @Override
    void demanderPosition() {
        ihm.demandeCoup(this.joueurCourant);
    }
    public boolean gererCoup(int numColonne, char lettre, JoueurGeneral joueurCourant) {
        CoupGeneral new_coup = new CoupOthello(numColonne, lettre);
        return plateauGeneral_.verifierCoup(new_coup, joueurCourant);
    }
    public void effectuerCoup(int numColonne, char lettre, JoueurGeneral joueurCourant){
        CoupOthello new_Coup = new CoupOthello(numColonne, lettre);
        plateauGeneral_.effectuerCoup(new_Coup, joueurCourant);
    }
    public boolean testJeuFini(){
        super.getAdversaire(joueurCourant);
        JoueurGeneral j1 = this.joueurCourant;
        JoueurGeneral j2 = getAdversaire(this.joueurCourant);
        return plateauGeneral_.JeuFini(j1,j2);
    }

    @Override
    void afficherCoupAI(CoupGeneral coupAI) {
        ihm.afficherCoupIA(coupAI.getNumLigne(),coupAI.getLettre());
    }


    @Override
    void demander_RejouerAI() {
        ihm.demandeRejouerAI(this.joueur1,this.joueur2);
    }

    public int CoupPossibleDuJoueur(JoueurGeneral joueur){
        List<CoupGeneral> coupDuJoueurCourant= plateauGeneral_.prendTousLesCoupsPossible(joueur);
        return coupDuJoueurCourant.size();
    }
    public CoupGeneral PrendreUnCoupExemple(JoueurGeneral joueur){
        List<CoupGeneral> exempleUnCoupPourJoueur = plateauGeneral_.prendTousLesCoupsPossible(joueur);
        return exempleUnCoupPourJoueur.get(0);
    }
    public int NumLigneExemple(JoueurGeneral joueur){
        CoupGeneral coupExemple=PrendreUnCoupExemple(joueur);
        return coupExemple.getNumLigne();
    }
    public char CharColonneExemple(JoueurGeneral joueur){
        CoupGeneral coupExemple = PrendreUnCoupExemple(joueur);
        return coupExemple.getLettre();
    }
}