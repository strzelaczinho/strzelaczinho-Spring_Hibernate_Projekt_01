package spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DAO.CustomerDAO;

import spring.Entities.Customer;
import spring.service.CustomerService;



@Controller
@RequestMapping("/customer")
public class CustomerController {

	// tutaj zaszla zmiaina teraz wstrzykuje customer service 
	@Autowired 
	private CustomerService customerService;
	
	@GetMapping("/list") 
	public String listCustomers(Model theModel) {
		
		// pobieram customerów z dao 
		List<Customer> theCustomers = customerService.getCustomers(); // delegate calls to cuustomerService
				
		//dodaje wszystkich customerow do modelu
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}

@GetMapping("/showFormForAdd")	// dzia³a jak  shortcut dla  @RequestMapping(method = RequestMethod.GET).
public String showFormForAdd(Model theModel) 
{
	// tworzy nowy model to bnd form data
	Customer theCustomer = new Customer();
	 theModel.addAttribute("customer",theCustomer);
	
	 
	return "customer-form";
		
}
@PostMapping("/saveCustomer")	//dzia³a jak  shortcut dla  @RequestMaping(method= RequestMethod.POST)
public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {//The @ModelAttribute is an annotation that binds a method parameter or method return value to a named model attribute and then exposes it to a web view. 
//<form:form action="saveCustomer" modelAttribute="customer" method="POST">	 too jest tn kod z jsp strony z przyklejonem customerem do metody saveCustomer
	// save the customer using our service // zapiisuje ciagle ten sam utworzony model 
	customerService.saveCustomer(theCustomer);
	
	
	return "redirect:/customer/list";
}
@GetMapping("/showFormForUpdate")	// mapowanie formy od przychdzacego parametru
public String pokazFormeDoUpdejtowania(@RequestParam("customerId") int theId, Model theModel) //biduje przychodzacy parametr i mapuje go na customerId 
{
	//pobieram customera z bazy
	Customer theCustomer = customerService.getCustomer(theId);
	
	// ustawiam customera jako model atrybutu do wczesniejszej populacji formy i wwwypisywaniu dostepnych oosob w bazie danych.
	theModel.addAttribute("customer",theCustomer);
	
	// wysylam do formy 
	return "customer-form";
}
@GetMapping("/delete")
public String usunUsera(@RequestParam("customerId") int theId,Model theModel)
{
	
	// usuwanie customera
	customerService.deleteCustomer(theId);
	return "redirect:/customer/list";
}
@PostMapping("/search")
public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {

	// search customers from the service
	List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
			
	// add the customers to the model
	theModel.addAttribute("customers", theCustomers);

	return "list-customers";		
}


}

