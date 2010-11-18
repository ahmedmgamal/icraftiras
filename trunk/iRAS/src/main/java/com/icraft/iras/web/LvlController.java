package com.icraft.iras.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.icraft.iras.model.Lvl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "lvls", formBackingObject = Lvl.class)
@RequestMapping("/lvls")
@Controller
public class LvlController {
}
