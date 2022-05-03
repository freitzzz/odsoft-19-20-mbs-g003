package pt.isep.cms.subjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.subjects.shared.Subject;

public class SubjectModelUnitTests {

	@Test(expected = IllegalArgumentException.class)
	public void testSubjectCreationWithNullNameThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String name = null;

		String acronym = "ODSOFT";

		new Subject(externalIdentifier, name, acronym);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSubjectCreationWithNullAcronymThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = null;

		new Subject(externalIdentifier, name, acronym);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSubjectCreationInWhichAcronymHasSpacesThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSO FT";

		new Subject(externalIdentifier, name, acronym);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSubjectCreationInWhichNameLengthIsLessThanSixThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String name = "Orga";

		String acronym = "ODSOFT";

		new Subject(externalIdentifier, name, acronym);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSubjectCreationInWhichAcronymLengthIsLessThanThreeThrowsIllegalArgumentException() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "OS";

		new Subject(externalIdentifier, name, acronym);

	}

	@Test
	public void testSubjectCreationCompletesSuccessfullyIfAllConditionsAreMet() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		new Subject(externalIdentifier, name, acronym);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddClassMethodThrowsIllegalArgumentExceptionIfNullClassIsPassedAsArgument() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		CMSClass cmsClass = null;

		subject.addClass(cmsClass);

	}

	@Test(expected = IllegalStateException.class)
	public void testAddClassMethodThrowsIllegalStateExceptionIfSubjectAlreadyContainsClass() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

		CMSClass cmsClass2 = new CMSClass(1, "M1E");

		subject.addClass(cmsClass2);

	}
	
	@Test
	public void testAddClassMethodCompletesSuccessfullyIfAllConditionsAreMet() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		CMSClass cmsClass = new CMSClass(1, "M1E");

		subject.addClass(cmsClass);

	}
	
	@Test
	public void testNameMethodReturnsNamePassedAsArgumentInConstructor() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		String subjectName = subject.name();

		assertEquals(name, subjectName);

	}
	
	@Test
	public void testIdMethodReturnsExternalIdentifierPassedAsArgumentInConstructor() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		long subjectId = subject.id();

		assertEquals(externalIdentifier, subjectId);

	}
	
	@Test
	public void testChangeIdMethodUpdatesExternalIdentifierSuccessfully() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		long newExternalIdentifier = 2;

		subject.changeId(newExternalIdentifier);

		long subjectId = subject.id();

		assertEquals(newExternalIdentifier, subjectId);

	}
	
	@Test
	public void testAvailableClassesMethodReturnsNonModifiableSubjectAvailableClassesList() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		List<CMSClass> availableClassesBeforeAdd = subject.availableClasses();

		CMSClass cmsClass = new CMSClass(1, "M1E");

		availableClassesBeforeAdd.add(cmsClass);

		List<CMSClass> availableClassesAfterAdd = subject.availableClasses();

		assertNotEquals(availableClassesBeforeAdd, availableClassesAfterAdd);

	}

	@Test
	public void testEqualsMethodReturnsFalseIfNullParameterIsPassed() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		Subject subjectToCompare = null;

		boolean isEqual = subject.equals(subjectToCompare);

		assertFalse(isEqual);

	}

	@Test
	public void testEqualsMethodReturnsTrueIfSubjectWithSameHashCodeIsPassedAsParameter() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		long externalIdentifier2 = 1;

		String name2 = "Organização do Desenvolvimento de Software";

		String acronym2 = "ODSOFT";

		Subject subjectToCompare = new Subject(externalIdentifier2, name2, acronym2);

		boolean isEqual = subject.equals(subjectToCompare);

		assertTrue(isEqual);

	}

	@Test
	public void testHashCodeMethodReturnsSubjectExternalIdentifier() {

		long externalIdentifier = 1;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);

		long subjectHashCode = subject.hashCode();

		assertEquals(externalIdentifier, subjectHashCode);

	}

}
