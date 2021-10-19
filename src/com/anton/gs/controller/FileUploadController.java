package com.anton.gs.controller;

import com.anton.gs.controller.command.*;
import com.anton.gs.model.dao.impl.UserDaoImpl;
import com.anton.gs.model.exception.CommandException;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.util.impl.UrlModifierImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


import static com.anton.gs.controller.command.UsefulRegEx.*;

@WebServlet(urlPatterns = "/upload_controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20)
public class FileUploadController extends HttpServlet {
    private static final String FILE = "file";
    private static final String IMG = "img";
    private static final String AVATAR = "avatar";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String appDir = req.getServletContext().getRealPath(PLUG_EMPTY);
        String email = (String) req.getSession().getAttribute(SessionAttribute.EMAIL);
        String fileName = req.getPart(FILE).getSubmittedFileName();
        if (fileName == null || fileName.equals(PLUG_EMPTY)) {
            String url = req.getParameter(RequestParam.CURRENT_PATH);
            url = UrlModifierImpl.getInstance().modifyUrl(url);
            resp.sendRedirect(req.getParameter(RequestParam.CURRENT_PATH));
        } else {
            req.getSession().setAttribute(SessionAttribute.AVATAR_FILE_NAME, fileName);
            String uploadFileDir = appDir + File.separator + IMG + File.separator + AVATAR;
            File fileSaveDir = new File(uploadFileDir);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            boolean result;
            try {
                for (Part part : req.getParts()) {
                    if (part.getSubmittedFileName() != null) {
                        part.write(uploadFileDir + File.separator + part.getSubmittedFileName());
                    }
                }
                result = UserDaoImpl.getInstance().updateAvatarFileName(email, fileName);
                if (result) {
                    String url = req.getParameter(RequestParam.CURRENT_PATH);
                    url = UrlModifierImpl.getInstance().modifyUrl(url);
                    req.getRequestDispatcher(url).forward(req, resp);
                }
            } catch (IOException e) {
                resp.sendError(500, "message");
            } catch (DaoException e) {
                resp.sendError(500, "message");
            }
        }
    }
}
