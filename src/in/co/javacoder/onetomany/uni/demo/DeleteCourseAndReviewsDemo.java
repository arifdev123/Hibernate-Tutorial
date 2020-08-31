package in.co.javacoder.onetomany.uni.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.onetomany.uni.entity.Course;
import in.co.javacoder.onetomany.uni.entity.Instructor;
import in.co.javacoder.onetomany.uni.entity.InstructorDetail;
import in.co.javacoder.onetomany.uni.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {

		System.out.println("Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		
		
		System.out.println("Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Beginning the Transaction...");
			// Start a transaction
			session.beginTransaction();
		
			// get the course
			int theId = 10;
			
			Course tempCourse = session.get(Course.class, theId);
			
			// print the course 
			System.out.println(" Deleting the Course : " + tempCourse);
			
			// print the course reviews
			System.out.println(" Deleting associated reviews : " + tempCourse.getReviews());
			
			session.delete(tempCourse);

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
