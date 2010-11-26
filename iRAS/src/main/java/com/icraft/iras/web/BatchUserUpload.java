package com.icraft.iras.web;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

import com.icraft.iras.model.Lvl;
import com.icraft.iras.model.Resource;
import com.icraft.iras.model.ResourceSkillLevel;
import com.icraft.iras.model.Skill;

import au.com.bytecode.opencsv.CSVReader;

@RequestMapping("/batchuserupload/**")
@Controller
public class BatchUserUpload {
	private static final Logger logger = LoggerFactory.getLogger(BatchUserUpload.class);

	@RequestMapping(method = RequestMethod.POST, value = "submitFile")
	public void get(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fileText = new String(file.getBytes());
		CSVReader reader = new CSVReader(new StringReader(fileText));
		String[] nextLine;
		nextLine = reader.readNext();
		HashMap<Integer, Skill> skillsColumns = new HashMap<Integer, Skill>();
		// identify skills Columns
		for (int i = 0; i < nextLine.length; i++) {
			if ((nextLine[i].indexOf("[") > 0) && ((nextLine[i].indexOf("]") < nextLine[i].length()))) {
				String skillName = (nextLine[i].substring(nextLine[i].indexOf("[") + 1, nextLine[i].indexOf("]")));
				skillName = skillName.trim();
				Skill skill = null;
				try {
					skill = Skill.findSkillsByName(skillName).getSingleResult();
				} catch (Exception e) {

					if (skill == null) {
						logger.info("Adding Skill " + skillName);
						skill = new Skill();
						skill.setName(skillName);
						skill.persist();
					}
				}

				skillsColumns.put(i, skill);
				System.out.println(skillName);

			}

		}

		while ((nextLine = reader.readNext()) != null) {
			try {
				Resource resource = new Resource();
				resource.setFullName(nextLine[1]);
				resource.setEmailAddress(nextLine[5]);
				//initialize  set 
				if (resource.getResourceSkillLevels() == null) {
					resource.setResourceSkillLevels(new ArrayList<ResourceSkillLevel>());
				}
				
				// seach for skills column and adding them 
				for (int i = 0; i < nextLine.length; i++) {
					ResourceSkillLevel resourceSkillLevel = new ResourceSkillLevel();

					if (skillsColumns.get(i) != null) {
						String levelName=nextLine[i].trim();
						if(levelName== null || levelName.equals("")){
							levelName="No Experiance";
						}
						resourceSkillLevel.setLvl((Lvl.findLvlsByName(levelName)).getSingleResult());
						resourceSkillLevel.setResource(resource);
						resourceSkillLevel.setSkill(skillsColumns.get(i));
						resource.getResourceSkillLevels().add(resourceSkillLevel);
						logger.info("adding skill level"+resourceSkillLevel);
					}
				}

				resource.persist();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		model.addAttribute("fileText", fileText);
		logger.info("Request Submited: ");

		response.sendRedirect("../../resources");
		// return "..\";

	}

	public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping
	public String index() {
		return "batchuserupload/index";
	}
}
