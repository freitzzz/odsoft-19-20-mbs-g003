package pt.isep.cms.students;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.subjects.shared.Subject;

public class StudentModelUnitTests {

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationWithNullFirstNameThrowsIllegalArgumentException() {

		String firstName = null;

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationWithNullLastNameThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = null;

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationWithNullGenderThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = null;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationWithNullBirthDateThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = null;

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationWithNullAssignedClassThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = null;

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationInWhichFirstNameHasSpacesThrowsIllegalArgumentException() {

		String firstName = "with	spaces";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationInWhichLastNameHasSpacesThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "with spaces";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationInWhichFirstNameHasLessThanThreeCharactersThrowsIllegalArgumentException() {

		String firstName = "tw";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationInWhichLastNameHasLessThanThreeCharactersThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "tw";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationInWhichBirthDateIsAfterTodaysDateThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(System.currentTimeMillis() + 10000);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testStudentCreationInAssignedClassHasAlreadyReachedMaximumCapacityThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		for (int i = 0; i < 20; i++) {

			assignedClass.addStudent();

		}

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}
	
	@Test
	public void testStudentCreationUpdatesClassCurrentCapacitySuccessfully() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");
		
		byte currentCapacityBeforeStudentCreation = assignedClass.currentCapacity();

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
		
		byte currentCapacityAfterStudentCreation = assignedClass.currentCapacity();
		
		assertNotEquals(currentCapacityBeforeStudentCreation, currentCapacityAfterStudentCreation);

	}

	@Test
	public void testStudentCreationSucceedsIfAllConditionsAreMet() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

	}

	@Test
	public void testStudentNameMethodReturnsAConcatenatedStringOfFirstAndLastNameWithASpaceAsSeparator() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		String nameReturned = student.name();

		String nameExpected = new StringBuilder().append(firstName).append(' ').append(lastName).toString();

		assertEquals(nameReturned, nameExpected);

	}
	
	@Test
	public void testStudentAssignedClassMethodReturnsClassPassedOnConstructor() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		CMSClass studentAssignedClass = student.assignedClass();

		assertEquals(assignedClass, studentAssignedClass);

	}

	@Test
	public void testStudentChangeIdMethodUpdatesStudentIdentificationNumberCorrectly() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		long idBefore = student.id();

		long newId = 50l;

		assertNotEquals(idBefore, newId);

		student.changeId(newId);

		long idAfter = student.id();

		assertEquals(newId, idAfter);

	}

	@Test
	public void testEqualsMethodReturnsFalseIfNullParameterIsPassed() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		Student studentToCompare = null;

		boolean isEqual = student.equals(studentToCompare);

		assertFalse(isEqual);

	}

	@Test
	public void testEqualsMethodReturnsTrueIfStudentWithSameHashCodeIsPassedAsParameter() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		String firstName2 = "firstname";

		long externalIdentifier2 = 1;

		String lastName2 = "lastnadsdme";

		Gender gender2 = Gender.MALE;

		Date birthDate2 = new Date(0);

		CMSClass assignedClass2 = new CMSClass(1, "M1E");

		Student studentToCompare = new Student(externalIdentifier2, firstName2, lastName2, gender2, birthDate2,
				assignedClass2);

		boolean isEqual = student.equals(studentToCompare);

		assertTrue(isEqual);

	}

	@Test
	public void testHashCodeMethodReturnsStudentExternalIdentifier() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		long studentHashCode = student.hashCode();

		assertEquals(externalIdentifier, studentHashCode);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnrolSubjectMethodThrowsIllegalArgumentExceptionIfSubjectParameterIsNull() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		Subject subject = null;

		student.enrolSubject(subject);

	}

	@Test(expected = IllegalStateException.class)
	public void testEnrolSubjectMethodThrowsIllegalStateExceptionIfStudentAssignedClassIsNotAvailableOnSubject() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(2, "M1E");

		subject.addClass(cmsClass);

		student.enrolSubject(subject);

	}

	@Test(expected = IllegalStateException.class)
	public void testEnrolSubjectMethodThrowsIllegalStateExceptionIfStudentHasAlreadyEnroledInTheSubject() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		student.enrolSubject(subject);

		Subject subject2 = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass2 = new CMSClass(1, "M1E");

		subject2.addClass(cmsClass2);

		student.enrolSubject(subject2);

	}

	public void testEnrolSubjectMethodCompletesSuccessfullyIfAllConditionsAreMet() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		student.enrolSubject(subject);

	}

}
