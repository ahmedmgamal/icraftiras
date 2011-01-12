package com.icraft.iras.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.icraft.iras.model.Vacancy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "vacancys", formBackingObject = Vacancy.class)
@RequestMapping("/vacancys")
@Controller
public class VacancyController {
}
