package pt.isep.cms.teachers.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.isep.cms.teachers.shared.Gender;

public class GenderTest {

	@Test(expected = IllegalArgumentException.class)
	public void testGenderParseWithNullStringThrowsIllegalArgumentException() {
		
		String genderStringToParse = null;
		
		Gender.parse(genderStringToParse);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGenderParseWithUnrecognizedGenderStringThrowsIllegalArgumentException() {
		
		String genderStringToParse = "neither male or female";
		
		Gender.parse(genderStringToParse);
		
	}
	
	@Test
	public void testGenderParseRecognizesMaleString() {
		
		String genderStringToParse = "Male";
		
		Gender genderParsed = Gender.parse(genderStringToParse);
		
		Gender maleGender = Gender.MALE;
		
		assertEquals(genderParsed, maleGender);
	}
	
	@Test
	public void testGenderParseRecognizesFemaleString() {
		
		String genderStringToParse = "Female";
		
		Gender genderParsed = Gender.parse(genderStringToParse);
		
		Gender femaleGender = Gender.FEMALE;
		
		assertEquals(genderParsed, femaleGender);
	}
	
	@Test
	public void testGenderParseRecognizesMaleOrFemaleStringWithDifferentCases() {
		
		String genderStringToParse = "mAle";
		
		Gender genderParsed = Gender.parse(genderStringToParse);
		
		Gender maleGender = Gender.MALE;
		
		assertEquals(genderParsed, maleGender);
		
		
		genderStringToParse = "FEMALE";
		
		genderParsed = Gender.parse(genderStringToParse);
		
		Gender femaleGender = Gender.FEMALE;
		
		assertEquals(genderParsed, femaleGender);
		
	}

}
