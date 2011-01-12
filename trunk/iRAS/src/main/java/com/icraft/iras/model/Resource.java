package com.icraft.iras.model;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;
import java.util.Set;
import javax.persistence.CascadeType;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findResourcesByEmailAddress" })
public class Resource {

    @NotNull
    private String fullName;

    private Integer yearsOfExperience;
    
    @NotNull
    private double expectedSalary;
    
    @NotNull
    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ResourceSkillLevel> resourceSkillLevels;

    @javax.persistence.Lob
    public String cvText;
    
    @NotNull
    private String address;
   
    @NotNull
    private String region;
    
    @NotNull
    private String mobile;
   
    @NotNull
    private String dateOfBirth;
    
    @NotNull
    private String faculty;
    
    @NotNull
    private String yearOfGraduate;
   
    @NotNull
    private String grade;

    private String course;

    private String certificates;

    private String otherTechno;
    
    @NotNull
    private String blackBelt;

    private String currentEmployer;

    private String curentJobTitle;
    
    @NotNull
    private Integer availabilatyForWork;
    
    @NotNull
    private String roole;
    
    @NotNull
    private String university;


    public static TypedQuery<Resource> findResourcesInEmailAddressSet(Set<String> emailAddressSet) {
     
    	if (emailAddressSet == null || emailAddressSet.size() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        EntityManager em = Resource.entityManager();
        TypedQuery<Resource> q = em.createQuery("SELECT Resource FROM Resource AS resource WHERE resource.emailAddress in (:emailAddressSet) ", Resource.class);
        q.setParameter("emailAddressSet", emailAddressSet);
        return q;
      
    }
}
