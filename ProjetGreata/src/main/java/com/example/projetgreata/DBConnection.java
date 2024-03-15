package com.example.projetgreata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/COURS";
    private static final String DATABASE_USER = "Lakdar";
    private static final String DATABASE_PASSWORD = "95490dbgt";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connexion à la base de données réussie.");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : Driver JDBC non trouvé.");
            e.printStackTrace();

            throw new RuntimeException("Erreur lors du chargement du driver JDBC.", e);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
            // De même, vous pourriez choisir de relancer l'exception ou de traiter l'erreur différemment.
            throw new RuntimeException("Erreur de connexion à la base de données.", e);
        }
    }
}
