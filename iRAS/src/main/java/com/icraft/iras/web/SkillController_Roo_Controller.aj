// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.web;

import com.icraft.iras.model.Skill;
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

privileged aspect SkillController_Roo_Controller {
    
    @Autowired
    private GenericConversionService SkillController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String SkillController.create(@Valid Skill skill, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("skill", skill);
            return "skills/create";
        }
        skill.persist();
        return "redirect:/skills/" + encodeUrlPathSegment(skill.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String SkillController.createForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "skills/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String SkillController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("skill", Skill.findSkill(id));
        model.addAttribute("itemId", id);
        return "skills/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String SkillController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("skills", Skill.findSkillEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Skill.countSkills() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("skills", Skill.findAllSkills());
        }
        return "skills/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String SkillController.update(@Valid Skill skill, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("skill", skill);
            return "skills/update";
        }
        skill.merge();
        return "redirect:/skills/" + encodeUrlPathSegment(skill.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String SkillController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("skill", Skill.findSkill(id));
        return "skills/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String SkillController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Skill.findSkill(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/skills?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @RequestMapping(params = { "find=ByName", "form" }, method = RequestMethod.GET)
    public String SkillController.findSkillsByNameForm(Model model) {
        return "skills/findSkillsByName";
    }
    
    @RequestMapping(params = "find=ByName", method = RequestMethod.GET)
    public String SkillController.findSkillsByName(@RequestParam("name") String name, Model model) {
        model.addAttribute("skills", Skill.findSkillsByName(name).getResultList());
        return "skills/list";
    }
    
    Converter<Skill, String> SkillController.getSkillConverter() {
        return new Converter<Skill, String>() {
            public String convert(Skill skill) {
                return new StringBuilder().append(skill.getName()).toString();
            }
        };
    }
    
    @PostConstruct
    void SkillController.registerConverters() {
        conversionService.addConverter(getSkillConverter());
    }
    
    private String SkillController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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
