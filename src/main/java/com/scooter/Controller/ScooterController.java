package com.scooter.Controller;

import com.scooter.Entity.Brand;
import com.scooter.Entity.Scooter;
import com.scooter.Service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/scooter")
public class ScooterController {
    @Autowired
    private ScooterService scooterService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<List<Scooter>> findAll(){
        return ResponseEntity.ok(scooterService.findAll());
    }
    @GetMapping(value = "",params = {"brandId"})
    public ResponseEntity<Map<String,Object>> findByScooterId(@RequestParam(value = "brandId")Integer brandId){
        Map<String,Object> model=new LinkedHashMap<>();
        List<Scooter> scooterList=scooterService.findByBrandId(brandId);
        if(scooterList.isEmpty()) return ResponseEntity.notFound().build();
        model.put("brandId",brandId);
        model.put("scooterList", scooterList);
        return ResponseEntity.ok(model);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> scooter(@PathVariable("id")Integer id){
        int scooterId=id;
        Map<String,Object> model=new LinkedHashMap<>();
        Scooter scooter=scooterService.findById(scooterId);
        if(scooter==null) return ResponseEntity.notFound().build();
        Brand brand=scooter.getBrand();
        List<Scooter> otherScooterList=scooterService.findByBrandIdWhereNotEqualId(brand.getId(),scooterId);

        model.put("scooter",scooter);
        model.put("otherScooterList",otherScooterList);

        return ResponseEntity.ok(model);
    }




}
