package com.scooter.Service;

import com.scooter.Dao.UserDao;
import com.scooter.Entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserServiceTest{
    @Mock
    protected UserDao userDao;
    @InjectMocks
    protected UserService userService;

    protected User user;

    @BeforeEach
    void setUp() {
        user=new User("test","test","test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        userService.findById(user.getId());
        verify(userDao,times(1)).findById(user.getId());
    }

    @Test
    void save() {
        userService.save(user);
        verify(userDao,times(1)).save(user);
    }

}