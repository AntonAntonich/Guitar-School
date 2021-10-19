package com.anton.gs.controller.command.impl;
import com.anton.gs.controller.RoleType;
import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.UserService;
import com.anton.gs.model.util.impl.EmailSenderImpl;
import com.anton.gs.model.util.impl.PasswordCoderImpl;
import com.anton.gs.model.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.anton.gs.controller.command.MessagePath.*;
import static com.anton.gs.controller.command.PageAddress.*;


public class SignupCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService;
    private EmailSenderImpl emailSenderImpl;
    private static final int TUTOR_ID = 1;
    private static final int STUDENT_ID = 2;

    public SignupCommand(UserService userService) {
        this.userService = userService;
        emailSenderImpl = EmailSenderImpl.getInstance();
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {

        String userEmail = req.getParameter(RequestParam.EMAIL);
        String userPassword = req.getParameter(RequestParam.PASSWORD);
        String repeatedPassword = req.getParameter(RequestParam.REPEATED_PASSWORD);
        String userNickName = req.getParameter(RequestParam.NICKNAME);
        String userRole = detectRole(req);
        Router router = new Router();
        String page = null;

        Map<String, String> errorMessagesMap = UserValidatorImpl
                .getInstance().validateForm(userEmail, userPassword, repeatedPassword, userNickName);

        if (!isFormValid(errorMessagesMap, req)) {
            setErrorValues(router);
            return router;
        }

        if (!isPasswordsMatches(userPassword, repeatedPassword)) {
            setErrorValues(router);
            req.setAttribute(MessageAttribute.ERROR_PASSWORD, MessageManager.getProperty(PASSWORD_MISMATCH_PATH));
            req.setAttribute(MessageAttribute.ERROR_REPEAT_PASSWORD, MessageManager.getProperty(PASSWORD_MISMATCH_PATH));
            return router;
        }

        try {
            if (!userService.isUserExist(userEmail)) {
                userService.registerNewUser(userEmail, userPassword, userNickName, userRole);
                emailSenderImpl.sendEmail(MessageManager.getProperty(EMAIL_ACTIVATION_SUBJECT),
                        MessageManager.getProperty(EMAIL_ACTIVATION_MESSAGE) + userEmail, userEmail);
                userService.addToAvatarTable(userEmail);
                page = PageManager.getProperty(CONFIRM_REGISTRATION_PAGE);
                router.setTypeRedirect();
                router.setPage(page);
            } else {
                setErrorValues(router);
                req.setAttribute(MessageAttribute.ERROR_EMAIL, MessageManager.getProperty(EMAIL_EXIST_PATH));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return router;
    }

    private boolean isFormValid(Map<String, String> formData, HttpServletRequest req) {
        boolean result = true;

        for (String key : formData.keySet()) {
            if (!key.isEmpty()) {
                req.setAttribute(key, formData.get(key));
                result = false;
            }
        }

        return result;
    }

    private void setErrorValues(Router router) {
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.SIGNUP_PAGE));
    }

    private String detectRole(HttpServletRequest req) throws CommandException {
        String roleSelected = req.getParameter(RequestParam.ROLE_ID);
        int roleId = Integer.parseInt(roleSelected);
        String role = null;

        switch (roleId) {
            case TUTOR_ID:
                role = RoleType.TUTOR;
                break;
            case STUDENT_ID:
                role = RoleType.STUDENT;
                break;
            default:
                throw new CommandException();
        }
        return role;
    }

    private boolean isPasswordsMatches(String userPassword, String repeatedPassword) {
        String userPasswordCrypted = PasswordCoderImpl.getInstance().codePassword(userPassword);
        String userPasswordRepeatedCrypted = PasswordCoderImpl.getInstance().codePassword(repeatedPassword);
        return userPasswordCrypted.equals(userPasswordRepeatedCrypted);
    }
}
