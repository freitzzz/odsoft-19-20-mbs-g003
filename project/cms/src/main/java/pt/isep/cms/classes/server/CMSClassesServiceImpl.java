package pt.isep.cms.classes.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.classes.client.CMSClassesService;
import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;

@SuppressWarnings("serial")
public class CMSClassesServiceImpl extends RemoteServiceServlet implements
    CMSClassesService {
      
  private final HashMap<Long, CMSClass> cmsClasss = new HashMap<Long, CMSClass>();

  public CMSClassesServiceImpl() {
    initCMSClasss();
  }
  
  private void initCMSClasss() {
	  
  }

	@Override
	public CMSClass addCMSClass(CMSClass cmsClass) {
		
		long id = cmsClasss.size();
		
		id++;
		
		cmsClass.changeId(id);
		
		cmsClasss.put(id, cmsClass);
		
		return cmsClass;
		
	}

	@Override
	public ArrayList<CMSClassDetails> getCMSClassDetails() {
		ArrayList<CMSClassDetails> cmsClassDetails = new ArrayList<CMSClassDetails>();
		
		Iterator<Long> ids = cmsClasss.keySet().iterator();
	    
		while(ids.hasNext()) {
			
			CMSClass cmsClass = cmsClasss.get(ids.next());
			
			cmsClassDetails.add(new CMSClassDetails(cmsClass.acronym(), cmsClass.id()));
			
	    }
		
		return cmsClassDetails;
	}
}
