package pt.isep.cms.subjects.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import pt.isep.cms.classes.shared.CMSClass;

/**
 * A model representation for a teacher
 */
@Entity
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	private long externalIdentifier;

	// required for database mapping
	@Id
	private long internalIdentifier;

	private String name;

	private String acronym;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	private List<CMSClass> availableClasses;

	// Required by GWT
	public Subject() {
	}

	public Subject(long externalIdentifier, String name, String acronym) {

		grantNameIsNotNull(name);

		grantAcronymIsNotNull(acronym);

		grantAcronymDoesNotContainSpaces(acronym);

		grantNameLengthIsGreaterThanFive(name);

		grantAcronymIsAtLeastThreeCharactersLong(acronym);

		this.name = name;

		this.acronym = acronym;

		this.availableClasses = new ArrayList<>();
		
		this.externalIdentifier = externalIdentifier;

	}
	
	public void addClass(CMSClass cmsClass) {
		
		if(cmsClass == null) {
			
			throw new IllegalArgumentException("cannot add null class");
			
		}
		
		if(availableClasses.contains(cmsClass)) {
			
			throw new IllegalStateException("subject already contains class");
			
		}
		
		availableClasses.add(cmsClass);
		
	}
	
	public String name() {
		return name;
	}

	public void changeId(long id) {
		this.externalIdentifier = id;
	}

	public long id() {
		return externalIdentifier;
	}
	
	public List<CMSClass> availableClasses(){
		
		return new ArrayList<CMSClass>(availableClasses);
		
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

	private void grantNameLengthIsGreaterThanFive(String name2) {

		if (name2.length() < 5) {
			throw new IllegalArgumentException("name length needs to greater than five");
		}

	}

	private void grantAcronymIsAtLeastThreeCharactersLong(String acronym2) {

		if (acronym2.length() < 3) {
			throw new IllegalArgumentException("acronym has to be at least three characters long");
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

	private void grantNameIsNotNull(String name2) {

		boolean isNull = name2 == null;

		if (isNull) {
			throw new IllegalArgumentException("name cannot be null");
		}

	}
}
