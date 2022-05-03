package pt.isep.cms.classes.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CMSClassesServiceGWTTestSuit extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite for Teachers");
	    suite.addTestSuite(CMSClassesServiceGWTTest.class);
	    return suite;
	}
} 