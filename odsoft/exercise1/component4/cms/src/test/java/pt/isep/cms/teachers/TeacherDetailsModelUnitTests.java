package pt.isep.cms.teachers;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeacherDetailsModelUnitTests {

	@Test
	public void testNameMethodReturnsTeacherName() {
		
		String teacherName = "teacher";
		
		TeacherDetails teacherDetails = new TeacherDetails(teacherName);
		
		String nameReturned = teacherDetails.name();
		
		assertEquals(teacherName, nameReturned);
		
	}

}
