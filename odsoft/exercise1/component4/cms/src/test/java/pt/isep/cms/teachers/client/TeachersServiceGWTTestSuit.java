package pt.isep.cms.teachers.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TeachersServiceGWTTestSuit extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite for Teachers");
	    suite.addTestSuite(TeachersServiceGWTTest.class); 
	    return suite;
	}
} 