package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.StudentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UpdateCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private StudentService studentService;

    public UpdateCommand (StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String studentEmail = req.getParameter(RequestParam.EMAIL);
        String tutorEmail = (String) req.getSession().getAttribute(SessionAttribute.EMAIL);
        String skill = req.getParameter(RequestParam.SKILL);
        List<Contract> contractList = null;
        String message = null;
        boolean iStudentsPressed = true;
        req.getSession().setAttribute(SessionAttribute.IS_STUDENT_PRESSED, iStudentsPressed);
        try {
            if(studentService.updateStudent(studentEmail, skill)) {
                message = MessageManager.getProperty(MessagePath.SUCCESS_UPDATE);
                contractList = studentService.showStudents(tutorEmail);
                req.setAttribute(MessageAttribute.UPDATE_STUDENT_MESSAGE, message);
                req.setAttribute(RequestAttribute.CONTRACTS, contractList);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.TUTOR_PAGE));
        return router;
    }
}
