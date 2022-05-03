package pt.isep.cms.students.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

@SuppressWarnings("serial")
public class StudentsMySQLServiceImpl extends RemoteServiceServlet implements StudentsService {

	private EntityManagerFactory factory;

	private EntityManager manager;

	public StudentsMySQLServiceImpl() {
		
		com.mysql.jdbc.Driver.class.getClass().getName();

		initStudents();

		this.factory = Persistence.createEntityManagerFactory("CMS");

		this.manager = this.factory.createEntityManager();

	}

	private void initStudents() {

	}

	@Override
	public Student addStudent(Student student) {
		
		CMSClass cmsClass = (CMSClass)this.manager.createQuery("SELECT C FROM CMSClass C WHERE C.externalIdentifier = " + student.assignedClass().id()).getSingleResult();
		
		student.assignedClass = cmsClass;
		
		long id = this.manager.createQuery("SELECT T FROM Student T").getMaxResults();
		
		id++;

		student.changeId(id);

		this.manager.getTransaction().begin();

		this.manager.persist(student);

		this.manager.getTransaction().commit();

		return student;

	}

	@Override
	public ArrayList<StudentDetails> getStudentDetails() {
		ArrayList<StudentDetails> studentDetails = new ArrayList<StudentDetails>();

		List<Student> students = (List<Student>) this.manager.createQuery("SELECT T FROM Student T").getResultList();

		for (Student student : students) {

			studentDetails.add(new StudentDetails(student.name()));

		}

		return studentDetails;
	}
}
