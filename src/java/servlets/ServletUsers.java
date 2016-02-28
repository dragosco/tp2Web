/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilisateurs.gestionnaire.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Dragos
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");  
        String forwardTo = "";  
        String message = "";  
  
        if (action != null) {
            
            if (action.equals("listerLesUtilisateurs")) {  
                int first = 0;
                int nb = 10;
                
                if(request.getParameter("first") != null) {
                    if (!request.getParameter("first").isEmpty())
                        first = Integer.parseInt(request.getParameter("first"));
                }
                if(request.getParameter("nb") != null) {
                    if (!request.getParameter("nb").isEmpty())
                        nb = Integer.parseInt(request.getParameter("nb"));
                }
                
                
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(first, nb);  
                request.setAttribute("listeDesUsers", liste);
                
                if (request.getParameter("avance") != null && request.getParameter("avance").equals("next")) {
                    forwardTo = "index.jsp?action=listerLesUtilisateurs&first=" + first + "&nb=" + nb + "&avance=next";
                    first += nb;
                    
                } else if ((request.getParameter("avance") != null && request.getParameter("avance").equals("preview"))) {
                    forwardTo = "index.jsp?action=listerLesUtilisateurs&first=" + first + "&nb=" + nb + "&avance=preview";
                    first -= nb;
                    
                }
                
                forwardTo = "index.jsp?action=listerLesUtilisateurs&first=" + first + "&nb=" + nb;
                message = "Liste des utilisateurs";
                
            } else if (action.equals("creerUtilisateursDeTest")) {  
                gestionnaireUtilisateurs.creerUtilisateursDeTest(request.getParameter("nbUsers"));  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";
                
            } else if (action.equals("creerUnUtilisateur")) {
                gestionnaireUtilisateurs.creeUtilisateur(request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("login"), request.getParameter("password"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
                
            } else if (action.equals("chercherParLogin")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getUsers(request.getParameter("login"));
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Détails des utilisateurs";
                
            } else if (action.equals("updateUtilisateur")) {
                gestionnaireUtilisateurs.modifierUtilisateur(request.getParameter("login"), request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("password"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste mise à jour";
            } else if (action.equals("authentification")) {
                if (gestionnaireUtilisateurs.verifierPassword(request.getParameter("login"), request.getParameter("password"))) {
                    forwardTo = "index.jsp?action=authentificationReussie";
                    message = "Authetification réussie";
                }
                else {
                    forwardTo = "index.jsp?action=authentificationNonReussie";
                    message = "Mot de passe incorrect";
                }
                
            }
            
            else {  
                forwardTo = "index.jsp?action=todo";  
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";  
            }  
        }  
  
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);  
        dp.forward(request, response);  
        // Après un forward, plus rien ne peut être exécuté après !
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
