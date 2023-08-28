package com.scooter.Controller;

import com.scooter.Service.CommentService;
import com.scooter.Entity.*;
import com.scooter.Service.ScooterService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/comment")
@CrossOrigin
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ScooterService scooterService;

    @GetMapping({"","/"})
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable("id")Integer id){
        Comment comment=commentService.findById(id);
        if(comment==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(comment);
    }

    @GetMapping(value="",params = {"scooterId"})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> findByScooterId(@RequestParam("scooterId")Integer scooterId){
        Map<String,Object> model=new LinkedHashMap<>();
        if(scooterService.findById(scooterId)==null) return ResponseEntity.notFound().build();

        model.put("scooterId",scooterId);
        model.put("commentList",commentService.findByScooterId(scooterId));
        model.put("averageScore",commentService.getAverageScore(scooterId));
        return ResponseEntity.ok(model);
    }

    @PostMapping("")
    public ResponseEntity<Comment> insert(@RequestBody Map<String,Object> data) throws Exception {
        Integer score=Integer.valueOf(data.get("score").toString());
        String description=data.get("description").toString();
        Integer scooterId=Integer.valueOf(data.get("scooterId").toString());
        Integer userId=Integer.valueOf(data.get("userId").toString());
        Comment comment=new Comment(score,description,scooterId,userId);
        int row=commentService.insert(comment);
        if(row!=1) return ResponseEntity.badRequest().build();

        List<Comment> commentList=commentService.findAll();
        comment=commentList.get(commentList.lastIndexOf(comment));
        URI location=new URI(getContextPath()+"/api/comment/"+comment.getId());
        return ResponseEntity.created(location).body(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") Integer id,@RequestBody Map<String,Object> data) throws Exception {
        Comment comment=commentService.findById(id);
        if(comment==null) return ResponseEntity.notFound().build();

        Integer score=Integer.valueOf(data.get("score").toString());
        String description=data.get("description").toString();

        comment=comment.toBuilder().score(score).description(description).build();
        int row=commentService.update(comment.getScore(),comment.getDescription(),comment.getId());
        if(row!=1) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Integer id){
        commentService.deleteById(id);
        return ResponseEntity.ok().body("Successfully Deleted.");
    }

    private String getContextPath(){
        return servletContext.getContextPath();
    }

}
