package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import com.anton.gs.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SearchUserCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;
    private UserService userService;

    public SearchUserCommand(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String email = req.getParameter(RequestParam.EMAIL);
        User user = null;
        try {
           user = userService.findUser(email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        String page = PageManager.getProperty(PageAddress.SEARCH_USER_PAGE);
        req.getSession().setAttribute(SessionAttribute.USER, user);
        router.setTypeRedirect();
        router.setPage(page);
        return router;
    }
}
