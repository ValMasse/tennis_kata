import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompteurDeScoreTennisTest {

    private CompteurDeScoreTennis compteur;

    List<Joueur> joueurs = new ArrayList<Joueur>();
    List<Partie> parties = new ArrayList<Partie>();

    @BeforeEach
    public void setup(){
        // Init joueurs
        Joueur joueur1 = new Joueur("Jean Hugues", 0, 0, 0, false, false);
        Joueur joueur2 = new Joueur("Valentin", 0, 0, 0, false, false);
        Joueur joueur3 = new Joueur("Jean Jacques", 0, 0, 0, false, false);
        Joueur joueur4 = new Joueur("Valerio", 0, 0, 0, false, false);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);
        joueurs.add(joueur4);

        // Init parties
        List<Joueur> joueursPartie1 = new ArrayList<Joueur>();
        joueursPartie1.add(joueurs.get(0));
        joueursPartie1.add(joueurs.get(1));
        Partie partie1 = new Partie("Match JH vs V", joueursPartie1, false, false);
        parties.add(partie1);
    }

    @Test
    @DisplayName("devrait créer une nouvelle partie")
    public void creerPartie(){
        Partie partie1 = new Partie("partie1", joueurs, false, false);
        assertNotNull(partie1);
    }

    @Test
    @DisplayName("devrait pouvoir choisir les 2 joueurs")
    public void choisirJoueurs(){
        List<Joueur> listeDeuxJoueurs = new ArrayList<Joueur>(2);
        listeDeuxJoueurs.add(joueurs.get(0));
        listeDeuxJoueurs.add(joueurs.get(2));
        assertTrue(listeDeuxJoueurs.size() == 2);
    }

    @Test
    @DisplayName("les 2 joueurs devraient avoir 0 pts")
    public void scoreDepart(){
        Joueur j1 = joueurs.get(0);
        assertEquals(0, j1.getScore());
        Joueur j2 = joueurs.get(1);
        assertEquals(0, j2.getScore());
    }

    @Test
    @DisplayName("les 2 joueurs devraient avoir 0 jeux gagnés")
    public void jeuxGagnes(){
        Joueur j1 = joueurs.get(0);
        assertEquals(0, j1.getJeu());
        Joueur j2 = joueurs.get(1);
        assertEquals(0, j2.getJeu());
    }

    @Test
    @DisplayName("les 2 joueurs devraient avoir 0 sets gagnés")
    public void setsGagnes(){
        Joueur j1 = joueurs.get(0);
        assertEquals(0, j1.getSet());
        Joueur j2 = joueurs.get(1);
        assertEquals(0, j2.getSet());
    }

    @Test
    @DisplayName("devrait pouvoir notifier qu'un joueur a gagné 1 pt")
    public void joueurGagnant(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        joueur1.setaGagnePoint(true);
        assertEquals(true, joueur1.isaGagnePoint());
    }

    @Test
    @DisplayName("si le gagnant a 0 pts il passe à 15")
    public void testPoint0A15(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        joueur1.setaGagnePoint(true);
        this.compteur.gagnerUnPoint(joueur1);
        assertEquals(15, joueur1.getScore());
    }

    @Test
    @DisplayName("si le gagnant a 15 pts il passe à 30")
    public void testPoint15AA30(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        joueur1.setaGagnePoint(true);
        joueur1.setScore(15);
        this.compteur.gagnerUnPoint(joueur1);
        assertEquals(30, joueur1.getScore());
    }

    @Test
    @DisplayName("si le gagnant a 30 pts il passe à 40")
    public void testPoint30A40(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        joueur1.setaGagnePoint(true);
        joueur1.setScore(30);
        this.compteur.gagnerUnPoint(joueur1);
        assertEquals(40, joueur1.getScore());
    }

    @Test
    @DisplayName("si les joueurs sont à égalité à 40pts et qu'aucun n'a un avantage alors le gagnant prend l'avantage")
    public void prendAvantage(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setScore(40);
        joueur2.setScore(40);
        joueur1.setaGagnePoint(true);
        this.compteur.gagnerUnPoint(joueur1);
        assertEquals(true, joueur1.isGagneAvantage());
    }

    @Test
    @DisplayName("si les joueurs sont à égalité à 40pts et qu'un joueur a un avantage et qu'il perd alors il perd son avantage")
    public void perdAvantage(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setScore(40);
        joueur2.setScore(40);
        joueur1.setGagneAvantage(true);
        joueur2.setaGagnePoint(true);
        this.compteur.perdreLAvantage(joueur1);
        assertEquals(false, joueur1.isGagneAvantage());
    }

    @Test
    @DisplayName("si les joueurs sont à égalité à 40pts et qu'un joueur a un avantage et qu'il gagne alors il gagne le jeu")
    public void gagneJeu(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setScore(40);
        joueur2.setScore(40);
        joueur1.setGagneAvantage(true);
        joueur1.setaGagnePoint(true);
        this.compteur.gagnerUnJeuAvecAvantage(joueur1, joueur2);
        assertEquals(1, joueur1.getJeu());
    }

    @Test
    @DisplayName("quand un jeu est gagné le score revient à 0-0")
    public void reinitScore(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        this.compteur.gagnerUnJeu(joueur1, joueur2);
        assertEquals(0, joueur1.getScore());
        assertEquals(0, joueur2.getScore());
    }

    @Test
    @DisplayName("quand un joueur a gagné 6 jeux et l'autre moins de 4 alors le premier joueur gagne le set")
    public void gagneUnSet(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setJeu(6);
        joueur2.setJeu(4);
        this.compteur.gagnerUnSet(joueur1, joueur2, partie1);
        assertEquals(1, joueur1.getSet());
    }

    @Test
    @DisplayName("si les 2 joueurs ont 5 jeux il faut avoir 2 jeux d'avance et 7 jeux pour gagner le set")
    public void avoirDeuxJeuxDavancePourGagngerLeSet(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setJeu(5);
        joueur2.setJeu(5);
        joueur1.setScore(40);
        joueur2.setScore(30);
        this.compteur.gagnerUnJeu(joueur1, joueur2);
        joueur1.setScore(40);
        joueur2.setScore(30);
        this.compteur.gagnerUnJeu(joueur1, joueur2);
        this.compteur.gagnerUnSet(joueur1, joueur2, partie1);
        assertEquals(1, joueur1.getSet());
    }

    @Test
    @DisplayName("quand les 2 joueurs ont 6 jeux gagnés alors on passe en jeu décisif")
    public void passerEnJeuDecisif(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setJeu(6);
        joueur2.setJeu(6);
        this.compteur.passerEnJeuDecisif(joueur1, joueur2, partie1);
        assertEquals(true, partie1.isJeuDecisif());
    }

    @Test
    @DisplayName("quand il y a jeu décisif les points sont comptés point par point")
    public void pointsJeuDecisif(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        partie1.setJeuDecisif(true);
        joueur1.setaGagnePoint(true);
        this.compteur.compterPointParPoint(partie1, joueur1);
        assertEquals(1, joueur1.getScore());
    }

        @Test
        @DisplayName("Si un joueur gagne un point pendant un jeu decisif il passe de 0 à 1, puis 2, ... jusqu'à 7")
        public void gagneUnPointPendantJD() {
            Partie partie1 = parties.get(0);
            Joueur joueur1 = partie1.getJoueurs().get(0);
            Joueur joueur2 = partie1.getJoueurs().get(1);
            // Le joueur1 est actuellement à 4 pts
            joueur1.setScore(4);
            partie1.setJeuDecisif(true);
            joueur1.setaGagnePoint(true);
            this.compteur.compterPointParPoint(partie1, joueur1);
            assertEquals(5, joueur1.getScore());
        }

        @Test
        @DisplayName("Il faut avoir deux points d'avance pour gagner le jeu et donc le set")
        public void gagnerSetAvecDeuxPointsDAvance(){
            Partie partie1 = parties.get(0);
            Joueur joueur1 = partie1.getJoueurs().get(0);
            Joueur joueur2 = partie1.getJoueurs().get(1);
            joueur1.setJeu(6);
            joueur2.setJeu(6);
            partie1.setJeuDecisif(true);
            joueur1.setScore(3);
            joueur2.setScore(3);
            joueur1.setaGagnePoint(true);
            this.compteur.compterPointParPoint(partie1, joueur1);
            joueur1.setaGagnePoint(true);
            this.compteur.compterPointParPoint(partie1, joueur1);
            this.compteur.gagnerUnJeuDecisif(partie1, joueur1,joueur2);
            this.compteur.gagnerUnSet(joueur1, joueur2, partie1);
            assertEquals(7, joueur1.getJeu());
            assertEquals(1, joueur1.getSet());
        }

        @Test
        @DisplayName("Le premier joueur a 2 sets gagnés gagne la partie")
        public void gagnerLaPartie(){
            Partie partie1 = parties.get(0);
            Joueur joueur1 = partie1.getJoueurs().get(0);
            Joueur joueur2 = partie1.getJoueurs().get(1);
            joueur1.setSet(1);
            joueur1.setJeu(5);
            joueur2.setJeu(2);
            joueur1.setScore(40);
            joueur2.setScore(15);
            joueur1.setaGagnePoint(true);
            this.compteur.gagnerUnJeu(joueur1, joueur2);
            this.compteur.gagnerUnSet(joueur1, joueur2, partie1);
            this.compteur.gagnerLaPartie(joueur1, partie1);
            assertTrue(partie1.isPartieTerminee() == true);
        }

        @Test
        @DisplayName("Quand un joueur a gagné, il n'est plus possible de changer les scores")
        public void partieTermineeScoreBloque(){
            Partie partie1 = parties.get(0);
            partie1.setPartieTerminee(true);
            Joueur joueur1 = partie1.getJoueurs().get(0);
            Joueur joueur2 = partie1.getJoueurs().get(1);
            joueur1.setSet(1);
            joueur2.setSet(1);
            joueur1.setJeu(5);
            joueur2.setJeu(2);
            joueur1.setScore(40);
            joueur2.setScore(30);
            joueur1.setaGagnePoint(true);
            this.compteur.gagnerUnJeu(joueur1, joueur2);
            this.compteur.gagnerUnSet(joueur1, joueur2, partie1);
            this.compteur.bloquerLesScores(partie1, joueur1, joueur2);
            assertEquals(6, joueur1.getJeu());
            assertEquals(2, joueur1.getSet());
        }

        @Test
        @DisplayName("L'utilisateur doit être avertie que la partie est finie")
        public void avertissementPartieTerminee(){
            Partie partie1 = parties.get(0);
            Joueur joueur1 = partie1.getJoueurs().get(0);
            Joueur joueur2 = partie1.getJoueurs().get(1);
            partie1.setPartieTerminee(true);
            this.compteur.afficherNomVainqueurEtPartieTerminee(partie1, joueur1, joueur2);
            assertNotNull(this.compteur.getMessage());
        }









}