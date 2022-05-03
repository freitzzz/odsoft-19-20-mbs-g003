package pt.isep.cms.subjects.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.subjects.shared.SubjectDetails;

@RemoteServiceRelativePath("subjectsService")
public interface SubjectsService extends RemoteService {
	
  Subject addSubject(Subject subject);
  
  ArrayList<SubjectDetails> getSubjectDetails();
}
