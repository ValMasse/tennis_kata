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
        Joueur joueur1 = new Joueur("Jean Hugues", 0, 0, 0, false);
        Joueur joueur2 = new Joueur("Valentin", 0, 0, 0, false);
        Joueur joueur3 = new Joueur("Jean Jacques", 0, 0, 0, false);
        Joueur joueur4 = new Joueur("Valerio", 0, 0, 0, false);
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


}