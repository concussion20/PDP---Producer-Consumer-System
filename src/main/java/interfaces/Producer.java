package interfaces;

import java.io.IOException;

/**
 * The interface of the Producer.
 */
public interface Producer {

  String STUDENT_FILE = "studentVle.csv";

  /**
   * Process the studentVle.csv file.
   * @param fileDir the dir of CSV file
   * @throws IOException IOException
   * @throws InterruptedException InterruptedException
   */
  void processStudentVleFile(String fileDir) throws IOException, InterruptedException;
}
