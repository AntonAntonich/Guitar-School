package com.anton.gs.controller.command.impl.topage;

import com.anton.gs.controller.command.Command;
import com.anton.gs.controller.command.PageAddress;
import com.anton.gs.controller.command.PageManager;
import com.anton.gs.controller.command.Router;
import com.anton.gs.model.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ToCardsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) throws  CommandException {
        Router router = new Router();
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.CARDS_PAGE));
        return router;
    }
}
