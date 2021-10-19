package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import com.anton.gs.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UnbanUserCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService;
    private AdminService adminService;

    public UnbanUserCommand(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String email = req.getParameter(RequestParam.EMAIL);
        List<String> blockedUsers;
        String message;
        try {
            blockedUsers = adminService.findBannedUsers();
            if(adminService.unbanUser(email)){
                message = MessageManager.getProperty(MessagePath.USER_UNBANNED_SUCCESSFUL);
            } else {
                message = MessageManager.getProperty(MessagePath.USER_UNBANNED_UNSUCCESSFUL);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        String page = PageManager.getProperty(PageAddress.SHOW_BANNED_USERS_PAGE);
        req.setAttribute(RequestAttribute.BANNED_USERS, blockedUsers);
        req.setAttribute(RequestAttribute.UNBANNED_USER, email);
        req.setAttribute(MessageAttribute.USER_UNBANNED, message);
        router.setTypeForward();
        router.setPage(page);
        return router;
    }
}
