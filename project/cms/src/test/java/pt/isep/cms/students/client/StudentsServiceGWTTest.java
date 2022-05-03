package pt.isep.cms.students.client;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

public class StudentsServiceGWTTest extends GWTTestCase {
	
	private StudentsServiceAsync rpcService;

	@Override
	public String getModuleName() {
		return "pt.isep.cms.students.TestCMSJUnit";
	}
	
	@Override
	protected void gwtSetUp() throws Exception {
		super.gwtSetUp();
		rpcService = GWT.create(StudentsService.class);
	}
	
	@Test
	public void testAddStudent() {

		String firstName = "firstname";

		long externalIdentifier = 0;

		String lastName = "lastname";

		Gender gender = Gender.MALE;

		Date birthDate = new Date(0);

		CMSClass assignedClass = new CMSClass(1, "M1E");

		Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
		
		long studentIdBeforeAdd = student.id();
		
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");
		
		delayTestFinish(2000);
		
		rpcService.addStudent(student, new AsyncCallback<Student>() {
			
			@Override
			public void onSuccess(Student result) {
				
				//After a successful add the id of the student should be different of the one before adding
				
				long studentIdAfterAdd = result.id();
				
				assertNotSame(studentIdBeforeAdd, studentIdAfterAdd);
				
				finishTest();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				fail(caught.getMessage());
			}
		});
		
	}
	
	@Test
	public void testGetStudentDetails() {
		
		StudentsServiceAsync rpcForGetStudents = GWT.create(StudentsService.class);
		
		ServiceDefTarget target = (ServiceDefTarget) rpcForGetStudents;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");
		
		delayTestFinish(50000);
		
		rpcForGetStudents.getStudentDetails(new AsyncCallback<ArrayList<StudentDetails>>() {
			
			@Override
			public void onSuccess(ArrayList<StudentDetails> result) {
				
				ArrayList<StudentDetails> localStudentDetails = new ArrayList<StudentDetails>(result);
				
				String firstName = "firstname";

				long externalIdentifier = 1;

				String lastName = "lastname";

				Gender gender = Gender.MALE;

				Date birthDate = new Date(0);

				CMSClass assignedClass = new CMSClass(1, "M1E");

				Student student = new Student(externalIdentifier, firstName, lastName, gender, birthDate, assignedClass);
				
				StudentsServiceAsync rpcForGetStudents = GWT.create(StudentsService.class);
				
				ServiceDefTarget target = (ServiceDefTarget) rpcForGetStudents;
				
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");
				
				rpcForGetStudents.addStudent(student, new AsyncCallback<Student>() {
					
					@Override
					public void onSuccess(Student result) {
						
						localStudentDetails.add(new StudentDetails(result.name()));
						
						StudentsServiceAsync rpcForGetStudents = GWT.create(StudentsService.class);
						
						ServiceDefTarget target = (ServiceDefTarget) rpcForGetStudents;
						
						target.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");
						
						rpcForGetStudents.getStudentDetails(new AsyncCallback<ArrayList<StudentDetails>>() {
							
							@Override
							public void onSuccess(ArrayList<StudentDetails> result) {
								
								// If getStudentDetails is working as supposed
								// Then the result of the request should be equal to the one we have been building on the test
								
								int expectedStudentsDetailsLength = localStudentDetails.size();
								
								int returnedStudentsDetailsLength = result.size();
								
								assertEquals(returnedStudentsDetailsLength, expectedStudentsDetailsLength);
								
								for(int i = 0 ; i < result.size(); i++) {
									
									String expectedName = localStudentDetails.get(i).name();
									
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
