package in.co.javacoder.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// Creating the session Factory 
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
				
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		try {
			
			/*
			 * Student existStudent = session.get(Student.class, 2);
			 * existStudent.setFirstName("Anees"); existStudent.setLastName("Fathima");
			 * existStudent.setEmail("anees@example.com");
			 */
			
			/*
			 * session.
			 * createQuery("update Student set firstName='Juwairiya' , lastName='M' where id = 4"
			 * ) .executeUpdate();
			 */
			Student existStudent2 = session.get(Student.class, 4);
			existStudent2.setEmail("juwairiya@yahoo.com");
			session.saveOrUpdate(existStudent2);

			System.out.println(session.get(Student.class, 4).toString());
				
			session.getTransaction().commit();
			
		}finally {
			session.close();
			factory.close();
		}

	}

}
