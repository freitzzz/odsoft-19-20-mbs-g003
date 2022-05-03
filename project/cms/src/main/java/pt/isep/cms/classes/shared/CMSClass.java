package pt.isep.cms.classes.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A model representation for a teacher
 */
@Entity
public class CMSClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private long externalIdentifier;

	// required for database mapping
	@Id
	private long internalIdentifier;
	
	private String acronym;

	private byte maximumCapacity;

	private byte currentStudentsNumber;
	
	
	// Required by GWT
	public CMSClass() {
	}

	public CMSClass(long externalIdentifier, String acronym) {
		
		grantAcronymIsNotNull(acronym);
		
		grantAcronymDoesNotContainSpaces(acronym);
		
		grantAcronymIsThreeCharectersLong(acronym);
		
		this.acronym = acronym;
		
		this.currentStudentsNumber = 0;
		
		this.maximumCapacity = 20;
		
		this.externalIdentifier = externalIdentifier;

	}
	
	public String acronym() {
		return acronym;
	}
	
	public void changeId(long id) {
		this.externalIdentifier = id;
	}

	public long id() {
		return externalIdentifier;
	}
	
	public boolean hasReachedMaximumCapacity() {
		
		return currentStudentsNumber == maximumCapacity;
		
	}
	
	public byte currentCapacity() {
		
		return currentStudentsNumber;
	}
	
	public void addStudent() {
		
		if(hasReachedMaximumCapacity()) {
			
			throw new IllegalStateException("class has reached maximum capacity and cannot accept new students");
			
		}
		
		currentStudentsNumber++;
		
	}
	
	@Override
	public boolean equals(Object toCompare) {
		
		if(toCompare == null) {
			return false;
		}
		
		return toCompare.hashCode() == hashCode();
		
	}
	
	@Override
	public int hashCode() {
		
		return (int)externalIdentifier;
		
	}

	private void grantAcronymIsThreeCharectersLong(String acronym2) {

		if (acronym2.length() != 3) {
			throw new IllegalArgumentException("acronym has to be three characters long");
		}

	}

	private void grantAcronymDoesNotContainSpaces(String acronym2) {

		boolean containsSpaces = acronym2.replaceAll("\\s+", "").length() != acronym2.length();

		if (containsSpaces) {
			throw new IllegalArgumentException("acronym cannot contain spaces");
		}

	}

	private void grantAcronymIsNotNull(String acronym2) {

		if (acronym2 == null) {
			throw new IllegalArgumentException("acronym cannot be null");
		}

	}
}
