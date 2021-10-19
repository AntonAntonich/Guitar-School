package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.entity.order.Order;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowOrdersCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;

    public ShowOrdersCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        List<Order> orders;
        try {
            orders = adminService.showOrders();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.setAttribute(RequestAttribute.ORDERS, orders);
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.SHOW_ORDERS_PAGE));
        return router;
    }
}
