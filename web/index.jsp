
<%--
    Document   : index
    Created on : 16 sept. 2009, 16:54:32
    Author     : michel buffa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionnaire d'utilisateurs</title>
        <link rel="stylesheet" href="resources/style.css" />
    </head>
    
    <body>
        <header>
            <div id="headerleft">
                <div id="logo"><img src="resources/users_icon.png"></div>
                <div id="titre"><h1>Gestionnaire d'utilisateurs</h1></div>
            </div>

            <div id="log-inBlock">
                <form class="login" action="ServletUsers" method="get">
                    <fieldset id="loginFieldset">
                        <p class="formLogin">Authentification</p>
                        <label for="loginLogin" class="formElemLogin">Login : </label><input type="text" name="login" id="loginLogin" class="loginInput"/><br>
                        <label for="loginPass" class="formElemLogin">Password : </label><input type="password" name="password" id="loginPass" class="loginInput" /><br>
                        <input type="hidden" name="action" value="authentification"/><br>
                        <button type="submit" value="login" name="submit" id="loginButton">LOGIN</button>
                    </fieldset>
                </form>
            </div>
        </header>
    <main>
        <nav>
            <h2>Menu de gestion des utilisateurs</h2>
            
    
            
            <!--<a class="menu" id="linkTest "href="ServletUsers?action=creerUtilisateursDeTest">Créer <input type="text" name="nbUsers" id="nbUsers" /> utilisateurs de test-->

            <form class="menu" id="testUsers" action="ServletUsers" method="get">
                <fieldset>
                    <legend class="form">Test :</legend>
                    <label for="nbUsers" class="formElem" id="labelTest">Créer <input type=number name="nbUsers" id="nbUsers" /> utilisateurs de test</label>
                    <input type="hidden" name="action" value="creerUtilisateursDeTest"/>
                    <button type="submit" value="Test" name="submit" id="testButton">Test</button>
                </fieldset>
            </form>
            
            <form class="menu" id="formCreation" action="ServletUsers" method="get">
                <fieldset>
                    <legend class="form">Créer un utilisateur :</legend>
                    <label for="creeNom" class="formElem">Nom : </label><input type="text" name="nom" id="creeNom"/><br>
                    <label for="creePrenom" class="formElem">Prénom : </label><input type="text" name="prenom" id="creePrenom"/><br>
                    <label for="creeLogin" class="formElem">Login : </label><input type="text" name="login" id="creeLogin"/><br>
                    <label for="creePass" class="formElem">Password : </label><input type="text" name="password" id="creePass"/><br>
                    <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->
                    <input type="hidden" name="action" value="creerUnUtilisateur"/><br>
                    <button type="submit" value="Créer l'utilisateur" name="submit">Créer l'utilisateur</button>
                </fieldset>
            </form>

            <form class="menu" id="formAffiche "action="ServletUsers" method="get">
                <fieldset>
                    <legend class="form">Afficher un utilisateur :</legend>
                    <label for="loginAffiche" class="formElem">Login : </label><input type="text" name="login" id="loginAffiche"/><br>
                    <input type="hidden" name="action" value="chercherParLogin"/><br>
                    <button type="submit" value="Chercher" name="submit">Chercher</button>
                </fieldset>
            </form>

            
            <form class="menu" id="formModif" action="ServletUsers" method="get">
                <fieldset>
                    <legend class="form">Modifier un utilisateur :</legend>
                    <label for="loginModif" class="formElem">Login : </label><input type="text" name="login" id="loginmMdif"/><br>
                    <label for="nomModif" class="formElem">Nom : </label><input type="text" name="nom" id="nommodif"/><br>
                    <label for="prenomModif" class="formElem">Prénom : </label><input type="text" name="prenom" id="prenomModif"/><br>
                    <label for="passModif" class="formElem">Password : </label><input type="text" name="password" id="passModif"/><br>
                    <input type="hidden" name="action" value="updateUtilisateur"/><br>
                    <button type="submit" value="Mettre à jour" name="submit">Mettre à jour</button>
                </fieldset>
            </form>
            
        </nav>
        <!-- Fin du menu -->

        <section>
            <c:if test="${param['action'] == 'authentificationReussie'}" >
                <h4>Congrats ! Authentification réussie</h4>
            </c:if>
            <c:if test="${param['action'] == 'authentificationNonReussie'}" >
                <h5>Bummer ! Mot de passe et/ou login incorrect(s)</h5>
            </c:if>
        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->
            <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >
            <h2 class="liste">Liste des utilisateurs</h2>

            <a class="liste" href="ServletUsers?action=listerLesUtilisateurs&first=${param.first}&nb=${param.nb}">Actualiser</a>

            <div id="zoneAvance">
                <c:if test="${param.first >= 10}">
                    <div  class="avanceButton" id="previewButton"><a href="ServletUsers?action=listerLesUtilisateurs&first=${param.first}&nb=${param.nb}&avance=preview">Preview</a></div>
                </c:if>
                
                
                <div  class="avanceButton" id="nextButton"><a href="ServletUsers?action=listerLesUtilisateurs&first=${param.first}&nb=${param.nb}&avance=next">Next</a></div>
            </div>
            <article>
                
                
                <table>
                    <!-- La ligne de titre du tableau des comptes -->
                    <tr id="attributsTableau">
                        <td class="attributsTableau">Login</td>
                        <td class="attributsTableau">Nom</td>
                        <td class="attributsTableau">Prénom</td>
                        <td class="attributsTableau">Password</td>
                    </tr>

                    <!-- Ici on affiche les lignes, une par utilisateur -->
                    <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->
                    <c:set var="total" value="0"/>

                    <c:forEach var="u" items="${requestScope['listeDesUsers']}">
                        <tr id="contenuTableau">
                            <td class="elemTableau">${u.login}</td>
                            <td class="elemTableau">${u.lastname}</td>
                            <td class="elemTableau">${u.firstname}</td>
                            <td class="elemTableau">${u.password}</td>
                            <!-- On compte le nombre de users -->
                            <c:set var="total" value="${total+1}"/>
                        </tr>
                    </c:forEach>
                        
                    <!-- Affichage du solde total dans la dernière ligne du tableau -->
                    <tr id="footerTableau">
                        <td>TOTAL</td>
                        <td colspan="3" id="totalCell">${param.first} - ${param.first + param.nb}</td>
                    </tr>
                </table>
                        
                        
            </article>
            
            </c:if>
            
        </section>
        
    </main>
        <footer>
            <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->
            <c:if test="${!empty param['message']}">
                <h3>Reçu message : ${param.message}</h3>
            </c:if>
        </footer>    
    </body>
</html>