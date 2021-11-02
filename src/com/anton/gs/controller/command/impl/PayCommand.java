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

public class PayCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private PaymentService paymentService;
    private static final String SUCCESSFUL = "SUCCESSFUL";
    private static final String UNSUCCESSFUL = "UNSUCCESSFUL";
    private static final String NOT_ENOUGH = "NOT_ENOUGH";

    public PayCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Router execute(HttpServletRequest req) throws CommandException {
        Router router = new Router();
        String email = (String)req.getSession().getAttribute(SessionAttribute.EMAIL);
        String role = (String) req.getSession().getAttribute(SessionAttribute.ROLE);
        String bankAccount = req.getParameter(RequestParam.BANK_ACCOUNT_NUMBER);
        Integer price = Integer.parseInt((String) req.getSession().getAttribute(SessionAttribute.PRICE));
        String genre = (String) req.getSession().getAttribute(SessionAttribute.GENRE);

        String emailFromDb;
        String transferMessage;
        if(role.equals(RoleType.NONE)){
            router.setPage(PageManager.getProperty(PageAddress.INDEX_PAGE));
            router.setTypeRedirect();
            return router;
        }
        if(price.equals(0)|| bankAccount == null || bankAccount.isEmpty()){
            router.setPage(PageManager.getProperty(PageAddress.PAYMENT_PAGE));
            router.setTypeRedirect();
            return router;
        }

        try{
            emailFromDb = paymentService.findClientByAccount(bankAccount);
            if(!email.equals(emailFromDb)) {
                wrongAction(req, router);
                return router;
            }
            transferMessage = paymentService.transfer(bankAccount, price);
            int rest = paymentService.findRestByAccount(bankAccount);
            switch(transferMessage){
                case SUCCESSFUL:
                    successfulAction(router, req, email, genre);
                    break;
                case NOT_ENOUGH:
                    notEnoughAction(req, router,rest);
                    break;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return router;
    }

    private void successfulAction(Router router, HttpServletRequest req, String email, String genre) throws ServiceException {

                if(paymentService.createNewOrder(email, genre)){
                    String page = PageManager.getProperty(PageAddress.PAYMENT_SUCCESSFUL_PAGE);
                    req.getSession().setAttribute(SessionAttribute.ROLE, RoleType.NONE);
                    router.setTypeRedirect();
                    router.setPage(page);
                } else {
                    router.setTypeRedirect();
                    router.setPage(PageManager.getProperty(PageAddress.INDEX_PAGE));
                }

    }

    private void notEnoughAction(HttpServletRequest req, Router router, int rest){
        String transferMessage = MessageManager.getProperty(MessagePath.THERE_IS_NOT_ENOUGH_MONEY);
        String restMessage = MessageManager.getProperty(MessagePath.YOUR_REST_IS);
        String page = PageManager.getProperty(PageAddress.PAYMENT_PAGE);
        req.getSession().setAttribute(MessageAttribute.REST_MSG, restMessage);
        req.getSession().setAttribute(SessionAttribute.REST, rest);
        req.getSession().setAttribute(SessionAttribute.TRANSFER_MSG, transferMessage);
        router.setTypeRedirect();
        router.setPage(page);
    }
    private void wrongAction(HttpServletRequest req, Router router){
        String message;
        String page = PageManager.getProperty(PageAddress.PAYMENT_PAGE);
        message = MessageManager.getProperty(MessagePath.THERE_IS_NO_SUCH_CLIENT);
        req.getSession().setAttribute(MessageAttribute.REST_MSG, message);
        req.getSession().setAttribute(SessionAttribute.REST, RegExConstants.PLUG_EMPTY);
        req.getSession().setAttribute(SessionAttribute.TRANSFER_MSG, RegExConstants.PLUG_EMPTY);
        router.setTypeRedirect();
        router.setPage(page);
    }
}
