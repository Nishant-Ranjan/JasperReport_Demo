<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<p><b>Employee Details</b></p>
	<table border = '1'>
	<thead>
	<tr>
		<th>Employee Id</th>
		<th>Employee Name</th>
		<th>Department</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="list" items = "${EmployeeList}">
		<tr>
		
			<td>${list.getEmpId()}</td>
			<td>${list.getEmpName()}</td>
			<td>${list.getDept()}</td>
			
		</tr>
		</c:forEach>
	</tbody>
	</table><br>
	<a href = '<portlet:resourceURL id="generateReport"></portlet:resourceURL>'>Generate Report! ! !</a>
</body>
</html>