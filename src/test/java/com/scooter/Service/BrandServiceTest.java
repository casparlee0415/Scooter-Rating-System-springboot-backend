package com.scooter.Service;

import com.scooter.Dao.BrandDao;
import com.scooter.Entity.Brand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class BrandServiceTest {
    @Mock
    protected BrandDao brandDao;
    @InjectMocks
    protected BrandService brandService;
    protected Brand brand;
    @BeforeEach
    void setUp(){
        brand=new Brand(14,"Aeonmetor 宏佳騰");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        brandService.findAll();
        verify(brandDao,times(1)).findAll();
    }

    @Test
    void findById() {
        brandService.findById(14);
        verify(brandDao,times(1)).findById(14);
    }


}