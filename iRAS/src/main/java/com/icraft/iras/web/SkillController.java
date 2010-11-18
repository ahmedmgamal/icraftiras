package com.icraft.iras.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.icraft.iras.model.Skill;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "skills", formBackingObject = Skill.class)
@RequestMapping("/skills")
@Controller
public class SkillController {
}
