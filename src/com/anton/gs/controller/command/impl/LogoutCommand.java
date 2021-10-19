package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.RoleType;
import com.anton.gs.controller.command.Command;
import com.anton.gs.controller.command.Router;
import com.anton.gs.controller.command.PageAddress;
import com.anton.gs.controller.command.SessionAttribute;
import com.anton.gs.controller.command.PageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(SessionAttribute.ROLE, RoleType.NONE);
        Router router = new Router();
        String page = PageManager.getProperty(PageAddress.INDEX_PAGE);
        router.setPage(page);
        router.setTypeRedirect();
        return router;
    }
}
