package com.scooter.Dao;

import com.scooter.Entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScooterDao extends JpaRepository<Scooter,Integer> {

    @Query(value = "select s from Scooter s where s.brand.id=?1")
    List<Scooter> findByBrandId(Integer brandId);

    @Query(value = "select s from Scooter s where s.brand.id=?1 AND s.id<>?2 " +
            "ORDER BY s.price ASC LIMIT 3")
    List<Scooter> findByBrandIdWhereNotEqualId(Integer brandId,Integer id);
}
