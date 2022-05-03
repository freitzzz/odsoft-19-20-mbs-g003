package pt.isep.cms.subjects.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.subjects.client.SubjectsService;
import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.subjects.shared.SubjectDetails;

@SuppressWarnings("serial")
public class SubjectsServiceImpl extends RemoteServiceServlet implements
    SubjectsService {
      
  private final HashMap<Long, Subject> subjects = new HashMap<Long, Subject>();

  public SubjectsServiceImpl() {
    initSubjects();
  }
  
  private void initSubjects() {
	  
  }

	@Override
	public Subject addSubject(Subject subject) {
		
		long id = subjects.size();
		
		id++;
		
		subject.changeId(id);
		
		subjects.put(id, subject);
		
		return subject;
		
	}

	@Override
	public ArrayList<SubjectDetails> getSubjectDetails() {
		ArrayList<SubjectDetails> subjectDetails = new ArrayList<SubjectDetails>();
		
		Iterator<Long> ids = subjects.keySet().iterator();
	    
		while(ids.hasNext()) {
			
			Subject subject = subjects.get(ids.next());
			
			subjectDetails.add(new SubjectDetails(subject.name()));
			
	    }
		
		return subjectDetails;
	}
}
