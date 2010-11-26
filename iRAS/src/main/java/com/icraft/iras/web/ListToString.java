package com.icraft.iras.web;

import java.util.List;

import org.springframework.core.convert.converter.Converter;


public  class ListToString implements Converter<List, String> {

    public String convert(List source) {
        return source.toString();
    }

}