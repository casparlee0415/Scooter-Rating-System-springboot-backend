package com.scooter.Entity;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.Accessors;

@Entity
@Data
@Table(name = "user")
@Accessors(chain = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_email")
    private String email;
    @Column(name="user_name")
    private String name;
    @Column(name="img_url")
    private String imgUrl;

    public User(String email,String name,String imgUrl){
        setUser(email,name,imgUrl);
    }

    public User setUser(String email,String name,String imgUrl){
        this.id=null;
        this.email=email;
        this.name=name;
        this.imgUrl=imgUrl;
        return this;
    }

    @Override
    public boolean equals(Object o){
        User user=(User)o;
        if(!this.email.equals(user.email)) return false;
        if(!this.name.equals(user.name)) return false;
        if(!this.imgUrl.equals(user.imgUrl)) return false;
        return true;
    }
}
