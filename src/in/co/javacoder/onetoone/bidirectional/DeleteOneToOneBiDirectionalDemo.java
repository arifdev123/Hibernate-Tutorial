package in.co.javacoder.onetoone.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Instructor;
import in.co.javacoder.entity.InstructorDetail;

public class DeleteOneToOneBiDirectionalDemo {

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
			int theId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId); 
			System.out.println("Found instructor detail: " + tempInstructorDetail);
			
			if(tempInstructorDetail != null) {
							
				System.out.println("Associated Instructor : " + tempInstructorDetail.getInstructor());
				
			}
			// Delete the instructorDetail object and associated Instructor will also be deleted due to cascadeType set to ALL
			System.out.println("Deleting the tempInstructorDetail : " + tempInstructorDetail );
			
			// remove the associated object reference
			// remove th bi-directional link
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			if(session != null) session.close();
			if(factory != null) factory.close();
		}
	}

}
