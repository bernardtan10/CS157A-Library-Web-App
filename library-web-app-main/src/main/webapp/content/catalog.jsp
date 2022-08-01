<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html>

<head>
	<style type = "text/css">
		body{background-color: white;}
	</style>
	
</head>

<body>
	
	<table Width="900" height="200" align="center" border="0">

		<tr height=50>
			<td align="center" bgcolor="coral" colspan=5> <h1> <font align="center" face="Blackadder ITC" color="black"> Library WebApp </font> </h>
		</tr>

		<tr height=50>
			<td align="center" width=300><a href="home.jsp" style="text-decoration:none;"><font size="5"color="black"> Home </font> </a>
			<td align="center" width=300><font size="6" color="black" face="algerian"> Catalog </font>
			<td align="center" width=300><a href="signup.jsp" style="text-decoration:none;"><font size="5"color="black"> Sign Up </font> </a>
		</tr>

		
		<tr height=100>
		
			<td height = 100 colspan="5" align="center" bgcolor="grey"> <br> <br>
			
			<div class="container">
			
		<div class="jumbotron">
			<div align="center">
			
    			<form action = "/LibraryWebApp/AppController" method="post">
        			List:&nbsp;
  						<select name="action">
  							<option value="0">Select an action</option>
    						<optgroup label="Insert Actions">
      						<option value="1">Insert a new User</option>
      						<option value="2">Insert a new Librarian</option>
    						</optgroup>
    						<optgroup label="Select Actions">
      						<option value="3">List of all Users</option>
      						<option value="4">Search for User by Room #</option>
      						<option value="5">Search for Book by # of Pages</option>
      						<option value="6">Search for Book by Publisher</option>
      						<option value="7">Search for Asset by Category</option>
      						<option value="8">Search for Librarian by Asset Check-In</option>
    						</optgroup>
  						</select>
  						
  					<br/><br/>
  					
  					<input type="text" name="v1" />
  					<br/><br/>
  					
  					<input type="text" name="v2" />
  					<br/><br/>
  					
  					<input type="text" name="v3" />
  					<br/><br/>
  					
  					<input type="text" name="v4" />
  					<br/><br/>
  					
        			<input type="submit" value="Submit" />
        			<input type="reset" value ="Clear" />
        			
    			</form>
    			
			</div>
		</div>

	</div>
	
	<br></br>
	
	<div align = "center">
		<br></br>
		Results:
		<br></br>
		<%=request.getAttribute("final")%>
	</div>
			
			
			
			<br> <br>

		</tr>

	</table>

</body>

</html>






