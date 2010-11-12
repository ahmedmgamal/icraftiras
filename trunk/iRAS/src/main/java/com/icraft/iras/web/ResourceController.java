package com.icraft.iras.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.icraft.iras.model.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "resources", formBackingObject = Resource.class)
@RequestMapping("/resources")
@Controller
public class ResourceController {
}
