package com.anton.gs.controller.command.impl.topage;

import com.anton.gs.controller.RoleType;
import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToProfilePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute(SessionAttribute.ROLE);
        String page = null;
        switch (role){
            case RoleType
                    .STUDENT:
                page = PageManager.getProperty(PageAddress.STUDENT_PAGE);
                break;
            case RoleType.TUTOR:
                page = PageManager.getProperty(PageAddress.TUTOR_PAGE);
                break;
            default:
                throw new CommandException();
        }
        Router router = new Router();
        router.setTypeForward();
        router.setPage(page);
        return router;
    }
}
