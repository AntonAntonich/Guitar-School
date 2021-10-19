package com.anton.gs.controller;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.SendingContext.RunTime;

import static com.anton.gs.controller.command.RequestAttribute.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;


@WebServlet(urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(RequestParam.COMMAND);

        Command command = CommandProvider.defineCommand(commandName);
        Router router = null;
        try {
            router = command.execute(req);
        } catch (CommandException e) {
            logger.log(Level.ERROR, e);
            //resp.sendError(500);
        }
        String page = router.getPage();

        switch (router.getType()) {
            case FORWARD:
                try {
                    req.getRequestDispatcher(page).forward(req, resp);
                } catch (ServletException e) {
                    logger.log(Level.ERROR, e);
                   // resp.sendError(500);
                } catch (IOException e) {
                    logger.log(Level.ERROR, e);
                    //resp.sendError(500);
                }
                break;
            case REDIRECT:
                try {
                    resp.sendRedirect(page);
                } catch (IOException e) {
                    logger.log(Level.ERROR, e);
                   // resp.sendError(500);
                }
                break;
            default:
                resp.sendRedirect(PageManager.getProperty(PageAddress.INDEX_PAGE));
        }

    }
}
