package in.co.javacoder.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		System.out.println("Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		System.out.println("Creating a Student object...");
		// Create a Student object
		Student tempStudent = new Student("Jameelun", "Nisha", "jameel@gmail.com");

		System.out.println("Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();

		System.out.println("Beginning the Transaction...");
		// Start a transaction
		session.beginTransaction();
		try {
		System.out.println("Saving the student object...");
		// Save the student object
		session.save(tempStudent);

		System.out.println("Commiting the transaction...");
		// Commit the transaction to write the data in the tempStudent object in the
		// database table
		session.getTransaction().commit();

		System.out.println("Data saved !!!");
		} finally {
			session.close();
		}
		
		// my new code 
		
		// create a new session 
		System.out.println("Creating another new session...");
		session = factory.getCurrentSession();
		
		try {
			
		
		// Start a transaction
		System.out.println("Beginning the Transaction...");
		session.beginTransaction();
		
		//Get the primary key id of the new student record
		System.out.println("Saved student id : " + tempStudent.getId());
		
		// Retrieving the student object from the database
		Student savedStudent = session.get(Student.class, tempStudent.getId());
		
		// Commiting the transaction
		session.getTransaction().commit();
		
		System.out.println("The retrieved the saved Student record. \n Detail of the saved student is : " + savedStudent.toString());
		
		} finally {
			session.close();
		}
		
		
		

	}

}
