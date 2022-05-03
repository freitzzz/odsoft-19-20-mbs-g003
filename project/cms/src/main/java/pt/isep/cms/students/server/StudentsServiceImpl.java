package pt.isep.cms.students.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

@SuppressWarnings("serial")
public class StudentsServiceImpl extends RemoteServiceServlet implements
    StudentsService {
      
  private final HashMap<Long, Student> students = new HashMap<Long, Student>();

  public StudentsServiceImpl() {
    initStudents();
  }
  
  private void initStudents() {
	  
  }

	@Override
	public Student addStudent(Student student) {
		
		long id = students.size();
		
		id++;
		
		student.changeId(id);
		
		students.put(id, student);
		
		return student;
		
	}

	@Override
	public ArrayList<StudentDetails> getStudentDetails() {
		ArrayList<StudentDetails> studentDetails = new ArrayList<StudentDetails>();
		
		Iterator<Long> ids = students.keySet().iterator();
	    
		while(ids.hasNext()) {
			
			Student student = students.get(ids.next());
			
			studentDetails.add(new StudentDetails(student.name()));
			
	    }
		
		return studentDetails;
	}
}
