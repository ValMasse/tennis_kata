import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompteurDeScoreTennisTest {

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
        Partie partie1 = new Partie("Match JH vs V", joueursPartie1);
        parties.add(partie1);
    }

    @Test
    @DisplayName("devrait créer une nouvelle partie")
    public void creerPartie(){
        Partie partie1 = new Partie("partie1", joueurs);
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
        if (joueur1.isaGagnePoint() == true && joueur1.getScore() == 0){
            joueur1.setScore(15);
        }
        assertEquals(15, joueur1.getScore());
    }

    @Test
    @DisplayName("si le gagnant a 15 pts il passe à 30")
    public void testPoint15AA30(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        joueur1.setaGagnePoint(true);
        joueur1.setScore(15);
        if (joueur1.isaGagnePoint() == true && joueur1.getScore() == 15){
            joueur1.setScore(30);
        }
        assertEquals(30, joueur1.getScore());
    }

    @Test
    @DisplayName("si le gagnant a 30 pts il passe à 40")
    public void testPoint30A40(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        joueur1.setaGagnePoint(true);
        joueur1.setScore(30);
        if (joueur1.isaGagnePoint() == true && joueur1.getScore() == 30){
            joueur1.setScore(40);
        }
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
        if (joueur1.isaGagnePoint() == true && joueur1.getScore() == 40 && joueur2.getScore() == 40){
            joueur1.setGagneAvantage(true);
        }
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
        if (joueur2.isaGagnePoint() == true && joueur1.getScore() == 40 && joueur2.getScore() == 40 && joueur1.isGagneAvantage() == true){
            joueur1.setGagneAvantage(false);
        }
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
        if (joueur1.isaGagnePoint() == true && joueur1.getScore() == 40 && joueur2.getScore() == 40 && joueur1.isGagneAvantage() == true){
            joueur1.setGagneAvantage(false);
            joueur1.setJeu(joueur1.getJeu()+1);
        }
        assertEquals(1, joueur1.getJeu());
    }

    @Test
    @DisplayName("quand un jeu est gagné le score revient à 0-0")
    public void reinitScore(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        int nbreJeuxJ1 = joueur1.getJeu();
        joueur1.setJeu(joueur1.getJeu()+1);
        if (joueur1.getJeu() != nbreJeuxJ1){
            joueur1.setScore(0);
            joueur2.setScore(0);
        }
        assertEquals(0, joueur1.getScore());
        assertEquals(0, joueur2.getScore());
    }

    @Test
    @DisplayName("quand un joueur a gagné 6 jeu et l'autre moins de 4 alors le premier joueur gagne le set")
    public void gagneUnSet(){
        Partie partie1 = parties.get(0);
        Joueur joueur1 = partie1.getJoueurs().get(0);
        Joueur joueur2 = partie1.getJoueurs().get(1);
        joueur1.setJeu(6);
        joueur2.setJeu(4);
        if (joueur1.getJeu() == 6 && joueur2.getJeu() <= 4){
            joueur1.setSet(joueur1.getSet()+1);
            // On réinitialise les jeux
            joueur1.setJeu(0);
            joueur2.setJeu(0);
        }
        assertEquals(1, joueur1.getSet());
    }

    //@Test
    //@DisplayName("si les 2 joueurs ont 5 jeux")
}