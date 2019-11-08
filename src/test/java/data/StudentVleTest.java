package data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentVleTest {
  private StudentVle studentVle;

  @Before
  public void setUp() throws Exception {
    final String titleStr = "\"code_module\",\"code_presentation\",\"id_student\",\"id_site\",\"date\","
        + "\"sum_click\"\n";
    final String itemStr = "\"AAA\",\"2013J\",\"28400\",\"546652\",\"-10\",\"4\"\n";
    studentVle = new StudentVle(titleStr, itemStr);
  }

  @Test
  public void getModule() {
    Assert.assertEquals("AAA", studentVle.getModule());
  }

  @Test
  public void getPresentation() {
    Assert.assertEquals("2013J", studentVle.getPresentation());
  }

  @Test
  public void getDate() {
    Assert.assertEquals("-10", studentVle.getDate());
  }

  @Test
  public void getSumClick() {
    Assert.assertEquals("4", studentVle.getSumClick());
  }
}