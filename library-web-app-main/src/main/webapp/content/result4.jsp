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
<h1>Results of the Query: Donates identified by AssetID</h1>
<br></br>
<table id="results">
		<tr>
		<td> <b>Donation ID</b> </td>
		<td> <b>Donation Date</b> </td>
		<td> <b>Asset ID</b> </td>
		<td> <b>User ID</b> </td>
		</tr>
	<c:forEach var="element" items="${list}">
        <tr> 
        <td> ${element.donate_id} </td>
        <td> ${element.donate_date} </td>
        <td> ${element.asset_id} </td>
        <td> ${element.user_id} </td>
        </tr>
    </c:forEach>
</table>
<br></br>
<input type="button" value="Go Back to Catalog Page" onclick="window.location='content/homepage.jsp'" >
</body>
</html>