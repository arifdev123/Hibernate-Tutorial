package in.co.javacoder.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.manytomany.entity.Course;
import in.co.javacoder.manytomany.entity.Instructor;
import in.co.javacoder.manytomany.entity.InstructorDetail;
import in.co.javacoder.manytomany.entity.Review;
import in.co.javacoder.manytomany.entity.Student;


public class DeletePacManCourseDemo {

	public static void main(String[] args) {

		System.out.println("Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		
		
		System.out.println("Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Beginning the Transaction...");
			// Start a transaction
			session.beginTransaction();
		
			// get student mary from the database
			
			int courseId = 10;
			Course tempCourse = session.get(Course.class, courseId);
			
			// Deleting the course
			System.out.println("\n Deleting the course: " + tempCourse);
			session.delete(tempCourse);
			
			System.out.println("\n Deleted." );
			
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
