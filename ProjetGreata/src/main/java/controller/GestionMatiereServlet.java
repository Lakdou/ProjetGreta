package controller;

import dao.MatiereDAO;
import dao.MatiereDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Matiere;

import java.io.IOException;

@WebServlet("/gestionMatiere")
public class GestionMatiereServlet extends HttpServlet {

    private MatiereDAO matiereDAO;

    public void init() {
        matiereDAO = new MatiereDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "nouveau":
                    showNewForm(request, response);
                    break;
                case "modifierForm":
                    showEditForm(request, response);
                    break;
                case "supprimer":
                    supprimerMatiere(request, response);
                    break;
                default:
                    listerMatieres(request, response);
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
                    ajouterMatiere(request, response);
                    break;
                case "modifier":
                    modifierMatiere(request, response);
                    break;
                default:
                    listerMatieres(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajouterMatiere.jsp");
        dispatcher.forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Matiere existingMatiere = matiereDAO.trouverParId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifierMatiere.jsp");
        request.setAttribute("matiere", existingMatiere);
        dispatcher.forward(request, response);
    }

    private void ajouterMatiere(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        Matiere newMatiere = new Matiere();
        newMatiere.setNom(nom);
        matiereDAO.ajouter(newMatiere);
        response.sendRedirect("gestionMatiere?action=liste");
    }

    private void modifierMatiere(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");

        Matiere matiere = new Matiere(id, nom);
        matiereDAO.modifier(matiere);
        response.sendRedirect("gestionMatiere?action=liste");
    }

    private void supprimerMatiere(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        matiereDAO.supprimer(id);
        response.sendRedirect("gestionMatiere?action=liste");

    }

    private void listerMatieres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listeMatieres", matiereDAO.listerToutesLesMatieres());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

}
