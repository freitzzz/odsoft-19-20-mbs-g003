package pt.isep.cms.subjectclasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.subjectclasses.shared.SubjectClass;
import pt.isep.cms.subjects.shared.Subject;

public class SubjectClassModelUnitTests {

	@Test(expected = IllegalArgumentException.class)
	public void testSubjectClassCreationWithNullSubjectThrowsIllegalArgumentException() {

		Subject subject = null;

		CMSClass cmsClass = new CMSClass(1, "M1E");

		new SubjectClass(subject, cmsClass);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSubjectClassCreationWithNullClassThrowsIllegalArgumentException() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = null;

		new SubjectClass(subject, cmsClass);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSubjectClassCreationWithClassThatIsNotAvailableOnSubjectThrowsIllegalArgumentException() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");

		new SubjectClass(subject, cmsClass);
	}

	@Test
	public void testSubjectClassCreationCompletesSuccessfullyIfAllConditionsAreMet() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");
		
		subject.addClass(cmsClass);

		new SubjectClass(subject, cmsClass);
	}
	
	@Test
	public void testSubjectReferencedSubjectMethodReturnsSubjectPassedOnConstructor() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");
		
		subject.addClass(cmsClass);

		SubjectClass subjectClass = new SubjectClass(subject, cmsClass);
		
		Subject referencedSubject = subjectClass.referencedSubject();
		
		assertEquals(subject, referencedSubject);
	}
	
	@Test
	public void testSubjectReferencedClassMethodReturnsSubjectPassedOnConstructor() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");
		
		subject.addClass(cmsClass);

		SubjectClass subjectClass = new SubjectClass(subject, cmsClass);
		
		CMSClass referencedClass = subjectClass.referencedClass();
		
		assertEquals(subject, referencedClass);
	}

	@Test
	public void testEqualsMethodReturnsFalseIfNullParameterIsPassed() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");
		
		subject.addClass(cmsClass);

		SubjectClass subjectClass = new SubjectClass(subject, cmsClass);

		SubjectClass subjectClassToCompare = null;

		boolean isEqual = subjectClass.equals(subjectClassToCompare);

		assertFalse(isEqual);

	}

	@Test
	public void testEqualsMethodReturnsTrueIfSubjectClassWithSameHashCodeIsPassedAsParameter() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");
		
		subject.addClass(cmsClass);

		SubjectClass subjectClass = new SubjectClass(subject, cmsClass);
		
		Subject subject2 = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass2 = new CMSClass(1, "M1E");
		
		subject2.addClass(cmsClass2);

		SubjectClass subjectClassToCompare = new SubjectClass(subject2, cmsClass2);

		boolean isEqual = subjectClass.equals(subjectClassToCompare);

		assertTrue(isEqual);

	}

	@Test
	public void testHashCodeMethodReturnsSubjectClassExternalIdentifier() {

		Subject subject = new Subject(1, "Organização do Desenvolvimento do Software", "ODSOFT");

		CMSClass cmsClass = new CMSClass(1, "M1E");
		
		subject.addClass(cmsClass);

		SubjectClass subjectClass = new SubjectClass(subject, cmsClass);
		
		long externalIdentifier = subject.hashCode() + cmsClass.hashCode();

		long subjectClassHashCode = subjectClass.hashCode();

		assertEquals(externalIdentifier, subjectClassHashCode);

	}

}
