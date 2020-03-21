package com.smartosc.mobile.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
//@TypeDef(
//        name = "json",
//        typeClass = JsonStringType.class
//)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProd;

//    @Type(type = "json")
//    @Column(columnDefinition = "json")
//    private ArrayList<String> image;
    private String image;

    private Long price;

    private String status;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Category category;
}
