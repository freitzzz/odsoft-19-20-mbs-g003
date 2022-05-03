package pt.isep.cms.teachers.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.teachers.client.TeachersService;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

@SuppressWarnings("serial")
public class TeachersServiceImpl extends RemoteServiceServlet implements
    TeachersService {
      
  private final HashMap<Long, Teacher> teachers = new HashMap<Long, Teacher>();

  public TeachersServiceImpl() {
  }
  
	@Override
	public Teacher addTeacher(Teacher teacher) {
		
		long id = teachers.size();
		
		id++;
		
		teacher.changeId(id);
		
		teachers.put(id, teacher);
		
		return teacher;
		
	}

	@Override
	public ArrayList<TeacherDetails> getTeacherDetails() {
		ArrayList<TeacherDetails> teacherDetails = new ArrayList<TeacherDetails>();
		
		Iterator<Long> ids = teachers.keySet().iterator();
	    
		while(ids.hasNext()) {
			
			Teacher teacher = teachers.get(ids.next());
			
			teacherDetails.add(new TeacherDetails(teacher.name(), teacher.id()));
			
	    }
		
		return teacherDetails;
	}

	public Boolean deleteTeacher(String id) {
  		System.out.println("ID" + id);
		teachers.remove(Long.valueOf(id));
		return true;
	}

	public ArrayList<TeacherDetails> deleteTeachers(ArrayList<String> ids) {

		for (int i = 0; i < ids.size(); ++i) {
			deleteTeacher(ids.get(i));
		}

		return getTeacherDetails();
	}

	@Override
	public Teacher getTeacherRecordDetails(long id) {
		return teachers.get(id);
	}
}
