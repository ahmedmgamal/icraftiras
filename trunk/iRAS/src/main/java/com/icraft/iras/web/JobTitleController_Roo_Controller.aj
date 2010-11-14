// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.web;

import com.icraft.iras.model.JobTitle;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect JobTitleController_Roo_Controller {
    
    @Autowired
    private GenericConversionService JobTitleController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String JobTitleController.create(@Valid JobTitle jobTitle, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("jobTitle", jobTitle);
            return "jobtitles/create";
        }
        jobTitle.persist();
        return "redirect:/jobtitles/" + encodeUrlPathSegment(jobTitle.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String JobTitleController.createForm(Model model) {
        model.addAttribute("jobTitle", new JobTitle());
        return "jobtitles/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String JobTitleController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("jobtitle", JobTitle.findJobTitle(id));
        model.addAttribute("itemId", id);
        return "jobtitles/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String JobTitleController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("jobtitles", JobTitle.findJobTitleEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) JobTitle.countJobTitles() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("jobtitles", JobTitle.findAllJobTitles());
        }
        return "jobtitles/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String JobTitleController.update(@Valid JobTitle jobTitle, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("jobTitle", jobTitle);
            return "jobtitles/update";
        }
        jobTitle.merge();
        return "redirect:/jobtitles/" + encodeUrlPathSegment(jobTitle.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String JobTitleController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("jobTitle", JobTitle.findJobTitle(id));
        return "jobtitles/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String JobTitleController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        JobTitle.findJobTitle(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/jobtitles?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    Converter<JobTitle, String> JobTitleController.getJobTitleConverter() {
        return new Converter<JobTitle, String>() {
            public String convert(JobTitle jobTitle) {
                return new StringBuilder().append(jobTitle.getTitle()).append(" ").append(jobTitle.getAverageRate()).toString();
            }
        };
    }
    
    @PostConstruct
    void JobTitleController.registerConverters() {
        conversionService.addConverter(getJobTitleConverter());
    }
    
    private String JobTitleController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
