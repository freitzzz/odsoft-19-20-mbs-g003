package pt.isep.cms.contacts.client;

// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.client.view.ContactsView;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

public class ContactsGWTTest extends GWTTestCase {
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
/*
	public void testAddContact() {
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		delayTestFinish(10000);

		Contact toAdd = new Contact("50", "hi", "last", "email@example.com");
		contactsService.addContact(toAdd, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Contact result) {

				contactsService.getContact("22", new AsyncCallback<Contact>() {

					@Override
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					@Override
					public void onSuccess(Contact result) {
						assertNotNull(result);
						assertEquals("email@example.com", result.getEmailAddress());
						assertEquals("hi", result.getFirstName());
						assertEquals("last", result.getLastName());
						finishTest();
					}
				});

			}
		});
	}

	public void testUpdateContact() {
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		delayTestFinish(10000);

		Contact toAdd = new Contact("50", "hi", "last", "email@example.com");
		contactsService.addContact(toAdd, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Contact result) {

				contactsService.getContact("22", new AsyncCallback<Contact>() {

					@Override
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					@Override
					public void onSuccess(Contact result) {
						assertNotNull(result);
						assertEquals("email@example.com", result.getEmailAddress());
						assertEquals("hi", result.getFirstName());
						assertEquals("last", result.getLastName());

						Contact toUpdate = new Contact("52", "not", "melast", "otheremail@example.com");

						contactsService.updateContact(toUpdate, new AsyncCallback<Contact>() {

							@Override
							public void onFailure(Throwable caught) {
								// The request resulted in an unexpected error.
								fail("Request failure: " + caught.getMessage());
							}

							@Override
							public void onSuccess(Contact result) {
								assertNotNull(result);
								assertEquals("otheremail@example.com", result.getEmailAddress());
								assertEquals("not", result.getFirstName());
								assertEquals("melast", result.getLastName());
								finishTest();
							}
						});
					}
				});

			}
		});
	}
*/
	public void testDeleteContact() {
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		delayTestFinish(10000);

		Contact toAdd = new Contact("50", "hi", "last", "email@example.com");
		contactsService.addContact(toAdd, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Contact result) {
				contactsService.deleteContact("22", new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						assertTrue("should be able to delete", result);

						rpcService.getContact("22", new AsyncCallback<Contact>() {

							@Override
							public void onFailure(Throwable caught) {
								// The request should fail since there is no contact because it was deleted. The correct behaviour is to return 404
								assertTrue("should fail with 404", caught.getMessage().contains("404"));
								finishTest();
							}

							@Override
							public void onSuccess(Contact result) {
								// The request resulted in an unexpected error.
								fail("Request failure");
							}
						});
					}
				});
			}
		});
	}

	public void testDeleteContacts() {
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		delayTestFinish(10000);

		Contact toAdd = new Contact("50", "hi", "last", "email@example.com");
			
		contactsService.addContact(toAdd, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Contact result) {
				ArrayList<String> toDelete = new ArrayList<>();
				toDelete.add("22");

				contactsService.deleteContacts(toDelete, new AsyncCallback<ArrayList<ContactDetails>>() {

					@Override
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					@Override
					public void onSuccess(ArrayList<ContactDetails> result) {
						assertTrue("should have some size", result.size() > 0);
						finishTest();
					}
				});
			}
		});
	}

}
