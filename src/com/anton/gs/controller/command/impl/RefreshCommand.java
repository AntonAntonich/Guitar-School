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
import java.util.List;

public class  RefreshCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;
    private UserService userService;

    public RefreshCommand(AdminService adminService, UserService userService)
    {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        String email = req.getParameter(RequestParam.EMAIL);
        String role = req.getParameter(RequestParam.ROLE);
        String skill = req.getParameter(RequestParam.SKILL);
        String degree = req.getParameter(RequestParam.DEGREE);

        Router router = new Router();
        String message;
        User user = null;

        try {
            if (adminService.refreshUser(email, role, skill, degree)) {
                message = MessageManager.getProperty(MessagePath.USER_REFRESHED_SUCCESS);
            } else {
                message = MessageManager.getProperty(MessagePath.USER_REFRESHED_UNSUCCESSFUL);
            }
            user = userService.findUser(email);

            action(req, router, message, user);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return router;
    }

    private void action(HttpServletRequest request, Router router, String message, User user) {
        String url = request.getParameter(RequestParam.CURRENT_PATH);
        request.setAttribute(RequestAttribute.REFRESH_USER_MSG, message);
        request.setAttribute(RequestAttribute.USER, user);
        url = UrlModifierImpl.getInstance().modifyUrl(url);
        router.setTypeForward();
        router.setPage(url);
    }
}
