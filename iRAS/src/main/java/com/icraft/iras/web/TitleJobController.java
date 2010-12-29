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

import com.icraft.iras.model.JobTitle;

@RequestMapping("/titlejob/**")
@Controller
public class TitleJobController {

    @RequestMapping(method = RequestMethod.POST, value ="calc")
    public String get(@RequestParam("titles")long[] jobTitles,@RequestParam("number")String[] resourceNumber ,ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    List<String> requiredJobTitle=new ArrayList<String>();
    List<String> required=new ArrayList<String>();
    List<Double>  houreRate=new ArrayList<Double>();
    List<Double> houre=new ArrayList<Double>();
    	int i;
    	Double sum=0.0;
    	for(i=0;i<jobTitles.length;i++){
    		long v=jobTitles[i];
    		JobTitle job=JobTitle.findJobTitle(v);
    		required.add(job.getTitle());
    		Double x=job.getAverageRate();
    		houre.add(x);
    		int y=Integer.parseInt(resourceNumber[i]);
    		Double z=x*y;
    		sum+=z;
    	}
    	houreRate=houre;
    	requiredJobTitle=required;
    	modelMap.addAttribute("reqJobTitles",requiredJobTitle);
    	modelMap.addAttribute("RateOfHoure", houreRate);
    	modelMap.addAttribute("numOfResources", resourceNumber);
    	modelMap.addAttribute("total",sum);
    	
    
    	
   
   return "titlejob/balance";
    }

    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(method= RequestMethod.GET)
    public String index(ModelMap model,HttpServletRequest request) {
    	List<JobTitle> titleJob=new ArrayList<JobTitle>();
    	List<String> addressJob=new ArrayList<String>();
    	List<String> finalAddress=new ArrayList<String>();
    	titleJob=JobTitle.findAllJobTitles();
    	for(Object o:titleJob){
    	JobTitle job=(JobTitle)o;
    	String jobAddress=job.getTitle();
    	addressJob.add(jobAddress);
    	}
    	finalAddress=addressJob;
    	model.addAttribute("addressjob",finalAddress);
    	model.addAttribute("titleJOb",titleJob);
    	
    	
        return "titlejob/list";
    }
    
    
    	
    
  
}
