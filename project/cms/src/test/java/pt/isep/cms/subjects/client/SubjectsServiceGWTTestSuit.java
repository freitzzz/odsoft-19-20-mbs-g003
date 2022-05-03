package pt.isep.cms.subjects.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SubjectsServiceGWTTestSuit extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite for Subjects");
	    suite.addTestSuite(SubjectsServiceGWTTest.class); 
	    return suite;
	}
} 