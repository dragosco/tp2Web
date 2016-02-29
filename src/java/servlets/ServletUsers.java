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
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;  
import utilisateurs.modeles.Utilisateur;  

/**
 *
 * @author thais
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods. 
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
            
            if (action.equals("authentification")) {
                if (gestionnaireUtilisateurs.verifierPassword(request.getParameter("login"), request.getParameter("password"))) {
                    forwardTo = "index.jsp?action=authentificationReussie";
                    message = "Authetification réussie";
                }
                else {
                    forwardTo = "index.jsp?action=authentificationNonReussie";
                    message = "Mot de passe incorrect";
                }
            } else { 
                Collection<Utilisateur> liste; // = gestionnaireUtilisateurs.getAllUsers();
                int total = 0;
                
                if (action.equals("chercherParLogin")) {
                    liste = gestionnaireUtilisateurs.getUsers(request.getParameter("login"));
                    request.setAttribute("listeDesUsers", liste);
                    total = liste.size();
                    forwardTo = "index.jsp?action=listerLesUtilisateurs&first=0&nb=10&total=" + total;
                    message = "Détails des utilisateurs";
                } else {                    
                    int first = 0;
                    int nb = 10;
                    String avance = "";

                    if(request.getParameter("first") != null && !request.getParameter("first").isEmpty()) {
                        first = Integer.parseInt(request.getParameter("first"));
                    }
                    if(request.getParameter("nb") != null && !request.getParameter("nb").isEmpty()) {
                        nb = Integer.parseInt(request.getParameter("nb"));
                    }
//                    if(request.getParameter("total") != null && !request.getParameter("total").isEmpty()) {
//                        total = Integer.parseInt(request.getParameter("total"));
//                    }
                    
                    if (action.equals("creerUtilisateursDeTest")) {  
                        gestionnaireUtilisateurs.creerUtilisateursDeTest(request.getParameter("nbUsers"));
                        //liste = gestionnaireUtilisateurs.getAllUsers();  
                        //request.setAttribute("listeDesUsers", liste);
                        //forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                        message = "Liste des utilisateurs";
                    } else if (action.equals("creerUnUtilisateur")) {
                        gestionnaireUtilisateurs.creeUtilisateur(request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("login"), request.getParameter("password"));
                        //total = gestionnaireUtilisateurs.getAllUsers().size();
                        //liste = gestionnaireUtilisateurs.getAllUsers();
                        //request.setAttribute("listeDesUsers", liste);
                        //forwardTo = "index.jsp?action=listerLesUtilisateurs";
                        message = "Liste des utilisateurs";
                    } else if (action.equals("updateUtilisateur")) {
                        gestionnaireUtilisateurs.modifierUtilisateur(request.getParameter("login"), request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("password"));
                        //liste = gestionnaireUtilisateurs.getAllUsers();
                        message = "Liste mise à jour";
                    } else if(action.equals("supprimerUtilisateur")) {
                        gestionnaireUtilisateurs.supprimerUtilisateur(request.getParameter("login"));
                        //total = gestionnaireUtilisateurs.getAllUsers().size();
                        message = "Utilisateur supprimé";
                    } else if (action.equals("listerLesUtilisateurs")) {  
                        message = "Liste des utilisateurs";
                    } else if (action.equals("preview")) {  
                        message = "Liste des utilisateurs";
                        //avance += "&avance=preview";
                        first -= nb;
                    } else if (action.equals("next")) {  
                        //avance += "&avance=next";
                        first += nb;
                        message = "Liste des utilisateurs";
                    }

                    if (request.getParameter("avance") != null && request.getParameter("avance").equals("next")) {
//                        avance += "&avance=next";
//                        first += nb;
                    } else if ((request.getParameter("avance") != null && request.getParameter("avance").equals("preview"))) {
                        //forwardTo = "index.jsp?action=listerLesUtilisateurs&first=" + first + "&nb=" + nb + "&avance=preview";
//                        avance += "&avance=preview";
//                        first -= nb;
                    } else {
                        //avance += "&avance=next";                        
                    }

                    liste = gestionnaireUtilisateurs.getAllUsers(first, nb);  
                    request.setAttribute("listeDesUsers", liste);                    
                    total = gestionnaireUtilisateurs.getAllUsers().size();
                    //total = liste.size();
                    
                    forwardTo = "index.jsp?action=listerLesUtilisateurs&first=" + first + "&nb=" + nb + "&total=" + total; // + avance;

                    //forwardTo = "index.jsp?action=listerLesUtilisateurs&first=" + first + "&nb=" + nb;
                    //message = "Liste des utilisateurs";

                    //}

                    //request.setAttribute("listeDesUsers", liste);  
                    //forwardTo = "index.jsp?action=listerLesUtilisateurs";
                }
                
//                } else {  
//                    forwardTo = "index.jsp?action=todo";  
//                    message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";  
//                }  
            }
        } else {
            forwardTo = "index.jsp";
            message = "test";
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
