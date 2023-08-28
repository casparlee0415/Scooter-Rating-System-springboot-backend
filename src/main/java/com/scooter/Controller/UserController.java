package com.scooter.Controller;

import com.scooter.Entity.User;
import com.scooter.Service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/user")
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ServletContext servletContext;
    @GetMapping({"","/"})
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id){
        User user=userService.findById(id);
        if(user==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping({"","/"})
    public ResponseEntity<User> insert(@RequestBody Map<String,Object> data) throws Exception {
        String email=data.get("email").toString();
        String name=data.get("name").toString();
        String imgUrl=data.get("imgUrl").toString();
        Integer id= userService.findIdByEmail(email);
        User user=new User(id,email,name,imgUrl);
        user=userService.save(user);
        return ResponseEntity.created(new URI(getContextPath()+"/api/user/"+user.getId())).body(user);

    }
    @PutMapping("/{id}")
    public ResponseEntity<User> save(@PathVariable("id") Integer id,@RequestBody Map<String,Object> data){
        if(userService.findById(id)==null) return ResponseEntity.notFound().build();
        String email=data.get("email").toString();
        String name=data.get("name").toString();
        String imgUrl=data.get("imgUrl").toString();
        User user=new User(id,email,name,imgUrl);
        return ResponseEntity.ok(userService.save(user));
    }

    private String getContextPath(){
        return servletContext.getContextPath();
    }

}
