package com.icraft.iras.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class OffshoreCalculator {
	private List<JobTitle> jobTitles = new ArrayList<JobTitle>();
	private double totalCost;
	private int numberOfResources; 
	private String test;
}
