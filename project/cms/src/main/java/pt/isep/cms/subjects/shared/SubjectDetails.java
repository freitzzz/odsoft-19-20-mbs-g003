package pt.isep.cms.subjects.shared;

import java.io.Serializable;

/**
 * A detailed model of a subject
 */
public class SubjectDetails implements Serializable{
	
	private static final long serialVersionUID = 13L;
	
	private String name;
	
	public SubjectDetails() {}
	
	public SubjectDetails(String name) {
		
		this.name = name;
	}
	
	public String name() { 
		return name; 
	}
	
}
