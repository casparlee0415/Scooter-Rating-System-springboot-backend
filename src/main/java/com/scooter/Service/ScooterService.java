package com.scooter.Service;

import com.scooter.Dao.ScooterDao;
import com.scooter.Entity.Scooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {
    @Autowired
    private ScooterDao scooterDao;

    public List<Scooter> findAll(){
        return scooterDao.findAll();
    }
    public Scooter findById(Integer id){
        return scooterDao.findById(id).orElse(null);
    }

    public List<Scooter> findByBrandId(Integer brandId){
        return scooterDao.findByBrandId(brandId);
    }

    public List<Scooter> findByBrandIdWhereNotEqualId(Integer brandId,Integer id){
        return scooterDao.findByBrandIdWhereNotEqualId(brandId,id);
    }

}
