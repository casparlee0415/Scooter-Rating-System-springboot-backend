package com.scooter.Service;

import com.scooter.Dao.ScooterDao;
import com.scooter.Entity.Brand;
import com.scooter.Entity.Scooter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ScooterServiceTest {
    @Mock
    protected ScooterDao scooterDao;
    @InjectMocks
    protected ScooterService scooterService;
    @BeforeEach
    void setUp() {
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll(){
        scooterService.findAll();
        verify(scooterDao,times(1)).findAll();
    }
    @Test
    void findById() {
        scooterService.findById(14);
        verify(scooterDao,times(1)).findById(14);
    }

    @Test
    void findByBrandId() {
        scooterService.findByBrandId(14);
        verify(scooterDao,times(1)).findByBrandId(14);
    }

    @Test
    void findByBrandIdWhereNotEqualId() {
        scooterService.findByBrandIdWhereNotEqualId(14,14);
        verify(scooterDao,times(1)).findByBrandIdWhereNotEqualId(14,14);
    }
}