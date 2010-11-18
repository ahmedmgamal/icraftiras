package com.icraft.iras.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

public aspect UploadUsersController_Roo {
	@RequestMapping(method = RequestMethod.GET)
	public String UploadUsersController.uploadusers(Model model) {
		//model.addAttribute("csv", new Csv());
		System.out.println("xxxxcccccc____________" + model);
		return "uploadusers";
	}

	// @RequestMapping( method = RequestMethod.POST)
	// public String UploadUsersController.saveUsers(Csv csv ) {
	//
	// System.out.println("RequestMethod.POST____________"+csv.getCsv());
	// return "uploadUsers";
	// }

	// @RequestMapping(method = RequestMethod.POST)
	// public String processSubmit(@ModelAttribute("csv") Csv csv,
	// BindingResult result, SessionStatus status) {
	// System.out.println("RequestMethod.POST____________" + csv);
	//
	// return "uploadUsers";
	//
	// }

	// @RequestMapping(value = "/uploadUsers", method = RequestMethod.POST)
	// public String handleFormUpload(@RequestParam("name") String name
	// ) {
	// System.out.println("RequestMethod.POST____________" + name);
	//
	// return null;
	// }
	 
//    @RequestMapping(method = RequestMethod.POST, value = "form")
//        
//	public String UploadUsersController.homeUpload(@Valid Csv csv,BindingResult result, Model model, HttpServletRequest request) {
//		// public String homeSubmit(HttpServletRequest
//		// request,HttpServletResponse resp) {
//		System.out.println("Welcome home!" + csv);
//		System.out.println("xxxxxxxxxxxxxx" + csv.getCsv());
//		return "/resource";
//	}

	// public String post(@RequestParam("name") String name, ModelMap
	// modelMap,@PathVariable String pathURL){
	//
	// System.out.println("RequestMethod.POST____________" + name);
	// return pathURL;
	//
	//
	// }
    @RequestMapping(method = RequestMethod.POST, value ="csvFileupload")
    public String UploadUsersController.processImageUpload(

    @RequestParam("csvFile") MultipartFile image) throws IOException {
System.out.println("iiiiiiiiiiiiiiiiiiiiiiii\n"+new String(image.getBytes()));
   
    return "resources/list";
    }
}
