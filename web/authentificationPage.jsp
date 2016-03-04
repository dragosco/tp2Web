<%-- 
    Document   : authentificationPage
    Created on : 2 mars 2016, 11:34:20
    Author     : Dragos
--%>
<div id="loginBlock">
    <form class="formElemLogin" id="loginForm" action="ServletUsers" method="get">
        <h3 class="formElemLogin">Se connecter</h3>
        
        <div class="formElemLogin"><input type="text" name="login" placeholder="Username" id="loginLogin" class="loginInput"/></div>
        <div class="formElemLogin"><input type="password" name="password" placeholder="Password" id="loginPass" class="loginInput"/></div>
        <input type="hidden" name="action" value="authentification"/>
        <input type="hidden" name="etat" value="reussie"/>
        <button class="formElemLogin" type="submit" value="login" name="submit" id="loginButton">LOGIN</button>
        
    </form>
</div>
                
<div id="formCreationBlock">
    <form class="formElemBlock" id="createForm" action="ServletUsers" method="get">
        <h3 class="formElemBlock">S'enregistrer</h3>
        <div class="formElemBlock"><input type="text" name="nom" placeholder="Nom" id="creeNom"/></div>
        <div class="formElemBlock"><input type="text" name="prenom" placeholder="Prénom" id="creePrenom"/></div>
        <div class="formElemBlock"><input type="text" name="login" placeholder="Username" id="creeLogin"/></div>
        <div class="formElemBlock"><input type="text" name="password" placeholder="Password" id="creePass"/></div>
        <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->
        <input type="hidden" name="action" value="creerUnUtilisateur"/>
        <button class="formElemBlock" type="submit" value="Créer l'utilisateur" name="submit">SIGN UP</button>
    </form>
</div>
