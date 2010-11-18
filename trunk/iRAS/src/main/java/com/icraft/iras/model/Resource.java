package com.icraft.iras.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import javax.validation.constraints.Past;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public class Resource {

    @NotNull
    private String fullName;


//    @Past
//    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(style = "S-")
//    private Calendar dateOfBirth;

  
    private Integer yearsOfExperience;

    private double expectedSalary;

   
   private String emailAddress;

   
}
