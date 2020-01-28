package in.co.javacoder.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Instructor;
import in.co.javacoder.entity.InstructorDetail;

public class DeleteOneToOneDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// Retreiving the data from DB and getting an object
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId); 
			System.out.println("Fount instructor : " + tempInstructor);
			
			if(tempInstructor != null) {
				
				// Delete the instructor and his/her details
				//
				// Note : this will ASLO delete the details object because of CascadeType.ALL
				//
				
				System.out.println("Deleting : " + tempInstructor);
				session.delete(tempInstructor);
				
			}
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {}
	}

}
