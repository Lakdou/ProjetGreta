package dao;

import models.Compte;

import java.util.List;

public interface CompteDAO {


    Compte trouverParId(int id);

    static Compte trouverParNomUtilisateur(int idCompte) {
        return null;
    }


    Compte trouverParNomUtilisateur(String login);
    boolean existe(String login);
    void add(Compte compte);
    void supprimer(int id);
    void modifier(Compte compte);
    List<Compte> tousLesComptes();

}
