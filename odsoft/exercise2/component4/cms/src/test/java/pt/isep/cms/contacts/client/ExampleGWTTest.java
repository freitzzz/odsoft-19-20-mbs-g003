package pt.isep.cms.contacts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.client.ContactsServiceAsync;
import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.client.view.ContactsView;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ExampleGWTTest extends GWTTestCase {
	private ContactsPresenter contactsPresenter;
	private ContactsServiceAsync rpcService;
	private HandlerManager eventBus;
	private ContactsPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.contacts.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(ContactsService.class);
		mockDisplay = new ContactsView();
		contactsPresenter = new ContactsPresenter(rpcService, eventBus, mockDisplay);
	}

	public void testContactSort() {
		ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();
		contactDetails.add(new ContactDetails("0", "c_contact"));
		contactDetails.add(new ContactDetails("1", "b_contact"));
		contactDetails.add(new ContactDetails("2", "a_contact"));
		contactsPresenter.setContactDetails(contactDetails);
		contactsPresenter.sortContactDetails();
		assertTrue(contactsPresenter.getContactDetail(0).getDisplayName().equals("a_contact"));
		assertTrue(contactsPresenter.getContactDetail(1).getDisplayName().equals("b_contact"));
		assertTrue(contactsPresenter.getContactDetail(2).getDisplayName().equals("c_contact"));
	}

	public void testContactsService() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		contactsService.getContact("2", new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}
	
	public void testAddContact() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		String id = "should_be_different";
		
		String firstName = "test_add_contact_first_name";
		
		String lastName = "test_add_contact_last_name";
		
		String emailAddress = "test_add_contact_email@email.com";
		
		Contact contact = new Contact("",firstName, lastName, emailAddress);
		
		delayTestFinish(15000);
		
		// Send a request to the server.
		contactsService.addContact(contact, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				assertTrue(id != result.getId());
				
				assertEquals(result.getFirstName(), firstName);
				
				assertEquals(result.getLastName(), lastName);
				
				assertEquals(result.getEmailAddress(), emailAddress);
				
				String testingId = result.getId();
				
				contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
					
					@Override
					public void onSuccess(Contact result) {
						assertEquals(testingId, result.getId());
						
						finishTest();
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						fail(caught.getMessage());
						
					}
				});
			}
		});
	}
	
	public void testDeleteContact() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");
		
		delayTestFinish(25000);

		String id = "should_be_different";
		
		String firstName = "test_add_contact_first_name";
		
		String lastName = "test_add_contact_last_name";
		
		String emailAddress = "test_add_contact_email@email.com";
		
		Contact contact = new Contact("",firstName, lastName, emailAddress);
		
		// Send a request to the server.
		contactsService.addContact(contact, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				assertTrue(id != result.getId());
				
				assertEquals(result.getFirstName(), firstName);
				
				assertEquals(result.getLastName(), lastName);
				
				assertEquals(result.getEmailAddress(), emailAddress);
				
				String testingId = result.getId();
				
				contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
					
					@Override
					public void onSuccess(Contact result) {
						assertEquals(testingId, result.getId());
						
						contactsService.deleteContact(result.getId(), new AsyncCallback<Boolean>() {
							
							@Override
							public void onSuccess(Boolean result) {
								assertTrue(result);
								
								contactsService.getContact(testingId, new AsyncCallback<Contact>() {
									
									@Override
									public void onSuccess(Contact result) {
										
										if(result != null) {
										
											fail("deleteContact should have deleted the contact previously");
											
										}
										
										finishTest();
										
									}
									
									@Override
									public void onFailure(Throwable caught) {
										
										finishTest();
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
		});
	}
	
	public void testUpdateContact() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");
		
		delayTestFinish(35000);

		String id = "should_be_different";
		
		String firstName = "test_add_contact_first_name";
		
		String lastName = "test_add_contact_last_name";
		
		String emailAddress = "test_add_contact_email@email.com";
		
		Contact contact = new Contact("",firstName, lastName, emailAddress);
		
		// Send a request to the server.
		contactsService.addContact(contact, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				assertTrue(id != result.getId());
				
				assertEquals(result.getFirstName(), firstName);
				
				assertEquals(result.getLastName(), lastName);
				
				assertEquals(result.getEmailAddress(), emailAddress);
				
				String testingId = result.getId();
				
				contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
					
					@Override
					public void onSuccess(Contact result) {
						assertEquals(testingId, result.getId());
						
						String updatedFirstName = "this_should_be_the_new_name";
						
						String notUpdatedLastName = result.getLastName();
						
						String notUpdatedEmailAddress = result.getEmailAddress();
						
						Contact updatingContact = new Contact(result.getId(), updatedFirstName, result.getLastName(), result.getEmailAddress());
						
						contactsService.updateContact(updatingContact, new AsyncCallback<Contact>() {
							
							@Override
							public void onSuccess(Contact result) {
								assertEquals(updatingContact.getId(), result.getId());
								
								assertEquals(updatingContact.getFirstName(), updatedFirstName);
								
								assertEquals(updatingContact.getLastName(), notUpdatedLastName);
								
								assertEquals(updatingContact.getEmailAddress(), notUpdatedEmailAddress);
								
								contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
									
									@Override
									public void onSuccess(Contact result) {
										assertEquals(result.getFirstName(), updatedFirstName);
										
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
		});
	}
	
	public void testGetContact() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");
		
		delayTestFinish(35000);

		String id = "should_be_different";
		
		String firstName = "test_add_contact_first_name";
		
		String lastName = "test_add_contact_last_name";
		
		String emailAddress = "test_add_contact_email@email.com";
		
		Contact contact = new Contact("",firstName, lastName, emailAddress);
		
		// Send a request to the server.
		contactsService.addContact(contact, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				assertTrue(id != result.getId());
				
				assertEquals(result.getFirstName(), firstName);
				
				assertEquals(result.getLastName(), lastName);
				
				assertEquals(result.getEmailAddress(), emailAddress);
				
				String testingId = result.getId();
				
				String testingFirstName = result.getFirstName();
				
				String testingLastName = result.getLastName();
				
				String testingEmailAddress = result.getEmailAddress();
				
				contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
					
					@Override
					public void onSuccess(Contact result) {
						assertEquals(testingId, result.getId());
						
						assertEquals(testingFirstName, result.getFirstName());
						
						assertEquals(testingLastName, result.getLastName());
						
						assertEquals(testingEmailAddress, result.getEmailAddress());
						
						finishTest();
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						fail(caught.getMessage());
						
					}
				});
			}
		});
	}
	
	public void testGetContactDetails() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");
		
		delayTestFinish(35000);

		String id = "should_be_different";
		
		String firstName = "test_add_contact_first_name";
		
		String lastName = "test_add_contact_last_name";
		
		String emailAddress = "test_add_contact_email@email.com";
		
		Contact contact = new Contact("",firstName, lastName, emailAddress);
		
		// Send a request to the server.
		contactsService.addContact(contact, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				assertTrue(id != result.getId());
				
				assertEquals(result.getFirstName(), firstName);
				
				assertEquals(result.getLastName(), lastName);
				
				assertEquals(result.getEmailAddress(), emailAddress);
				
				String testingId = result.getId();
				
				String testingDisplayName = result.getLightWeightContact().getDisplayName();
				
				contactsService.getContactDetails(new AsyncCallback<ArrayList<ContactDetails>>() {
					
					@Override
					public void onSuccess(ArrayList<ContactDetails> result) {
						ContactDetails contactDetails =  result.stream().filter(new Predicate<ContactDetails>() {

							@Override
							public boolean test(ContactDetails arg0) {
								return arg0.getId().equals(testingId);
							}})
						.findFirst()
						.get();
						
						assertEquals(testingDisplayName, contactDetails.getDisplayName());
						
						finishTest();
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
						fail(caught.getMessage());
						
					}
				});
			}
		});
	}
	
	public void testDeleteContacts() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");
		
		delayTestFinish(50000);

		String id = "should_be_different";
		
		String firstName = "test_add_contact_first_name";
		
		String lastName = "test_add_contact_last_name";
		
		String emailAddress = "test_add_contact_email@email.com";
		
		Contact contact = new Contact("",firstName, lastName, emailAddress);
		
		// Send a request to the server.
		contactsService.addContact(contact, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				assertTrue(id != result.getId());
				
				String testingId = result.getId();
				
				String testingFirstName = result.getFirstName();
				
				String testingLastName = result.getLastName();
				
				String testingEmailAddress = result.getEmailAddress();
				
				contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
					
					@Override
					public void onSuccess(Contact result) {
						assertEquals(testingId, result.getId());
						
						assertEquals(testingFirstName, result.getFirstName());
						
						assertEquals(testingLastName, result.getLastName());
						
						assertEquals(testingEmailAddress, result.getEmailAddress());
						
						String newId = "should_be_different";
						
						String newFirstName = "test_add_contact_first_name";
						
						String newLastName = "test_add_contact_last_name";
						
						String newEmailAddress = "test_add_contact_email@email.com";
						
						Contact newContact = new Contact("",newFirstName, newLastName, newEmailAddress);
						
						contactsService.addContact(newContact, new AsyncCallback<Contact>() {
							public void onFailure(Throwable caught) {
								// The request resulted in an unexpected error.
								fail("Request failure: " + caught.getMessage());
							}
							
							public void onSuccess(Contact result) {
								assertTrue(newId != result.getId());
								
								String newTestingId = result.getId();
								
								String newTestingFirstName = result.getFirstName();
								
								String newTestingLastName = result.getLastName();
								
								String newTestingEmailAddress = result.getEmailAddress();
								
								contactsService.getContact(result.getId(), new AsyncCallback<Contact>() {
									
									@Override
									public void onSuccess(Contact result) {
										assertEquals(newTestingId, result.getId());
										
										assertEquals(newTestingFirstName, result.getFirstName());
										
										assertEquals(newTestingLastName, result.getLastName());
										
										assertEquals(newTestingEmailAddress, result.getEmailAddress());
										
										ArrayList<String> contactsToDelete = new ArrayList<String>();
										
										contactsToDelete.add(testingId);
										
										contactsToDelete.add(newTestingId);
										
										contactsService.deleteContacts(contactsToDelete, new AsyncCallback<ArrayList<ContactDetails>>() {
											
											@Override
											public void onSuccess(ArrayList<ContactDetails> result) {
												
												for(int i = 0; i < result.size(); i++) {
												
													ContactDetails contactDetails = result.get(i);
													
													if(contactDetails.getId().equals(testingId)) {
														
														fail("deleteContacts failed to delete contact with id: " + testingId);
														
													}
													
												}
												
												for(int i = 0; i < result.size(); i++) {
													
													ContactDetails contactDetails = result.get(i);
													
													if(contactDetails.getId().equals(newTestingId)) {
														
														fail("deleteContacts failed to delete contact with id: " + newTestingId);
														
													}
													
												}
												
												contactsService.getContact(testingId, new AsyncCallback<Contact>() {
													
													@Override
													public void onSuccess(Contact result) {
														
														if(result != null) {
														
															fail("getContact should fail if id of contact is: " + testingId);
														
														}
														
														contactsService.getContact(newTestingId, new AsyncCallback<Contact>() {
															
															@Override
															public void onSuccess(Contact result) {
																
																if(result != null) {
																
																	fail("getContact should fail if id of contact is: " + newTestingId);
																
																}
																
																finishTest();
															}
															
															@Override
															public void onFailure(Throwable caught) {
																finishTest();
																
															}
														});
													}
													
													@Override
													public void onFailure(Throwable caught) {
														
														contactsService.getContact(newTestingId, new AsyncCallback<Contact>() {
															
															@Override
															public void onSuccess(Contact result) {
																
																if(result != null) {
																
																	//fail("getContact should fail if id of contact is: " + newTestingId);
																
																}		
															}
															
															@Override
															public void onFailure(Throwable caught) {
																finishTest();
																
															}
														});
														
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
						});
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						fail(caught.getMessage());
						
					}
				});
			}
		});
	}
}
