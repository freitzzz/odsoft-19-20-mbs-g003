package pt.isep.cms.contacts;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.contacts.client.ContactsGWTTest;;

public class ExampleGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Contacts Application");
		    suite.addTestSuite(ContactsGWTTest.class); 
		    return suite;
		  }
		} 