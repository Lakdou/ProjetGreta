package models;

public class Specialite {
    private int idCompte;
    private int idMatiere;

    // Constructeur par défaut
    public Specialite() {
    }

    // Constructeur avec tous les paramètres
    public Specialite(int idCompte, int idMatiere) {
        this.idCompte = idCompte;
        this.idMatiere = idMatiere;
    }

    // Getters et Setters
    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    // Méthode toString pour représenter l'objet Specialite sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "Specialite{" +
                "idCompte=" + idCompte +
                ", idMatiere=" + idMatiere +
                '}';
    }
}
