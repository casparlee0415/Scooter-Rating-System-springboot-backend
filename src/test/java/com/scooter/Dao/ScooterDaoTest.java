package com.scooter.Dao;

import com.scooter.Entity.Scooter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ScooterDaoTest {

    @Autowired
    private ScooterDao scooterDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll(){
        assertThat(scooterDao.findAll()).contains(scooterDao.findById(14).orElse(null));
    }
    @Test
    void findById() {
        assertEquals(14,
                scooterDao.findById(14).orElse(null).getId());
    }

    @Test
    void findByBrandId() {
        assertThat(scooterDao.findByBrandId(14).stream().filter(s->s.getBrand().getId()==14).count())
                .isGreaterThan(0);
    }

    @Test
    void findByBrandIdWhereNotEqualId() {
        assertThat(scooterDao.findByBrandIdWhereNotEqualId(14,14))
                .doesNotContain(scooterDao.findById(14).orElse(null));
    }
}