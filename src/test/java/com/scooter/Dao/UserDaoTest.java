package com.scooter.Dao;

import com.scooter.Entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    private User user;
    @BeforeEach
    void setUp() {
        user=new User("test","test","test");
        user=userDao.save(user);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll(){
        assertThat(userDao.findAll()).contains(user);
    }
    @Test
    void findIdByEmail() {
        assertEquals(user.getId(),userDao.findIdByEmail(user.getEmail()));
    }

    @Test
    void findById(){
        assertEquals(user,userDao.findById(user.getId()).orElse(null));
    }

    @Test
    void save(){
        User user2=user.toBuilder().email("test2").name("test2").imgUrl("test2").build();
        assertEquals(user2,userDao.save(user2));
    }
}