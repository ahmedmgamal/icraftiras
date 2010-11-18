package com.icraft.iras.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import com.icraft.iras.model.Resource;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.icraft.iras.model.Skill;
import com.icraft.iras.model.Lvl;

@RooJavaBean
@RooToString
@RooEntity
public class ResourceSkillLevel {

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private Skill skill;

    @ManyToOne
    private Lvl lvl;
}
