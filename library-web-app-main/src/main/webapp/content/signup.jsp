<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
	<style type = "text/css">
		body{background-color: white;}
	</style>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Library Registration Form</title>

</head>

<body>
	
	<table Width="900" height="200" align="center" border="0">

		<tr height=50 >
			<td align="center" bgcolor="coral" colspan=5> <h1> <font align="center" face="Blackadder ITC" color="black"> Library WebApp </font> </h>
		</tr>

		<tr height=50>
			<td align="center" width=300><a href="home.jsp" style="text-decoration:none;"><font size="5"color="black"> Home </font> </a>
			<td align="center" width=300><a href="catalog.jsp" style="text-decoration:none;"><font size="5"color="black"> Catalog </font> </a>
			<td align="center" width=300><font size="6" color="black" face="algerian"> Sign Up </font>
		</tr>

		<tr height=100>
		
			<td colspan="5" align="center" bgcolor="grey"> <br> <br>
				
				<form action="homepage.jsp" method="post">
				
				<table>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="password" /></td>
				</tr>
				<tr>
					<td>UserName</td>
					<td><input type="text" name="firstName" /></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="lastName" /></td>
				</tr>
				<tr>
					<td>Date of Birth</td>
					<td><input type="text" name="dateOfBirth" /></td>
				</tr>
				<tr>
					<td>Email Address</td>
					<td><input type="text" name="emailAddress" /></td>
				</tr></table>
			<input type="submit" value="Submit" /></form>
			
			<br> <br>
				
				
			
		</tr>

	</table>

</body>

</html>


