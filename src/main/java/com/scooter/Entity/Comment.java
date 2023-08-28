package com.scooter.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.ParseException;
import java.util.Date;

import static com.scooter.Util.ServiceUtil.dateFormat;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "comment")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer score;
    @Column(name="description")
    private String description;
    @Column(name="timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    @ManyToOne
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    public Comment(Integer score,String description,Integer scooterId,Integer userId){
        setComment(score,description,scooterId,userId);
    }

    public Comment setComment(Integer score,String description,Integer scooterId,Integer userId){
        this.scooter=new Scooter().setId(scooterId);
        this.user=new User().setId(userId);
        setTimestamp();

        this.id=null;
        this.score=score;
        this.description=description;


        return this;
    }

    public Comment setTimestamp(){
        try {
            this.timestamp=dateFormat.parse(dateFormat.format(new Date()));
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return this;
    }
    @Override
    public boolean equals(Object o){
        Comment comment=(Comment) o;
        if(!this.score.equals(comment.score)) return false;
        if(!this.description.equals(comment.description)) return false;
        if(!dateFormat.format(this.timestamp).equals(dateFormat.format(comment.timestamp))) return false;
        if(!this.scooter.getId().equals(comment.scooter.getId())) return false;
        if(!this.user.getId().equals(comment.user.getId())) return false;

        return true;

    }


}
