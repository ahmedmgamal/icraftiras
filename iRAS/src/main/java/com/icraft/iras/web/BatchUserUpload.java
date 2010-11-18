package com.icraft.iras.web;

import java.io.IOException;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.icraft.iras.model.Resource;

import au.com.bytecode.opencsv.CSVReader;


@RequestMapping("/batchuserupload/**")
@Controller
public class BatchUserUpload {
	private static final Logger logger = LoggerFactory.getLogger(BatchUserUpload.class);

    @RequestMapping(method = RequestMethod.POST, value = "submitFile")
    public void get(@RequestParam("file")MultipartFile file ,  Model  model , HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String fileText=new String(file.getBytes());
    	 CSVReader reader = new CSVReader(new StringReader(fileText));
    	    String [] nextLine;
    	    while ((nextLine = reader.readNext()) != null) {
    	     Resource resource= new Resource();
    	     resource.setFullName(nextLine[1]);
    	     resource.setEmailAddress(nextLine[5]);
    	     resource.persist();
    	        System.out.println(nextLine[0] + nextLine[1] + "etc...");
    	    }
    	       
    	
    	
    	
    	
    	model.addAttribute("fileText", fileText);
    	logger.info("Request Submited: "+fileText);
    
    	response.sendRedirect("../../resources");
    	//return "..\";
    	
    }

    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "batchuserupload/index";
    }
}
