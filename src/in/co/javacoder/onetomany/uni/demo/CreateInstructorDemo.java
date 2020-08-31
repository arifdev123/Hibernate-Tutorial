package in.co.javacoder.onetomany.uni.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Student;
import in.co.javacoder.onetomany.entity.Course;
import in.co.javacoder.onetomany.entity.Instructor;
import in.co.javacoder.onetomany.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		System.out.println("Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		System.out.println("Creating a Instructor object...");
		// Create a Student object
		InstructorDetail tempInstructorDetail = 
				new InstructorDetail("http://www.youtube.com", "luv 2 code");
		
		Instructor tempInstructor = 
				new Instructor("Arif", "Mohamed", "darby@luv2code.com",tempInstructorDetail);  // associate the objects tempInstructorDetail

		System.out.println("Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();
		
		try {
		// associate the instructor details object with the instructor object
				tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Beginning the Transaction...");
		// Start a transaction
		session.beginTransaction();
		
		
			System.out.println("Saving the Instructor object...");
			// Save the student object
			session.save(tempInstructor);

			System.out.println("Commiting the transaction...");
			// Commit the transaction to write the data in the tempStudent object in the
			// database table
			session.getTransaction().commit();

			System.out.println("Done !!!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
		

	}

}
