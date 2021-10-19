package com.anton.gs.controller.command.impl;

import com.anton.gs.controller.RoleType;
import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.service.PaymentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RestCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private PaymentService paymentService;

    public RestCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String bankAccountNumber = req.getParameter(RequestParam.BANK_ACCOUNT_NUMBER);
        String email = (String)req.getSession().getAttribute(SessionAttribute.EMAIL);
        String page = PageManager.getProperty(PageAddress.PAYMENT_PAGE);
        String message = MessageManager.getProperty(MessagePath.YOUR_REST_IS);
        String role = (String) req.getSession().getAttribute(SessionAttribute.ROLE);
        router.setTypeRedirect();
        router.setPage(page);
        int rest = 0;
        boolean result;
        if(role.equals(RoleType.NONE)){
            router.setTypeRedirect();
            router.setPage(PageManager.getProperty(PageAddress.INDEX_PAGE));
            return router;
        }
        try {
            String emailFromDb = paymentService.findClientByAccount(bankAccountNumber);
            if(!email.equals(emailFromDb)) {
                message = MessageManager.getProperty(MessagePath.THERE_IS_NO_SUCH_CLIENT);
                req.getSession().setAttribute(MessageAttribute.REST_MSG, message);
                req.getSession().setAttribute(SessionAttribute.REST, UsefulRegEx.PLUG_EMPTY);
                req.getSession().setAttribute(SessionAttribute.TRANSFER_MSG, UsefulRegEx.PLUG_EMPTY);
                return router;
            }
            rest = paymentService.findRestByAccount(bankAccountNumber);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        req.getSession().setAttribute(SessionAttribute.REST, rest);
        req.getSession().setAttribute(MessageAttribute.REST_MSG, message);
        req.getSession().setAttribute(SessionAttribute.TRANSFER_MSG, UsefulRegEx.PLUG_EMPTY);
        return router;
    }
}
