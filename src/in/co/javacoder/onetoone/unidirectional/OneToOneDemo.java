package in.co.javacoder.onetoone.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Instructor;
import in.co.javacoder.entity.InstructorDetail;

public class OneToOneDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// create the objects
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.youtube.com", "luv 2 code");
			
			Instructor tempInstructor = 
					new Instructor("Arif", "Mohamed", "darby@luv2code.com",tempInstructorDetail);  // associate the objects tempInstructorDetail
			
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			//
			// Note : this will ASLO save the details object because of CascadeType.ALL
			//
			
			System.out.println("Saving instructor : " + tempInstructor);
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {}
	}

}
