public class Joueur {

    private String nom;
    private int score;
    private int jeu;
    private int set;
    private boolean aGagnePoint;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setJeu(int jeu) {
        this.jeu = jeu;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public void setaGagnePoint(boolean aGagnePoint) {
        this.aGagnePoint = aGagnePoint;
    }

    public boolean isaGagnePoint() {
        return aGagnePoint;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public int getJeu() {
        return jeu;
    }

    public int getSet() {
        return set;
    }

    public Joueur(String nom, int score, int jeu, int set, boolean aGagnePoint) {
        this.nom = nom;
        this.score = score;
        this.jeu = jeu;
        this.set = set;
        this.aGagnePoint = aGagnePoint;
    }
}

