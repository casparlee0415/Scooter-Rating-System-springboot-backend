package com.scooter.Dao;

import com.scooter.Entity.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.scooter.Util.ServiceUtil.dateFormat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class CommentDaoTest{

    @Autowired
    private CommentDao commentDao;
    private Comment insertComment;
    private Comment updateComment;

    private int insertQuery(Comment comment){
         return commentDao.insert(comment.getScore(),
                 comment.getDescription(), dateFormat.format(comment.getTimestamp()),
                 comment.getScooter().getId(), comment.getUser().getId());

    }

    private int updateQuery(Comment comment){
        return commentDao.update(comment.getScore(), comment.getDescription(), comment.getId());
    }

    @BeforeEach
    void setUp() {
        insertComment = new Comment(5, "nice", 14, 1);
        insertQuery(insertComment);
        commentDao.findAll();
        List<Comment> commentList=commentDao.findAll();
        int id=commentList.get(commentList.lastIndexOf(insertComment)).getId();
        insertComment.setId(id);
        updateComment = insertComment.toBuilder().score(4).description("great").build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll(){
        assertThat(commentDao.findAll()).contains(insertComment);
    }
    @Test
    void findById(){
        assertThat(commentDao.findById(insertComment.getId()).orElse(null))
                .isEqualTo(insertComment);
    }
    @Test
    void findByScooterId() {
        assertThat(commentDao.findByScooterId(14)).contains(insertComment);
    }

    @Test
    void insert() {
        assertEquals(1,insertQuery(insertComment.setTimestamp()));
    }

    @Test
    void update() {
        assertEquals(1,updateQuery(updateComment));
    }
}