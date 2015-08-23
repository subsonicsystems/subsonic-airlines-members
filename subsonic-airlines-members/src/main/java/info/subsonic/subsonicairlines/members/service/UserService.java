package info.subsonic.subsonicairlines.members.service;

import info.subsonic.subsonicairlines.members.model.User;

import java.io.Serializable;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This class is for user service.
 */
@Stateless
public class UserService implements Serializable {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The EntityManager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Tests if username exists.
     * 
     * @param username the username.
     * @return true if username exists; false otherwise.
     */
    public boolean existsUsername(String username) {
        TypedQuery<Number> query = em.createNamedQuery(User.EXISTS_USERNAME, Number.class);
        query.setParameter("username", username.toLowerCase());
        int count = query.getSingleResult().intValue();

        if (count < 1) {
            return false;
        }

        return true;
    }

    /**
     * Adds User.
     * 
     * @param user the User.
     */
    public void add(User user) {
        Calendar current = Calendar.getInstance();

        user.setCreated(current);
        user.setUpdated(current);

        em.persist(user);
    }

}
