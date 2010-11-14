package com.icraft.iras.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icraft.iras.model.JobTitle;
import com.icraft.iras.model.OffshoreCalculator;

@RequestMapping("/calculator")
@Controller
@SessionAttributes(value={"totalCost"})
public class OffshoreCalculatorController {
	
	@RequestMapping(params = "view", method = RequestMethod.GET)
    public String createForm(Model model) {
        //model.addAttribute("jobTitle", new JobTitle());
		OffshoreCalculator calculator = new OffshoreCalculator();
		model.addAttribute("calculator", calculator);
		model.addAttribute("jobtitlesList", JobTitle.findAllJobTitles());
		model.addAttribute("jobTitles", calculator.getJobTitles());
		model.addAttribute("totalCost", calculator.getTotalCost());
		model.addAttribute("test", calculator.getTest());
		
		model.addAttribute("numberOfResources", calculator.getNumberOfResources());
        return "jobtitles/calculator";
    }
	
	
	@RequestMapping(method = RequestMethod.POST)
    public String calculatePrice(@Valid OffshoreCalculator offshoreCalculator, BindingResult result, Model model, HttpServletRequest request) {
		System.out.println("Calculate cost...");
        if (result.hasErrors()) {
            //model.addAttribute("jobTitle", jobTitle);
            return "jobtitles/create";
        }
        //TODO calculate
        offshoreCalculator.setTotalCost(1200);
        System.out.println(offshoreCalculator.getTotalCost());
        return "redirect:/calculator?view" ;//+ encodeUrlPathSegment(jobTitle.getId().toString(), request);
    }
	

}
