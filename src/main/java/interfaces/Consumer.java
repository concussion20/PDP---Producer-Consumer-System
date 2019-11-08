package interfaces;

import data.CSVItem;
import java.io.IOException;

/**
 * The interface of the Consumer.
 */
public interface Consumer {

  String COURSE_FILE = "courses.csv";
  String OUTPUT_DIR = "output";
  String THRESHOLD_FILE_NAME = "activity-threshold.csv";
  String THRESHOLD_HEADERS = "module_presentation, date, total_clicks\n";
  String MODULE_PRESENTATION_FILE_HEADERS = "date, total_clicks\n";

  /**
   * Processes a CSV item.
   * Update cnt map.
   *
   * @param item a CSV item object
   * @throws IOException IOException
   */
  void processItem(CSVItem item) throws IOException;
}
