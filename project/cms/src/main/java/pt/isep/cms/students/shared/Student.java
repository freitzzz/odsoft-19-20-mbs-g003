package pt.isep.cms.students.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.subjects.shared.Subject;

/**
 * A model representation for a teacher
 */
@Entity
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private long externalIdentifier;

	// required for database mapping
	@Id
	private long internalIdentifier;

	private String firstName;

	private String lastName;

	private Gender gender;

	private Date birthDate;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	private List<Subject> enroledSubjects;
	
	@ManyToOne
	public CMSClass assignedClass;

	// Required by GWT
	public Student() {
	}

	public Student(long externalIdentifier, String firstName, String lastName, Gender gender, Date birthDate, CMSClass assignedClass) {

		grantFirstNameIsNotNull(firstName);

		grantLastNameIsNotNull(lastName);

		grantGenderIsNotNull(gender);

		grantBirthDateIsNotNull(birthDate);
		
		grantClassIsNotNull(assignedClass);

		grantFirstNameDoesNotContainSpaces(firstName);

		grantLastNameDoesNotContainSpaces(lastName);

		grantFirstNameLengthIsGreaterThanTwo(firstName);

		grantLastNameLengthIsGreaterThanTwo(lastName);

		grantBirthDateMustBeBeforeThanTodaysDate(birthDate);
		
		grantClassHasntReachedMaximumCapacity(assignedClass);
		
		assignedClass.addStudent();
		
		this.firstName = firstName;

		this.lastName = lastName;

		this.gender = gender;

		this.birthDate = birthDate;
		
		this.assignedClass = assignedClass;

		this.enroledSubjects = new ArrayList<>();
		
		this.externalIdentifier = externalIdentifier;

	}
	
	public String name() {
		return new StringBuilder().append(firstName).append(' ').append(lastName).toString();
	}
	
	public CMSClass assignedClass() {
		
		return assignedClass;
		
	}

	public void changeId(long id) {
		this.externalIdentifier = id;
	}

	public long id() {
		return externalIdentifier;
	}

	public void enrolSubject(Subject subject2) {
		
		if(subject2 == null) {
			
			throw new IllegalArgumentException("subject to be enroled cannot be null");
			
		}
		
		List<CMSClass> subjectAvailableClasses = subject2.availableClasses();
		
		boolean isClassAvailableOnSubject = subjectAvailableClasses.contains(assignedClass);
		
		if(!isClassAvailableOnSubject) {
			
			throw new IllegalStateException("student class is not available on subject");
			
		}
		
		if(enroledSubjects.contains(subject2)) {
			
			throw new IllegalStateException("student has already enroled the subject");
			
		}
		
		enroledSubjects.add(subject2);
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

	private void grantClassHasntReachedMaximumCapacity(CMSClass assignedClass2) {
		
		if(assignedClass2.hasReachedMaximumCapacity()) {
			
			throw new IllegalArgumentException("class has already reached the maximum capacity of students");
			
		}
		
	}

	private void grantClassIsNotNull(CMSClass assignedClass2) {
		
		boolean isNull = assignedClass2 == null;
		
		if(isNull) {
			
			throw new IllegalArgumentException("assigned class cannot be null");
			
		}
		
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
