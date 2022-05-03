package pt.isep.cms.classes.shared;

import java.io.Serializable;

/**
 * A detailed model of a class
 */
public class CMSClassDetails implements Serializable{
	
	private static final long serialVersionUID = 13L;
	
	private String name;
	
	private long externalIdentifier;
	
	public CMSClassDetails() {}
	
	public CMSClassDetails(String name, long externalIdentifier) {
		
		this.name = name;
		
		this.externalIdentifier = externalIdentifier;
	}
	
	public String name() { 
		return name; 
	}
	
	public long externalIdentifier() { 
		return externalIdentifier; 
	}
	
}
