package com.icraft.iras.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;

import com.icraft.iras.model.Resource;

@RequestMapping("/technosearch/**")
@Controller
public class TechnoSearchController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "technosearch/techSearch";
    }
    @RequestMapping(method=RequestMethod.POST,value="ss")
    public String getSearch(@RequestParam("techno")String searchWord, ModelMap model,HttpServletRequest request){
    	List<Resource> resourceList=new ArrayList<Resource>();
    	List<Resource> searchList=new ArrayList<Resource>();
    	resourceList=Resource.findAllResources();
    	for(Object o:resourceList){
    		Resource resource=(Resource) o;
    		String cvs=resource.getCvText();
    		String cv=cvs.toLowerCase();
    		int index=cv.indexOf(searchWord);
    		if(index!=-1){
    			searchList.add(resource);
    		}
    		
    		
    	}
    	model.addAttribute("listShow", searchList);
    	return"technosearch/resultSearch";
    }
}
