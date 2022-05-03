package pt.isep.cms.teachers.shared;

import java.io.Serializable;

/**
 * A detailed model of a teacher
 */
public class TeacherDetails implements Serializable{
	
	private static final long serialVersionUID = 13L;
	
	private String name;
	
	public TeacherDetails() {}
	
	public TeacherDetails(String name) {
		
		this.name = name;
	}
	
	public String name() { 
		return name; 
	}
	
}
