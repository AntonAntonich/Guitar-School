package test.com.anton.gs.model.service;

import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.DaoException;
import com.anton.gs.model.exception.ServiceException;
import test.com.anton.gs.model.repository.impl.UserRepositoryImpl;
import com.anton.gs.model.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepositoryImpl repository;
    private AutoCloseable closeable;
    @InjectMocks
    private UserServiceImpl service;

    @BeforeEach
    void initService(){
        closeable = MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void activateUserPositive() throws ServiceException {
        when(repository.activateUser("guitarschool.epam.user@gmail.com")).thenReturn(1);
        boolean result = service.activateUser("guitarschool.epam.user@gmail.com");
        assertTrue(result);
    }

    @Test
    void activateUserNegative() throws ServiceException {
        when(repository.activateUser("guitarschool.epam.user@gmail.com")).thenReturn(2);
        boolean result = service.activateUser("guitarschool.epam.user@gmail.com");
        assertFalse(result);
    }

    @Test
    void isUserExistPositive() throws ServiceException{
        when(repository.findUserMail("guitarschool.epam.user@gmail.com"))
                .thenReturn("guitarschool.epam.user@gmail.com");
        boolean result = service.isUserExist("guitarschool.epam.user@gmail.com");
        assertTrue(result);
    }

    @Test
    void isUserExistNegative() throws ServiceException{
        when(repository.findUserMail("guitarschool.epam.user@gmail.co"))
                .thenReturn("guitarschool.epam.user@gmail.com");
        boolean result = service.isUserExist("guitarschool.epam.user@gmail.com");
        assertFalse(result);
    }


    @Test
    void findUserPositive() throws ServiceException, DaoException {
        User actual =  repository.createUser("guitarschool.epam.user2@gmail.com");
        User user = service.findUser("guitarschool.epam.user2@gmail.com");
        assertEquals(actual, user);
    }

    @Test
    void findUserNull() throws ServiceException, DaoException {
        given(repository.createUser("guitarschool.epam.user2@gmail.co")).willReturn(null);
        User user = service.findUser("guitarschool.epam.user2@gmail.com");
        assertNull(user);
    }


}