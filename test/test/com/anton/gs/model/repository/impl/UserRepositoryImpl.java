package test.com.anton.gs.model.repository.impl;

import com.anton.gs.model.dao.UserDao;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.entity.user.UserCreator;
import com.anton.gs.model.exception.DaoException;


import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserDao {
    private static UserRepositoryImpl userRepository;
    private List<User> userList;
    private UserCreator userCreator;

    private UserRepositoryImpl() {
        userList = new ArrayList<>();
        userCreator = UserCreator.getInstance();
        userList.add(userCreator.createUser(
                1,
                "guitarschool.epam.user@gmail.com",
                "user",
                "student",
                "start",
                "beginner",
                true
        ));

        userList.add(userCreator.createUser(
                2,
                "guitarschool.epam.user2@gmail.com",
                "user2",
                "student",
                "start",
                "beginner",
                true
        ));

        userList.add(userCreator.createUser(
                3,
                "guitarschool.epam.use3r@gmail.com",
                "user3",
                "student",
                "start",
                "beginner",
                true
        ));

    }

    public static UserRepositoryImpl getInstance(){
        if(userRepository == null) {
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    @Override
    public int activateUser(String email) {
        int result = 0;
        for (User currentUser : userList) {
            if(currentUser.getEmail().equals(email)) {
               currentUser.setActive(true);
               result = 1;
            }
        }


        return result;
    }

    @Override
    public String findUserRoleById(Integer userId) {
        String role = null;
        for(User user : userList) {
            if(user.getUserId() == userId) {
                role = user.getUserRole();
            }
        }
        return role;
    }

    @Override
    public Integer findUserIdByEmail(String userEmail) {
        int userId = 0;
        for(User user : userList) {
            if(user.getEmail().equals(userEmail)) {
                userId = user.getUserId();
            }
        }
        return userId;
    }

    @Override
    public void addUser(String email, String password, String nickName, int roleId, int degree, int level) throws DaoException {

    }

    @Override
    public String findUserMail(String email) {
        String dbEmail = null;

        for(User user : userList) {
            if(user.getEmail().equals(email)) {
                dbEmail = email;
            }
        }
        return dbEmail;
    }

    @Override
    public String findUserPasswordByEmail(String emailFromDB) throws DaoException {
        return null;
    }

    @Override
    public User createUser(String userMail) throws DaoException {
        User user = null;
        for(User current : userList) {
            if(current.getEmail().equals(userMail)) {
                user = current;
            }
        }
        return user;
    }

    @Override
    public boolean isUserActive(String userMail) throws DaoException {
        return false;
    }

    @Override
    public boolean isUserBlocked(String email) throws DaoException {
        return false;
    }

    @Override
    public String findAvatarName(String email) throws DaoException {
        return null;
    }

    @Override
    public void addToAvatarTable(String userEmail) throws DaoException {

    }

    @Override
    public boolean updateAvatarFileName(String email, String fileName) throws DaoException {
        return false;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }
}
