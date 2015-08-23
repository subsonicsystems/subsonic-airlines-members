package info.subsonic.subsonicairlines.members.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = User.EXISTS_USERNAME, query = "SELECT COUNT(u) FROM User u WHERE LOWER(u.username) = :username") })
public class User implements Serializable {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The min length of username.
     */
    public static final int USERNAME_MIN_LENGTH = 6;

    /**
     * The max length of username.
     */
    public static final int USERNAME_MAX_LENGTH = 255;

    /**
     * The regexp of username.
     */
    public static final String USERNAME_REGEXP = "[a-zA-Z0-9_\\-]+";

    /**
     * The regexp of password.
     */
    public static final String PASSWORD_REGEXP = "[a-zA-Z0-9_!\"#$%\\&'\\(\\)\\-=\\^~\\\\\\|@`\\[\\{;\\+:\\*\\]\\}\\,<\\.>/\\?]+";

    /**
     * The min length of password.
     */
    public static final int PASSWORD_MIN_LENGTH = 6;

    /**
     * The max length of password.
     */
    public static final int PASSWORD_MAX_LENGTH = 64;

    /**
     * The query of existsUsername.
     */
    public static final String EXISTS_USERNAME = "User.existsUsername";

    /**
     * The ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The username.
     */
    @Column(name = "username", length = 255, unique = true)
    @SuppressWarnings("checkstyle:magicnumber")
    private String username;

    /**
     * The password.
     */
    @Column(name = "password", length = 64)
    @SuppressWarnings("checkstyle:magicnumber")
    private String password;

    /**
     * The created time.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Calendar created;

    /**
     * The updated time.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Calendar updated;

    /**
     * The version.
     */
    @Version
    @Column(name = "version")
    private Long version;

    /**
     * Gets ID.
     * 
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID.
     * 
     * @param id the ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     * 
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     * 
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets created.
     * 
     * @return the created.
     */
    public Calendar getCreated() {
        return created;
    }

    /**
     * Sets created.
     * 
     * @param created the created.
     */
    public void setCreated(Calendar created) {
        this.created = created;
    }

    /**
     * Gets updated.
     * 
     * @return the updated.
     */
    public Calendar getUpdated() {
        return updated;
    }

    /**
     * Sets updated.
     * 
     * @param updated the updated.
     */
    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    /**
     * Gets version.
     * 
     * @return the version.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets version.
     * 
     * @param version the version.
     */
    public void setVersion(Long version) {
        this.version = version;
    }

}
