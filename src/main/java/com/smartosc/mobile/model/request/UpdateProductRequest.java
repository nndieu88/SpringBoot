package com.smartosc.mobile.model.request;

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

    private String model;

    private ArrayList<String> image;

    private Long price;

    private String status;

    private String description;

}
