package com.scooter.Entity;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
@Table(name = "scooter")
@Accessors(chain = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Scooter {
    @Id
    @Column(name = "scooter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="scooter_name")
    private String name;
    @Column(name = "scooter_type")
    private String type;
    @Column
    private Double price;
    @Column(name = "engine_type")
    private String engine;
    @Column
    private String transmission;
    @Column
    private String displacement;
    @Column
    private String performance;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
