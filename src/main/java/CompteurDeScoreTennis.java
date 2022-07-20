public class CompteurDeScoreTennis {

    private static String message;

    public static String getMessage() {
        return message;
    }

    public static void gagnerUnPoint(Joueur joueur){
        if (joueur.getScore() <= 15){
            joueur.setScore(joueur.getScore() + 15);
        } else if (joueur.getScore() == 30){
            joueur.setScore(joueur.getScore() + 10);
        } else if (joueur.getScore() == 40 && joueur.isGagneAvantage() == false){
            joueur.setGagneAvantage(true);
        }

    }

    public static void perdreLAvantage(Joueur joueur){
        if (joueur.getScore() == 40 && joueur.isGagneAvantage() == true && joueur.isaGagnePoint() == false){
            joueur.setGagneAvantage(false);
        }
    }

    public static void passerEnJeuDecisif(Joueur joueur1, Joueur joueur2, Partie partie){
        if (joueur1.getJeu() == 6 && joueur2.getJeu() == 6){
            partie.setJeuDecisif(true);
        }
    }

    public static void gagnerUnJeu(Joueur joueur1, Joueur joueur2){
        if (joueur1.getScore() == 40 && joueur2.getScore() <= 30){
            joueur1.setJeu(joueur1.getJeu() + 1);
            joueur1.setScore(0);
            joueur2.setScore(0);
        }
    }

    // Il faut 5 pts minimum pr gagner le jeu decisif
    public static void gagnerUnJeuDecisif(Partie partie, Joueur joueur1, Joueur joueur2){
        if (partie.isJeuDecisif() == true && joueur1.getScore() == 5 && joueur2.getScore() <= 3){
            joueur1.setJeu(joueur1.getJeu() + 1);
            joueur1.setScore(0);
            joueur2.setScore(0);
        } else if (partie.isJeuDecisif() == true && joueur1.getScore() == 7 && joueur2.getScore() == 5){
            joueur1.setJeu(joueur1.getJeu() + 1);
            joueur1.setScore(0);
            joueur2.setScore(0);
        }
    }

    public static void gagnerUnJeuAvecAvantage(Joueur joueur1, Joueur joueur2){
        if (joueur1.getScore() == 40 && joueur1.isGagneAvantage() == true){
            joueur1.setJeu(joueur1.getJeu() + 1);
            joueur1.setScore(0);
            joueur2.setScore(0);
        }
    }

    public static void compterPointParPoint(Partie partie, Joueur joueur){
        if (partie.isJeuDecisif() == true && joueur.isaGagnePoint() == true){
            if (joueur.getScore() < 7){
                joueur.setScore(joueur.getScore()+1);
            }

        }
    }

    public static void gagnerUnSet(Joueur joueur1, Joueur joueur2, Partie partie){
        if (joueur1.getJeu() == 6 && joueur2.getJeu() <= 4){
            joueur1.setSet(joueur1.getSet() +1);
        } else if (joueur2.getJeu() == 6 && joueur1.getJeu() <= 4){
            joueur2.setSet(joueur2.getSet() +1);
        } else if (joueur1.getJeu() == 7 && joueur2.getJeu() == 5){
                joueur1.setSet(joueur1.getSet()+1);
        } else if (joueur2.getJeu() == 7 && joueur1.getJeu() == 5){
                joueur2.setSet(joueur2.getSet()+1);
        } else if(partie.isJeuDecisif() == true && joueur2.getJeu() == 7 && joueur1.getJeu() == 6){
            joueur2.setSet(joueur2.getSet() + 1);
        } else if(partie.isJeuDecisif() == true && joueur1.getJeu() == 7 && joueur2.getJeu() == 6){
            joueur1.setSet(joueur1.getSet() + 1);
        }

    }

    public static void gagnerLaPartie(Joueur joueur, Partie partie){
        if (joueur.getSet() == 2){
            partie.setPartieTerminee(true);
            System.out.println(joueur.getNom() + " a gagné la partie ! Bravo");
        }
    }

    public static void bloquerLesScores(Partie partie, Joueur joueur1, Joueur joueur2){
        if (partie.isPartieTerminee() == true){
            System.out.println(joueur1.getNom() + " score =  " + joueur1.getSet() + " set(s)");
            System.out.println(joueur2.getNom() + " score =  " + joueur2.getSet() + " set(s)");
            if (partie.isPartieTerminee() == true && joueur1.getSet() > joueur2.getSet()){
                System.out.println( " BRAVO  " + joueur1.getNom());
            } else {
                System.out.println(" BRAVO  " + joueur2.getNom());
            }
        }
    }

    public static void afficherNomVainqueurEtPartieTerminee(Partie partie, Joueur joueur1, Joueur joueur2){
        if (partie.isPartieTerminee() == true && joueur1.getSet() > joueur2.getSet()){
            message = " a gagné la partie ! Le match est terminé ! Prêt pour un nouveau game ?";
            System.out.println(joueur1.getNom() + message);
        } else {
            message = " a gagné la partie ! Le match est terminé ! Prêt pour un nouveau game ?";
            System.out.println(joueur1.getNom() + message);
        }
    }

}
