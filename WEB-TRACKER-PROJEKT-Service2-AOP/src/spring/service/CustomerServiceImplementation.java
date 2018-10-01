package spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.DAO.CustomerDAO;
import spring.Entities.Customer;

@Service //Oznacza to, ¿e @Service, @Repository, @Controller to tak naprawdê adnotacja @Component z dodatkowymi informacjami. I tak:@Service — wskazuje serwis w warstwie logiki biznesowej @Repository — wskazuje DAO w warstwie przechowywania danych @Controller — wskazuje kontroler w warstwie prezentacji
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();// przekierowuje zawolania do DAO 
	}
	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.saveCustomer(theCustomer);
	}
	@Override
	@Transactional
	public Customer getCustomer(int theId)
	{
		
		return customerDAO.getCustomer(theId);
	}
	@Override
	@Transactional
	public void deleteCustomer(int theId) {
	
		 customerDAO.deleteCustomer(theId);
	}
	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {

		return customerDAO.searchCustomers(theSearchName);
	}
	
}
