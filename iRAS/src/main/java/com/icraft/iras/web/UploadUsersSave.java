package com.icraft.iras.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/uploaduserssave/**")
@Controller
public class UploadUsersSave {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("gggggggggggggggggggggg");
    }

    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	
    	System.out.println("@PathVariable");
    }

    @RequestMapping
    public String index(Model model) {
    	
    	Csv c=new Csv();
    	c.setCsv("xyzzzzzzzzzzzzzzzz");
    	model.addAttribute("csv", c);
    	System.out.println("cccccccc");
        return "uploaduserssave/index";
    }
}
