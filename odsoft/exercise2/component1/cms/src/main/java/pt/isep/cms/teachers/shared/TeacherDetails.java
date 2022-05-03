package pt.isep.cms.teachers.shared;

import java.io.Serializable;

/**
 * A detailed model of a teacher
 */
public class TeacherDetails implements Serializable{
	
	private static final long serialVersionUID = 13L;
	
	private long id;
	private String name;
	
	public TeacherDetails() {}
	
	public TeacherDetails(String name, long id) {
		this.name = name;
		this.id = id;
	}
	
	public String name() { 
		return name; 
	}

	public long id() {
		return id;
	}
	
}
