package com.icraft.iras.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity
public class JobTitle {

    @NotNull
    private String title;

    @NotNull
    private Double averageRate;

}
