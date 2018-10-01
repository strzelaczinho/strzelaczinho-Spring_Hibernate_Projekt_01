<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	
	<!-- reference our style sheet -->

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" /> <!--  pageContext .... contextPath daje nam wlasciwa nazwe aplikacji -->

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		<!--  doodaje przycisk 													spring controller mapping				klasa z style css o nazwie add-button					-->
			<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;" class="add-button"/>
			
		<!--  Tutaj dodalem searchBox -->
			<form:form action="search" method="POST">
				Search customer: <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>			
			
			
			
			<!--  html table -->	
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
					
				</tr>
				
				
				<!--Petla po wszystkich customerach z listy customers,  itemy ktore zostaly przekazane w bean  jako atrybut -->			
				<c:forEach var="tempCustomer" items="${customers}">
		<!-- The c:url tag formats a URL into a string and stores it into a variable. This tag automatically performs URL rewriting when necessary. The var attribute specifies the variable that will contain the formatted URL. -->		
				<c:url var="updateLink" value="/customer/showFormForUpdate">	<!-- tutaj tworze update link z id customera  czyli url = updateLink  oraz wartosc = zmapowanyAdres-- a nizej podlaczam customerId w petli z wartoscia tempCustomer.id -->				
					<c:param name="customerId" value="${tempCustomer.id}" /> <!--  tutaj dodajemy do linka przekazywawnej strony parameter o nazwie customerId i wartosci tempCustomer.id z petli :) -->				
					</c:url> 
			
			
		<!-- tworze delete liink -->
				<c:url var="deleteLink" value="/customer/delete">					
				<c:param name="customerId" value="${tempCustomer.id}" /> 				
				</c:url> 
			
			
			
					<tr>
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}" onclick="if(!(confirm('Czy jestes pewien ze chcesz usunac tego uzytkownika?'))) return false"> <!--  jesli user kliknie cancel zwracamy false -->
							Delete</a>
						</td>
					</tr>
		
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









