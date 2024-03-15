package dao;

import models.Matiere;
import java.util.List;

public interface MatiereDAO {
    void ajouter(Matiere matiere);
    void modifier(Matiere matiere);
    void supprimer(int id);
    List<Matiere> listerToutesLesMatieres();
    Matiere trouverParId(int id);
}
