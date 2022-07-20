import java.util.List;

public class Partie {

    private String partie;
    private List<Joueur> joueurs;
    private boolean jeuDecisif;
    private boolean isPartieTerminee;

    public boolean isPartieTerminee() {
        return isPartieTerminee;
    }

    public void setPartieTerminee(boolean partieTerminee) {
        isPartieTerminee = partieTerminee;
    }

    public boolean isJeuDecisif() {
        return jeuDecisif;
    }

    public void setJeuDecisif(boolean jeuDecisif) {
        this.jeuDecisif = jeuDecisif;
    }

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

    public Partie(String partie, List<Joueur> joueurs, boolean jeuDecisif, boolean isPartieTerminee) {
        this.partie = partie;
        this.joueurs = joueurs;
        this.jeuDecisif = jeuDecisif;
        this.isPartieTerminee = isPartieTerminee;
    }
}
