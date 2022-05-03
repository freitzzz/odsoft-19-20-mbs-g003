package pt.isep.cms.contacts.shared;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ContactTest {

  private Contact contact;

  @Before
  public void setup() {
    contact = new Contact("1", "first", "last", "myemail@example.com");
  }

  @Test
  public void getFullNameShouldWorkCorrectly() {
    assertEquals(contact.getFullName(), "first last");
  }

  @Test
  public void getFirstNameShouldWorkCorrectly() {
    assertEquals(contact.getFirstName(), "first");
  }

  @Test
  public void getLastNameShouldWorkCorrectly() {
    assertEquals(contact.getLastName(), "last");
  }

  @Test
  public void getEmailAddressShouldWorkCorrectly() {
    assertEquals(contact.getEmailAddress(), "myemail@example.com");
  }

  @Test
  public void setIdShouldWorkCorrectly() {
    contact.setId("50");
    assertEquals(contact.getId(), "50");
  }

  @Test
  public void setFirstNameShouldWorkCorrectly() {
    contact.setFirstName("another first");
    assertEquals(contact.getFirstName(), "another first");
  }

  @Test
  public void setLastNameShouldWorkCorrectly() {
    contact.setLastName("another last");
    assertEquals(contact.getLastName(), "another last");
  }

  @Test
  public void setEmailAddressShouldWorkCorrectly() {
    contact.setEmailAddress("anotheremail@example.com");
    assertEquals(contact.getEmailAddress(), "anotheremail@example.com");
  }

  @Test
  public void getLightweightContactShouldWorkCorrectly() {
    ContactDetails details = contact.getLightWeightContact();

    assertEquals("1", details.getId());
    assertEquals("first last", details.getDisplayName());
  }

}