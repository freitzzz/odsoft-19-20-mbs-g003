package pt.isep.cms.students;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.students.server.StudentsServiceImpl;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

public class StudentsServiceImplUnitTests {
	
	@Test
	public void testAddStudentMethodUpdatesStudentIdCorrectly() {
		
		StudentsServiceImpl studentsService = new StudentsServiceImpl();
		
		int existingStudentsBefore = studentsService.getStudentDetails().size();
		
		String firstName = "firstname";

		long externalIdentifier = 0;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
		
		// The student identification number is the number of existing students before the addition + 1
		long studentIdExpected = existingStudentsBefore + 1;
		
		long studentIdBefore = student.id();
		
		assertNotEquals(studentIdExpected, studentIdBefore);
		
		studentsService.addStudent(student);
		
		long studentIdAfter = student.id();
		
		assertEquals(studentIdAfter, studentIdExpected);
	}
	
	@Test
	public void testAddStudentMethodUpdatesExistingStudentsCorrectly() {
		
		StudentsServiceImpl studentsService = new StudentsServiceImpl();
		
		int existingStudentsBefore = studentsService.getStudentDetails().size();
		
		assertEquals(existingStudentsBefore, 0);
		
		String firstName = "firstname";

		long externalIdentifier = 0;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
		
		studentsService.addStudent(student);
		
		int existingStudentsAfter = studentsService.getStudentDetails().size();
		
		assertEquals(1, existingStudentsAfter);
	}
	
	@Test
	public void testGetStudentDetailsMethodReturnsZeroIfNotStudentsWerePreviouslyAdded() {
		
		StudentsServiceImpl studentsService = new StudentsServiceImpl();
		
		int existingStudentsExcepted = 0;
		
		int existingStudents = studentsService.getStudentDetails().size();
		
		assertEquals(existingStudentsExcepted, existingStudents);
		
	}
	
	@Test
	public void testGetStudentDetailsReflectsStudentsAdditionCorrectly() {
		
		StudentsServiceImpl studentsService = new StudentsServiceImpl();
		
		int existingStudentsBefore = studentsService.getStudentDetails().size();
		
		int existingStudentsExpected = 0;
		
		assertEquals(existingStudentsExpected, existingStudentsBefore);
		
		String firstName = "firstname";

		long externalIdentifier = 0;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
		
		studentsService.addStudent(student);
		
		existingStudentsExpected++;
		
		int existingStudentsAfter = studentsService.getStudentDetails().size();
		
		assertEquals(existingStudentsExpected, existingStudentsAfter);

	}
	
	@Test
	public void testGetStudentDetailsCreatesStudentDetailsModelsWithStudentName() {
		
		StudentsServiceImpl studentsService = new StudentsServiceImpl();
		
		String firstName = "firstname";

		long externalIdentifier = 0;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
		
		studentsService.addStudent(student);
		
		String studentName = student.name();
		
		StudentDetails studentDetails = studentsService.getStudentDetails().get(0);
		
		String studentNameInDetaislModel = studentDetails.name();
		
		assertEquals(studentNameInDetaislModel, studentName);
		
	}
}
