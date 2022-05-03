package pt.isep.cms.teachers.shared;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.teachers.shared.Gender;
import pt.isep.cms.teachers.shared.Teacher;

public class TeacherTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullFirstNameThrowsIllegalArgumentException() {
		
		String firstName = null;
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullLastNameThrowsIllegalArgumentException() {
		
		String firstName = "firstname";
		
		String lastName = null;
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullGenderThrowsIllegalArgumentException() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
		Gender gender = null;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationWithNullBirthDateThrowsIllegalArgumentException() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = null;
		
		new Teacher(firstName, lastName, gender, birthDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichFirstNameHasSpacesThrowsIllegalArgumentException() {
		
		String firstName = "with	spaces";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichLastNameHasSpacesThrowsIllegalArgumentException() {
		
		String firstName = "firstname";
		
		String lastName = "with spaces";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichFirstNameHasLessThanThreeCharactersThrowsIllegalArgumentException() {
		
		String firstName = "tw";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichLastNameHasLessThanThreeCharactersThrowsIllegalArgumentException() {
		
		String firstName = "firstname";
		
		String lastName = "tw";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTeacherCreationInWhichBirthDateIsAfterTodaysDateThrowsIllegalArgumentException() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(System.currentTimeMillis() + 10000);
		
		new Teacher(firstName, lastName, gender, birthDate);
		
	}
	
	@Test
	public void testTeacherCreationSucceedsIfAllConditionsAreMet() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		new Teacher(firstName, lastName, gender, birthDate);
		
	}
	
	@Test
	public void testTeacherNameMethodReturnsAConcatenatedStringOfFirstAndLastNameWithASpaceAsSeparator() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		Teacher teacher = new Teacher(firstName, lastName, gender, birthDate);
		
		String nameReturned = teacher.name();
		
		String nameExpected = new StringBuilder()
				.append(firstName)
				.append(' ')
				.append(lastName)
				.toString();
		
		assertEquals(nameReturned, nameExpected);
		
	}
	
	@Test
	public void testTeacherChangeIdMethodUpdatesTeacherIdentificationNumberCorrectly() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
		Gender gender = Gender.MALE;
		
		Date birthDate = new Date(0);
		
		Teacher teacher = new Teacher(firstName, lastName, gender, birthDate);
		
		long idBefore = teacher.id();
		
		long newId = 50l;
		
		assertNotEquals(idBefore, newId);
		
		teacher.changeId(newId);
		
		long idAfter = teacher.id();
		
		assertEquals(newId, idAfter);
		
	}

}
