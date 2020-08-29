package in.co.javacoder.onetoone.unidirectional;

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
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId); 
			System.out.println("Found instructor : " + tempInstructor);
			
			if(tempInstructor != null) {
				
				// Delete the instructor and his/her details
				//
				// Note : this will ASLO delete the details object because of CascadeType.ALL
				//
				
				System.out.println("Deleting : " + tempInstructor);
				System.out.println("Deleting instructor details : " + tempInstructor.getInstructorDetail());
				session.delete(tempInstructor);
				
			}
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {}
	}

}
