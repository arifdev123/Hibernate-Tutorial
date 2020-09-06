package in.co.javacoder.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.manytomany.entity.Course;
import in.co.javacoder.manytomany.entity.Instructor;
import in.co.javacoder.manytomany.entity.InstructorDetail;
import in.co.javacoder.manytomany.entity.Review;
import in.co.javacoder.manytomany.entity.Student;


public class DeleteMaryStudentDemo {

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
		
			// get student john from the database
			
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\n Loaded student: " + tempStudent);
			System.out.println("\n Course of the loaded student: " + tempStudent.getCourses());
			
		
			System.out.println("\nDeleting the student : " + tempStudent);
			session.delete(tempStudent);
			System.out.println("\n Deleted.");
			
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
