package com.icraft.iras.web;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.comparator.DirectoryFileComparator;
import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import org.mcavallo.opencloud.filters.DictionaryFilter;
import org.mcavallo.opencloud.filters.Filter;
import org.mcavallo.opencloud.formatters.HTMLFormatter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.icraft.iras.model.Resource;

@RequestMapping("/findresource/**")
@Controller
public class FindResourceController {

    @RequestMapping(method = RequestMethod.POST)
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("FindResourceController.get()");
    }
    @RequestMapping(method = RequestMethod.POST, value = "findSkill")
	public String get(@RequestParam("skill") String skill, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
    	
    	System.out.println("FindResourceController.get()"+skill);
    	
		return "findresource/findSkillsResult";
    	
    }

    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

 @RequestMapping(method = RequestMethod.GET)
    public String index( Model model, HttpServletRequest request, HttpServletResponse response) {
    System.out.println("FindResourceController.index()");
    	try {
			Cloud cloud = new Cloud();
			// Sets the number of tag to display in the cloud
			cloud.setMaxTagsToDisplay(50000);
			// We want to use a maximum font size of 38px for a tag,
			// so we set the maximum weight value to 38.0 for convenience.
			cloud.setMaxWeight(50.0);
			cloud.setMinWeight(10.0);
			// Sets a default url to assign to tags.
			// The format specifier %s will be substituted by the tag name
			cloud.setDefaultLink("http://www.flickr.com/photos/tags/%s/");
			// Add sample tags with corresponding score
			//System.out.println(request.getSession().getServletContext().getRealPath("\\WEB-INF\\classes\\")+"stopWords.txt"));
			Filter<Tag> dictionaryFilter = new DictionaryFilter(new Scanner(new File (request.getSession().getServletContext().getRealPath("\\WEB-INF\\classes")+"\\stopWords.txt")));
			
			cloud.addInputFilter(dictionaryFilter);
			
	
	
			
			
//			cloud.getWordPattern();
			List<Resource> allResources=Resource.findAllResources();
//			HTMLFormatter f= new HTMLFormatter();
//			System.out.println(f.html(cloud));
			for (Resource resource:allResources){
				
				cloud.addText(resource.cvText);
				System.out.println("cv add for "+resource.getFullName());
			}
//			/System.out.println("___________________________");
//			System.out.println(cloud.allTags().size());
//			DataOutputStream allTagsOutput = new DataOutputStream(new FileOutputStream("g:\\allTags.txt"));
//
			for (Tag t:cloud.tags(  new Tag.ScoreComparatorDesc())){
				System.out.println((t.getName()+"\t"+t.getScore()));
			
			}
			model.addAttribute("cloud",cloud);
			model.addAttribute("tags",cloud.tags(new Tag.ScoreComparatorDesc()));
			
		} catch (Exception e) {
		e.printStackTrace();
		}
    	
    	
        return "findresource/index";
    }
}
