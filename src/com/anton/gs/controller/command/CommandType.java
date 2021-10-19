package com.anton.gs.controller.command;

import com.anton.gs.controller.command.impl.*;
import com.anton.gs.controller.command.impl.topage.*;

import com.anton.gs.model.dao.impl.AdminDaoImpl;
import com.anton.gs.model.service.impl.AdminServiceImpl;
import com.anton.gs.model.service.impl.PaymentServiceImpl;
import com.anton.gs.model.service.impl.StudentServiceImpl;
import com.anton.gs.model.service.impl.UserServiceImpl;

public enum CommandType {
    DEFAULT_COMMAND(new DefaultCommand()),
    SIGNIN(new SigninCommand(UserServiceImpl.getInstance())),
    SIGNUP(new SignupCommand(UserServiceImpl.getInstance())),
    LOGOUT(new LogoutCommand()),
    TO_MAIN_PAGE(new ToMainPageCommand()),
    TO_SIGNIN_PAGE(new ToSigninPageCommand()),
    TO_SIGNUP_PAGE(new ToSignupPageCommand()),
    TO_ABOUT_PAGE(new ToAboutPageCommand()),
    TO_CARDS(new ToCardsPageCommand()),
    TO_PROFILE_PAGE(new ToProfilePageCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountCommand(UserServiceImpl.getInstance())),
    BUY(new BuyCommand(UserServiceImpl.getInstance())),
    SHOW_ALL_USERS(new ShowAllUsersCommand(UserServiceImpl.getInstance())),
    BAN(new BanCommand(AdminServiceImpl.getInstance(), UserServiceImpl.getInstance())),
    REFRESH(new RefreshCommand(AdminServiceImpl.getInstance(), UserServiceImpl.getInstance())),
    TO_SEARCH_USER(new ToSearchUserCommand()),
    SEARCH_USER(new SearchUserCommand(AdminServiceImpl.getInstance(), UserServiceImpl.getInstance())),
    BANNED_USERS(new BannedUsersCommand( UserServiceImpl.getInstance(), AdminServiceImpl.getInstance())),
    UNBAN(new UnbanUserCommand(UserServiceImpl.getInstance(), AdminServiceImpl.getInstance())),
    PAY(new PayCommand(PaymentServiceImpl.getInstance())),
    REST(new RestCommand(PaymentServiceImpl.getInstance())),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SHOW_ORDERS(new ShowOrdersCommand(AdminServiceImpl.getInstance())),
    DRAW(new DrawUpContractCommand(AdminServiceImpl.getInstance())),
    CONFIRM_ORDER(new ConfirmOrderCommand(AdminServiceImpl.getInstance())),
    SHOW_CONTRACTS(new ShowContractsCommand(AdminServiceImpl.getInstance())),
    SHOW_COURSES(new ShowCoursesCommand(StudentServiceImpl.getInstance())),
    SHOW_STUDENTS(new ShowStudentsCommand(StudentServiceImpl.getInstance())),
    UPDATE(new UpdateCommand(StudentServiceImpl.getInstance())),
    CONFIRM_CONTRACT(new ConfirmContractCommand(AdminServiceImpl.getInstance()));

    private Command command;


    private CommandType(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
