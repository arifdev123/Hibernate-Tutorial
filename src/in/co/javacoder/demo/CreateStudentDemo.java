package in.co.javacoder.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		System.out.println("Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		System.out.println("Creating a Student object...");
		// Create a Student object
		Student tempStudent = new Student("Mohamed", "Arif", "arif123@gmail.com");

		System.out.println("Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();

		System.out.println("Beginning the Transaction...");
		// Start a transaction
		session.beginTransaction();

		System.out.println("Saving the student object...");
		// Save the student object
		session.save(tempStudent);

		System.out.println("Commiting the transaction...");
		// Commit the transaction to write the data in the tempStudent object in the
		// database table
		session.getTransaction().commit();

		System.out.println("Done !!!");

	}

}
