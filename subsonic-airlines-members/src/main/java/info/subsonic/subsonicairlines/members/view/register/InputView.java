package info.subsonic.subsonicairlines.members.view.register;

import info.subsonic.subsonicairlines.members.model.User;
import info.subsonic.subsonicairlines.members.service.UserService;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;

/**
 * This class is for register input view.
 */
@Named("registerInputView")
@RequestScoped
public class InputView implements Serializable {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The UserService.
     */
    @Inject
    private UserService userService;

    /**
     * The User.
     */
    private User user;

    /**
     * The FacesContext.
     */
    private FacesContext facesContext;

    /**
     * The ResourceBundle.
     */
    private ResourceBundle resourceBundle;

    /**
     * Initializes.
     */
    @PostConstruct
    public void init() {
        user = new User();
    }

    /**
     * Saves User.
     * 
     * @return the outcome.
     * @throws ServletException if an error occurs.
     */
    public String save() throws ServletException {
        facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        resourceBundle = ResourceBundle.getBundle("info.subsonic.subsonicairlines.members.web.messages.Messages",
                uiViewRoot.getLocale());

        UIInput usernameUiInput = (UIInput) uiViewRoot.findComponent("form:username");
        usernameUiInput.setValid(true);

        UIInput passwordUiInput = (UIInput) uiViewRoot.findComponent("form:password");
        passwordUiInput.setValid(true);

        boolean isValid = true;

        if (!isUsernameValid()) {
            usernameUiInput.setValid(false);
            isValid = false;
        }

        if (!isPasswordValid()) {
            passwordUiInput.setValid(false);
            isValid = false;
        }

        if (!isValid) {
            return "";
        }

        userService.add(user);

        return "/register/completed";
    }

    /**
     * Gets User.
     *
     * @return the User.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets User.
     *
     * @param user the User.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Tests if username is valid.
     * 
     * @return true if username is valid; false otherwise.
     */
    private boolean isUsernameValid() {

        if (StringUtils.isBlank(user.getUsername())) {
            String message = resourceBundle.getString("user.username.required");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:username", facesMessage);
            return false;
        }

        if (!user.getUsername().matches(User.USERNAME_REGEXP)) {
            String message = resourceBundle.getString("user.username.invalid");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:username", facesMessage);
            return false;
        }

        if (user.getUsername().length() < User.USERNAME_MIN_LENGTH) {
            String message = resourceBundle.getString("user.username.minLength");
            message = message.replace("{min}", String.valueOf(User.USERNAME_MIN_LENGTH));

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:username", facesMessage);
            return false;
        }

        if (user.getUsername().length() > User.USERNAME_MAX_LENGTH) {
            String message = resourceBundle.getString("user.username.maxLength");
            message = message.replace("{max}", String.valueOf(User.USERNAME_MAX_LENGTH));
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:username", facesMessage);
            return false;
        }

        if (userService.existsUsername(user.getUsername())) {
            String message = resourceBundle.getString("user.username.exists");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:username", facesMessage);
            return false;
        }

        return true;
    }

    /**
     * Tests if password is valid.
     * 
     * @return true if password is valid; false otherwise.
     */
    private boolean isPasswordValid() {

        if (StringUtils.isBlank(user.getPassword())) {
            String message = resourceBundle.getString("user.password.required");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:password", facesMessage);
            return false;
        }

        if (!user.getPassword().matches(User.PASSWORD_REGEXP)) {
            String message = resourceBundle.getString("user.password.invalid");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:password", facesMessage);
            return false;
        }

        if (user.getPassword().length() < User.PASSWORD_MIN_LENGTH) {
            String message = resourceBundle.getString("user.password.minLength");
            message = message.replace("{min}", String.valueOf(User.PASSWORD_MIN_LENGTH));
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:password", facesMessage);
            return false;
        }

        if (user.getPassword().length() > User.PASSWORD_MAX_LENGTH) {
            String message = resourceBundle.getString("user.password.maxLength");
            message = message.replace("{max}", String.valueOf(User.PASSWORD_MAX_LENGTH));
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            facesContext.addMessage("form:password", facesMessage);
            return false;
        }

        return true;
    }

}
