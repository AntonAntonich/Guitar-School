package com.anton.gs.controller.command.impl.topage;

import com.anton.gs.controller.command.Command;
import com.anton.gs.controller.command.PageAddress;
import com.anton.gs.controller.command.PageManager;
import com.anton.gs.controller.command.Router;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.service.AdminService;

import javax.servlet.http.HttpServletRequest;

public class ToSearchUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String page = PageManager.getProperty(PageAddress.SEARCH_USER_PAGE);
        router.setTypeForward();
        router.setPage(page);
        return router;
    }
}
