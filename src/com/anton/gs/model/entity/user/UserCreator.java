package com.anton.gs.model.entity.user;


import com.sun.org.apache.xpath.internal.operations.Bool;

public final class UserCreator {
    private static UserCreator userCreator;

    private UserCreator(){}

    public static UserCreator getInstance(){
        if(userCreator == null) {
            userCreator = new UserCreator();
        }
        return userCreator;
    }

    public static User createUser(int userId,
                                  String email,
                                  String nickName,
                                  String userRole,
                                  String tutorDegree,
                                  String studentSkillLevel,
                                  Boolean isActive) {

        User user = new User(userId, email, nickName, userRole, tutorDegree, studentSkillLevel, isActive);
        return user;
    }

}
