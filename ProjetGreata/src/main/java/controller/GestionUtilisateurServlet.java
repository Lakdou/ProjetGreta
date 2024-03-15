package controller;

import dao.CompteDAO;
import dao.CompteDAOImpl;
import models.Compte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Matiere;

import java.io.IOException;

@WebServlet("/GestionUtilisateurServlet")
public class GestionUtilisateurServlet extends HttpServlet {

    private CompteDAO compteDAO;

    @Override
    public void init() {
        this.compteDAO = new CompteDAOImpl();
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
                    supprimerCompte(request, response);
                    break;
                default:
                    listerComptes(request, response);
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
                    ajouterCompte(request, response);
                    break;
                case "modifier":
                    modifierCompte(request, response);
                    break;
                default:
                    listerComptes(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajouterUtilisateur.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Compte existingCompte = compteDAO.trouverParId(id);
        if(existingCompte != null) {
            request.setAttribute("compte", existingCompte);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp");
            dispatcher.forward(request, response);
        } else {
            // Gérer le cas où le compte est null, par exemple en redirigeant vers une page d'erreur ou la liste des comptes
            response.sendRedirect("gestionUtilisateurs?action=liste");
        }
    }


    private void ajouterCompte(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        Compte nouveauCompte = new Compte(0, login, password);
        compteDAO.add(nouveauCompte);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

    private void modifierCompte(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Compte compte = new Compte(id, username, password);
        compteDAO.modifier(compte);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

    private void supprimerCompte(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        compteDAO.supprimer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

    private void listerComptes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listeComptes", compteDAO.tousLesComptes());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp"); // Assurez-vous d'avoir cette page JSP
        dispatcher.forward(request, response);
    }
}
