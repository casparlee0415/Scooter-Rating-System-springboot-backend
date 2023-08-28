package com.scooter.Dao;

import com.scooter.Entity.Brand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class BrandDaoTest{
    @Autowired
    private BrandDao brandDao;

    private Brand brand;
    @BeforeEach
    void setUp() {
        brand=new Brand(34,"BMW");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll(){
        assertThat(brandDao.findAll()).contains(brand);
    }

    @Test
    void findById(){
        assertThat(brandDao.findById(brand.getId()).orElse(null)).isEqualTo(brand);
    }
}