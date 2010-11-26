package com.icraft.iras.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.icraft.iras.model.Resource;

@RequestMapping("/loadcvs/**")
@Controller
public class LoadCvs {

	@RequestMapping(method = RequestMethod.POST)
	public String get(@RequestParam("cvsFolder") String cvs, @RequestParam("pattern") String pattern,
			ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {

File cvsFolder = new File(cvs);
		File[] cvsFiles = cvsFolder.listFiles();
		for (File cvFile : cvsFiles)

		{

			AutoDetectParser parser = new AutoDetectParser();
			Metadata metadata = new Metadata();
			ContentHandler handler = new BodyContentHandler();
			try {
				parser.parse(new FileInputStream(cvFile), handler, metadata);

				System.out.println("\n" + cvFile.getPath() + ":\t");
				String cvText = handler.toString();

				Pattern email = Pattern.compile("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})",
						Pattern.MULTILINE);
				Set<String>  emails =new HashSet<String>();
				
				
				Matcher fit = email.matcher(cvText);
				while (fit.find()) {
					String extractedEmail=fit.group();
					emails.add(extractedEmail);
				}
				//add update cv file in db 

				
				TypedQuery<Resource>  resourcesQuery =Resource.findResourcesInEmailAddressSet(emails);
				List<Resource> resources=resourcesQuery.getResultList();
				if (resources != null && resources.size()>0){
				Resource r=	resources.get(0);
				r.setCvText(cvText);
				r.merge();
				System.out.println(r);
				}else{
					Resource r=	new Resource ();
					r.setId(new Long("0"));
					r.setFullName(emails.toArray()[0].toString());
					r.setEmailAddress(emails.toArray()[0].toString());
					r.setCvText(cvText);
					r.persist();
						
					
					
				}
			
				
				
				
				

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		return "loadcvs/index";
	}

	public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {

		System.out.println("LoadCvs.index()");
		return "loadcvs/index";
	}
}
