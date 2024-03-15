package controller;

import dao.CompteDAO;
import dao.CompteDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Compte;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private CompteDAO compteDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        compteDAO = new CompteDAOImpl(); // initialisation de DAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            boolean utilisateurExiste = compteDAO.existe(username);

            if (utilisateurExiste) {

                Compte utilisateur = compteDAO.trouverParNomUtilisateur(username);
                if (utilisateur != null && utilisateur.getMotDePasse().equals(password)&&utilisateur.getLogin().equals(username)) {

                    request.getSession().setAttribute("username", utilisateur.getLogin());
                    // redirige vers home.jsp si l'utilisateur existe et le mot de passe est correct
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
                    dispatcher.forward(request, response);
                }else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
                    dispatcher.forward(request, response);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur lors de la vérification dans la base de données, rediriger vers home.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
