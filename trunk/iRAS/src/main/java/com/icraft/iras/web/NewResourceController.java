package com.icraft.iras.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;

import com.icraft.iras.model.Lvl;
import com.icraft.iras.model.Resource;
import com.icraft.iras.model.ResourceSkillLevel;
import com.icraft.iras.model.Skill;
import com.icraft.iras.model.Vacancy;

@RequestMapping("/newresource/**")
@Controller
public class NewResourceController {
    Set <Skill> skillList=new HashSet<Skill>();
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

   /* @RequestMapping
    public String index() {
        return "newresource/index";
    }*/
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String newRes(@PathVariable(value="id") long vacId,ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    Vacancy vac=Vacancy.findVacancy(vacId);
    skillList=vac.getSkills();
    List skilLevl=Lvl.findAllLvls();
    modelMap.addAttribute("resource",new Resource());
    modelMap.addAttribute("vacancy", vac);
    modelMap.addAttribute("skill", vac.getSkills());
    modelMap.addAttribute("lvl",skilLevl);
    return"newresource/new";
    }
    
    
    
    
    
    @RequestMapping(method=RequestMethod.POST)
    public String saveResource(Resource resource,HttpServletRequest request,ModelMap model,BindingResult result){
    
    	if(result.hasErrors()){
    		
    	model.addAttribute("resource",resource);
    	return"newresource/new";
    
    	}
    	
    	List <ResourceSkillLevel> resSkillLevl=new ArrayList<ResourceSkillLevel>();
    
    	for(Skill o:skillList){
    	Skill sk=o;
    	ResourceSkillLevel resSkilLvl=new ResourceSkillLevel();
    	Lvl lvl=new Lvl();
    	String skillName=sk.getName();
    	if(request.getParameter(skillName)==null){
			return"newresource/error";
		}
    	
    	int lvlId=Integer.parseInt(request.getParameter(skillName));
    	long skilLvlId=(long)lvlId;
    	lvl=Lvl.findLvl(skilLvlId);
    	resSkilLvl.setLvl(lvl);
    	resSkilLvl.setSkill(sk);
    	resSkilLvl.setResource(resource);
    	resSkillLevl.add(resSkilLvl);
    }
    	
    resource.setResourceSkillLevels(resSkillLevl);
    resource.setBlackBelt(request.getParameter("blackBelt"));
    resource.setYearsOfExperience(Integer.parseInt(request.getParameter("num_experience")));
    resource.persist();
    
    return"newresource/index";	
    }
}
