package com.anton.gs.controller.command;

import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute router.
     * set redirect or forward method, set destination page url
     * @param req the req
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest req) throws  CommandException;
}
