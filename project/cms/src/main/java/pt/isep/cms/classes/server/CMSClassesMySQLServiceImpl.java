package pt.isep.cms.classes.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.classes.client.CMSClassesService;
import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;

@SuppressWarnings("serial")
public class CMSClassesMySQLServiceImpl extends RemoteServiceServlet implements CMSClassesService {

	private EntityManagerFactory factory;

	private EntityManager manager;

	public CMSClassesMySQLServiceImpl() {
		
		com.mysql.jdbc.Driver.class.getClass().getName();

		initCMSClasss();

		this.factory = Persistence.createEntityManagerFactory("CMS");

		this.manager = this.factory.createEntityManager();

	}

	private void initCMSClasss() {

	}

	@Override
	public CMSClass addCMSClass(CMSClass cmsClass) {

		long id = this.manager.createQuery("SELECT T FROM CMSClass T").getMaxResults();

		id++;

		cmsClass.changeId(id);

		this.manager.getTransaction().begin();

		this.manager.persist(cmsClass);

		this.manager.getTransaction().commit();

		return cmsClass;

	}

	@Override
	public ArrayList<CMSClassDetails> getCMSClassDetails() {
		ArrayList<CMSClassDetails> cmsClassDetails = new ArrayList<CMSClassDetails>();

		List<CMSClass> cmsClasss = (List<CMSClass>) this.manager.createQuery("SELECT T FROM CMSClass T").getResultList();

		for (CMSClass cmsClass : cmsClasss) {

			cmsClassDetails.add(new CMSClassDetails(cmsClass.acronym(), cmsClass.id()));

		}

		return cmsClassDetails;
	}
}
