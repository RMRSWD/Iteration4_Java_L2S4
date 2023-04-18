package main;
import controleur.Controleur_Othello;
import controleur.Controleur_Awale;
import modele.modele_JeuAwale.CoupAwale;
import modele.modele_JeuAwale.JoueurAwale;
import modele.modele_JeuAwale.PlateauAwale;
import modele.modele_JeuOthello.CoupOthello;
import modele.modele_JeuOthello.DamierOthello;
import modele.modele_JeuOthello.JoueurOthello;
import vue.Ihm;
public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        String numJeu;
        String nomJ1;
        String nomJ2;
        String versionJeu;
        String niveauAI;
        String niveauDif;
        while(true) {
            //Jeu Othello
            numJeu = ihm.choixJeu();
            if (numJeu.equals("1")) {
                nomJ1 = ihm.saisirNomJoueur1();
                versionJeu = ihm.choixVersionJeu(nomJ1);
                CoupOthello coupOthelle = new CoupOthello();
                JoueurOthello joueurCourant = new JoueurOthello();
                JoueurOthello joueurAdversaire = new JoueurOthello();
                DamierOthello plateauOthello = new DamierOthello(8,8);
                JoueurOthello joueur1 = new JoueurOthello(nomJ1,"⚫");

                if(versionJeu.equals("1")) {
                    nomJ2 = ihm.saisirNomJoueur2(nomJ1);
                    JoueurOthello joueur2 = new JoueurOthello(nomJ2,"⚪");
                    Controleur_Othello jeu_Othello = new Controleur_Othello(ihm,joueur1, joueur2, joueurAdversaire,joueurCourant,coupOthelle,plateauOthello);
                    jeu_Othello.jouer();
                    break;
                }
                else{
                    JoueurOthello joueur2 = new JoueurOthello("AI","⚪");
                    Controleur_Othello jeu_OthelloAI = new Controleur_Othello(ihm,joueur1, joueur2, joueurAdversaire,joueurCourant,coupOthelle,plateauOthello);
                    niveauAI = ihm.choixNieauAi();
                    if(niveauAI.equals("1")){
                        jeu_OthelloAI.jouerAI(niveauAI,numJeu);
                        break;
                    }
                    else {
                        niveauDif = ihm.choixNiveauAIDif();
                        jeu_OthelloAI.jouerAI(niveauDif, numJeu);
                        break;

                    }
                }
            }
//            Jeu Awale
            else if (numJeu.equals("2")) {
                nomJ1 = ihm.saisirNomJoueur1();
                versionJeu = ihm.choixVersionJeu(nomJ1);
                CoupAwale coupAwale = new CoupAwale();
                JoueurAwale joueurCourant = new JoueurAwale();
                JoueurAwale joueurAdversaire = new JoueurAwale();
                PlateauAwale plateauAwale = new PlateauAwale(2,7);
                JoueurAwale joueur1 = new JoueurAwale(nomJ1,1);
                if(versionJeu.equals("1")) {
                    nomJ2 = ihm.saisirNomJoueur2(nomJ1);
                    JoueurAwale joueur2 = new JoueurAwale(nomJ2,0);
                    Controleur_Awale jeu_Awale = new Controleur_Awale(ihm,joueur1,joueur2 , coupAwale, joueurCourant, joueurAdversaire,  plateauAwale);
                    jeu_Awale.jouer();
                    break;
                }
                else{
                    JoueurAwale joueur2 = new JoueurAwale("AI",0);
                    Controleur_Awale jeu_AwaleAI = new Controleur_Awale(ihm,joueur1,joueur2,coupAwale, joueurCourant, joueurAdversaire, plateauAwale);
                    niveauAI = ihm.choixNieauAi();
                    if(niveauAI.equals("1")){
                        jeu_AwaleAI.jouerAI(niveauAI,numJeu);
                        break;
                    }
                    else {
                        niveauDif = ihm.choixNiveauAIDif();
                        jeu_AwaleAI.jouerAI(niveauDif, numJeu);
                        break;
                    }
                }
            } else {ihm.afficherErreurChoisirJeu();
            }
        }
    }
}
