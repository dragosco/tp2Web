<%-- 
    Document   : usersTableSection
    Created on : 2 mars 2016, 11:42:50
    Author     : Dragos
--%>
<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
                
    <h2 class="liste">Liste des utilisateurs<a class="liste" href="ServletUsers?action=listerLesUtilisateurs&first=${param.first}&nb=${param.nb}&total=${param.total}">Actualiser</a></h2>

    

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
                <td colspan="3" id="totalCell">${param.first} - ${param.first + total}</td>
            </tr>
        </table>

    <nav>
        
        <c:if test="${param.first >= param.nb}">
            <div id="previewBlock">
                <a id="previewButton" href="ServletUsers?action=preview&first=${param.first}&nb=${param.nb}&total=${param.total}">Preview</a>
                <!-- &avance=preview -->
            </div>
        </c:if>
        <c:if test="${param.first < param.nb}">
            <div id="previewBlockBlank"></div>
        </c:if>
        
        <c:if test="${param.first + param.nb < param.total}">
            <div id="nextBlock">
                <a id="nextButton" href="ServletUsers?action=next&first=${param.first}&nb=${param.nb}&total=${param.total}">Next</a>
                <!-- &avance=next -->
            </div>
        </c:if>
        
    </nav>
</section>
