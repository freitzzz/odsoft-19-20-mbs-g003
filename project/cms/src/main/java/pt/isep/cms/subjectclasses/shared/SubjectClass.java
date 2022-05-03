package pt.isep.cms.subjectclasses.shared;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import pt.isep.cms.classes.shared.CMSClass;
import pt.isep.cms.subjects.shared.Subject;

/**
 * A model representation for a subject class
 */
@Entity
public class SubjectClass implements Serializable {

	private static final long serialVersionUID = 1L;

	// required for database mapping
	@Id
	private long internalIdentifier;

	private Subject subject;

	private CMSClass cmsClass;

	// Required by GWT
	public SubjectClass() {
	}

	public SubjectClass(Subject subject, CMSClass cmsClass) {

		grantSubjectIsNotNull(subject);

		grantClassIsNotNull(cmsClass);

		grantClassIsAvailableOnSubject(subject, cmsClass);

		this.subject = subject;

		this.cmsClass = cmsClass;

	}
	
	public Subject referencedSubject() {
		
		return subject;
		
	}
	
	public CMSClass referencedClass() {
		
		return cmsClass;
		
	}

	private void grantSubjectIsNotNull(Subject subject2) {

		boolean isNull = subject2 == null;

		if (isNull) {
			throw new IllegalArgumentException("subject cannot be null");
		}

	}

	private void grantClassIsNotNull(CMSClass cmsClass2) {

		boolean isNull = cmsClass2 == null;

		if (isNull) {
			throw new IllegalArgumentException("class cannot be null");
		}

	}

	private void grantClassIsAvailableOnSubject(Subject subject2, CMSClass cmsClass2) {

		List<CMSClass> subjectAvailableClasses = subject2.availableClasses();

		boolean isAvailable = false;

		isAvailable = subjectAvailableClasses.contains(cmsClass2);

		if (!isAvailable) {

			throw new IllegalArgumentException("class is not available on subject");

		}

	}
	
	@Override
	public boolean equals(Object toCompare) {
		
		if(toCompare == null) {
			return false;
		}
		
		return toCompare.hashCode() == hashCode();
		
	}
	
	@Override
	public int hashCode() {
		
		return subject.hashCode() + cmsClass.hashCode();
		
	}
}
