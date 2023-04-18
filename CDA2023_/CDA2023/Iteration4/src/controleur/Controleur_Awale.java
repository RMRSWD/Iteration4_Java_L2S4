package controleur;
import modele.Coup_Joueur_Plateau_Generaux.CoupGeneral;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;
import modele.Coup_Joueur_Plateau_Generaux.PlateauGeneral;
import modele.modele_JeuAwale.CoupAwale;
import vue.Ihm;
public class Controleur_Awale extends Jeu_UsePatronDeMethode{
    public Controleur_Awale(Ihm ihm, JoueurGeneral joueur1, JoueurGeneral joueur2, CoupGeneral coupGeneral, JoueurGeneral joueurCourant, JoueurGeneral joueurAdversaire , PlateauGeneral plateauGeneral){
        super(ihm,joueur1, joueur2, joueurAdversaire,joueurCourant, coupGeneral, plateauGeneral);
        ihm.setControleur_Awale(this);
    }
    public String afficherPlateauAwale(){
        return  plateauGeneral_.toString();
    }
    @Override
    public void afficherPlateau() {
        ihm.afficherPlateau_Awale();
    }
    @Override
    void demanderPosition() {
        this.ihm.demandePositionAwale(this.joueurCourant.getNomJ(), this.joueurCourant.getCouleur());
    }
    @Override
    boolean testJeuFini() {
        return plateauGeneral_.JeuFini(this.joueur1, this.joueur2);
    }
    @Override
    void demandeRejouer() {
        ihm.demandeRejouerAwale(this.joueur1, this.joueur2);
    }
    @Override
    void afficherCoupAI(CoupGeneral coupAI) {
        ihm.afficherCoupIA(coupAI.getNumLigne(),'N');
    }
    @Override
    void demander_RejouerAI() {
        ihm.demandeRejouerAIAwale(this.joueur1, this.joueur2);
    }
    public void effectuerCoup(int num) {
        coupGeneral_ = new CoupAwale(num);
        plateauGeneral_.effectuerCoup(coupGeneral_, this.joueurCourant);
    }
    public boolean gererCoup(int num) {
        CoupGeneral coup_new = new CoupAwale(num);
        return plateauGeneral_.verifierCoup(coup_new, this.joueurCourant );
    }
}
