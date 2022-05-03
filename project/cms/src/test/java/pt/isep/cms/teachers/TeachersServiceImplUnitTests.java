package pt.isep.cms.teachers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.teachers.server.TeachersServiceImpl;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeachersServiceImplUnitTests {
	
	@Test
	public void testAddTeacherMethodUpdatesTeacherIdCorrectly() {
		
		TeachersServiceImpl teachersService = new TeachersServiceImpl();
		
		int existingTeachersBefore = teachersService.getTeacherDetails().size();
		
		Teacher teacher = new Teacher(0, "firstname","lastname", "ABC", Gender.MALE, new Date(0));
		
		// The teacher identification number is the number of existing teachers before the addition + 1
		long teacherIdExpected = existingTeachersBefore + 1;
		
		long teacherIdBefore = teacher.id();
		
		assertNotEquals(teacherIdExpected, teacherIdBefore);
		
		teachersService.addTeacher(teacher);
		
		long teacherIdAfter = teacher.id();
		
		assertEquals(teacherIdAfter, teacherIdExpected);
	}
	
	@Test
	public void testAddTeacherMethodUpdatesExistingTeachersCorrectly() {
		
		TeachersServiceImpl teachersService = new TeachersServiceImpl();
		
		int existingTeachersBefore = teachersService.getTeacherDetails().size();
		
		assertEquals(existingTeachersBefore, 0);
		
		Teacher teacher = new Teacher(0, "firstname","lastname", "ABC", Gender.MALE, new Date(0));
		
		teachersService.addTeacher(teacher);
		
		int existingTeachersAfter = teachersService.getTeacherDetails().size();
		
		assertEquals(1, existingTeachersAfter);
	}
	
	@Test
	public void testGetTeacherDetailsMethodReturnsZeroIfNotTeachersWerePreviouslyAdded() {
		
		TeachersServiceImpl teachersService = new TeachersServiceImpl();
		
		int existingTeachersExcepted = 0;
		
		int existingTeachers = teachersService.getTeacherDetails().size();
		
		assertEquals(existingTeachersExcepted, existingTeachers);
		
	}
	
	@Test
	public void testGetTeacherDetailsReflectsTeachersAdditionCorrectly() {
		
		TeachersServiceImpl teachersService = new TeachersServiceImpl();
		
		int existingTeachersBefore = teachersService.getTeacherDetails().size();
		
		int existingTeachersExpected = 0;
		
		assertEquals(existingTeachersExpected, existingTeachersBefore);
		
		Teacher teacher = new Teacher(0, "firstname","lastname","ABC", Gender.MALE, new Date(0));
		
		teachersService.addTeacher(teacher);
		
		existingTeachersExpected++;
		
		int existingTeachersAfter = teachersService.getTeacherDetails().size();
		
		assertEquals(existingTeachersExpected, existingTeachersAfter);

	}
	
	@Test
	public void testGetTeacherDetailsCreatesTeacherDetailsModelsWithTeacherName() {
		
		TeachersServiceImpl teachersService = new TeachersServiceImpl();
		
		Teacher teacher = new Teacher(0, "firstname","lastname","ABC", Gender.MALE, new Date(0));
		
		teachersService.addTeacher(teacher);
		
		String teacherName = teacher.name();
		
		TeacherDetails teacherDetails = teachersService.getTeacherDetails().get(0);
		
		String teacherNameInDetaislModel = teacherDetails.name();
		
		assertEquals(teacherNameInDetaislModel, teacherName);
		
	}
}
