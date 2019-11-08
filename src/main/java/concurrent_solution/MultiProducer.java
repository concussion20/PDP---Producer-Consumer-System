package concurrent_solution;

import interfaces.Producer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import data.StudentVle;

/**
 * Producer runs in Multi-thread environment.
 */
public class MultiProducer implements Producer, Runnable {

  private BlockingQueue<StudentVle> blockingQueue;
  private String fileDir;

  /**
   * The constructor of MultiProducer.
   *
   * @param blockingQueue the blocking queue for multi-thread processing
   * @param fileDir      the dir path of CSV file for run
   */
  public MultiProducer(final BlockingQueue<StudentVle> blockingQueue, final String fileDir) {
    this.blockingQueue = blockingQueue;
    this.fileDir = fileDir;
  }

  /**
   * Process the studentVle.csv file.
   * @param fileDir the dir of CSV file
   * @throws IOException IOException
   * @throws InterruptedException InterruptedException
   */
  @Override
  public void processStudentVleFile(final String fileDir) throws IOException, InterruptedException {
    final FileReader fr = new FileReader(fileDir + File.separator + STUDENT_FILE);
    final BufferedReader bf = new BufferedReader(fr);

    // get titles
    final String titleStr = bf.readLine();

    // get items
    String itmStr;
    while ((itmStr = bf.readLine()) != null) {
      final StudentVle item = new StudentVle(titleStr, itmStr);
      // consume item
      this.blockingQueue.put(item);
    }
    //set dummy item to indicate the point to exit and output files
    final StudentVle dummy = new StudentVle(null, null);
    this.blockingQueue.put(dummy);

    bf.close();
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread,
   * starting the thread causes the object's
   * <code>run</code> method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method <code>run</code> is that it may take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    try {
      processStudentVleFile(this.fileDir);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
