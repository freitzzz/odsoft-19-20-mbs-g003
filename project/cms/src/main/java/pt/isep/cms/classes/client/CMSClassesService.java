package pt.isep.cms.classes.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;

@RemoteServiceRelativePath("classesService")
public interface CMSClassesService extends RemoteService {
	
  CMSClass addCMSClass(CMSClass cmsClass);
  
  ArrayList<CMSClassDetails> getCMSClassDetails();
}
