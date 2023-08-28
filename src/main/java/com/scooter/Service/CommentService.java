package com.scooter.Service;

import com.scooter.Dao.CommentDao;
import com.scooter.Entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.scooter.Util.ServiceUtil.dateFormat;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> findAll(){
        return commentDao.findAll();
    }
    public Comment findById(Integer commentId){
        return commentDao.findById(commentId).orElse(null);
    }

    public List<Comment> findByScooterId(Integer scooterId){
        return commentDao.findByScooterId(scooterId);
    }

    public Double getAverageScore(Integer scooterId){
        List<Comment> commentList=findByScooterId(scooterId);
        double average = commentList.stream()
                .mapToDouble(item -> item.getScore().doubleValue())
                .average().orElse(0.0);
        average = Double.valueOf(String.format("%.1f",average));

        return average;
    }

    public int update(Integer score,String description,Integer commentId){
        return commentDao.update(score,description,commentId);
    }

    public int insert(Comment comment){
        Integer score=comment.getScore();
        String description= comment.getDescription();
        String timestamp=dateFormat.format(comment.getTimestamp());
        Integer scooterId=comment.getScooter().getId();
        Integer userId=comment.getUser().getId();
        return commentDao.insert(score,description,timestamp,scooterId,userId);
    }

    public void deleteById(Integer id){
        commentDao.deleteById(id);
    }

}
