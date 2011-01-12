package com.icraft.iras.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import java.util.Set;
import com.icraft.iras.model.Skill;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class Vacancy {

    private String jobTitle;

    private String description;

    private String qualifications;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<Skill>();
}
