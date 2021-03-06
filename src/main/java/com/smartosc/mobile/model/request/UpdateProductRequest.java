package com.smartosc.mobile.model.request;

import com.smartosc.mobile.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private String nameProd;

    private Category category;

    private String image;

    private Long price;

    private String status;

    private String description;

    private Long cate_id;

}
