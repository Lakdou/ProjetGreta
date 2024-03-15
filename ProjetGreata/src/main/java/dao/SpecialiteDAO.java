package dao;

import models.Specialite;
import java.util.List;

public interface SpecialiteDAO {
    void ajouter(Specialite specialite);
    void supprimer(int idCompte, int idMatiere);
    void modifier(int idCompte, int idMatiere, Specialite specialite);

    Specialite trouver(int idCompte, int idMatiere);
    List<Specialite> listerToutesLesSpecialites();
    List<Specialite> listerSpecialitesParCompte(int idCompte);
}
