package in.co.javacoder.onetomany_uni.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.onetomany_uni.entity.Course;
import in.co.javacoder.onetomany_uni.entity.Instructor;
import in.co.javacoder.onetomany_uni.entity.InstructorDetail;
import in.co.javacoder.onetomany_uni.entity.Review;

public class CreateCourseAndReviewsDemo {

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
		
			// create a course
			
			Course tempCourse = new Course("Pacman - How to score one million points.");
			
			// add some reviews for the course
			tempCourse.addReview(new Review("Great course ... loved it!"));
			tempCourse.addReview(new Review("Cool course, job well done."));
			tempCourse.addReview(new Review("What a dump course, you are an idiot !"));
			
			// save the course ... and leverage the cascade all.
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);

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
