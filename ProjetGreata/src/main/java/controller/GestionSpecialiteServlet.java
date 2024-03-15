package controller;

import dao.SpecialiteDAO;
import dao.SpecialiteDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Specialite;

import java.io.IOException;

@WebServlet("/gestionSpecialite")
public class GestionSpecialiteServlet extends HttpServlet {

    private SpecialiteDAO specialiteDAO;

    public void init() {
        specialiteDAO = new SpecialiteDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "nouveau":
                    showNewForm(request, response);
                    break;
                case "supprimer":
                    supprimerSpecialite(request, response);
                    break;
                case "modifier":
                    showEditForm(request, response);
                    break;
                default:
                    listerSpecialites(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "ajouter":
                    ajouterSpecialite(request, response);
                    break;
                case "modifier":
                    modifierSpecialite(request, response);
                    break;
                default:
                    listerSpecialites(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajouterSpecialite.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les identifiants à partir de la requête
        int idCompte = Integer.parseInt(request.getParameter("idCompte"));
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        // Utiliser la méthode trouver pour récupérer la spécialité
        Specialite specialite = specialiteDAO.trouver(idCompte, idMatiere);
        if (specialite != null) {
            request.setAttribute("specialite", specialite);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifierSpecialite.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Spécialité non trouvée.");
        }
    }


    private void modifierSpecialite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCompteAncien = Integer.parseInt(request.getParameter("idCompteAncien"));
        int idMatiereAncien = Integer.parseInt(request.getParameter("idMatiereAncien"));
        int idCompteNouveau = Integer.parseInt(request.getParameter("idCompte"));
        int idMatiereNouveau = Integer.parseInt(request.getParameter("idMatiere"));

        Specialite specialite = new Specialite(idCompteNouveau, idMatiereNouveau);
        specialiteDAO.modifier(idCompteAncien, idMatiereAncien, specialite);
        response.sendRedirect("gestionSpecialite?action=liste");
    }

    private void ajouterSpecialite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCompte = Integer.parseInt(request.getParameter("idCompte"));
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        Specialite newSpecialite = new Specialite(idCompte, idMatiere);
        specialiteDAO.ajouter(newSpecialite);
        response.sendRedirect("gestionSpecialite?action=liste");
    }

    private void supprimerSpecialite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCompte = Integer.parseInt(request.getParameter("idCompte"));
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        specialiteDAO.supprimer(idCompte, idMatiere);
        response.sendRedirect("gestionSpecialite?action=liste");
    }

    private void listerSpecialites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listeSpecialites", specialiteDAO.listerToutesLesSpecialites());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp"); // Assurez-vous d'avoir cette page JSP
        dispatcher.forward(request, response);

    }
}