package pt.isep.cms.students.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

public interface StudentsServiceAsync {

  public void addStudent(Student student, AsyncCallback<Student> callback);
  
  public void getStudentDetails(AsyncCallback<ArrayList<StudentDetails>> callback);
}

