package in.co.javacoder.onetomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.onetomany.entity.Course;
import in.co.javacoder.onetomany.entity.Instructor;
import in.co.javacoder.onetomany.entity.InstructorDetail;

public class CreateCourseDemo {

	public static void main(String[] args) {

		System.out.println("Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		
		
		System.out.println("Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Beginning the Transaction...");
			// Start a transaction
			session.beginTransaction();
		
			// get the instructor from the db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
				
			// create some courses
			
			Course tempCourse1 = new Course("Air Guitar - The ultimate guide");
			Course tempCourse2 = new Course("The Pinball Masterclass");
			
			// add the courses to the instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			// the save courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			

			System.out.println("Commiting the transaction...");
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
