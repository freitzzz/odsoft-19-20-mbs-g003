package pt.isep.cms.teachers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.teachers.shared.Teacher;

public class TeacherModelUnitTests {

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullFirstNameThrowsIllegalArgumentException() {

		String firstName = null;

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullLastNameThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = null;

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullAcronymThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = null;

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullGenderThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = null;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullBirthDateThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = null;

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichFirstNameHasSpacesThrowsIllegalArgumentException() {

		String firstName = "with	spaces";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichLastNameHasSpacesThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "with spaces";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichAcronymHasSpacesThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "withspaces";

		String acronym = "AB C";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichFirstNameHasLessThanThreeCharactersThrowsIllegalArgumentException() {

		String firstName = "tw";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichLastNameHasLessThanThreeCharactersThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "tw";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichAcronymLengthIsLessThanThreeThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "AB";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichAcronymLengthIsGreaterThanFourThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABCDE";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichBirthDateIsAfterTodaysDateThrowsIllegalArgumentException() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(System.currentTimeMillis() + 10000);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test
	public void testTeacherCreationSucceedsIfAllConditionsAreMet() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

	}

	@Test
	public void testTeacherNameMethodReturnsAConcatenatedStringOfFirstAndLastNameWithASpaceAsSeparator() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		String nameReturned = teacher.name();

		String nameExpected = new StringBuilder().append(firstName).append(' ').append(lastName).toString();

		assertEquals(nameReturned, nameExpected);

	}

	@Test
	public void testTeacherChangeIdMethodUpdatesTeacherIdentificationNumberCorrectly() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		long idBefore = teacher.id();

		long newId = 50l;

		assertNotEquals(idBefore, newId);

		teacher.changeId(newId);

		long idAfter = teacher.id();

		assertEquals(newId, idAfter);

	}

	@Test
	public void testTeacherTeachesSubjectMethodReturnsFalseIfTeacherIsntAssignedToAnySubject() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		boolean teachesSubject = teacher.teachesSubject(subject);

		assertFalse(teachesSubject);

	}

	@Test
	public void testTeacherTeachesSubjectMethodReturnsFalseIfTeacherAssignedSubjectClassesDontContainSubjectPassedByParameter() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		Subject subject2 = new Subject(2, "Organização do Desenvolvimento de Software", "ODSOFT");

		teacher.teachSubject(subject, cmsClass);

		boolean teachesSubject = teacher.teachesSubject(subject2);

		assertFalse(teachesSubject);
	}

	@Test
	public void testTeacherTeachesSubjectMethodReturnsTrueIfTeacherAssignedSubjectClassesContainSubjectPassedByParameter() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		teacher.teachSubject(subject, cmsClass);

		Subject subject2 = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass2 = new CMSClass(1, "M1E");

		subject2.addClass(cmsClass2);

		boolean teachesSubject = teacher.teachesSubject(subject2);

		assertTrue(teachesSubject);
	}

	@Test
	public void testTeacherIsAssignedToClassMethodReturnsFalseIfTeacherIsntAssignedToAnyClass() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		CMSClass cmsClass = new CMSClass(1, "M1E");

		boolean teachesSubject = teacher.isAssignedToClass(cmsClass);

		assertFalse(teachesSubject);

	}

	@Test
	public void testTeacherIsAssignedToClassReturnsFalseIfTeacherAssignedSubjectClassesDontContainClassPassedByParameter() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		CMSClass cmsClass2 = new CMSClass(2, "M1E");

		teacher.teachSubject(subject, cmsClass);

		boolean teachesSubject = teacher.isAssignedToClass(cmsClass2);

		assertFalse(teachesSubject);
	}

	@Test
	public void testTeacherIsAssignedToClassMethodReturnsTrueIfTeacherAssignedSubjectClassesContainClassPassedByParameter() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		teacher.teachSubject(subject, cmsClass);

		boolean teachesSubject = teacher.isAssignedToClass(cmsClass);

		assertTrue(teachesSubject);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherTeachSubjectMethodThrowsIllegalArgumentExceptionIfSubjectParameterIsNull() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = null;

		CMSClass cmsClass = new CMSClass(1, "M1E");

		teacher.teachSubject(subject, cmsClass);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeacherTeachSubjectMethodThrowsIllegalArgumentExceptionIfClassParameterIsNull() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = null;

		teacher.teachSubject(subject, cmsClass);

	}

	@Test(expected = IllegalStateException.class)
	public void testTeacherTeachSubjectMethodThrowsIllegalStateExceptionIfClassIsNotAvailableInSubject() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		CMSClass cmsClass2 = new CMSClass(2, "M1E");

		teacher.teachSubject(subject, cmsClass2);

	}

	@Test(expected = IllegalStateException.class)
	public void testTeacherTeachSubjectMethodThrowsIllegalStateExceptionIfTeacherAlreadyTeachesTheSubject() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		teacher.teachSubject(subject, cmsClass);

		Subject subject2 = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass2 = new CMSClass(1, "M1E");

		subject2.addClass(cmsClass2);

		teacher.teachSubject(subject2, cmsClass2);

	}

	@Test
	public void testTeacherTeachSubjectMethodDoesntThrowExceptionIfAllSubjectConditionsAreMet() {

		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);

		Subject subject = new Subject(1, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		teacher.teachSubject(subject, cmsClass);

		Subject subject2 = new Subject(2, "Organização do Desenvolvimento de Software", "ODSOFT");

		CMSClass cmsClass2 = new CMSClass(2, "M1E");

		subject2.addClass(cmsClass2);

		teacher.teachSubject(subject2, cmsClass2);

	}

	@Test
	public void testEqualsMethodReturnsFalseIfNullParameterIsPassed() {
		
		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
		
		Teacher teacherToCompare = null;
		
		boolean isEqual = teacher.equals(teacherToCompare);
		
		assertFalse(isEqual);
		
	}
	
	@Test
	public void testEqualsMethodReturnsTrueIfTeacherWithSameHashCodeIsPassedAsParameter() {
		
		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
		
		String firstName2 = "firstname";

		long externalIdentifier2 = 1;

		String lastName2 = "lastnadsdme";

		String acronym2 = "ABC";

		Gender gender2 = Gender.MALE;

		Date birthDate2 = new Date(0);

		Teacher teacherToCompare = new Teacher(externalIdentifier2, firstName2, lastName2, acronym2, gender2, birthDate2);
		
		boolean isEqual = teacher.equals(teacherToCompare);
		
		assertTrue(isEqual);
		
	}
	
	@Test
	public void testHashCodeMethodReturnsTeacherExternalIdentifier() {
		
		String firstName = "firstname";

		long externalIdentifier = 1;

		String lastName = "lastname";

		String acronym = "ABC";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		Teacher teacher = new Teacher(externalIdentifier, firstName, lastName, acronym, gender, birthDate);
		
		long teacherHashCode = teacher.hashCode();
		
		assertEquals(externalIdentifier, teacherHashCode);
		
	}

}
