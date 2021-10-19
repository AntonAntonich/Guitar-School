package com.anton.gs.controller.command;

import java.util.Optional;

public class CommandProvider {

    public static Command  defineCommand(String commandName) {
        Command current;
        if(commandName == null || commandName.isEmpty()){
            current = CommandType.DEFAULT_COMMAND.getCommand();
            return current;
        }
        try{
            CommandType type =
                    CommandType.valueOf(commandName.toUpperCase().replaceAll("\\s+", UsefulRegEx.UNDERSCORE));
            current = type.getCommand();
        }catch (IllegalArgumentException e){
            //log
            current = CommandType.DEFAULT_COMMAND.getCommand();
        }
        return current;
    }
}
