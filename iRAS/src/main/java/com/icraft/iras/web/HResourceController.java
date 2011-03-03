package com.icraft.iras.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.icraft.iras.model.Lvl;
import com.icraft.iras.model.Resource;
import com.icraft.iras.model.ResourceSkillLevel;
import com.icraft.iras.model.Skill;
import com.icraft.iras.model.Vacancy;

@RequestMapping("/hresource/**")
@Controller
public class HResourceController {
	Set<Skill> skillsList=new HashSet<Skill>();
	
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(value="/{Id}")
    public String index(@PathVariable("Id") long id,ModelMap modelMap, HttpServletRequest request) {
    	List<Lvl> skillLevel=new ArrayList<Lvl>();
    	Vacancy vac=Vacancy.findVacancy(id);
    	Set<Skill> resourceSkill=new HashSet<Skill>();
    	resourceSkill=vac.getSkills();
    	skillLevel=Lvl.findAllLvls();
    	String describe= vac.getDescription();
    	String jobName=	vac.getJobTitle();
    	modelMap.addAttribute("skilsList", resourceSkill);
    	modelMap.addAttribute("sklLevel", skillLevel);
    	modelMap.addAttribute("job_name", jobName);
    	modelMap.addAttribute("job_describtion",describe );
    	modelMap.addAttribute("job_skills",resourceSkill);
    	skillsList= resourceSkill;
    	
        return "hresource/sub";
    }
    
    @RequestMapping(method= RequestMethod.POST,value ="save")
    public String saveMethod(@RequestParam("file") MultipartFile file,ModelMap modelMap, HttpServletRequest request) throws IOException{
    	
    	List <ResourceSkillLevel> resSkillLevl=new ArrayList<ResourceSkillLevel>();
    			String cvText=null;
    			

    			
    			
    	    	
    			//handel cv
    	    	
    		   	 InputStream cv=file.getInputStream(); 
    				AutoDetectParser parser = new AutoDetectParser();
    				Metadata metadata = new Metadata();
    				ContentHandler handler = new BodyContentHandler();
    			
    					try {
    						parser.parse(cv, handler, metadata);
    						cvText=handler.toString();
    						if(cvText.equals("\n")||cvText.equals("")){
    							return"hresource/errorCv";
    						}
    					}
    					catch (SAXException e) {
    						
    					return"hresource/errorCv";
    					} catch (TikaException e) {
    						
    						return"hresource/errorCv";
    					}
    					
    						catch(FileNotFoundException ex){
    							return"hresource/errorCv";
    						}
    						String fileName=file.getOriginalFilename();
    						String directory="C:\\Documents and Settings\\"+fileName;
    						FileOutputStream fs=new FileOutputStream(directory);
    						fs.write(file.getBytes());
    						fs.flush();
    						fs.close();
    						
    				        
    				       
    				    
    				       
    				        
    				       
    				       
    				        
    				       
    	//resource object
    	
    	Resource resource=new Resource();
    	
    	
    	
    
    
    	
	//set resource skill level list
    	
    	for(Object o:skillsList){
    		Skill skil=(Skill) o;
    		ResourceSkillLevel resourceSkilLevel=new ResourceSkillLevel();
    		Lvl levl=new Lvl();
    		String skillName=skil.getName();
    		if(request.getParameter(skillName)==null){
    			return"hresource/error";
    		}
    		
    	
    		else{
    		String skillValue=request.getParameter(skillName);
    		int skillLevelId=Integer.parseInt(skillValue);
    		long levelId=(long)skillLevelId;
    		levl=Lvl.findLvl(levelId);
    		resourceSkilLevel.setLvl(levl);
    		resourceSkilLevel.setSkill(skil);
    		resourceSkilLevel.setResource(resource);
    		resSkillLevl.add(resourceSkilLevel);
    		}
    	}
    	
    	
    	resource.setResourceSkillLevels(resSkillLevl);
   
    	
    	//validate
    	if(request.getParameter("Address").equals("")||request.getParameter("Availabilty_work_period").equals("")||request.getParameter("BlackBelt")==null||request.getParameter("birthDate").equals("")||request.getParameter("email").equals("")||request.getParameter("expected_salary").equals("")||request.getParameter("faculty").equals("")||request.getParameter("name").equals("")||request.getParameter("grade").equals("")||request.getParameter("mobile").equals("")||request.getParameter("region").equals("")||request.getParameter("role").equals("")||request.getParameter("university").equals("")||request.getParameter("yearOfGraduate").equals("")||request.getParameter("num_experience")==null||resource.getResourceSkillLevels()==null){
    		return "hresource/error";
    	}
    	
    	
    
    	
    	//set resource variabls
    	resource.setCvText(cvText);
    	resource.setAddress(request.getParameter("Address"));
    	resource.setFullName(request.getParameter("name"));
    	String date=request.getParameter("birthDate");
    	SimpleDateFormat df=new SimpleDateFormat("dd/mm/yyyy", Locale.US);
    	try{
    	Date myBirthdate=df.parse(date);
    	resource.setDateOfBirth(myBirthdate);
    	}catch(Exception ex){
    		return"hresource/dateError";
    	}
    	
    	resource.setEmailAddress(request.getParameter("email"));
    	resource.setRegion(request.getParameter("region"));
    	resource.setUniversity(request.getParameter("university"));
    	resource.setFaculty(request.getParameter("faculty"));
    	resource.setGrade(request.getParameter("grade"));
    	try{
    		resource.setMobile(Integer.parseInt(request.getParameter("mobile")));
    		resource.setYearOfGraduate(Integer.parseInt(request.getParameter("yearOfGraduate")));	
    		resource.setYearsOfExperience(Integer.parseInt(request.getParameter("num_experience")));
    		resource.setAvailabilatyForWork(Integer.parseInt(request.getParameter("Availabilty_work_period")));
    		resource.setExpectedSalary(Double.parseDouble(request.getParameter("expected_salary")));
    	}catch(Exception ea){
    		return"hresource/num_error";
    	}
    	
    	resource.setCourse(request.getParameter("courses"));
    	resource.setCertificates(request.getParameter("certificates"));
    	resource.setOtherTechno(request.getParameter("other_techno"));
    	resource.setBlackBelt(request.getParameter("BlackBelt"));
    	resource.setCurrentEmployer(request.getParameter("Current_Employer"));
    	
    	resource.setCurentJobTitle(request.getParameter("current_job_title"));
    	resource.setRoole(request.getParameter("role"));
    	
    	
    	
    
    	resource.persist();
    
    	
    
    	
    	
    	modelMap.addAttribute("member_name",resource.getFullName() );
    	
    	    	return"hresource/index";
    }
       
		}
