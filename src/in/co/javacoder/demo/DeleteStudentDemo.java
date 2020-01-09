package in.co.javacoder.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.co.javacoder.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			
			/*
			 * Student student = session.get(Student.class, 1);
			 * System.out.println("Deleting : " + student.toString());
			 * session.delete(student);
			 */
			System.out.println("Deleting student with id = 2");
			session.createQuery(" delete from Student where id = 2")
			.executeUpdate();
			transaction.commit();
			
			
		}finally {
			session.close();
			factory.close();
		}

	}

}
