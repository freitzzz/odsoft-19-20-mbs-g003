package pt.isep.cms.teachers.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeacherDetailsTest {

	@Test
	public void testNameMethodReturnsTeacherName() {
		String teacherName = "teacher";
		long teacherID = 13321321;

		TeacherDetails teacherDetails = new TeacherDetails(teacherName, teacherID);

		assertEquals(teacherName, teacherDetails.name());
		assertEquals(teacherID, teacherDetails.id());
	}

}
