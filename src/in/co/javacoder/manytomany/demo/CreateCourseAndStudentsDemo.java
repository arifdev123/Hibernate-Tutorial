package in.co.javacoder.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.manytomany.entity.Course;
import in.co.javacoder.manytomany.entity.Instructor;
import in.co.javacoder.manytomany.entity.InstructorDetail;
import in.co.javacoder.manytomany.entity.Review;
import in.co.javacoder.manytomany.entity.Student;


public class CreateCourseAndStudentsDemo {

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
		
			// create a course
			
			Course tempCourse = new Course("Pacman - How to score one million points.");
			
			// add some reviews for the course
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("\ntempCourse: " + tempCourse);
			
			// create a student
			
			Student tempStudent1 = new Student("John", "Doe", "john@javacoder.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@javacoder.com");
			
			// add the students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			//  Save students
			System.out.println("\nSaving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nSaved :  " + tempCourse.getStudents());
			
			
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
