package in.co.javacoder.onetomany.eager_vs_lazy.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import in.co.javacoder.onetomany.eager_vs_lazy.entity.Course;
import in.co.javacoder.onetomany.eager_vs_lazy.entity.Instructor;
import in.co.javacoder.onetomany.eager_vs_lazy.entity.InstructorDetail;



public class FetchJoinDemo {

	public static void main(String[] args) {

		System.out.println("luv2code Creating the session factory..");
		// Create a hibernate session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		
		
		System.out.println("luv2code Creating a session to make database transaction...");
		// Create session object to save a student object
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Beginning the Transaction...");
			// Start a transaction
			session.beginTransaction();
		
			// Option 2: Hibernate query with HQL
			// get the instructor from the db
			int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "
																+ "JOIN FETCH i.courses "
																+ "where i.id=:theInstructorId"
											, Instructor.class);
			
			// set the parameter
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
				
			System.out.println("luv2code Instructor : " + tempInstructor);
			
			System.out.println("luv2code Commiting the transaction...");
			session.getTransaction().commit();
			
			// closing the session before
			session.close();
			
			System.out.println("The session is closed........");
			
			// get all the courses for the given instructor
			
			System.out.println("luv2code Courses : " + tempInstructor.getCourses());
			
			System.out.println("luv2code Done !!!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
		

	}

}
