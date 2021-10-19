package com.anton.gs.controller.filter;

import com.anton.gs.controller.RoleType;
import com.anton.gs.controller.command.PageAddress;
import com.anton.gs.controller.command.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE
}, urlPatterns = {
        "/jsp/roles/*",
        "/jsp/first_screen.jsp"})

public class ProfileFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String role = (String)session.getAttribute(SessionAttribute.ROLE);
        logger.log(Level.INFO, role + "in prof filter");
        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Cache-control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expire", 0);
        if(role.equals(RoleType.NONE)) {
            response.sendRedirect("http://localhost:8080/Guitar_School_0_0_2_war_exploded/index.jsp");
        }

        filterChain.doFilter(request, response);
    }
}
