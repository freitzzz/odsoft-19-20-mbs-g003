package pt.isep.cms.students.shared;

import java.io.Serializable;

/**
 * A detailed model of a student
 */
public class StudentDetails implements Serializable{
	
	private static final long serialVersionUID = 13L;
	
	private String name;
	
	public StudentDetails() {}
	
	public StudentDetails(String name) {
		
		this.name = name;
	}
	
	public String name() { 
		return name; 
	}
	
}
