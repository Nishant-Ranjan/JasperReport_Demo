package com.myProject.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.ResourceResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.dao.Employeedao;
import com.myProject.model.Employee;

@Service
public class EmployeeServices {
	
	@Autowired
	Employeedao dao;
	
	public List<Employee> getEmployeeList(){
		return dao.getEmployeeList();
	}
	
	public void generateReport(ResourceResponse response) throws JRException, IOException, SQLException{
		dao.generateEmployeeReport(response);
	}

}
