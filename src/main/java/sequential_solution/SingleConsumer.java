package sequential_solution;

import interfaces.Consumer;
import data.CSVItem;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import map_reducer.MapReducer;

/**
 * Consumer runs in Single-thread environment.
 */
public class SingleConsumer implements Consumer {

  private Map<String, Map<String, Double>> cntMap;
  private double threshold;
  private MapReducer mapReducer;

  /**
   * Constructor.
   * @param fileDir fileDir of course.csv. Use it to preFill cntMap.
   * @param threshold user specified threshold value
   * @throws IOException IOException
   */
  public SingleConsumer(final String fileDir, final double threshold) throws IOException {
    this.cntMap = new HashMap<>();
    this.threshold = threshold;
    this.mapReducer = new MapReducer();
    processCourseFile(fileDir);
  }

  /**
   * use map_reducer helper to preFill map.
   * @param fileDir fileDir of course.csv
   * @throws IOException IOException
   */
  private void processCourseFile(final String fileDir) throws IOException {
    mapReducer.preFillMap(cntMap, fileDir + File.separator + COURSE_FILE);
  }

  /**
   * Processes a CSV item.
   *
   * @param item a CSV item object
   */
  @Override
  public void processItem(final CSVItem item) throws IOException {
    // todo
    if (item == null) {
      mapReducer.outPut(cntMap, OUTPUT_DIR, threshold, THRESHOLD_FILE_NAME, THRESHOLD_HEADERS,
          MODULE_PRESENTATION_FILE_HEADERS);
      return;
    }
    mapReducer.processItem(cntMap, item);
  }
}
