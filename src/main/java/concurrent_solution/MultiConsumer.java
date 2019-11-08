package concurrent_solution;

import data.CSVItem;
import data.StudentVle;
import interfaces.Consumer;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import map_reducer.MapReducer;

/**
 * Consumer runs in Multi-thread environment.
 */
public class MultiConsumer implements Consumer, Runnable {

  private BlockingQueue<StudentVle> blockingQueue;
  private Map<String, Map<String, Double>> cntMap;
  private double threshold;
  private MapReducer mapReducer;

  /**
   * Constructor.
   * @param blockingQueue blockingQueue. Take from it.
   * @param fileDir courses.csv file dir. In order to preFill cntMap.
   * @param threshold user specified threshold value.
   * @throws IOException IOException
   */
  public MultiConsumer(final BlockingQueue<StudentVle> blockingQueue, final String fileDir,
      final double threshold)
      throws IOException {
    this.blockingQueue = blockingQueue;
    this.cntMap = new HashMap<>();
    this.threshold = threshold;
    this.mapReducer = new MapReducer();
    processCourseFile(fileDir);
  }

  /**
   * preFill cntMap using courses.csv.
   * @param fileDir courses.csv file dir
   * @throws IOException IOException
   */
  private void processCourseFile(final String fileDir) throws IOException {
    mapReducer.preFillMap(cntMap, fileDir + File.separator + COURSE_FILE);
  }

  /**
   * Processes a CSV item.
   * Update cnt map.
   *
   * @param item a CSV item object
   */
  @Override
  public void processItem(final CSVItem item) {
    mapReducer.processItem(cntMap, item);
  }

  /**
   * Take from BlockQueue and update map.
   * Output files in the end.
   */
  @Override
  public void run(){
    try {
      CSVItem item;
      while (true) {
        item = blockingQueue.take();
        if (item.getPropertiesSize() == 0) {
          mapReducer.outPut(cntMap, OUTPUT_DIR, threshold, THRESHOLD_FILE_NAME, THRESHOLD_HEADERS,
              MODULE_PRESENTATION_FILE_HEADERS);
          break;
        }
        processItem(item);
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
