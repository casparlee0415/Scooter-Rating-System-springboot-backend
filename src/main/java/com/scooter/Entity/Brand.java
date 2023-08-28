package com.scooter.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Data
@Table(name = "brand")
@Accessors(chain = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Brand{
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="brand_name")
    private String name;

}
