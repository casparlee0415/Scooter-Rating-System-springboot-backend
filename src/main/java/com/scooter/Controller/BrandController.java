package com.scooter.Controller;

import com.scooter.Entity.Brand;
import com.scooter.Service.BrandService;
import com.scooter.Service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/brand")
public class BrandController{
    @Autowired
    private BrandService brandService;
    @GetMapping({"","/"})
    public ResponseEntity<List<Brand>> findAll(){
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable("id") Integer id){
        Brand brand=brandService.findById(id);
        if(brand==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(brand);
    }
}
