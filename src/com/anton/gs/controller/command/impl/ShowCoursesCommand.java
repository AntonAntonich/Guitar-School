package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.dao.StudentDao;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.StudentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowCoursesCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private StudentService studentService;

    public ShowCoursesCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String email = (String)req.getSession().getAttribute(SessionAttribute.EMAIL);
        boolean isCoursesPressed = true;
        req.getSession().setAttribute(SessionAttribute.IS_COURSES_PRESSED, isCoursesPressed);
        List<Contract> contractList;
        try{
            contractList = studentService.showCourses(email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.setAttribute(RequestAttribute.CONTRACTS, contractList);
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.STUDENT_PAGE));
        return router;
    }
}
