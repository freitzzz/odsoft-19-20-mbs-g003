package pt.isep.cms.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.classes.server.CMSClassesServiceImpl;
import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.classes.shared.CMSClassDetails;
import pt.isep.cms.common.shared.Gender;

public class CMSClassesServiceImplUnitTests {
	
	@Test
	public void testAddCMSClassMethodUpdatesCMSClassIdCorrectly() {
		
		CMSClassesServiceImpl cmsClasssService = new CMSClassesServiceImpl();
		
		int existingCMSClasssBefore = cmsClasssService.getCMSClassDetails().size();
		
		long externalIdentifier = 0;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
		
		// The cmsClass identification number is the number of existing cmsClasss before the addition + 1
		long cmsClassIdExpected = existingCMSClasssBefore + 1;
		
		long cmsClassIdBefore = cmsClass.id();
		
		assertNotEquals(cmsClassIdExpected, cmsClassIdBefore);
		
		cmsClasssService.addCMSClass(cmsClass);
		
		long cmsClassIdAfter = cmsClass.id();
		
		assertEquals(cmsClassIdAfter, cmsClassIdExpected);
	}
	
	@Test
	public void testAddCMSClassMethodUpdatesExistingCMSClasssCorrectly() {
		
		CMSClassesServiceImpl cmsClasssService = new CMSClassesServiceImpl();
		
		int existingCMSClasssBefore = cmsClasssService.getCMSClassDetails().size();
		
		assertEquals(existingCMSClasssBefore, 0);
		
		long externalIdentifier = 0;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
		
		cmsClasssService.addCMSClass(cmsClass);
		
		int existingCMSClasssAfter = cmsClasssService.getCMSClassDetails().size();
		
		assertEquals(1, existingCMSClasssAfter);
	}
	
	@Test
	public void testGetCMSClassDetailsMethodReturnsZeroIfNotCMSClasssWerePreviouslyAdded() {
		
		CMSClassesServiceImpl cmsClasssService = new CMSClassesServiceImpl();
		
		int existingCMSClasssExcepted = 0;
		
		int existingCMSClasss = cmsClasssService.getCMSClassDetails().size();
		
		assertEquals(existingCMSClasssExcepted, existingCMSClasss);
		
	}
	
	@Test
	public void testGetCMSClassDetailsReflectsCMSClasssAdditionCorrectly() {
		
		CMSClassesServiceImpl cmsClasssService = new CMSClassesServiceImpl();
		
		int existingCMSClasssBefore = cmsClasssService.getCMSClassDetails().size();
		
		int existingCMSClasssExpected = 0;
		
		assertEquals(existingCMSClasssExpected, existingCMSClasssBefore);
		
		long externalIdentifier = 0;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
		
		cmsClasssService.addCMSClass(cmsClass);
		
		existingCMSClasssExpected++;
		
		int existingCMSClasssAfter = cmsClasssService.getCMSClassDetails().size();
		
		assertEquals(existingCMSClasssExpected, existingCMSClasssAfter);

	}
	
	@Test
	public void testGetCMSClassDetailsCreatesCMSClassDetailsModelsWithCMSClassAcronym() {
		
		CMSClassesServiceImpl cmsClasssService = new CMSClassesServiceImpl();
		
		long externalIdentifier = 0;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
		
		cmsClasssService.addCMSClass(cmsClass);
		
		String cmsClassName = cmsClass.acronym();
		
		CMSClassDetails cmsClassDetails = cmsClasssService.getCMSClassDetails().get(0);
		
		String cmsClassNameInDetaislModel = cmsClassDetails.name();
		
		assertEquals(cmsClassNameInDetaislModel, cmsClassName);
		
	}
}
