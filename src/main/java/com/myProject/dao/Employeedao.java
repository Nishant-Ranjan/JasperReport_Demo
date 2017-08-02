package com.myProject.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myProject.model.Employee;

@Repository
public class Employeedao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList(){
		Session session = null;
		List<Employee> empList = new ArrayList<Employee>();
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Employee.class);
			empList = criteria.list();
		}
		catch(Exception e){
			System.out.println("Error in getting employee list :"+ e);
		}
		return empList;
		
	}
	
	@SuppressWarnings({ "unchecked" })
	public void generateEmployeeReport(ResourceResponse response) throws JRException, IOException, SQLException{
		System.out.println("Entered Generate Employee Report dao");
		Session session = null;
			Map parameters = new HashMap();  
			parameters.put("Title", "Employee Report");  
			  
			File empreport;
			empreport = new File(getClass().getClassLoader().getResource("/Employee.jrxml").getFile());
			String path = empreport.getAbsolutePath();
			System.out.println("Path = "+ path);
			JasperReport jasperReport = JasperCompileManager.compileReport(path);  
			System.out.println("Jasper Report: "+ jasperReport);
			
			 Connection c = sessionFactory.
		                getSessionFactoryOptions().getServiceRegistry().
		                getService(ConnectionProvider.class).getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);
			System.out.println("Jasper Print:" + jasperPrint);
			JRXlsExporter exporter = new JRXlsExporter();
			System.out.println("Exporter: "+ exporter);
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	      //  exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "D://data//Demo_Portlet.xls" ); 
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					response.getPortletOutputStream());
			exporter.exportReport();
	        System.out.println("Report Generated Successfully! ! ! ");
	}

}
