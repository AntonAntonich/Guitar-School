package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.AdminService;
import com.anton.gs.model.service.impl.AdminServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowContractsCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private AdminService adminService;

    public ShowContractsCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        List<Contract> contractList;
        try{
            contractList = adminService.showAllContracts();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.setAttribute(RequestAttribute.CONTRACTS, contractList);
        router.setTypeForward();
        router.setPage(PageManager.getProperty(PageAddress.SHOW_ALL_CONTRACTS));
        return router;
    }
}
