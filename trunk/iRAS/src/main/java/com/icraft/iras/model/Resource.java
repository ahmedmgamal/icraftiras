package com.icraft.iras.model;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.Past;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import com.icraft.iras.model.Skill;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findResourcesByEmailAddress" })

public class Resource {

    @NotNull
    private String fullName;

    private Integer yearsOfExperience;

    private double expectedSalary;

    private String emailAddress;

    @OneToMany(cascade=CascadeType.ALL )
    private List<ResourceSkillLevel> resourceSkillLevels ;
    
    @javax.persistence.Lob

    public String cvText;
   
    
    
    public static TypedQuery<Resource> findResourcesInEmailAddressSet( Set<String> emailAddressSet) {
        if (emailAddressSet == null || emailAddressSet.size() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        EntityManager em = Resource.entityManager();
        TypedQuery<Resource> q = em.createQuery("SELECT Resource FROM Resource AS resource WHERE resource.emailAddress in (:emailAddressSet) ", Resource.class);
       
	       
        
       q.setParameter("emailAddressSet",emailAddressSet);
       
     //  System.out.println(q.getResultList());
      
        return q;
    }
    
}
