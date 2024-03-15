package dao;

import models.Compte;
import com.example.projetgreata.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAOImpl implements CompteDAO {


    public Compte trouverParId(int id) {
        Compte compte = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM utilisateurs WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                compte = new Compte();
                compte.setId(rs.getInt("id"));
                compte.setLogin(rs.getString("username")); // Assurez-vous que les noms des colonnes correspondent.
                compte.setMotDePasse(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur par ID.", e);
        }
        return compte;
    }

    @Override
    public Compte trouverParNomUtilisateur(String login) {
        Compte compte = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM utilisateurs WHERE username = ?");
            pstmt.setString(1, String.valueOf(login));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                compte = new Compte();
                compte.setId(rs.getInt("id"));
                compte.setLogin(rs.getString("username"));
                compte.setMotDePasse(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur par nom d'utilisateur.", e);
        }
        return compte;
    }

    @Override
    public boolean existe(String login) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM utilisateurs WHERE username = ?");
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Compte compte) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO utilisateurs (username, password) VALUES (?, ?)");
            pstmt.setString(1, compte.getLogin());
            pstmt.setString(2, compte.getMotDePasse());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM utilisateurs WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(Compte compte) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE utilisateurs SET username = ?, password = ? WHERE id = ?");
            pstmt.setString(1, compte.getLogin());
            pstmt.setString(2, compte.getMotDePasse());
            pstmt.setInt(3, compte.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
        public List<Compte> tousLesComptes() {
        List<Compte> comptes = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateurs");
            while (rs.next()) {
                Compte compte = new Compte(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                comptes.add(compte);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comptes;
    }
}
