package com.icraft.iras.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;

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


    @RequestMapping
    public String index(ModelMap modelMap, HttpServletRequest request) {
    	List<Lvl> skillLevel=new ArrayList<Lvl>();
    	Long id=(long) 1;
    	Vacancy vac=Vacancy.findVacancy(id);
    	Set<Skill> resourceSkill=new HashSet<Skill>();
    	resourceSkill=vac.getSkills();
    	skillLevel=Lvl.findAllLvls();
    	
    	modelMap.addAttribute("skilsList", resourceSkill);
    	modelMap.addAttribute("sklLevel", skillLevel);
    	skillsList= resourceSkill;
    	
        return "hresource/sub";
    }
    
    @RequestMapping(method= RequestMethod.POST,value ="save")
    public String saveMethod(ModelMap modelMap, HttpServletRequest request){
    	Resource resource=new Resource();
    	for(Object o:skillsList){
    		Skill skil=(Skill) o;
    		String skillName=skil.getName();
    		String skillValue=request.getParameter(skillName);
    	
    	}
    	resource.setAddress(request.getParameter("Address"));
    	resource.setFullName(request.getParameter("name"));
    	resource.setDateOfBirth(request.getParameter("birthDate"));
    	resource.setMobile(request.getParameter("mobile"));
    	resource.setEmailAddress(request.getParameter("email"));
    	resource.setRegion(request.getParameter("region"));
    	resource.setUniversity(request.getParameter("university"));
    	resource.setFaculty(request.getParameter("faculty"));
    	resource.setGrade(request.getParameter("grade"));
    	resource.setYearOfGraduate(request.getParameter("yearOfGraduate"));
    	resource.setCourse(request.getParameter("courses"));
    	resource.setCertificates(request.getParameter("certificates"));
    	resource.setOtherTechno(request.getParameter("other_techno"));
    	resource.setBlackBelt(request.getParameter("BlackBelt"));
    	resource.setCurrentEmployer(request.getParameter("Current_Employer"));
    	resource.setExpectedSalary(Double.parseDouble(request.getParameter("expected_salary")));
    	resource.setCurentJobTitle(request.getParameter("current_job_title"));
    	resource.setRoole(request.getParameter("role"));
    	resource.setAvailabilatyForWork(Integer.parseInt(request.getParameter("Availabilty_work_period")));
    	resource.setYearsOfExperience(Integer.parseInt(request.getParameter("num_experience")));
    	
    	
    	
    	
    	
    	return"hresource/index";
    }
    
}
