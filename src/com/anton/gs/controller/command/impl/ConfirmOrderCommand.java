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


public class ConfirmOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;

    public ConfirmOrderCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        String email = req.getParameter(RequestParam.EMAIL);
        int orderID = Integer.parseInt(req.getParameter(RequestParam.ORDER_ID));
        Router router = new Router();
        try {
            if(adminService.confirmOrder(orderID)) {
                List<Order> orders = adminService.showOrders();
                req.setAttribute(RequestAttribute.ORDERS, orders);
                router.setTypeForward();
                router.setPage(PageManager.getProperty(PageAddress.SHOW_ORDERS_PAGE));
            } else {
                router.setTypeRedirect();
                router.setPage(PageManager.getProperty(PageAddress.INDEX_PAGE));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return router;
    }
}
