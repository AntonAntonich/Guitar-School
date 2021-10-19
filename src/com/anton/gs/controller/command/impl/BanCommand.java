package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import com.anton.gs.model.service.UserService;
import com.anton.gs.model.util.impl.UrlModifierImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BanCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;
    private UserService userService;

    public BanCommand(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String email = req.getParameter(RequestParam.EMAIL);
        String message;
        User user = null;
        boolean result = false;
        try {
            if(!userService.isUserBlocked(email)) {
                adminService.banUser(email);
                user = userService.findUser(email);
                message = MessageManager.getProperty(MessagePath.USER_BANNED_SUCCESS);
            } else {
                message = MessageManager.getProperty(MessagePath.USER_ALREADY_BANNED);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.setAttribute(MessageAttribute.USER_BANNED, message);
        req.setAttribute(RequestAttribute.USER, user);
        String url = req.getParameter(RequestParam.CURRENT_PATH);
        url = UrlModifierImpl.getInstance().modifyUrl(url);
        router.setPage(url);
        router.setTypeForward();
        return router;
    }
}
