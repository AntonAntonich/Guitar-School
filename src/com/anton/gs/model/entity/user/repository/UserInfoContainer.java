package com.anton.gs.model.entity.user.repository;

import java.util.HashMap;
import java.util.Map;

class UserInfoContainer {
    private static final UserInfoContainer userInfoContainer = new UserInfoContainer();

    private static final int NONE_ROLE_ID = 1;
    private static final int ADMIN_ROLE_ID = 2;
    private static final int TUTOR_ROLE_ID = 3;
    private static final int STUDENT_ROLE_ID = 4;

    private static final int STUDENT_LEVEL_NONE_ID = 1;
    private static final int STUDENT_LEVEL_START_ID = 2;
    private static final int STUDENT_LEVEL_ELEMENTARY_ID = 3;
    private static final int STUDENT_LEVEL_INTERMEDIATE_ID = 4;
    private static final int STUDENT_LEVEL_ADVANCED_ID = 5;

    private static final int TUTOR_DEGREE_NONE_ID = 1;
    private static final int TUTOR_DEGREE_JUNIOR_ID = 2;
    private static final int TUTOR_DEGREE_MIDDLE_ID = 3;
    private static final int TUTOR_DEGREE_SENIOR_ID = 4;

    private static final String ROLE_NONE = "NONE";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_TUTOR = "TUTOR";
    private static final String ROLE_STUDENT = "STUDENT";

    private static final String STUDENT_SKILL_NONE = "NONE";
    private static final String STUDENT_SKILL_START = "START";
    private static final String STUDENT_SKILL_ELEMENTARY = "ELEMENTARY";
    private static final String STUDENT_SKILL_INTERMEDIATE = "INTERMEDIATE";
    private static final String STUDENT_SKILL_ADVANCED = "ADVANCED";

    private static final String TUTOR_DEGREE_NONE = "NONE";
    private static final String TUTOR_DEGREE_JUNIOR = "JUNIOR";
    private static final String TUTOR_DEGREE_MIDDLE = "MIDDLE";
    private static final String TUTOR_DEGREE_SENIOR = "SENIOR";

     Map<Integer, String> userRoleMap = new HashMap<>();
     Map<Integer, String> studentSkillMap = new HashMap<>();
     Map<Integer, String> tutorDegreeMap = new HashMap<>();

    private UserInfoContainer() {
        userRoleMap.put(NONE_ROLE_ID, ROLE_NONE);
        userRoleMap.put(ADMIN_ROLE_ID, ROLE_ADMIN);
        userRoleMap.put(TUTOR_ROLE_ID, ROLE_TUTOR);
        userRoleMap.put(STUDENT_ROLE_ID, ROLE_STUDENT);

        studentSkillMap.put(STUDENT_LEVEL_NONE_ID, STUDENT_SKILL_NONE);
        studentSkillMap.put(STUDENT_LEVEL_START_ID, STUDENT_SKILL_START);
        studentSkillMap.put(STUDENT_LEVEL_ELEMENTARY_ID, STUDENT_SKILL_ELEMENTARY);
        studentSkillMap.put(STUDENT_LEVEL_INTERMEDIATE_ID, STUDENT_SKILL_INTERMEDIATE);
        studentSkillMap.put(STUDENT_LEVEL_ADVANCED_ID, STUDENT_SKILL_ADVANCED);

        tutorDegreeMap.put(TUTOR_DEGREE_NONE_ID, TUTOR_DEGREE_NONE);
        tutorDegreeMap.put(TUTOR_DEGREE_JUNIOR_ID, TUTOR_DEGREE_JUNIOR);
        tutorDegreeMap.put(TUTOR_DEGREE_MIDDLE_ID, TUTOR_DEGREE_MIDDLE);
        tutorDegreeMap.put(TUTOR_DEGREE_SENIOR_ID, TUTOR_DEGREE_SENIOR);
    }

    static UserInfoContainer getInstance() {
        return userInfoContainer;
    }
}
