package com.codecool.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@NoArgsConstructor
public class Cart{
    @Getter
    private HashMap<String,Integer> products;


}
