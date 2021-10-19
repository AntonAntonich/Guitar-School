package com.anton.gs.model.validator;

import java.util.Map;

/**
 * The interface User validator.
 */
public interface UserValidator {
    /**
     * Validate form map.
     * comparing incoming values like patters with appropriate regular expression
     * setting message in map appropriating request attributes in jsp
     * @param userEmail        the user email
     * @param userPassword     the user password
     * @param repeatedPassword the repeated password
     * @param userNickName     the user nick name
     * @return the map
     */
    Map<String, String> validateForm(String userEmail,
                                            String userPassword,
                                            String repeatedPassword,
                                            String userNickName);
}
