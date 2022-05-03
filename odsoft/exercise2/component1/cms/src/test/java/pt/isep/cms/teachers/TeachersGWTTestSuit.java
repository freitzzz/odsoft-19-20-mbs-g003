package pt.isep.cms.teachers;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.teachers.client.TeachersGWTTest;

public class TeachersGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Teachers Application");
		    suite.addTestSuite(TeachersGWTTest.class); 
		    return suite;
		  }
		} 