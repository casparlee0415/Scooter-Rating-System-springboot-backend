package com.scooter.Service;

import com.scooter.Dao.CommentDao;
import com.scooter.Entity.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CommentServiceTest {
    @Mock
    protected CommentDao commentDao;
    @InjectMocks
    protected CommentService commentService;

    protected Comment insertComment;
    protected Comment editComment;
    @BeforeEach
    void setUp(){
        insertComment=new Comment(5,"nice",14,1);
        editComment=insertComment.setId(1);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void findByScooterId(){
        commentService.findByScooterId(14);
        verify(commentDao,times(1)).findByScooterId(14);
    }
    @Test
    void update(){
        commentService.update(editComment.getScore(),editComment.getDescription(),editComment.getId());
        verify(commentDao,times(1)).update(5,"nice",1);
    }

    @Test
    void insert(){
        commentService.insert(insertComment);
        verify(commentDao,times(1)).insert(anyInt(),anyString(),anyString(),anyInt(),anyInt());
    }

    @Test
    void delete(){
        commentService.deleteById(editComment.getId());
        verify(commentDao,times(1)).deleteById(editComment.getId());
    }

}
