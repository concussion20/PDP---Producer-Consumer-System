package main;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class MainProgramTest {

  @Test
  public void runSingle() {
    try {
      MainProgram.runSingle("files", 10000);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void runMulti() {
    try {
      MainProgram.runMulti("files", 10000);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }
}