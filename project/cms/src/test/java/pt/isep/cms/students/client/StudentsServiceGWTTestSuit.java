package pt.isep.cms.students.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class StudentsServiceGWTTestSuit extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite for Students");
	    suite.addTestSuite(StudentsServiceGWTTest.class); 
	    return suite;
	}
} 