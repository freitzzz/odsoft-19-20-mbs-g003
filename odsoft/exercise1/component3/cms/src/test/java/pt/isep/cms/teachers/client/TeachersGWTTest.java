/*
package pt.isep.cms.teachers.client;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.teachers.shared.Gender;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeachersGWTTest extends GWTTestCase {

  private TeachersServiceAsync rpcService;

  @Override
  public String getModuleName() {
    return "pt.isep.cms.teachers.TestCMSJUnit";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    rpcService = GWT.create(TeachersService.class);
  }

  @Test
  public void testDeleteTeacher() {
*/
/*
    String firstName = "quim";

    String lastName = "gebo";

    Gender gender = Gender.MALE;

    Date birthDate = new Date(0);

    Teacher teacher = new Teacher(firstName, lastName, gender, birthDate);

    long teacherIdBeforeAdd = teacher.id();

    ServiceDefTarget target = (ServiceDefTarget) rpcService;

    target.setServiceEntryPoint(GWT.getModuleBaseURL() + "teachers/teachersService");

    delayTestFinish(2000);

    rpcService.addTeacher(teacher, new AsyncCallback<Teacher>() {

      @Override
      public void onSuccess(Teacher result) {

        //After a successful add the id of the teacher should be different of the one before adding

        long teacherIdAfterAdd = result.id();

        assertNotSame(teacherIdBeforeAdd, teacherIdAfterAdd);

        ArrayList<String> ids = new ArrayList<>();
        ids.add(String.valueOf(teacher.id()));

        rpcService.deleteTeachers(ids, new AsyncCallback<ArrayList<TeacherDetails>>() {
          @Override
          public void onFailure(Throwable caught) {
            fail(caught.getMessage());
          }

          @Override
          public void onSuccess(ArrayList<TeacherDetails> result) {
            //After a successful add the id of the teacher should be different of the one before adding

            assertEquals(result.size(), 0);
            finishTest();
          }
        });

      }

      @Override
      public void onFailure(Throwable caught) {
        fail(caught.getMessage());
      }
    });*//*

  }

}
*/
