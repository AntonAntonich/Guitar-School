package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.RoleType;
import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.impl.AdminServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DrawUpContractCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminServiceImpl adminService;

    public DrawUpContractCommand(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        String email = req.getParameter(RequestParam.EMAIL);
        String genre = req.getParameter(RequestParam.GENRE);
        String role = RoleType.TUTOR.toLowerCase();
        Router router = new Router();
        List<String> tutorEmail;
        try {
            tutorEmail = adminService.findTutors(role);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.setAttribute(RequestAttribute.TUTORS, tutorEmail);
        req.setAttribute(RequestAttribute.EMAIL, email);
        req.setAttribute(RequestAttribute.GENRE, genre);
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.DRAW_CONTRACT));
        return router;
    }
}
