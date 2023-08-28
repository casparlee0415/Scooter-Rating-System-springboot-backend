package com.scooter.Controller;

import com.scooter.Entity.User;
import com.scooter.Service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/user")
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"","/"})
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping({"","/"})
    public ResponseEntity<User> save(@RequestBody Map<String,Object> data){
        String email=data.get("email").toString();
        String name=data.get("name").toString();
        String imgUrl=data.get("imgUrl").toString();
        Integer id= userService.findIdByEmail(email);
        User user=new User(id,email,name,imgUrl);

        return ResponseEntity.ok(userService.save(user));

    }
}
