<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1">
<title>Results Page</title>
<style>
      table,
      th,
      td {
        padding: 10px;
        border: 1px solid black;
        border-collapse: collapse;
      }
    </style>
</head>
<body>
<h1>Results of the Query: Checkins by Librarian ID</h1>
<br></br>
<table id="results">
		<tr>
		<td> <b>Checkin ID</b> </td>
		<td> <b>Checkin Date</b> </td>
		<td> <b>Asset ID</b> </td>
		<td> <b>Librarian ID</b> </td>
		</tr>
	<c:forEach var="element" items="${list}">
        <tr> 
        <td> ${element.checkin_id} </td>
        <td> ${element.checkin_date} </td>
        <td> ${element.asset_id} </td>
        <td> ${element.l_id} </td>
        </tr>
    </c:forEach>
</table>
<br></br>
<input type="button" value="Go Back to Catalog Page" onclick="window.location='content/homepage.jsp'" >
</body>
</html>