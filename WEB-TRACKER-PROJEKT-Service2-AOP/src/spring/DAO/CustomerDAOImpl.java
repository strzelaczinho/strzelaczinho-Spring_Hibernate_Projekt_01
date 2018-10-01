package spring.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.Entities.Customer;



@Repository// daje sygnal zeby przeskanowac te obiekty oraz zajac sie bledami. Dodaje sie do DAO implementacji. Jest takie cos jak @Component (skanuje beany ) z tego dziedziczy @Controller (do Spring MVC) oraz @Repository. Do DAO klasy implementacyjnych. Ulatwia autoskana , component skana .Spring automatycznie zarejestruje DAO implemetacje dzieki skanowaniu. oraz pomaga przy tlumaczeniu bledow powstslych przy podlaczeniu JDBC  
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory; // Spring spojrrzy na konfiguracje <bean id="sessionFactory" i wstrzyknie 
			
	@Override
	//	Teraz service zarz¹dza tranzakcj¹ 	@Transactional magicznee zaczyna i konczy tranzakcje . Poprawia kod . Normalnie pisalismy sessin.beginTransaction potem kod potem session.commit(). Teraz dzieki adnotacji nie musimy tego pisac . Skraca szybko czas pisania . 
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();// pobiera sesje Hiebernate z pliku spring-mvc-demo-servlet.
				
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class); // tworzy zapytanie Select * from Customer Cuustomer figuruje tu jako klasas 
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList(); // przesyla wyniki z zapytania
				
		// return the results		
		return customers;
	}
	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer ... finally LOL
		currentSession.saveOrUpdate(theCustomer);// DAO method zapisuje lub updejtuje customera do bazy . Jesli id jest empty INSERT new customer , else UPDATE obecnego custoomera
		
	}
	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}
	
	@Override
	public void deleteCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId"); // HQL
		theQuery.setParameter("customerId", theId); // parametr musi sie zgadzac z wartoscia delete From CUstomer where id=:customerId
		
		theQuery.executeUpdate();	// process, update deletes :)
	}
	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();		
		Query theQuery = null;
		// only search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) { // jesli to nie beda puste pola

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			//Pole theSearch name jest puste wiec ... zwroc wszystkich Klientow z bazy
			theQuery =currentSession.createQuery("from Customer", Customer.class);			
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
		
	}
//	For the condition when "theSearchName" is not empty, then we use it to compare against the first name or last name.
//	We also make use of the "like" clause and the "%" wildcard characters. This will allow us to search for substrings. For example, 
//	if we have customers with last name of "Patel", "Patterson" ... then we can search for "Pat" and it will match on those names.  
}






