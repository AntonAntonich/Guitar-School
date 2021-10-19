package com.anton.gs.model.util;

/**
 * The interface Password coder.
 */
public interface PasswordCoder {
     /**
      * Code password string.
      * returning encrypted password
      * @param password the password
      * @return the string
      */
     String codePassword(String password);
}
