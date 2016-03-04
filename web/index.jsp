
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
        <jsp:include page="header.jsp"/>

        <div id="wrapper">
            <c:if test="${param.action == 'authentification'}" >
                <jsp:include page="authentificationPage.jsp"/>
            </c:if>

            <c:if test="${param.action == 'authentification' && param.etat == 'reussie'}" >
                <c:redirect url="ServletUsers?action=listerLesUtilisateurs"></c:redirect>
            </c:if>

            <c:if test="${param.action == 'creerUtilisateursDeTest'}">
                <c:redirect url="ServletUsers?action=listerLesUtilisateurs&first=0&nb=10&total=10"></c:redirect>
            </c:if>

            <c:if test="${param.action == 'listerLesUtilisateurs'}" >

                <jsp:include page="functionalities.jsp"/>

                <jsp:include page="usersTableSection.jsp"/>

            </c:if>

            <c:if test="${param.action == authentification && param.etat == 'nonreussie'}" >
                    <h5>Bummer ! Mot de passe et/ou login incorrect(s). Réessayez !</h5>
                    <jsp:include page="authentificationPage.jsp"/>
            </c:if>
    
        </div>
        <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->
        <c:if test="${!empty param['message']}">
            <jsp:include page="footer.jsp"/>
        </c:if>
        

    </body>
</html>