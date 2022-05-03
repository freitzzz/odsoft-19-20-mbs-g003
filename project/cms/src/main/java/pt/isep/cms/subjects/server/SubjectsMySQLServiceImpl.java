package pt.isep.cms.subjects.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.subjects.client.SubjectsService;
import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.subjects.shared.SubjectDetails;

@SuppressWarnings("serial")
public class SubjectsMySQLServiceImpl extends RemoteServiceServlet implements SubjectsService {

	private EntityManagerFactory factory;

	private EntityManager manager;

	public SubjectsMySQLServiceImpl() {
		
		com.mysql.jdbc.Driver.class.getClass().getName();

		initSubjects();

		this.factory = Persistence.createEntityManagerFactory("CMS");

		this.manager = this.factory.createEntityManager();

	}

	private void initSubjects() {

	}

	@Override
	public Subject addSubject(Subject subject) {

		long id = this.manager.createQuery("SELECT T FROM Subject T").getMaxResults();

		id++;

		subject.changeId(id);

		this.manager.getTransaction().begin();

		this.manager.persist(subject);

		this.manager.getTransaction().commit();

		return subject;

	}

	@Override
	public ArrayList<SubjectDetails> getSubjectDetails() {
		ArrayList<SubjectDetails> subjectDetails = new ArrayList<SubjectDetails>();

		List<Subject> subjects = (List<Subject>) this.manager.createQuery("SELECT T FROM Subject T").getResultList();

		for (Subject subject : subjects) {

			subjectDetails.add(new SubjectDetails(subject.name()));

		}

		return subjectDetails;
	}
}
