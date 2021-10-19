package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActivateAccountCommand implements Command {
    private UserService userService;
    private static Logger logger = LogManager.getLogger();

    public ActivateAccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = null;
        String email = req.getParameter(RequestParam.EMAIL);
        String page = null;
        try{
            if(email != null && userService.activateUser(email)){
                page = PageManager.getProperty(PageAddress.COMPLITED_REGISTRATION_PAGE);
                router = new Router();
                router.setTypeForward();
                router.setPage(page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return router;
    }
}
