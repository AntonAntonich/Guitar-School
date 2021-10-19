package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import com.sun.deploy.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

public class ConfirmContractCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;

    public ConfirmContractCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String studentEmail = (String) req.getSession().getAttribute(SessionAttribute.EMAIL_STUDENT);
        String tutorEmail = req.getParameter(RequestParam.EMAIL_TUTOR);
        String genre = (String) req.getSession().getAttribute(SessionAttribute.GENRE_ORDER);
        String startDare = req.getParameter(RequestParam.START_DATE);
        String endDate = req.getParameter(RequestParam.END_DATE);
        String currentLevel = req.getParameter(RequestParam.CURRENT_LEVEL);
        String targetLevel = req.getParameter(RequestParam.TARGET_LEVEL);
        String guitarType = req.getParameter(RequestParam.GUITAR_TYPE);
        boolean result;
        try {
            result = adminService.createContract(studentEmail,
                    genre, tutorEmail,
                    startDare,
                    endDate, currentLevel, targetLevel, guitarType);
            if(result) {
                router.setTypeRedirect();
                router.setPage(PageManager.getProperty(PageAddress.SUCCESS_CONTRACT_CREATION));
            } else {
                router.setTypeRedirect();
                router.setPage(PageManager.getProperty(PageAddress.INDEX_PAGE));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw  new CommandException(e);
        }

        return router;
    }
}
