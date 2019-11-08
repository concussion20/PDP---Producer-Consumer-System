package data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CSVItemTest {
  private CSVItem csvItem;

  @Before
  public void setUp() throws Exception {
    final String titleStr = "\"code_module\",\"code_presentation\",\"id_student\",\"id_site\",\"date\","
        + "\"sum_click\"\n";
    final String itemStr = "\"AAA\",\"2013J\",\"28400\",\"546652\",\"-10\",\"4\"\n";
    csvItem = new CSVItem(titleStr, itemStr);
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(csvItem, csvItem);
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(csvItem.hashCode(), csvItem.hashCode());
  }

  @Test
  public void getProperty() {
    Assert.assertEquals("AAA", csvItem.getProperty("code_module"));
    Assert.assertEquals("2013J", csvItem.getProperty("code_presentation"));
  }

  @Test
  public void getPropertiesSize() {
    Assert.assertEquals(6, csvItem.getPropertiesSize());
  }
}