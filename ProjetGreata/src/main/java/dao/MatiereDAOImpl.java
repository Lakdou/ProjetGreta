package dao;

import models.Matiere;
import com.example.projetgreata.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDAOImpl implements MatiereDAO {

    @Override
    public void ajouter(Matiere matiere) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MATIERE (nom) VALUES (?)");
            pstmt.setString(1, matiere.getNom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(Matiere matiere) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE MATIERE SET nom = ? WHERE id = ?");
            pstmt.setString(1, matiere.getNom());
            pstmt.setInt(2, matiere.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM MATIERE WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Matiere> listerToutesLesMatieres() {
        List<Matiere> matieres = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MATIERE");
            while (rs.next()) {
                Matiere matiere = new Matiere(rs.getInt("id"), rs.getString("nom"));
                matieres.add(matiere);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matieres;
    }

    @Override
    public Matiere trouverParId(int id) {
        Matiere matiere = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MATIERE WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                matiere = new Matiere(rs.getInt("id"), rs.getString("nom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matiere;
    }
}
