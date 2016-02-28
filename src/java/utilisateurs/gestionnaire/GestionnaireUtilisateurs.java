
package utilisateurs.gestionnaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilisateurs.modeles.Utilisateur;

@Stateless
public class GestionnaireUtilisateurs {
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé
    // à partir du contenu de persistence.xml
    @PersistenceContext
    private EntityManager em;

    public void creerUtilisateursDeTest(String str) {
        int n = Integer.parseInt(str);
        for (int i=0; i < n; i++) {
            if (i%3 == 0) {
                creeUtilisateur("User"+i, "Uservitch", "usr_"+i, "pass"+i);
            }
            else if (i%3 == 1) {
                creeUtilisateur("User"+i, "Userson", "usr_"+i, "pass"+i);
            }
            else {
                creeUtilisateur("User"+i, "Userpont", "usr_"+i, "pass"+i);
            }
        }
    }

    public Utilisateur creeUtilisateur(String nom, String prenom, String login, String password) {
        Utilisateur u = new Utilisateur(nom, prenom, login, password);
        em.persist(u);
        return u;
    }

    public Collection<Utilisateur> getAllUsers() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }
    public Collection<Utilisateur> getAllUsers(int first, int nb) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u");
        q.setFirstResult(first);
        q.setMaxResults(nb);
        return q.getResultList();
    }
    
    public Collection<Utilisateur> getUsers(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login = :login");
        q.setParameter("login", login);
        return q.getResultList();
    }
    
    public void modifierUtilisateur (String login, String firstname, String lastname, String password) {
        Query q = em.createQuery("update Utilisateur u set u.firstname = :prenom, u.lastname = :nom, u.password = :password where u.login = :login");
        q.setParameter("login", login);
        q.setParameter("nom", lastname);
        q.setParameter("prenom", firstname);
        q.setParameter("password", password);
        q.executeUpdate();
        
    }
    
    public boolean verifierPassword (String login, String password) {
        boolean itsOK = false;
        for (Utilisateur u : getUsers(login)) {
            if (u.getPassword().equals(password)) {
                itsOK = true;
            }
            else {
                itsOK = false;
            }
        }
        return itsOK;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}