<%-- 
    Document   : functionalities
    Created on : 2 mars 2016, 11:39:22
    Author     : Dragos
--%>
<aside>
    <h2>Menu</h2>     

    <div class="functionalityBlock" id="functionalityBlock1">
        <form class="functionalityBlock" id="testUsers" action="ServletUsers" method="get">
            <h2 class="functionalityBlock" functionalityBlock">Créer utilisateurs de test</h2>
            <div class="functionalityBlock" id="testblock"><label for="nbUsers" class="formElem" id="labelTest">Créer <input type=number name="nbUsers" placeholder="50" id="nbUsers" /> utilisateurs de test</label></div>
            <input type="hidden" name="action" value="creerUtilisateursDeTest"/>
            <button class="functionalityBlock" type="submit" value="Test" name="submit" id="testButton">Test</button>
        </form>
    </div>
    <div class="functionalityBlock" id="functionalityBlock2">
        
        <form class="functionalityBlock" id="formAffiche "action="ServletUsers" method="get">
            <h2 class="functionalityBlock">Chercher utilisateur</h2>
            <div class="functionalityBlock"><input type="text" name="login" placeholder="Username" id="loginAffiche"/></div>
            <input type="hidden" name="action" value="chercherParLogin"/>
            <button class="functionalityBlock" type="submit" value="Chercher" name="submit">Chercher</button>
        </form>
    </div>
    <div class="functionalityBlock" id="functionalityBlock3">
        <form class="functionalityBlock" id="formModif" action="ServletUsers" method="get">
            <h2 class="functionalityBlock">Mettre à jour un utilisateur</h2>
            <div class="functionalityBlock"><input type="text" name="login" placeholder="Username" id="loginModif"/></div>
            <p class="functionalityBlock">Modifier :</p>
            <div class="functionalityBlock"><input type="text" name="nom" placeholder="Nom" id="nomModif"/></div>
            <div class="functionalityBlock"><input type="text" name="prenom" placeholder="Prenom" id="prenomModif"/></div>
            <div class="functionalityBlock"><input type="text" name="password" placeholder="Password" id="passModif"/></div>
            <input type="hidden" name="action" value="updateUtilisateur"/>
            <button class="functionalityBlock" type="submit" value="Mettre à jour" name="submit">Mettre à jour</button>
        </form>
    </div>
    <div class="functionalityBlock" id="functionalityBlock4">
        <form class="functionalityBlock" id="formSuppression" action="ServletUsers" method="get">
            <h2 class="functionalityBlock">Supprimer un utilisateur</h2>
            <div class="functionalityBlock"><input type="text" name="login" placeholder="Username" id="loginSuppr"/></div>
            <input type="hidden" name="action" value="supprimerUtilisateur"/>
            <button class="functionalityBlock" type="submit" value="Supprimer" name="submit">Supprimer</button>
        </form>
    </div>
</aside>
