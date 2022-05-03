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

public class TeachersServiceGWTTest extends GWTTestCase {
	
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
	public void testAddTeacher() {
		
		String firstName = "firstname";
		
		String lastName = "lastname";
		
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
				
				finishTest();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				fail(caught.getMessage());
			}
		});
		
	}
	
	@Test
	public void testGetTeacherDetails() {
		
		TeachersServiceAsync rpcForGetTeachers = GWT.create(TeachersService.class);
		
		ServiceDefTarget target = (ServiceDefTarget) rpcForGetTeachers;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "teachers/teachersService");
		
		delayTestFinish(50000);
		
		rpcForGetTeachers.getTeacherDetails(new AsyncCallback<ArrayList<TeacherDetails>>() {
			
			@Override
			public void onSuccess(ArrayList<TeacherDetails> result) {
				
				ArrayList<TeacherDetails> localTeacherDetails = new ArrayList<TeacherDetails>(result);
				
				String firstName = "firstname";
				
				String lastName = "lastname";
				
				Gender gender = Gender.MALE;
				
				Date birthDate = new Date(0);
				
				Teacher teacher = new Teacher(firstName, lastName, gender, birthDate);
				
				TeachersServiceAsync rpcForGetTeachers = GWT.create(TeachersService.class);
				
				ServiceDefTarget target = (ServiceDefTarget) rpcForGetTeachers;
				
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "teachers/teachersService");
				
				rpcForGetTeachers.addTeacher(teacher, new AsyncCallback<Teacher>() {
					
					@Override
					public void onSuccess(Teacher result) {
						
						localTeacherDetails.add(new TeacherDetails(result.name()));
						
						TeachersServiceAsync rpcForGetTeachers = GWT.create(TeachersService.class);
						
						ServiceDefTarget target = (ServiceDefTarget) rpcForGetTeachers;
						
						target.setServiceEntryPoint(GWT.getModuleBaseURL() + "teachers/teachersService");
						
						rpcForGetTeachers.getTeacherDetails(new AsyncCallback<ArrayList<TeacherDetails>>() {
							
							@Override
							public void onSuccess(ArrayList<TeacherDetails> result) {
								
								// If getTeacherDetails is working as supposed
								// Then the result of the request should be equal to the one we have been building on the test
								
								int expectedTeachersDetailsLength = localTeacherDetails.size();
								
								int returnedTeachersDetailsLength = result.size();
								
								assertEquals(returnedTeachersDetailsLength, expectedTeachersDetailsLength);
								
								for(int i = 0 ; i < result.size(); i++) {
									
									String expectedName = localTeacherDetails.get(i).name();
									
									String returnedName = result.get(i).name();
									
									assertEquals(returnedName, expectedName);
									
								}
								
								finishTest();
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								fail(caught.getMessage());
								
							}
						});
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						fail(caught.getMessage());
						
					}
				});
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				fail(caught.getMessage());
				
			}
		});
		
	}
	
}
