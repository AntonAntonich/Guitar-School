package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.Command;
import com.anton.gs.controller.command.Router;
import com.anton.gs.controller.command.PageAddress;
import com.anton.gs.controller.command.PageManager;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        String page = PageManager.getProperty(PageAddress.INDEX_PAGE);
        Router router = new Router();
        router.setTypeRedirect();
        router.setPage(page);
        return router;
    }
}
