package com.scooter.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.scooter.Entity.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandDao extends JpaRepository<Brand,Integer> {
}
