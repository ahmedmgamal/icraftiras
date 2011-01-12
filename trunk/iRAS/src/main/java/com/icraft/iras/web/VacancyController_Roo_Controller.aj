// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.web;

import com.icraft.iras.model.Skill;
import com.icraft.iras.model.Vacancy;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect VacancyController_Roo_Controller {
    
    @Autowired
    private GenericConversionService VacancyController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String VacancyController.create(@Valid Vacancy vacancy, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("vacancy", vacancy);
            return "vacancys/create";
        }
        vacancy.persist();
        return "redirect:/vacancys/" + encodeUrlPathSegment(vacancy.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String VacancyController.createForm(Model model) {
        model.addAttribute("vacancy", new Vacancy());
        return "vacancys/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String VacancyController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vacancy", Vacancy.findVacancy(id));
        model.addAttribute("itemId", id);
        return "vacancys/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String VacancyController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("vacancys", Vacancy.findVacancyEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Vacancy.countVacancys() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("vacancys", Vacancy.findAllVacancys());
        }
        return "vacancys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String VacancyController.update(@Valid Vacancy vacancy, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("vacancy", vacancy);
            return "vacancys/update";
        }
        vacancy.merge();
        return "redirect:/vacancys/" + encodeUrlPathSegment(vacancy.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String VacancyController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vacancy", Vacancy.findVacancy(id));
        return "vacancys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String VacancyController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Vacancy.findVacancy(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/vacancys?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("skills")
    public Collection<Skill> VacancyController.populateSkills() {
        return Skill.findAllSkills();
    }
    
    Converter<Skill, String> VacancyController.getSkillConverter() {
        return new Converter<Skill, String>() {
            public String convert(Skill skill) {
                return new StringBuilder().append(skill.getName()).toString();
            }
        };
    }
    
    Converter<Vacancy, String> VacancyController.getVacancyConverter() {
        return new Converter<Vacancy, String>() {
            public String convert(Vacancy vacancy) {
                return new StringBuilder().append(vacancy.getJobTitle()).append(" ").append(vacancy.getDescription()).append(" ").append(vacancy.getQualifications()).toString();
            }
        };
    }
    
    @PostConstruct
    void VacancyController.registerConverters() {
        conversionService.addConverter(getSkillConverter());
        conversionService.addConverter(getVacancyConverter());
    }
    
    private String VacancyController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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
