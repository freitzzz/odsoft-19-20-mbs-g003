package pt.isep.cms.contacts.shared;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ContactDetailsTest {

  private ContactDetails contactDetails;

  @Before
  public void setup() {
    contactDetails = new ContactDetails("1", "display");
  }

  @Test
  public void ensureGetIdWorksCorrectly() {
    assertEquals("1", contactDetails.getId());
  }

  @Test
  public void ensureGetDisplayNameWorksCorrectly() {
    assertEquals("display", contactDetails.getDisplayName());
  }

  @Test
  public void ensureSetIdWorksCorrectly() {
    contactDetails.setId("50");
    assertEquals("50", contactDetails.getId());
  }

  @Test
  public void ensureSetDisplayNameWorksCorrectly() {
    contactDetails.setDisplayName("another display");
    assertEquals("another display", contactDetails.getDisplayName());
  }

}