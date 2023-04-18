package controleur;
import modele.AI.AINaif;
import modele.AI.AI_Intelligent;
import modele.AI.AI_Strategie;
import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;
import vue.Ihm;
import java.util.HashMap;
import java.util.Map;
public abstract class Jeu_UsePatronDeMethode {
     Ihm ihm;
     PlateauGeneral plateauGeneral_;
     CoupGeneral coupGeneral_;
     JoueurGeneral joueur1;
     JoueurGeneral joueur2;
     JoueurGeneral joueurCourant ;
     JoueurGeneral joueurAdversaire;
     Map<String, AI_Strategie> lesStrategieAI;
    public Jeu_UsePatronDeMethode(Ihm ihm, JoueurGeneral joueur1, JoueurGeneral joueur2, JoueurGeneral joueurAdversaire, JoueurGeneral joueurCourant, CoupGeneral coupGeneral, PlateauGeneral plateauGeneral){
        this.plateauGeneral_ = plateauGeneral;
        this.coupGeneral_ = coupGeneral;
        this.joueurCourant = joueurCourant;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurAdversaire = joueurAdversaire;
        this.lesStrategieAI = new HashMap<>();
        this.ihm = ihm;
    }
    public void jouer(){
         initialiserJeu();
         initialiserJoueurCourant();
         afficherPlateau();
         do{
         demanderPosition();
         afficherPlateau();
         changerTourJoueur();
         }
         while(testJeuFini());
         joueurGargnePartie();
         demandeRejouer();
    }
    public void jouerAI(String codeAI, String numJeu) {
        addStrategie("1", new AINaif());
        addStrategie("3",new AI_Intelligent());
        addStrategie("4",new AI_Intelligent());
        addStrategie("5",new AI_Intelligent());
        AI_Strategie ai_strategie = re_prendreAIChoisit(codeAI);//créer un nouveau object AI
        initialiserJeu();
        initialiserJoueurCourant();
        afficherPlateau();
        do{
            if(getJoueurCourant().getNomJ().equals("AI")){
                CoupGeneral coupAI = ai_strategie.CoupAI(getDamierCourant(),getNomJ1(),getNomJ2(), getJoueurCourant(), codeAI, numJeu);
                if(coupAI != null){
                    effectuerCoupAI(coupAI, getJoueurCourant());
                    afficherCoupAI(coupAI);
                }
                else{
                    passerTourAI();
                }
            }
            else{
                demanderPosition();
            }
            afficherPlateau();
            changerTourJoueur();
            getJoueurCourant();
        }
        while(testJeuFini());
        joueurGargnePartie();
        demander_RejouerAI();
    }
    abstract void afficherPlateau();
    abstract void demanderPosition();
    abstract boolean testJeuFini();
    abstract void demandeRejouer();
    abstract void afficherCoupAI(CoupGeneral coupAI);
    abstract void demander_RejouerAI();
    public void initialiserJeu(){
        plateauGeneral_.creerUnDamier();
    }
    public JoueurGeneral initialiserJoueurCourant(){
        return this.joueurCourant = this.joueur1;
    }
    public JoueurGeneral changerTourJoueur(){
        if(this.joueurCourant == this.joueur1){
            this.joueurCourant = this.joueur2;
        }
        else if (this.joueurCourant == this.joueur2){
            this.joueurCourant = this.joueur1;
        }
        return joueurCourant;
    }
    public void joueurGargnePartie(){
        if(plateauGeneral_.JeuTermine(joueur1.getNomJ(), joueur2.getNomJ()).equals(joueur1.getNomJ())){
            joueur1.gagnerPartie();
            ihm.afficherGagneeUneParie(joueur1, plateauGeneral_.compterPionNoir());
            ihm.afficherPerduAdversaire(joueur2, plateauGeneral_.compterPionBlanc());
        }
        else if(plateauGeneral_.JeuTermine(joueur1.getNomJ(), joueur2.getNomJ()).equals(joueur2.getNomJ())){
            joueur2.gagnerPartie();
            ihm.afficherGagneeUneParie(joueur2, plateauGeneral_.compterPionBlanc());
            ihm.afficherPerduAdversaire(joueur1, plateauGeneral_.compterPionNoir());
        }
        else if(plateauGeneral_.JeuTermine(joueur1.getNomJ(), joueur2.getNomJ()).equals("ex aequo")){
            joueur1.casEgaux();
            ihm.afficherEgaux();
        }
    }

    public JoueurGeneral getJoueurCourant(){
        return this.joueurCourant;
    }

    public JoueurGeneral getAdversaire(JoueurGeneral joueurCourant){
        if(joueurCourant == joueur1){
            joueurAdversaire = joueur2;
        }
        else if (joueurCourant == joueur2){
            joueurAdversaire = joueur1;
        }
        return joueurAdversaire;
    }
    public void addStrategie(String code, AI_Strategie strategie)
    {
        this.lesStrategieAI.put(code, strategie);
    }
    public void passerTourAI(){
        ihm.passerTourIA();
    }

    public AI_Strategie re_prendreAIChoisit(String codeAI){
        return lesStrategieAI.get(codeAI);
    }

    public void effectuerCoupAI(CoupGeneral coupAI, JoueurGeneral joueurCourant){
        plateauGeneral_.effectuerCoup(coupAI, joueurCourant);

    }
    public JoueurGeneral getNomJ1(){
        return this.joueur1;
    }
    public JoueurGeneral getNomJ2(){
        return this.joueur2;
    }

    public PlateauGeneral getDamierCourant(){
        return this.plateauGeneral_;
    }


}