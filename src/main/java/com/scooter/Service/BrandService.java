package com.scooter.Service;

import com.scooter.Dao.BrandDao;
import com.scooter.Entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService {
    @Autowired
    private BrandDao brandDao;

    public List<Brand> findAll() {
        return brandDao.findAll();
    }

    public Brand findById(Integer id){
        return brandDao.findById(id).orElse(null);
    }

}
