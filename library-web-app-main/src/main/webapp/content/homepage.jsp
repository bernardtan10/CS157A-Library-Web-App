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
	<div class="container">
		<div class="jumbotron">
			<div>
				<h1>Library Catalog</h1>
			</div>
			<div align="center">
    			In order to use the Catalog below, please take note of the necessary parameters to use
    			each action, and please enter them accordingly in the text boxes. If there are less 
    			parameters needed than spaces offered, please leave the unneccesary spaces blank.
    			<br></br>
    			<b>Rules</b>
    			<br></br>
    			<b>Press the "Build Database" button upon each login</b>
    			<br></br>
    			<b>Press the "Drop Database" button when leaving, in order to drop tables.</b>
    			<br></br>
    			<b>All dates must be entered in the format YYYY/MM/DD, or will result in an error.</b>
    			<br></br>
    			<b>Each entity has its own ID format, where X is any integer value: 
    			User: 12XX ||
    			Librarian: 10XX ||
    			Volunteer: 20XX ||
    			Study Room (#): 20X ||
    			Library Card: 501XX ||
    			Publishing House: 9011XX ||
    			Book: 10010XX ||
    			DVD: 20010XX ||
    			Asset Category: 1XX
    			</b>
    			<b></b>
    			<br></br>
    			<form action = "/LibraryWebAppCS157A/LibController" method = "post">
    			<input type="submit" name = "connect" value="Build Database" />
    			<input type="submit" name = "disconnect" value="Drop Database" />
    			</form>
    			<br></br>
    			<form action = "/LibraryWebAppCS157A/LibController" method="post">
        			List:&nbsp;
  						<select name="action">
  							<option value="0">Select an action</option>
    						<optgroup label="Insert Actions">
      						<option value="1">Insert a new User (ID, Name, Date of Birth, Email)</option>
      						<option value="2">Insert a new Librarian (ID, Name)</option>
      						<option value="3">Insert a new Volunteer (ID, Name)</option>
      						<option value="4">Insert a new Study Room (Room #, Capacity)</option>
      						<option value="5">Insert a new Library Card (ID, Expiration Date)</option>
      						<option value="6">Insert a new Publishing House (ID, Name)</option>
      						<option value="7">Insert a new DVD (ID, Name, Length)</option>
      						<option value="8">Insert a new Book (ID, Name, # of Pages)</option>
      						<option value="9">Insert a new Category (ID, Name, Location)</option>
    						</optgroup>
    						<optgroup label="Select Actions">
      						<option value="10">List of all Users</option>
      						<option value="11">Search for User by Reserved Room # (Room #)</option>
      						<option value="12">Search for all Books over # of Pages (# of Pages)</option>
      						<option value="13">Search for Checkout by AssetID and UserID (AssetID, UserID)</option>
      						<option value="14">Search for Donor and Donation Date by AssetID (AssetID)</option>
      						<option value="15">Search for all Books published by PublisherID (PublisherID)</option>
      						<option value="16">Search for all Assets by CategoryID (CategoryID)</option>
      						<option value="17">Search for all Checkins by LibrarianID (LibrarianID) </option>
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
		Result:
		<br></br>
		<%=request.getAttribute("final")%>
	</div>
</body>
</html>