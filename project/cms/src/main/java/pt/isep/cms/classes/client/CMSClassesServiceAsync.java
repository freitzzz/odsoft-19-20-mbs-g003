package pt.isep.cms.classes.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;

public interface CMSClassesServiceAsync {

  public void addCMSClass(CMSClass cmsClass, AsyncCallback<CMSClass> callback);
  
  public void getCMSClassDetails(AsyncCallback<ArrayList<CMSClassDetails>> callback);
}

