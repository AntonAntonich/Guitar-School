package com.anton.gs.model.validator.impl;

import com.anton.gs.controller.command.MessageAttribute;
import com.anton.gs.controller.command.MessageManager;
import com.anton.gs.controller.command.MessagePath;
import com.anton.gs.model.validator.UserValidator;


import java.util.HashMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UserValidatorImpl implements UserValidator {
    private static final String PATTERN_EMAIL =
            "^([a-z\\d\\.\\-_]{6,50})(@)([a-z\\d\\-]{1,8})(\\.)([a-z]{2,8})(\\.[a-z]{2,8})?$";
    private static final String PATTERN_PASSWORD =
            "^(?=.*[0-9].*)(?=.*[a-z].*)(?=.*[A-Z]*)(?=.*[!@#$%^&*]*)[0-9A-Za-z!@#$%^&*]{10,20}$";
    private static final String PATTERN_NAME = "^[a-z0-9_-]{3,16}$";

    private static UserValidatorImpl userValidator;

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        if(userValidator == null) {
            userValidator = new UserValidatorImpl();
        }
        return userValidator;
    }

    @Override
    public  Map<String, String> validateForm(String userEmail,
                                                   String userPassword,
                                                   String repeatedPassword,
                                                   String userNickName) {
        Map<String, String> formMap = new HashMap<>();
        if (userEmail.isEmpty()) {
            formMap.put(MessageAttribute.ERROR_EMAIL, MessageManager.getProperty(MessagePath.EMPTY_FIELD_PATH));
        } else if (!isEmailValid(userEmail)) {
            formMap.put(MessageAttribute.ERROR_EMAIL, MessageManager.getProperty(MessagePath.EMAIL_CONSTRAINT_PATH));
        }

        if (userPassword.isEmpty()) {
            formMap.put(MessageAttribute.ERROR_PASSWORD, MessageManager.getProperty(MessagePath.EMPTY_FIELD_PATH));
        } else if (!isPasswordValid(userPassword)) {
            formMap.put(MessageAttribute.ERROR_PASSWORD, MessageManager.getProperty(MessagePath.PASSWORD_CONSTRAINT_PATH));
        }

        if (repeatedPassword.isEmpty()) {
            formMap.put(MessageAttribute.ERROR_REPEAT_PASSWORD, MessageManager.getProperty(MessagePath.EMPTY_FIELD_PATH));
        } else if (!isPasswordValid(repeatedPassword)) {
            formMap.put(MessageAttribute.ERROR_REPEAT_PASSWORD, MessageManager.getProperty(MessagePath.PASSWORD_CONSTRAINT_PATH));
        }

        if (userNickName.isEmpty()) {
            formMap.put(MessageAttribute.ERROR_NAME, MessageManager.getProperty(MessagePath.EMPTY_FIELD_PATH));
        } else if (!isNameValid(userNickName)) {
            formMap.put(MessageAttribute.ERROR_PASSWORD, MessageManager.getProperty(MessagePath.NAME_CONSTRAINT_PATH));
        }

        return formMap;
    }

    private static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PATTERN_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


}
