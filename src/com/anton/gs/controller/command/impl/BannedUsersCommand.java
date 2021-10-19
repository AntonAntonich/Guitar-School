package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import com.anton.gs.model.service.UserService;
import com.anton.gs.model.util.impl.UrlModifierImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BannedUsersCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService;
    private AdminService adminService;

    public BannedUsersCommand(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        List<String> bannedUsers;
        try {
            bannedUsers = adminService.findBannedUsers();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.setAttribute(RequestAttribute.BANNED_USERS, bannedUsers);
        String page = PageManager.getProperty(PageAddress.SHOW_BANNED_USERS_PAGE);
        router.setPage(page);
        router.setTypeForward();
        return router;
    }
}
