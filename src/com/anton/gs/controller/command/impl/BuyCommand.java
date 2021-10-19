package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.anton.gs.controller.RoleType.*;

public class BuyCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService;

    public BuyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        HttpSession session = req.getSession();
        String role = (String)session.getAttribute(SessionAttribute.ROLE);
        String email = (String) session.getAttribute(SessionAttribute.EMAIL);

        String price = req.getParameter(RequestParam.PRICE);
        String genre = req.getParameter(RequestParam.GENRE);

        req.getSession().setAttribute(SessionAttribute.PRICE, price);
        req.getSession().setAttribute(SessionAttribute.GENRE, genre);

        String page = null;
        switch (role.toUpperCase()) {
            case NONE:
                page = PageManager.getProperty(PageAddress.SIGNUP_PAGE);
                router.setTypeForward();
                router.setPage(page);
                break;
            case STUDENT:
                try {
                    page = detectStudentPage(email);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                    throw new CommandException(e);
                }
                router.setTypeForward();
                router.setPage(page);
                break;
            case TUTOR:
                page = PageManager.getProperty(PageAddress.TUTOR_PAGE);
                router.setTypeForward();
                router.setPage(page);
                break;
            case ADMIN:
                page = PageManager.getProperty(PageAddress.ADMIN_PAGE);
                router.setTypeForward();
                router.setPage(page);
                break;
            default:
                //TODO
        }

        return router;
    }

    private String detectStudentPage(String email) throws ServiceException {
        final String paymentPage = PageAddress.PAYMENT_PAGE;
        final String confirmPage = PageAddress.CONFIRM_REGISTRATION_PAGE;
        boolean isActive = userService.isUserActive(email);
        return isActive ? PageManager.getProperty(paymentPage) :
                PageManager.getProperty(confirmPage);
  }

}
