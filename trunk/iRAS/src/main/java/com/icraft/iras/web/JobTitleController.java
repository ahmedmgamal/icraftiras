package com.icraft.iras.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icraft.iras.model.JobTitle;

@RooWebScaffold(path = "jobtitles", formBackingObject = JobTitle.class, registerConverters=true)
@RequestMapping("/jobtitles")
@Controller
public class JobTitleController {
	

}
