package com.anton.gs.controller.command;

public class MessagePath {
    public static final String WRONG_ACTION_ADDRESS = "message.wrong_action";
    public static final String INCORRECT_EMAIL_OR_PASSWORD = "message.incorrect_email_or_password";
    public static final String EMPTY_FIELD_PATH = "message.empty_field";
    public static final String EMAIL_CONSTRAINT_PATH = "message.invalid_email";
    public static final String PASSWORD_CONSTRAINT_PATH = "message.invalid_password";
    public static final String PASSWORD_MISMATCH_PATH = "message.password_mismatch";
    public static final String NAME_CONSTRAINT_PATH = "message.invalid_name";
    public static final String EMAIL_EXIST_PATH = "message.mail_exist";
    public static final String EMAIL_ACTIVATION_SUBJECT = "message.mail.subject";
    public static final String EMAIL_ACTIVATION_MESSAGE = "message.mail.activate";
    public static final String USER_REFRESHED_SUCCESS = "message.admin.user_refreshed_success";
    public static final String USER_REFRESHED_UNSUCCESSFUL = "message.admin.user_refreshed_unsuccessful";
    public static final String USER_BANNED_SUCCESS = "message.admin.user_banned_successful";
    public static final String USER_BANNED_UNSUCCESSFUL = "message.admin.user_banned_unsuccessful";
    public static final String USER_UNBANNED_SUCCESSFUL = "message.admin.user_unbanned_successful";
    public static final String USER_UNBANNED_UNSUCCESSFUL = "message.admin.user_unbanned_unsuccessful";
    public static final String USER_ALREADY_BANNED = "message.admin.user_already_banned";
    public static final String ACCOUNT_NOT_ACTIVE = "message.account_not_active";
    public static final String YOUR_REST_IS = "message.payment.your_rest_is";
    public static final String THERE_IS_NO_SUCH_CLIENT = "message.payment.there_is_no_such_client";
    public static final String THERE_IS_NOT_ENOUGH_MONEY= "message.payment.there_is_not_enough";
    public static final String SUCCESS_UPDATE= "message.update_student.success";


    private MessagePath(String path) {

    }
}
