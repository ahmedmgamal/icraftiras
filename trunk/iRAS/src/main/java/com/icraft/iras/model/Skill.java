package com.icraft.iras.model;

import javax.persistence.Entity;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findSkillsByName" })
 

public class Skill {

    private String name;
}
