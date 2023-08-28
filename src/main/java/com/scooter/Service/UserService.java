package com.scooter.Service;

import com.scooter.Dao.UserDao;
import com.scooter.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }
    public User findById(Integer id){
        return userDao.findById(id).orElse(null);
    }

    public Integer findIdByEmail(String email){ return userDao.findIdByEmail(email); }

    public User save(User user){
        return userDao.save(user);
    }

}
