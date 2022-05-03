package pt.isep.cms.subjects.client;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.subjects.shared.SubjectDetails;

public class SubjectsServiceGWTTest extends GWTTestCase {
	
	private SubjectsServiceAsync rpcService;

	@Override
	public String getModuleName() {
		return "pt.isep.cms.subjects.TestCMSJUnit";
	}
	
	@Override
	protected void gwtSetUp() throws Exception {
		super.gwtSetUp();
		rpcService = GWT.create(SubjectsService.class);
	}
	
	@Test
	public void testAddSubject() {

		long externalIdentifier = 0;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);
		
		long subjectIdBeforeAdd = subject.id();
		
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "subjects/subjectsService");
		
		delayTestFinish(2000);
		
		rpcService.addSubject(subject, new AsyncCallback<Subject>() {
			
			@Override
			public void onSuccess(Subject result) {
				
				//After a successful add the id of the subject should be different of the one before adding
				
				long subjectIdAfterAdd = result.id();
				
				assertNotSame(subjectIdBeforeAdd, subjectIdAfterAdd);
				
				finishTest();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				fail(caught.getMessage());
			}
		});
		
	}
	
	@Test
	public void testGetSubjectDetails() {
		
		SubjectsServiceAsync rpcForGetSubjects = GWT.create(SubjectsService.class);
		
		ServiceDefTarget target = (ServiceDefTarget) rpcForGetSubjects;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "subjects/subjectsService");
		
		delayTestFinish(50000);
		
		rpcForGetSubjects.getSubjectDetails(new AsyncCallback<ArrayList<SubjectDetails>>() {
			
			@Override
			public void onSuccess(ArrayList<SubjectDetails> result) {
				
				ArrayList<SubjectDetails> localSubjectDetails = new ArrayList<SubjectDetails>(result);
				
				long externalIdentifier = 1;

				String name = "Organização do Desenvolvimento de Software";

				String acronym = "ODSOFT";

				Subject subject = new Subject(externalIdentifier, name, acronym);
				
				SubjectsServiceAsync rpcForGetSubjects = GWT.create(SubjectsService.class);
				
				ServiceDefTarget target = (ServiceDefTarget) rpcForGetSubjects;
				
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "subjects/subjectsService");
				
				rpcForGetSubjects.addSubject(subject, new AsyncCallback<Subject>() {
					
					@Override
					public void onSuccess(Subject result) {
						
						localSubjectDetails.add(new SubjectDetails(result.name()));
						
						SubjectsServiceAsync rpcForGetSubjects = GWT.create(SubjectsService.class);
						
						ServiceDefTarget target = (ServiceDefTarget) rpcForGetSubjects;
						
						target.setServiceEntryPoint(GWT.getModuleBaseURL() + "subjects/subjectsService");
						
						rpcForGetSubjects.getSubjectDetails(new AsyncCallback<ArrayList<SubjectDetails>>() {
							
							@Override
							public void onSuccess(ArrayList<SubjectDetails> result) {
								
								// If getSubjectDetails is working as supposed
								// Then the result of the request should be equal to the one we have been building on the test
								
								int expectedSubjectsDetailsLength = localSubjectDetails.size();
								
								int returnedSubjectsDetailsLength = result.size();
								
								assertEquals(returnedSubjectsDetailsLength, expectedSubjectsDetailsLength);
								
								for(int i = 0 ; i < result.size(); i++) {
									
									String expectedName = localSubjectDetails.get(i).name();
									
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
