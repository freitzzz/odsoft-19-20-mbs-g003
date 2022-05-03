package pt.isep.cms.subjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

import pt.isep.cms.common.shared.Gender;
import pt.isep.cms.subjects.server.SubjectsServiceImpl;
import pt.isep.cms.subjects.shared.Subject;
import pt.isep.cms.subjects.shared.SubjectDetails;

public class SubjectsServiceImplUnitTests {
	
	@Test
	public void testAddSubjectMethodUpdatesSubjectIdCorrectly() {
		
		SubjectsServiceImpl subjectsService = new SubjectsServiceImpl();
		
		int existingSubjectsBefore = subjectsService.getSubjectDetails().size();
		
		long externalIdentifier = 0;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);
		
		// The subject identification number is the number of existing subjects before the addition + 1
		long subjectIdExpected = existingSubjectsBefore + 1;
		
		long subjectIdBefore = subject.id();
		
		assertNotEquals(subjectIdExpected, subjectIdBefore);
		
		subjectsService.addSubject(subject);
		
		long subjectIdAfter = subject.id();
		
		assertEquals(subjectIdAfter, subjectIdExpected);
	}
	
	@Test
	public void testAddSubjectMethodUpdatesExistingSubjectsCorrectly() {
		
		SubjectsServiceImpl subjectsService = new SubjectsServiceImpl();
		
		int existingSubjectsBefore = subjectsService.getSubjectDetails().size();
		
		assertEquals(existingSubjectsBefore, 0);
		
		long externalIdentifier = 0;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);
		
		subjectsService.addSubject(subject);
		
		int existingSubjectsAfter = subjectsService.getSubjectDetails().size();
		
		assertEquals(1, existingSubjectsAfter);
	}
	
	@Test
	public void testGetSubjectDetailsMethodReturnsZeroIfNotSubjectsWerePreviouslyAdded() {
		
		SubjectsServiceImpl subjectsService = new SubjectsServiceImpl();
		
		int existingSubjectsExcepted = 0;
		
		int existingSubjects = subjectsService.getSubjectDetails().size();
		
		assertEquals(existingSubjectsExcepted, existingSubjects);
		
	}
	
	@Test
	public void testGetSubjectDetailsReflectsSubjectsAdditionCorrectly() {
		
		SubjectsServiceImpl subjectsService = new SubjectsServiceImpl();
		
		int existingSubjectsBefore = subjectsService.getSubjectDetails().size();
		
		int existingSubjectsExpected = 0;
		
		assertEquals(existingSubjectsExpected, existingSubjectsBefore);
		
		long externalIdentifier = 0;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);
		
		subjectsService.addSubject(subject);
		
		existingSubjectsExpected++;
		
		int existingSubjectsAfter = subjectsService.getSubjectDetails().size();
		
		assertEquals(existingSubjectsExpected, existingSubjectsAfter);

	}
	
	@Test
	public void testGetSubjectDetailsCreatesSubjectDetailsModelsWithSubjectName() {
		
		SubjectsServiceImpl subjectsService = new SubjectsServiceImpl();
		
		long externalIdentifier = 0;

		String name = "Organização do Desenvolvimento de Software";

		String acronym = "ODSOFT";

		Subject subject = new Subject(externalIdentifier, name, acronym);
		
		subjectsService.addSubject(subject);
		
		String subjectName = subject.name();
		
		SubjectDetails subjectDetails = subjectsService.getSubjectDetails().get(0);
		
		String subjectNameInDetaislModel = subjectDetails.name();
		
		assertEquals(subjectNameInDetaislModel, subjectName);
		
	}
}
