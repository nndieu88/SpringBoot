package com.smartosc.mobile.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProd;

    private String model;

    private String manufacturer;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private ArrayList<String> image;

    private Long price;

    private String status;

    private String decription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
