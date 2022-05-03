package pt.isep.cms.teachers.shared;

import java.io.Serializable;
import java.util.Date;


/**
 * A model representation for a teacher
 */
public class Teacher implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private Gender gender;
	
	private Date birthDate;
	
	// Required by GWT
	public Teacher() {}
	
	public Teacher(String firstName, String lastName, Gender gender, Date birthDate) {
		
		grantFirstNameIsNotNull(firstName);
		
		grantLastNameIsNotNull(lastName);
		
		grantGenderIsNotNull(gender);
		
		grantBirthDateIsNotNull(birthDate);
		
		grantFirstNameDoesNotContainSpaces(firstName);
		
		grantLastNameDoesNotContainSpaces(lastName);
		
		
		grantFirstNameLengthIsGreaterThanTwo(firstName);
		
		grantLastNameLengthIsGreaterThanTwo(lastName);
		
		grantBirthDateMustBeBeforeThanTodaysDate(birthDate);
		
		this.firstName = firstName;
		
		this.lastName = lastName;
		
		this.gender = gender;
		
		this.birthDate = birthDate;
		
	}
	
	public String name() {
		return new StringBuilder()
				.append(firstName)
				.append(' ')
				.append(lastName)
				.toString();
	}
	
	public void changeId(long id) {
		this.id = id;
	}
	
	public long id() {
		return id;
	}
	
	private void grantGenderIsNotNull(Gender gender2) {
		
		boolean isNull = gender2 == null;
		
		if(isNull) {
			throw new IllegalArgumentException("gender cannot be null");
		}
		
	}


	private void grantLastNameDoesNotContainSpaces(String lastName2) {
		
		boolean containsSpaces = lastName2.replaceAll("\\s+","").length() != lastName2.length();
		
		if(containsSpaces) {
			throw new IllegalArgumentException("last name cannot contain spaces");
		}
		
	}


	private void grantFirstNameDoesNotContainSpaces(String firstName2) {
		
		boolean containsSpaces = firstName2.replaceAll("\\s+","").length() != firstName2.length();
		
		if(containsSpaces) {
			throw new IllegalArgumentException("first name cannot contain spaces");
		}
		
	}


	private void grantBirthDateMustBeBeforeThanTodaysDate(Date birthDate2) {
		
		boolean isBeforeTodaysDate = birthDate2.before(new Date(System.currentTimeMillis()));
		
		if(!isBeforeTodaysDate) {
			throw new IllegalArgumentException("birth date must be before todays date");
		}
		
	}


	private void grantLastNameLengthIsGreaterThanTwo(String lastName2) {
		
		boolean isLastNameLengthGreaterThanTwo = lastName2.length() > 2;
		
		if(!isLastNameLengthGreaterThanTwo) {
			throw new IllegalArgumentException("last name length must be greater than two");
		}
		
	}


	private void grantFirstNameLengthIsGreaterThanTwo(String firstName2) {
		
		boolean isFirstNameLengthGreaterThanTwo = firstName2.length() > 2;
		
		if(!isFirstNameLengthGreaterThanTwo) {
			throw new IllegalArgumentException("first name length must be greater than two");
		}
		
	}


	private void grantBirthDateIsNotNull(Date birthDate2) {
		
		boolean isNull = birthDate2 == null;
		
		if(isNull) {
			throw new IllegalArgumentException("birth date cannot be null");
		}
		
	}


	private void grantLastNameIsNotNull(String lastName2) {
		
		boolean isNull = lastName2 == null;
		
		if(isNull) {
			throw new IllegalArgumentException("last name cannot be null");
		}
		
	}


	private void grantFirstNameIsNotNull(String firstName2) {
		
		boolean isNull = firstName2 == null;
		
		if(isNull) {
			throw new IllegalArgumentException("first name cannot be null");
		}
		
	}
}
