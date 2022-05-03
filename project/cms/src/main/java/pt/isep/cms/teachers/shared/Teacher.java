package pt.isep.cms.teachers.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.subjectclasses.shared.SubjectClass;
import pt.isep.cms.subjects.shared.Subject;

/**
 * A model representation for a teacher
 */
@Entity
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private long externalIdentifier;

	// required for database mapping
	@Id
	private long internalIdentifier;

	private String firstName;

	private String lastName;

	private String acronym;

	private Gender gender;

	private Date birthDate;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	private List<SubjectClass> assignedSubjectClasses;

	// Required by GWT
	public Teacher() {
	}

	public Teacher(long externalIdentifier, String firstName, String lastName, String acronym, Gender gender, Date birthDate) {

		grantFirstNameIsNotNull(firstName);

		grantLastNameIsNotNull(lastName);

		grantAcronymIsNotNull(acronym);

		grantGenderIsNotNull(gender);

		grantBirthDateIsNotNull(birthDate);

		grantFirstNameDoesNotContainSpaces(firstName);

		grantLastNameDoesNotContainSpaces(lastName);

		grantAcronymDoesNotContainSpaces(acronym);

		grantFirstNameLengthIsGreaterThanTwo(firstName);

		grantLastNameLengthIsGreaterThanTwo(lastName);

		grantAcronymIsEitherThreeOrFourCharectersLong(acronym);

		grantBirthDateMustBeBeforeThanTodaysDate(birthDate);

		this.firstName = firstName;

		this.lastName = lastName;

		this.acronym = acronym;

		this.gender = gender;

		this.birthDate = birthDate;
		
		this.assignedSubjectClasses = new ArrayList<SubjectClass>();
		
		this.externalIdentifier = externalIdentifier;

	}

	private void grantAcronymIsEitherThreeOrFourCharectersLong(String acronym2) {

		if (acronym2.length() < 3 || acronym2.length() > 4) {
			throw new IllegalArgumentException("acronym has to be either three or four characters long");
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

	public String name() {
		return new StringBuilder().append(firstName).append(' ').append(lastName).toString();
	}

	public void changeId(long id) {
		this.externalIdentifier = id;
	}

	public long id() {
		return externalIdentifier;
	}

	public boolean teachesSubject(Subject subject) {

		for (SubjectClass subjectClass : assignedSubjectClasses) {

			if (subjectClass.referencedSubject().equals(subject)) {

				return true;

			}

		}

		return false;

	}
	
	public void teachSubject(Subject subject, CMSClass cmsClass) {
		
		if(subject == null) {
			throw new IllegalArgumentException("subject cannot be null");
		}
		
		if(cmsClass == null) {
			throw new IllegalArgumentException("class cannot be null");
		}
		
		List<CMSClass> subjectAvailableClasses = subject.availableClasses();
		
		boolean isClassAvailableOnSubject = subjectAvailableClasses.contains(cmsClass);
		
		if(!isClassAvailableOnSubject) {
			
			throw new IllegalStateException("class is not available for the subject");
			
		}
		
		boolean alreadyTeachesSubject = teachesSubject(subject);
		
		if(alreadyTeachesSubject) {
			
			throw new IllegalStateException("teacher already teaches subject");
			
		}
		
		SubjectClass subjectClass = new SubjectClass(subject, cmsClass);
		
		assignedSubjectClasses.add(subjectClass);

	}

	public boolean isAssignedToClass(CMSClass cmsClass) {

		for (SubjectClass subjectClass : assignedSubjectClasses) {

			if (subjectClass.referencedClass() == cmsClass) {

				return true;

			}

		}

		return false;

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

	private void grantGenderIsNotNull(Gender gender2) {

		boolean isNull = gender2 == null;

		if (isNull) {
			throw new IllegalArgumentException("gender cannot be null");
		}

	}

	private void grantLastNameDoesNotContainSpaces(String lastName2) {

		boolean containsSpaces = lastName2.replaceAll("\\s+", "").length() != lastName2.length();

		if (containsSpaces) {
			throw new IllegalArgumentException("last name cannot contain spaces");
		}

	}

	private void grantFirstNameDoesNotContainSpaces(String firstName2) {

		boolean containsSpaces = firstName2.replaceAll("\\s+", "").length() != firstName2.length();

		if (containsSpaces) {
			throw new IllegalArgumentException("first name cannot contain spaces");
		}

	}

	private void grantBirthDateMustBeBeforeThanTodaysDate(Date birthDate2) {

		boolean isBeforeTodaysDate = birthDate2.before(new Date(System.currentTimeMillis()));

		if (!isBeforeTodaysDate) {
			throw new IllegalArgumentException("birth date must be before todays date");
		}

	}

	private void grantLastNameLengthIsGreaterThanTwo(String lastName2) {

		boolean isLastNameLengthGreaterThanTwo = lastName2.length() > 2;

		if (!isLastNameLengthGreaterThanTwo) {
			throw new IllegalArgumentException("last name length must be greater than two");
		}

	}

	private void grantFirstNameLengthIsGreaterThanTwo(String firstName2) {

		boolean isFirstNameLengthGreaterThanTwo = firstName2.length() > 2;

		if (!isFirstNameLengthGreaterThanTwo) {
			throw new IllegalArgumentException("first name length must be greater than two");
		}

	}

	private void grantBirthDateIsNotNull(Date birthDate2) {

		boolean isNull = birthDate2 == null;

		if (isNull) {
			throw new IllegalArgumentException("birth date cannot be null");
		}

	}

	private void grantLastNameIsNotNull(String lastName2) {

		boolean isNull = lastName2 == null;

		if (isNull) {
			throw new IllegalArgumentException("last name cannot be null");
		}

	}

	private void grantFirstNameIsNotNull(String firstName2) {

		boolean isNull = firstName2 == null;

		if (isNull) {
			throw new IllegalArgumentException("first name cannot be null");
		}

	}
}
