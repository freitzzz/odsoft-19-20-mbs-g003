package pt.isep.cms.subjects.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.subjects.shared.SubjectDetails;

public interface SubjectsServiceAsync {

  public void addSubject(Subject subject, AsyncCallback<Subject> callback);
  
  public void getSubjectDetails(AsyncCallback<ArrayList<SubjectDetails>> callback);
}

