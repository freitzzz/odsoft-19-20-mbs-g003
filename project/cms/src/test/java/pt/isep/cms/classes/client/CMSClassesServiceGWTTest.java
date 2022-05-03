package pt.isep.cms.classes.client;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;

public class CMSClassesServiceGWTTest extends GWTTestCase {
	
	private CMSClassesServiceAsync rpcService;

	@Override
	public String getModuleName() {
		return "pt.isep.cms.classes.TestCMSJUnit";
	}
	
	@Override
	protected void gwtSetUp() throws Exception {
		super.gwtSetUp();
		rpcService = GWT.create(CMSClassesService.class);
	}
	
	@Test
	public void testAddCMSClass() {

		long externalIdentifier = 0;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
		
		long cmsClassIdBeforeAdd = cmsClass.id();
		
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "classes/classesService");
		
		delayTestFinish(2000);
		
		rpcService.addCMSClass(cmsClass, new AsyncCallback<CMSClass>() {
			
			@Override
			public void onSuccess(CMSClass result) {
				
				//After a successful add the id of the classes should be different of the one before adding
				
				long cmsClassIdAfterAdd = result.id();
				
				assertNotSame(cmsClassIdBeforeAdd, cmsClassIdAfterAdd);
				
				finishTest();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				fail(caught.getMessage());
			}
		});
		
	}
	
	@Test
	public void testGetCMSClassDetails() {
		
		CMSClassesServiceAsync rpcForGetCMSClasss = GWT.create(CMSClassesService.class);
		
		ServiceDefTarget target = (ServiceDefTarget) rpcForGetCMSClasss;
		
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "classes/classesService");
		
		delayTestFinish(50000);
		
		rpcForGetCMSClasss.getCMSClassDetails(new AsyncCallback<ArrayList<CMSClassDetails>>() {
			
			@Override
			public void onSuccess(ArrayList<CMSClassDetails> result) {
				
				ArrayList<CMSClassDetails> localCMSClassDetails = new ArrayList<CMSClassDetails>(result);
				
				long externalIdentifier = 1;

				String acronym = "M1E";

				CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
				
				CMSClassesServiceAsync rpcForGetCMSClasses = GWT.create(CMSClassesService.class);
				
				ServiceDefTarget target = (ServiceDefTarget) rpcForGetCMSClasses;
				
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "classes/classesService");
				
				rpcForGetCMSClasses.addCMSClass(cmsClass, new AsyncCallback<CMSClass>() {
					
					@Override
					public void onSuccess(CMSClass result) {
						
						localCMSClassDetails.add(new CMSClassDetails(result.acronym(), result.id()));
						
						CMSClassesServiceAsync rpcForGetCMSClasss = GWT.create(CMSClassesService.class);
						
						ServiceDefTarget target = (ServiceDefTarget) rpcForGetCMSClasss;
						
						target.setServiceEntryPoint(GWT.getModuleBaseURL() + "classes/classesService");
						
						rpcForGetCMSClasss.getCMSClassDetails(new AsyncCallback<ArrayList<CMSClassDetails>>() {
							
							@Override
							public void onSuccess(ArrayList<CMSClassDetails> result) {
								
								// If getCMSClassDetails is working as supposed
								// Then the result of the request should be equal to the one we have been building on the test
								
								int expectedCMSClasssDetailsLength = localCMSClassDetails.size();
								
								int returnedCMSClasssDetailsLength = result.size();
								
								assertEquals(returnedCMSClasssDetailsLength, expectedCMSClasssDetailsLength);
								
								for(int i = 0 ; i < result.size(); i++) {
									
									String expectedName = localCMSClassDetails.get(i).name();
									
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
