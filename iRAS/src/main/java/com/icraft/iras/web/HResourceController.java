package com.icraft.iras.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.el.ELParseException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

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
    	String fileText = new String(file.getBytes());
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
    	if(request.getParameter("Address")==""||request.getParameter("Availabilty_work_period")==""||request.getParameter("BlackBelt")==""||request.getParameter("birthDate")==""||request.getParameter("email")==""||request.getParameter("expected_salary")==""||request.getParameter("faculty")==""||request.getParameter("name")==""||request.getParameter("grade")==""||request.getParameter("mobile")==""||request.getParameter("region")==""||request.getParameter("role")==""||request.getParameter("university")==""||request.getParameter("yearOfGraduate")==""||request.getParameter("num_experience")==""||resource.getResourceSkillLevels()==null||request.getParameter("file")==""){
    		return "hresource/error";
    	}
    	
    	//set resource variabls
    	resource.setCvText(fileText);
    	resource.setAddress(request.getParameter("Address"));
    	resource.setFullName(request.getParameter("name"));
    	resource.setDateOfBirth(request.getParameter("birthDate"));
    	
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
