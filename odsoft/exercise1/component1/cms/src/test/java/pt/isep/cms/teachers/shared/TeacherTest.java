package pt.isep.cms.teachers.shared;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TeacherTest {
  
  private Teacher teacher;

  @Before
  public void setup() {
    teacher = new Teacher("Jose", "Quim", Gender.FEMALE, new Date(0L));
    teacher.changeId(1L);
  }

  @Test
  public void teacherIdShouldBeOne() {
    assertEquals("id is correct", teacher.id(), 1L);
  }

  @Test
  public void teacherGenderShouldBeFemale() {
    assertEquals("gender is correct", teacher.gender(), "FEMALE");
  }

  @Test
  public void teacherBirthDateShouldBeCorrect() {
    assertEquals("birth date is correct", teacher.birthDate(), new Date(0L));
  }

  @Test(expected = IllegalArgumentException.class)
	public void teacherNullFirstNameThrowsException() {
		new Teacher(null, "last", Gender.FEMALE, new Date(0L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherNullLastNameThrowsException() {
		new Teacher("first", null, Gender.MALE, new Date(0L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherNullGenderThrowsException() {
		new Teacher("first", "last", null, new Date(0L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherNullBirthDateThrowsException() {
		new Teacher("first", "last", Gender.FEMALE, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherFirstNameWithSpacesThrowsException() {
		new Teacher("first            name", "last", Gender.FEMALE, new Date(0L));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherLastNameWithSpacesThrowsException() {
		new Teacher("first", "last     name", Gender.MALE, new Date(0L));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherShortFirstNameWithSpacesThrowsException() {
		new Teacher("fr", "last", Gender.MALE, new Date(0L));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherShortLastNameWithSpacesThrowsException() {
    new Teacher("first", "ss", Gender.MALE, new Date(0L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teacherIncorrectBirthDateThrowsException() {
		new Teacher("first", "last", Gender.MALE, new Date(System.currentTimeMillis() + 10000));
	}
	
}