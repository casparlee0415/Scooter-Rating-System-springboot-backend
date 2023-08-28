package com.scooter.Dao;

import com.scooter.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Query("select u.id from User u where u.email = :email")
    Optional<Integer> findIdByEmail(@Param("email") String email);
}
