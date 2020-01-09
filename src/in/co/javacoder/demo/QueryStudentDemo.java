package in.co.javacoder.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// Creating a sessionfactory
		System.out.println("...Creating a sessionfactory...");
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create a session from the above create session factory
		System.out.println("... Initializing the session ...");
		Session session = factory.getCurrentSession();
		try {
			// Beginning the transaction 
			System.out.println("... Beginning the Transaction ...");
			session.beginTransaction();
			
			// build HQL query to retrieve all the students 
			System.out.println("... Building the query to retrieve all the students ...");
			List<Student> theStudents = session.createQuery("from Student").list();
		
			// Display the students 
			displayStudents(theStudents);
			
			// Query students with last name is Arif 
			System.out.println("... Build the query to retrieve the students with last name is Arif");
			theStudents = session.createQuery("from Student s where s.lastName = 'Arif'").getResultList();
		
			// Display the students 
			displayStudents(theStudents);
			
			// Query students with email ends like @gmail.com 
			System.out.println("... Build the query to retrieve the students with email ends like @gmail.com");
			theStudents = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();
		
			// Display the students 
			displayStudents(theStudents);
			
			// Commiting the transaction
			System.out.println("... Commiting the Transaction ...");
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student student : theStudents) {
			System.out.println(student.toString());
		}
	}

}
