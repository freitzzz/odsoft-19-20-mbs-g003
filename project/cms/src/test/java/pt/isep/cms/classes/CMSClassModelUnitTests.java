package pt.isep.cms.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.isep.cms.classes.shared.CMSClass;

public class CMSClassModelUnitTests {

	@Test(expected = IllegalArgumentException.class)
	public void testCMSClassCreationWithNullAcronymThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String acronym = null;

		new CMSClass(externalIdentifier, acronym);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCMSClassCreationWithAcronymThatContainsSpacesThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String acronym = "M E";

		new CMSClass(externalIdentifier, acronym);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCMSClassCreationWithAcronymWhichLengthIsDifferentThanThreeThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String acronym = "M1EE";

		new CMSClass(externalIdentifier, acronym);
	}

	@Test
	public void testCMSClassCreationCompletesSuccessfullyIfAllConditionsAreMet() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		new CMSClass(externalIdentifier, acronym);
	}

	@Test
	public void testCMSClassHasReachedMaximumCapacityReturnsFalseIfNoMoreThanTwentyStudentsHaveBeenAdded() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		for (int i = 0; i < 19; i++) {

			cmsClass.addStudent();

		}

		boolean hasReachedMaximumCapacity = cmsClass.hasReachedMaximumCapacity();

		assertFalse(hasReachedMaximumCapacity);
	}

	@Test
	public void testCMSClassHasReachedMaximumCapacityReturnsTrueIfTwentyOrMoreStudentsHaveBeenAdded() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		for (int i = 0; i < 20; i++) {

			cmsClass.addStudent();

		}

		boolean hasReachedMaximumCapacity = cmsClass.hasReachedMaximumCapacity();

		assertTrue(hasReachedMaximumCapacity);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testCMSClassAddStudentThrowsIllegalStateExceptionIfMaximumCapacityWasPassed() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		for (int i = 0; i < 20; i++) {

			cmsClass.addStudent();

		}

		boolean hasReachedMaximumCapacity = cmsClass.hasReachedMaximumCapacity();

		assertTrue(hasReachedMaximumCapacity);
		
		cmsClass.addStudent();
	}
	
	@Test
	public void testCMSClassAddStudentUpdatesCurrentCapacitySuccessfully() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);
		
		byte currentCapacityBeforeAdd = cmsClass.currentCapacity();
		
		cmsClass.addStudent();
		
		byte currentCapacityAfterAdd = cmsClass.currentCapacity();

		assertNotEquals(currentCapacityBeforeAdd, currentCapacityAfterAdd);
	}

	@Test
	public void testCMSClassIdMethodReturnsExternalIdentifierPassedOnConstructor() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		long cmsClassId = cmsClass.id();

		assertEquals(externalIdentifier, cmsClassId);
	}

	@Test
	public void testCMSClassChangeIdMethodUpdatedExternalIdentifierSucessfully() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		long newExternalIdentifier = 2;

		cmsClass.changeId(newExternalIdentifier);

		long cmsClassId = cmsClass.id();

		assertEquals(newExternalIdentifier, cmsClassId);
	}
	
	@Test
	public void testCMSClassAcronymMethodReturnsClassAcronymSucessfully() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		String cmsClassAcronym = cmsClass.acronym();

		assertEquals(acronym, cmsClassAcronym);
	}

	@Test
	public void testCMSClassCurrentCapacityMethodReturnsZeroIfNoStudentsHaveBeenAdded() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		byte currentCapacity = cmsClass.currentCapacity();

		assertEquals(0, currentCapacity);
	}

	@Test
	public void testCMSClassCurrentCapacityMethodReturnsOneIfAStudentWasAdded() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		cmsClass.addStudent();

		byte currentCapacity = cmsClass.currentCapacity();

		assertEquals(1, currentCapacity);
	}

	@Test
	public void testEqualsMethodReturnsFalseIfNullParameterIsPassed() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		CMSClass cmsClassToCompare = null;

		boolean isEqual = cmsClass.equals(cmsClassToCompare);

		assertFalse(isEqual);

	}

	@Test
	public void testEqualsMethodReturnsTrueIfCMSClassWithSameHashCodeIsPassedAsParameter() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		long externalIdentifier2 = 1;

		String acronym2 = "M1E";

		CMSClass cmsClassToCompare = new CMSClass(externalIdentifier2, acronym2);

		boolean isEqual = cmsClass.equals(cmsClassToCompare);

		assertTrue(isEqual);

	}

	@Test
	public void testHashCodeMethodReturnsCMSClassExternalIdentifier() {

		long externalIdentifier = 1;

		String acronym = "M1E";

		CMSClass cmsClass = new CMSClass(externalIdentifier, acronym);

		long cmsClassHashCode = cmsClass.hashCode();

		assertEquals(externalIdentifier, cmsClassHashCode);

	}
	
}
