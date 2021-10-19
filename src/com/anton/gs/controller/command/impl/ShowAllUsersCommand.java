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
import java.util.List;

public class ShowAllUsersCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService;

    public ShowAllUsersCommand (UserService userService){
        this.userService = userService;
    }
    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        String page = null;
        Router router = new Router();
        List<User> users = null;
        try {
            users = userService.findAllUsers();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        page = PageManager.getProperty(PageAddress.SHOW_ALL_USERS_PAGE);
        req.setAttribute(RequestAttribute.USERS, users);
        router.setTypeForward();
        router.setPage(page);
        return router;
    }
}
