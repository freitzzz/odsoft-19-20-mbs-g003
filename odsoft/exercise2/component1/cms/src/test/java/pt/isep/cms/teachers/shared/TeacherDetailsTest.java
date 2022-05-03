package pt.isep.cms.teachers.shared;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeacherDetailsTest {

  @Test
  public void ensureIdWorksCorrectly() {
    TeacherDetails details = new TeacherDetails("name", 1L);

    assertEquals(1L, details.id());
  }

  @Test
  public void ensureNameWorksCorrectly() {
    TeacherDetails details = new TeacherDetails("name", 1L);

    assertEquals("name", details.name());
  }

}