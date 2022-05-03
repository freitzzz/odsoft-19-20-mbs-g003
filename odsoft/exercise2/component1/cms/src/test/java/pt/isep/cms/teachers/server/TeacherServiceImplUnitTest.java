package pt.isep.cms.teachers.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import pt.isep.cms.teachers.shared.Gender;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeacherServiceImplUnitTest {
  private TeachersServiceImpl teacherService;

  @Before
  public void setup() {
    teacherService = new TeachersServiceImpl();
    Teacher teacher = new Teacher("Jose", "Quim", Gender.FEMALE, new Date(0L));
    teacherService.addTeacher(teacher);
  }

  @Test
  public void ensureGetTeacherRecordDetailsReturnsTheCorrectTeacher() {
    Teacher retrievedTeacher = teacherService.getTeacherRecordDetails(1L);

    assertEquals(retrievedTeacher.id(), 1L);
    assertEquals(retrievedTeacher.birthDate(), new Date(0L));
    assertEquals(retrievedTeacher.gender(), "FEMALE");
    assertEquals(retrievedTeacher.name(), "Jose Quim");
  }

  @Test
  public void ensureAddTeacherWorksCorrectly() {
    Teacher toAdd = new Teacher("Jose", "Quim", Gender.FEMALE, new Date(0L));
    Teacher addedTeacher = teacherService.addTeacher(toAdd);

    assertEquals(addedTeacher.birthDate(), new Date(0L));
    assertEquals(addedTeacher.gender(), "FEMALE");
    assertEquals(addedTeacher.name(), "Jose Quim");
  }

  @Test
  public void ensureGetTeacherDetailsWorksCorrectly() {
    ArrayList<TeacherDetails> teacherDetails = teacherService.getTeacherDetails();

    assertTrue("teacher details size is correct", teacherDetails.size() == 1);
  }

}