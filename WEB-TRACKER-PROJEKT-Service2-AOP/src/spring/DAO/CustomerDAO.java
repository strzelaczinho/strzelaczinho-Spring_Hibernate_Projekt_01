package spring.DAO;

import java.util.List;

import spring.Entities.Customer;



public interface CustomerDAO {

	public List<Customer> getCustomers();
	public void saveCustomer(Customer theCustomer);
	
	public Customer getCustomer(int theId);
	
	public void deleteCustomer(int theId);
	public List<Customer> searchCustomers(String theSearchName);
	
}
