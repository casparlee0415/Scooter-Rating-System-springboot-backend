package com.scooter.Dao;

import com.scooter.Entity.Comment;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment,Integer> {
    String insertQuery="insert into comment " +
            "(score, description, timestamp,scooter_id,user_id) " +
            "Values( :score, :description, :timestamp, :scooterId, :userId )";
    String updateQuery="update comment set score = :score, description = :description" +
            " where comment_id = :commentId";
    @Query(value = "select c from Comment c where c.scooter.id = :scooterId")
    List<Comment> findByScooterId(@Param("scooterId") Integer scooterId);

    @Modifying
    @Transactional
    @Query(value = insertQuery,nativeQuery = true)
    int insert(@Param("score") Integer score,@Param("description") String description,
                @Param("timestamp") String timestamp,@Param("scooterId") Integer scooterId,
                @Param("userId") Integer userId);
    @Modifying
    @Transactional
    @Query(value = updateQuery,nativeQuery = true)
    int update(@Param("score") Integer score,@Param("description")String description,
                @Param("commentId") Integer commentId);
}
