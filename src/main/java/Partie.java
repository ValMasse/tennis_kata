import java.util.List;

public class Partie {

    private String partie;
    private List<Joueur> joueurs;

    public String getPartie() {
        return partie;
    }

    public void setPartie(String partie) {
        this.partie = partie;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Partie(String partie, List<Joueur> joueurs) {
        this.partie = partie;
        this.joueurs = joueurs;
    }
}
