package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session actuelle
        HttpSession session = request.getSession(false); // false signifie "ne pas créer une nouvelle session si elle n'existe pas déjà"

        if (session != null) {
            session.invalidate(); // Invalider la session pour déconnecter l'utilisateur
        }

        response.sendRedirect("home.html"); // Rediriger vers la page de connexion ou la page d'accueil après la déconnexion
    }
}