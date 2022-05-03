package pt.isep.cms.students.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

@RemoteServiceRelativePath("studentsService")
public interface StudentsService extends RemoteService {
	
  Student addStudent(Student student);
  
  ArrayList<StudentDetails> getStudentDetails();
}
