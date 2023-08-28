package com.scooter.Controller;

import com.scooter.Service.CommentService;
import com.scooter.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"","/"})
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @GetMapping(value="",params = {"scooterId"})
    @ResponseBody
    public Map<String,Object> findByScooterId(@RequestParam("scooterId")Integer scooterId){
        Map<String,Object> model=new LinkedHashMap<>();
        model.put("scooterId",scooterId);
        model.put("commentList",commentService.findByScooterId(scooterId));
        model.put("averageScore",commentService.getAverageScore(scooterId));
        return model;
    }

    @PostMapping("")
    public ResponseEntity<String> insert(@RequestBody Map<String,Object> data){
        Integer score=Integer.valueOf(data.get("score").toString());
        String description=data.get("description").toString();
        Integer scooterId=Integer.valueOf(data.get("scooterId").toString());
        Integer userId=Integer.valueOf(data.get("userId").toString());
        Comment comment=new Comment(score,description,scooterId,userId);
        int row=commentService.insert(comment);
        return ResponseEntity.ok(""+row+(row>1?" rows ":" row ")+"effected.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody Map<String,Object> data){
        Integer score=Integer.valueOf(data.get("score").toString());
        String description=data.get("description").toString();
        int row=commentService.update(score,description,id);
        return ResponseEntity.ok(""+row+(row>1?" rows ":" row ")+"effected.");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Integer id){
        commentService.deleteById(id);
    }


}
