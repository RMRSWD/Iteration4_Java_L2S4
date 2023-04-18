package vue;

import controleur.Controleur_Othello;
import controleur.Controleur_Awale;
import modele.Coup_Joueur_Plateau_Generaux.JoueurGeneral;

import java.util.Scanner;

public class Ihm {
    private Controleur_Othello controleur_Othello;
    private Controleur_Awale controleur_Awale;

    public Ihm() {
        System.out.println("------------------------------\n" +
                "Bienvenue Dans Le Menu Du Jeu \n"+
                "------------------------------");
        System.out.println("Veuillez choisissez un numéro en bas pour entrer le jeu: \n" +
                "1: Jeu Othello\n" +
                "2: Jeu Awalé");
    }

    public void setControleur_Othello(Controleur_Othello controleur_Othello) {
        this.controleur_Othello = controleur_Othello;
    }
    public void setControleur_Awale(Controleur_Awale controleur_Awale){
        this.controleur_Awale = controleur_Awale;
    }

    public void afficherDamier() {
        System.out.println(controleur_Othello.afficherDamier());
    }
    public void afficherPlateau_Awale(){
        System.out.println(controleur_Awale.afficherPlateauAwale());
    }
    public void demandeCoup(JoueurGeneral joueur){
        Scanner sc = new Scanner(System.in);
        JoueurGeneral joueurCourant = joueur;
        String input;
        int numColonne;
        char lettre;
        while (true) {
            System.out.println(joueurCourant.getNomJ() + joueurCourant.getCouleur() + " à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une lettre entre A et H (ex: 3D ) ou P pour passer son tour.");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("P")) {
                int casesPossible = controleur_Othello.CoupPossibleDuJoueur(joueur);
                if (casesPossible > 0) {
                    System.out.println("Vous ne pouvez passer votre tour. Vous avez "+ casesPossible + " case(s) possible(s) pour jouer.\n" +
                            "Pour vous aider: Vous pouvez jouer un coup à la position par exemple: " +
                            "( "+ controleur_Othello.NumLigneExemple(joueur) + "," + controleur_Othello.CharColonneExemple(joueur) + " )");
                    continue;
                } else {
                    System.out.println("Tour passé.");
                    break;
                }
            }
            if (input.length() != 2) {
                System.out.println("Entrée non valide veuillez réessayer.");
                continue;
            }
            numColonne = input.charAt(0) -'0'; // '0'=48
            lettre = input.charAt(1);

            if (numColonne < 1 || numColonne > 8) {
                System.out.println("Entrée non valide, veuillez réessayer.");
                continue;
            }
            if (lettre < 'A' || lettre > 'H') {
                System.out.println("Entrée non valide, veuillez réessayer.");
                continue;
            }
            boolean testCoupJoueur = controleur_Othello.gererCoup(numColonne, lettre, joueurCourant);
//            Teste que le coup que joueur saisit qui est correcte ?
            if(testCoupJoueur){
                controleur_Othello.effectuerCoup(numColonne, lettre, joueurCourant);
                break;
            }
            else{
                System.out.println("Veuillez re_saisir une position correcte.");
            }
        }
    }
    public void demandePositionAwale(String nomJ, String numJ){
        if(numJ.equals("1")) {
            System.out.println(nomJ + " à vous de jouer. Vous êtes le joueur 1");
        }
        else{
            System.out.println(nomJ + " à vous de jouer. Vous êtes le joueur 2");
            }
        Scanner sc = new Scanner(System.in);
        System.out.println("Vous pouvez prendre une case qui numérote de 1 à 6");
        int num = 0;
        boolean coupValide = false;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Vous ne pouvez que saisir une chiffre. Re-saisissez-vous!");
                sc.next();
            }
            num = sc.nextInt();
            if(num >= 1 && num <= 6){
                coupValide = controleur_Awale.gererCoup(num);
                if(!coupValide){
                    System.out.println("Coup invalide! Veuillez réessayer.");
                }
            }
            else{
                System.out.println("Le numéro saisi doit être compris entre 1 et 6. Veuillez réessayer.");
            }
        }
        while(!coupValide);
        controleur_Awale.effectuerCoup(num);
    }
    public void demandeRejouer(JoueurGeneral j1, JoueurGeneral j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        String rejouer = sc.nextLine();
        while(true) {
            if (rejouer.equalsIgnoreCase("r")) {
                controleur_Othello.jouer();
                break;
            } else if (rejouer.equalsIgnoreCase("q")) {
                afficherScoreFinal(j1, j2);
                break;
            } else {
                System.out.println("Veuillez re-saisir.Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
                rejouer = sc.nextLine();
            }
        }
    }
    public void demandeRejouerAwale(JoueurGeneral j1, JoueurGeneral j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        String rejouer = sc.nextLine();
        while(true) {
            if (rejouer.equalsIgnoreCase("r")) {
                controleur_Awale.jouer();
                break;
            } else if (rejouer.equalsIgnoreCase("q")) {
                afficherScoreFinal(j1, j2);
                break;
            } else {
                System.out.println("Veuillez re-saisir.Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
                rejouer = sc.nextLine();
            }
        }
    }
    public void demandeRejouerAI(JoueurGeneral j1, JoueurGeneral j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        while(true) {
                String rejouer = sc.nextLine();
                    if (rejouer.equalsIgnoreCase("r")) {
                        ChoixNieauAi_Rejouer();
                        break;
                    } else if (rejouer.equalsIgnoreCase("q")) {
                        afficherScoreFinal(j1, j2);
                        break;
                    } else {
                        System.out.println("Veuillez re-saisir.Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
                    }
//                }
            }
        }
    public void ChoixNieauAi_Rejouer() {
        System.out.println("Voulez-vous changer le mode de jeu Othello?"
                +"Vous pouvez choisir le mode de jeu en cliquant les numéros suivant: \n" +
                "1: La version AI naïf \n" +
                "2: La version AI intelligent");
        Scanner sc = new Scanner (System.in);
        while(true) {
            String choixVersionAI = sc.nextLine();
            if (choixVersionAI.equals("1")) {
                System.out.println("Vous êtes dans le mode de jouer contre AI (version agréable)");
                controleur_Othello.jouerAI(choixVersionAI,"1");
                break;

            } else if(choixVersionAI.equals("2")) {
                System.out.println("Vous êtes dans le mode de jouer contre AI (version difficile )");
                System.out.println("Quel niveau vous voulez jouer: \n" +
                        "3: Normal\n" +
                        "4: Moyen\n" +
                        "5: Difficile");
                String niveau = "";
                while (true) {
                    niveau = sc.nextLine();
                    if (niveau.equals("3")) {
                        System.out.println("Vous avez choisit le niveau normal.");
                        controleur_Othello.jouerAI(niveau,"1");
                        break;
                    } else if (niveau.equals("4")) {
                        System.out.println("Vous avez choisit le niveau moyen.");
                        controleur_Othello.jouerAI(niveau,"1");
                        break;
                    } else if (niveau.equals("5")) {
                        System.out.println("Vous avez choisit le niveau difficile.");
                        controleur_Othello.jouerAI(niveau,"1");
                        break;
                    } else {
                        System.out.println("Le nombre que vous avez sélectionné n'est pas valide. Veuillez saisir à nouveau.");
                    }
                }
            }
            else{
                System.out.println("Vous pouvez uniquement choisir entre 1 ou 2 pour entrer le mode de jeu\n" +
                        "1: La version AI naïf \n" +
                        "2: La version AI intelligent");
            }
        }
    }
    public void demandeRejouerAIAwale(JoueurGeneral j1, JoueurGeneral j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        while(true) {
            String rejouer = sc.nextLine();
            if (rejouer.equalsIgnoreCase("r")) {
                ChoixNieauAi_RejouerAwale();
                break;
            } else if (rejouer.equalsIgnoreCase("q")) {
                afficherScoreFinal(j1, j2);
                break;
            } else {
                System.out.println("Veuillez re-saisir.Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
            }
        }
    }
    public void ChoixNieauAi_RejouerAwale() {
        System.out.println("Voulez-vous changer le mode de jeu Awalé?"
                +"Vous pouvez choisir le mode de jeu en cliquant les numéros suivant: \n" +
                "1: La version AI naïf \n" +
                "2: La version AI intelligent");
        Scanner sc = new Scanner (System.in);
        while(true) {
            String choixVersionAI = sc.nextLine();
            if (choixVersionAI.equals("1")) {
                System.out.println("Vous êtes dans le mode de jouer contre AI (version agréable)");
                controleur_Awale.jouerAI(choixVersionAI,"2");
                break;

            } else if(choixVersionAI.equals("2")){
                System.out.println("Vous êtes dans le mode de jouer contre AI (version difficile )");
                System.out.println("Quel niveau vous voulez jouer: \n" +
                            "3: Normal\n" +
                            "4: Moyen\n" +
                            "5: Difficile");
                    String niveau;
                    while (true) {
                        niveau = sc.nextLine();
                        if (niveau.equals("3")) {
                            System.out.println("Vous avez choisit le niveau normal.");
                            controleur_Awale.jouerAI(niveau,"2");
                            break;
                        } else if (niveau.equals("4")) {
                            System.out.println("Vous avez choisit le niveau moyen.");
                            controleur_Awale.jouerAI(niveau,"2");
                            break;
                        } else if (niveau.equals("5")) {
                            System.out.println("Vous avez choisit le niveau difficile.");
                            controleur_Awale.jouerAI(niveau,"2");
                            break;
                        } else {
                            System.out.println("Le nombre que vous avez sélectionné n'est pas valide. Veuillez saisir à nouveau.");
                        }
                    }
            }
            else{
                System.out.println("Vous pouvez uniquement choisir entre 1 ou 2 pour entrer le mode de jeu\n" +
                        "1: La version AI naïf \n" +
                        "2: La version AI intelligent");
            }
        }
    }
    public void afficherGagneeUneParie(JoueurGeneral joueur, int nbPion){
        System.out.println(joueur.getNomJ()+ " : a gagné!" + " ; Le nombre de pions (graines) que vous avez: "+ nbPion);
    }
    public void afficherScoreFinal(JoueurGeneral j1, JoueurGeneral j2){
        System.out.println("Nombre partie(s) gagnée(s) " +j1.getNomJ() + ": "+ j1.getNbPartiesGagnees() );
        System.out.println("Nombre partie(s) gagnée(s) "+ j2.getNomJ() + ": "+ j2.getNbPartiesGagnees());
        System.out.println("Nombre partie(s) égal(aux): " +j1.getNbPartiesEgaux());
        System.out.println("Aurevoir!!!");
        System.out.println("L'application fait par l'équipe TPF avec: "+"\n 1: Vu The Duc" + "\n 2: Vu Ngoc Hai" + "\n 3: Nordine Seffar");
    }
    public void afficherEgaux() {
        System.out.println("ex aequo");
    }

    public void afficherPerduAdversaire(JoueurGeneral joueur, int nbPion) {
        System.out.println(joueur.getNomJ()+ " : a perdu!" + " ; Le nombre de pions (graines) que vous avez: "+ nbPion);
    }

public String choixVersionJeu(String nomJ1) {
    System.out.println("Vous pouvez choisir les mode de jeu en cliquant les numéros suivant: \n" +
            "1: la version humain vs humain \n" +
            "2: la version humain vs AI");
    Scanner sc = new Scanner(System.in);
    String choixVersion;
    while(true) {
        choixVersion = sc.nextLine();
        if (choixVersion.equals("1")) {
            break;
        } else if(choixVersion.equals("2")){
            break;
        }
        else{
            System.out.println("Vous pouvez uniquement choisir entre 1 ou 2 pour entrer le mode de jeu\n" +
                    "1: la version humain vs humain \n" +
                    "2: la version humain vs AI");
        }
    }
    return choixVersion;
}
public String choixNieauAi() {
    System.out.println("Vous pouvez choisir le mode de jeu en cliquant les numéros suivant: \n" +
            "1: La version AI naïf \n" +
            "2: La version AI intelligent");
    Scanner sc = new Scanner (System.in);
    String choixVersionAI;
    while(true) {
        choixVersionAI = sc.nextLine();
        if (choixVersionAI.equals("1")) {
            System.out.println("Vous êtes dans le mode de jouer contre AI (version agréable)");
            break;

        } else if(choixVersionAI.equals("2")){
            System.out.println("Vous êtes dans le mode de jouer contre AI (version difficile )");
            break;
        }
        else{
            System.out.println("Vous pouvez uniquement choisir entre 1 ou 2 pour entrer le mode de jeu\n" +
                    "1: La version AI naïf \n" +
                    "2: La version AI intelligent");
        }
    }
    return choixVersionAI;
}
    public String saisirNomJoueur1() {
        Scanner sc = new Scanner(System.in);
        boolean testNom = true;
        String nomJ1="";
        while(testNom) {
            System.out.println("Entrer le nom du premier joueur: ");
             nomJ1 = sc.nextLine();
            if(!nomJ1.equals("") && !nomJ1.equals("AI")){
//                choixVersionJeu(nomJ1);
                testNom = false;

            }
            else{
                System.out.println("Veuillez re-saisir le nom du joueur!");
            }
        }
        return nomJ1;
    }
    public String saisirNomJoueur2(String nomJ1) {
        Scanner sc = new Scanner(System.in);
        String nomJ2;
        while(true) {
            System.out.println("Entrer le nom du deuxième joueur: ");
            nomJ2 = sc.nextLine();
            if(!nomJ2.equals("") && !nomJ1.equals(nomJ2)){
                break;
            }
            else{
                System.out.println("Le nom du joueur 1 et joueur 2 doit être differement!!");
                System.out.println("Veuillez re-saisir le nom du joueur 2!");
            }
        }
        return nomJ2;
    }
    public void afficherCoupIA(int lineAI, char colAI) {
        if(colAI == 'N'){
            System.out.println("AI a choisit le numéro: "+ lineAI);
        }
        else {
            System.out.println("AI a fait le coup (" + lineAI + "," + colAI + ")");
        }

    }

    public void passerTourIA() {
        System.out.println("AI passe son tour");
    }

    public String choixJeu() {
        Scanner sc = new Scanner(System.in);
        String choix_jeu = sc.nextLine();
       return choix_jeu;
    }

    public void afficherErreurChoisirJeu() {
        System.out.println("Veuillez re-saisir un numéro qui correspond avec le numéro de jeu que vous voulez de jouer.");
    }

    public String choixNiveauAIDif() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel niveau vous voulez jouer: \n" +
                "3: Normal\n" +
                "4: Moyen\n" +
                "5: Difficile");
        String niveau;
        while(true){
            niveau = sc.nextLine();
            if(niveau.equals("3")){
                System.out.println("Vous avez choisit le niveau normal.");
                break;
            }
            else if(niveau.equals("4")){
                System.out.println("Vous avez choisit le niveau moyen.");
                break;
            }
            else if (niveau.equals("5")){
                System.out.println("Vous avez choisit le niveau difficile.");
                break;
            }
            else{
                System.out.println("Le nombre que vous avez sélectionné n'est pas valide. Veuillez saisir à nouveau.");
            }
        }
        return niveau;
    }
}





