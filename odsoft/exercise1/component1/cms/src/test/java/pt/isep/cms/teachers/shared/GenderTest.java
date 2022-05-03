package pt.isep.cms.teachers.shared;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GenderTest {

  @Test(expected = IllegalArgumentException.class)
  public void ensureExceptionOnGenderAsStringNull() {
    Gender.parse(null);
  }

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  public void ensureExceptionOnIncorrectGender() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("gender can only be male or female");

    Gender.parse("incorrect");
  }

  @Test
  public void ensureCorrectParsingOfMale() {
    Gender gender = Gender.parse("MALE");

    assertEquals(Gender.MALE.name(), gender.name());
  }

  @Test
  public void ensureCorrectParsingOfFemale() {
    Gender gender = Gender.parse("female");

    assertEquals(Gender.FEMALE.name(), gender.name());
  }

}