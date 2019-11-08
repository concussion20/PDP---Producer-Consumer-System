package data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
  private Course course;

  @Before
  public void setUp() throws Exception {
    final String titleStr = "\"code_module\",\"code_presentation\",\"code_presentation_length\"\n";
    final String itemStr = "\"AAA\",\"2013J\",\"365\"\n";
    course = new Course(titleStr, itemStr);
  }

  @Test
  public void getModule() {
    Assert.assertEquals("AAA", course.getModule());
  }

  @Test
  public void getPresentation() {
    Assert.assertEquals("2013J", course.getPresentation());
  }

  @Test
  public void getPresentationLen() {
    Assert.assertEquals("365", course.getPresentationLen());
  }
}