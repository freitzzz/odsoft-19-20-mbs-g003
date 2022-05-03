package pt.isep.cms.teachers.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

public interface TeachersServiceAsync {

  public void addTeacher(Teacher teacher, AsyncCallback<Teacher> callback);
  
  public void getTeacherDetails(AsyncCallback<ArrayList<TeacherDetails>> callback);
}

