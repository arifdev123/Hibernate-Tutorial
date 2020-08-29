package in.co.javacoder.onetomany.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	@OneToMany(mappedBy="instructor", cascade= {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	private List<Course> courses;
	
	public Instructor() {  }

	public Instructor(String firstName, String lastName, String email, InstructorDetail instructorDetail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.instructorDetail = instructorDetail;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}
	
	public void setInstructorDetail(InstructorDetail  instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	// add a convenience method to link for bi-directional ralationship
	
	public void add(Course tempCourse) {
		if(tempCourse != null) {
			courses = new ArrayList<>();
		}
		
		courses.add(tempCourse);
		tempCourse.setInstructor(this);
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
	
	

}
