package pt.isep.cms.teachers.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import pt.isep.cms.teachers.shared.Gender;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeachersServiceImplTest {
    @Test
    public void ensureGetTeacherRecordDetailsReturnsTheCorrectTeacher() {
        TeachersServiceImpl teacherService = new TeachersServiceImpl();
        Date date = new Date(1L);
        Teacher teacher = new Teacher("Pedro", "Quim", Gender.MALE, date);
        teacherService.addTeacher(teacher);

        Teacher retrievedTeacher = teacherService.getTeacherRecordDetails(1L);

        assertEquals(retrievedTeacher.id(), 1L);
        assertEquals(retrievedTeacher.birthDate(), date);
        assertEquals(retrievedTeacher.gender(), "MALE");
        assertEquals(retrievedTeacher.name(), "Pedro Quim");
    }

    @Test
    public void ensureAddTeacherWorksCorrectly() {
        TeachersServiceImpl teacherService = new TeachersServiceImpl();
        Date date = new Date(1L);
        Teacher teacher = new Teacher("Pedro", "Quim", Gender.MALE, date);
        teacherService.addTeacher(teacher);

        assertEquals(teacher.birthDate(), date);
        assertEquals(teacher.gender(), "MALE");
        assertEquals(teacher.name(), "Pedro Quim");
    }

    @Test
    public void ensureGetTeacherDetailsWorksCorrectly() {
        TeachersServiceImpl teacherService = new TeachersServiceImpl();
        Date date = new Date(1L);
        Teacher teacher = new Teacher("Pedro", "Quim", Gender.MALE, date);
        teacherService.addTeacher(teacher);

        ArrayList<TeacherDetails> teacherDetails = teacherService.getTeacherDetails();

        assertTrue("teacher details size is correct", teacherDetails.size() == 1);
    }

    @Test
    public void ensureDeleteTeacherWorksCorrectly() {
        TeachersServiceImpl teacherService = new TeachersServiceImpl();
        Date date = new Date(1L);
        Teacher teacher = new Teacher("Pedro", "Quim", Gender.MALE, date);
        teacherService.addTeacher(teacher);

        assertTrue(teacherService.getTeacherDetails().size() == 1);

        teacherService.deleteTeacher(String.valueOf(teacher.id()));

        assertTrue(teacherService.getTeacherDetails().size() == 0);
    }
}