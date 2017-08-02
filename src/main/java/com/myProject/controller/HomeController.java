package com.myProject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.ResourceResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.myProject.model.Employee;
import com.myProject.services.EmployeeServices;

@RequestMapping(value = "VIEW")
@Controller
public class HomeController {

	@Autowired
	EmployeeServices services;
	
	@RenderMapping
	public String getDefaultpage(ModelMap map){
		List<Employee> list = new ArrayList<Employee>();
		list = services.getEmployeeList();
		map.addAttribute("EmployeeList", list);
		return "Home";
	}
	
	@ResourceMapping(value = "generateReport")
	public void generateReport(ResourceResponse response) throws JRException, IOException, SQLException{
		services.generateReport(response);
	}

}
