package pt.isep.cms.teachers.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

@RemoteServiceRelativePath("teachersService")
public interface TeachersService extends RemoteService {
	
  Teacher addTeacher(Teacher teacher);
  
  ArrayList<TeacherDetails> getTeacherDetails();
}
