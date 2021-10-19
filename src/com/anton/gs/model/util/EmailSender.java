package com.anton.gs.model.util;

import com.anton.gs.model.exception.ServiceException;

public interface EmailSender {
    void sendEmail(String subject, String message, String recipientEmail) throws ServiceException;
}
