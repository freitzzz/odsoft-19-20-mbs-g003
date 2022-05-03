package pt.isep.cms.teachers.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.teachers.client.TeachersService;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

@SuppressWarnings("serial")
public class TeachersMySQLServiceImpl extends RemoteServiceServlet implements TeachersService {

	private EntityManagerFactory factory;

	private EntityManager manager;

	public TeachersMySQLServiceImpl() {
		
		com.mysql.jdbc.Driver.class.getClass().getName();

		initTeachers();

		this.factory = Persistence.createEntityManagerFactory("CMS");

		this.manager = this.factory.createEntityManager();

	}

	private void initTeachers() {

	}

	@Override
	public Teacher addTeacher(Teacher teacher) {

		long id = this.manager.createQuery("SELECT T FROM Teacher T").getMaxResults();

		id++;

		teacher.changeId(id);

		this.manager.getTransaction().begin();

		this.manager.persist(teacher);

		this.manager.getTransaction().commit();

		return teacher;

	}

	@Override
	public ArrayList<TeacherDetails> getTeacherDetails() {
		ArrayList<TeacherDetails> teacherDetails = new ArrayList<TeacherDetails>();

		List<Teacher> teachers = (List<Teacher>) this.manager.createQuery("SELECT T FROM Teacher T").getResultList();

		for (Teacher teacher : teachers) {

			teacherDetails.add(new TeacherDetails(teacher.name()));

		}

		return teacherDetails;
	}
}
