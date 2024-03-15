package dao;

import models.Specialite;
import com.example.projetgreata.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteDAOImpl implements SpecialiteDAO {

    @Override
    public void ajouter(Specialite specialite) {
        String query = "INSERT INTO SPECIALITE (idcompte, idmatiere) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, specialite.getIdCompte());
            pstmt.setInt(2, specialite.getIdMatiere());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout d'une spécialité", e);
        }
    }

    @Override
    public void supprimer(int idCompte, int idMatiere) {
        String query = "DELETE FROM SPECIALITE WHERE idcompte = ? AND idmatiere = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCompte);
            pstmt.setInt(2, idMatiere);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression d'une spécialité", e);
        }
    }


    @Override
    public List<Specialite> listerToutesLesSpecialites() {
        List<Specialite> specialites = new ArrayList<>();
        String query = "SELECT * FROM SPECIALITE";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Specialite specialite = new Specialite(rs.getInt("idcompte"), rs.getInt("idmatiere"));
                specialites.add(specialite);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des spécialités", e);
        }
        return specialites;
    }
    @Override
    public Specialite trouver(int idCompte, int idMatiere) {
        String SQL = "SELECT * FROM SPECIALITE WHERE idcompte = ? AND idmatiere = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, idCompte);
            pstmt.setInt(2, idMatiere);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Specialite(rs.getInt("idcompte"), rs.getInt("idmatiere"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void modifier(int idCompteAncien, int idMatiereAncien, Specialite specialite) {
        String query = "UPDATE SPECIALITE SET idcompte = ?, idmatiere = ? WHERE idcompte = ? AND idmatiere = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, specialite.getIdCompte());
            pstmt.setInt(2, specialite.getIdMatiere());
            pstmt.setInt(3, idCompteAncien);
            pstmt.setInt(4, idMatiereAncien);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification d'une spécialité", e);
        }
    }


    @Override
    public List<Specialite> listerSpecialitesParCompte(int idCompte) {
        List<Specialite> specialites = new ArrayList<>();
        String query = "SELECT * FROM SPECIALITE WHERE idcompte = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCompte);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Specialite specialite = new Specialite(rs.getInt("idcompte"), rs.getInt("idmatiere"));
                specialites.add(specialite);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des spécialités d'un compte", e);
        }
        return specialites;
    }
}
