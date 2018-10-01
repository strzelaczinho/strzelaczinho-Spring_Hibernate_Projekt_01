<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Manager Klientow </h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>
	
		<form:form action="saveCustomer" modelAttribute="customer" method="POST">	<!-- Ciagle ten sam osobnik z theModel.addAttribute("customer", theCustomer);
		Kiiedy forma zostanie submitowana Spring zawola customer.getFirstName  oraz customer.getLastName. Dopiero po klikniieciu save button dojdzie do setFirstName setLastName
		Dlatego jesli przekazujemy obiekt customer do ktorego wczesniej przekazalismy id zeby wyciagnac go z bazy , pola form:input beda juz ustawione na wartosci 
		juz utworzonego obiektu . Czyli to beda te wartosci ktore przekazalismy wczesniej po dodaniu do bazy i wyciagneliismy controlerem z podanym id. Dojdzie do prepopulate
		-->
			
			<form:hidden path="id" /> <!-- laczy dane z given customerem . Sledzi customera w bazie ktorego chcemy dodac czy updejetowac po nmerze id. Bez tej linii bedzie wybieralo z bazy randomowych lduzi do uupgrejdowawnia . Zmieni czyjes dane zamiast tego co chcemy  -->
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" /></td>
					</tr>
				
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td> 
					</tr>		<!--         typ        label     oraz css style save -->

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>









