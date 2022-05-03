package pt.isep.cms.teachers.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.teachers.client.presenter.ViewTeacherDetailsPresenter;
import pt.isep.cms.teachers.client.view.TeacherDetailsDialog;
import pt.isep.cms.teachers.shared.Gender;
import pt.isep.cms.teachers.shared.Teacher;
import pt.isep.cms.teachers.shared.TeacherDetails;

public class TeachersGWTTest extends GWTTestCase {
  private ViewTeacherDetailsPresenter teacherDetailsPresenter;
	private TeachersServiceAsync rpcService;
	private HandlerManager eventBus;
	private ViewTeacherDetailsPresenter.Display mockDisplay;

  @Override
  public String getModuleName() {
    return "pt.isep.cms.teachers.TestCMSJUnit";
  }

  public void gwtSetUp() {
		rpcService = GWT.create(TeachersService.class);
    mockDisplay = new TeacherDetailsDialog();
    teacherDetailsPresenter = new ViewTeacherDetailsPresenter(rpcService, eventBus, mockDisplay, 1L);
  }
  
  public void testGetTeacherRecordDetails() {
    TeachersServiceAsync rpcService = GWT.create(TeachersService.class);

    ServiceDefTarget target = (ServiceDefTarget) rpcService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "teachers/teachersService");
    
    delayTestFinish(10000);

    rpcService.addTeacher(new Teacher("Jose", "Antonio", Gender.FEMALE, new Date(0L)), new AsyncCallback<Teacher>() {

      @Override
      public void onFailure(Throwable caught) {
        // The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
      }

      @Override
      public void onSuccess(Teacher result) {
        rpcService.getTeacherRecordDetails(1, new AsyncCallback<Teacher>(){
    
          @Override
          public void onSuccess(Teacher result) {
            assertNotNull(result);
            assertEquals("Jose Antonio", result.name());
            assertEquals("FEMALE", result.gender());
            assertEquals(new Date(0L), result.birthDate());
            finishTest();
          }
        
          @Override
          public void onFailure(Throwable caught) {
            // The request resulted in an unexpected error.
            fail("Request failure: " + caught.getMessage());
          }
        });

      }
      
    });

  }

    public void testGetTeacherDetails() {
      TeachersServiceAsync rpcService = GWT.create(TeachersService.class);

      ServiceDefTarget target = (ServiceDefTarget) rpcService;
      target.setServiceEntryPoint(GWT.getModuleBaseURL() + "teachers/teachersService");
      
      delayTestFinish(10000);

      rpcService.addTeacher(new Teacher("Jose", "Antonio", Gender.FEMALE, new Date(0L)), new AsyncCallback<Teacher>() {

        @Override
        public void onFailure(Throwable caught) {
          // The request resulted in an unexpected error.
          fail("Request failure: " + caught.getMessage());
        }

        @Override
        public void onSuccess(Teacher result) {
          rpcService.getTeacherDetails(new AsyncCallback<ArrayList<TeacherDetails>>(){

            @Override
            public void onSuccess(ArrayList<TeacherDetails> result) {
              assertEquals(1, result.size());

              TeacherDetails retrieved = result.get(0);
              assertEquals("Jose Antonio", retrieved.name());

              finishTest();
            }

            @Override
            public void onFailure(Throwable caught) {
              // The request resulted in an unexpected error.
              fail("Request failure: " + caught.getMessage());
            }
          });

        }
        
      });

  }

}