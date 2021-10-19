package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.anton.gs.controller.RoleType.*;


public class SigninCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService;

    public SigninCommand(UserService service) {
        userService = service;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        final String userMail = req.getParameter(RequestParam.EMAIL);
        final String userPassword = req.getParameter(RequestParam.PASSWORD);
        Router router = new Router();

        try {
            if (secureData(userMail, userPassword)) {
                User user = userService.findUser(userMail);
                if(userService.isUserBlocked(user.getEmail())){
                    router.setTypeRedirect();
                    router.setPage(PageManager.getProperty(PageAddress.BLOCKED_USER_PAGE));
                    return router;
                }
                boolean isActive = user.getActive();
                req.getSession().setAttribute(SessionAttribute.IS_ACTIVE, isActive);
                correctAction(user, req, router);
            } else {
                wrongAction(router, req);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return router;
    }

    private void correctAction(User user, HttpServletRequest request, Router router) throws ServiceException {
        String page;
        String userRole = user.getUserRole().toUpperCase();
        switch (userRole) {
            case ADMIN:
                page = PageManager.getProperty(PageAddress.ADMIN_PAGE);
                setPageValues(user, router, page, request);
                break;
            case TUTOR:
                page = PageManager.getProperty(PageAddress.TUTOR_PAGE);
                setPageValues(user, router, page, request);
                break;
            case STUDENT:
                page = PageManager.getProperty(PageAddress.STUDENT_PAGE);
                setPageValues(user, router, page, request);
                break;
            default:
                page = PageManager.getProperty(PageAddress.INDEX_PAGE);
                router.setPage(page);
                router.setTypeRedirect();
                break;
        }
    }

    private void setPageValues(User user, Router router, String page, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        router.setTypeRedirect();
        router.setPage(page);

        String userMail = user.getEmail();
        String userNickName = user.getNickName();
        String userRole = user.getUserRole();
        String skill = user.getStudentSkillLevel();
        String degree = user.getTutorDegree();

        String avatarName = userService.findPhotoName(userMail);

        boolean isCoursesPressed = false;
        boolean isStudentsPressed = false;

        session.setAttribute(SessionAttribute.AVATAR_FILE_NAME, avatarName);
        session.setAttribute(SessionAttribute.IS_STUDENT_PRESSED, isStudentsPressed);
        session.setAttribute(SessionAttribute.IS_COURSES_PRESSED, isCoursesPressed);
        session.setAttribute(SessionAttribute.ROLE, userRole);
        session.setAttribute(SessionAttribute.EMAIL, userMail);
        session.setAttribute(SessionAttribute.NICKNAME, userNickName);
        session.setAttribute(SessionAttribute.ROLE, userRole);
        session.setAttribute(SessionAttribute.SKILL, skill);
        session.setAttribute(SessionAttribute.DEGREE, degree);
    }

    private boolean secureData(String userMail, String userPassword) throws ServiceException {
        return (userService.isUserExist(userMail)
                && userService.isUserEmailPasswordEquals(userMail, userPassword));
    }

    private void wrongAction(Router router, HttpServletRequest req) {
        String page = PageManager.getProperty(PageAddress.SIGNIN_PAGE);
        req.setAttribute(MessageAttribute.ERROR_MESSAGE, MessageManager.getProperty(MessagePath.INCORRECT_EMAIL_OR_PASSWORD));
        router.setTypeForward();
        router.setPage(page);
    }



}
