package sequential_solution;

import interfaces.Consumer;
import interfaces.Producer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import data.StudentVle;

/**
 * The single-thread version of producer.
 */
public class SingleProducer implements Producer {

  private Consumer consumer;

  /**
   * The constructor of SingleProducer.
   *
   * @param consumer the consumer for items
   * @throws IOException IOException
   */
  public SingleProducer(final Consumer consumer) throws IOException {
    this.consumer = consumer;
  }

  /**
   * Process the studentVle.csv file.
   * @param fileDir the dir of CSV file
   * @throws IOException IOException
   */
  @Override
  public void processStudentVleFile(final String fileDir) throws IOException {

    final FileReader fr = new FileReader(fileDir + File.separator + STUDENT_FILE);
    final BufferedReader bf = new BufferedReader(fr);

    // get titles
    final String titleStr = bf.readLine();

    // get items
    String itmStr;
    while ((itmStr = bf.readLine()) != null) {
      final StudentVle item = new StudentVle(titleStr, itmStr);
      // consume item
      this.consumer.processItem(item);
    }
    //set null item to indicate the point to exit and output files
    this.consumer.processItem(null);

    bf.close();
  }
}
