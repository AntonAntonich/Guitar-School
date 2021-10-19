package com.anton.gs.model.entity.user.repository;

import com.anton.gs.model.exception.RepositoryException;

import java.util.Map;

public class UserRepositoryImpl implements UserRepository <Integer, String>{
    private static final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    public static final String ROLE = "ROLE";
    public static final String DEGREE = "DEGREE";
    public static final String SKILL = "SKILL";
    private final UserInfoContainer userInfoContainer = UserInfoContainer.getInstance();
    private Map<Integer, String> map;

    private UserRepositoryImpl(){}

    public static UserRepositoryImpl getInstance(){
        return userRepository;
    }

    @Override
    public String findValue(String searchType, String value) throws RepositoryException {
       detectMapType(searchType);
        String targetValue = null;
        if (value != null) {
            for (String tmp : map.values()) {
                if (tmp.equals(value.toUpperCase())) {
                    targetValue = tmp;
                }
            }
        }

        if (targetValue == null) {
            throw new RepositoryException();
        }
        return targetValue;
    }

    @Override
    public String findValueById(String searchType, Integer valueId) throws RepositoryException {
        detectMapType(searchType);
        String targetValue = null;
        if (valueId != null) {
            targetValue = map.get(valueId);
        }
        if (targetValue == null) {
            throw new RepositoryException();
        }
        return targetValue;
    }

    @Override
    public Integer findValueId(String searchType, String value) throws RepositoryException {
        detectMapType(searchType);
        Integer roleId = null;
        for (Integer i : map.keySet()) {
            if (map.get(i).equals(value.toUpperCase())) {
                roleId = i;
            }
        }
        if (roleId == null) {
            throw new RepositoryException();
        }
        return roleId;
    }

    private void detectMapType(String searchType) throws RepositoryException {
        switch (searchType.toUpperCase()){
            case ROLE:
                map = userInfoContainer.userRoleMap;
                break;
            case DEGREE:
                map = userInfoContainer.tutorDegreeMap;
                break;
            case SKILL:
                map = userInfoContainer.studentSkillMap;
                break;
            default:
                throw new RepositoryException();
        }
    }
}
