package map_reducer;

import data.CSVItem;
import data.Course;
import data.StudentVle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * MapReducer(MultiConsumer Helper).
 * Help to update map and output results.
 */
public class MapReducer {

  private static final String OUTPUT_FILE_EXT = ".csv";

  /**
   * Read courses.csv and pre fill map.
   * @param cntMap cntMap
   * @param filePath file path of courses.csv
   * @throws IOException IOException
   */
  public void preFillMap(final Map<String, Map<String, Double>> cntMap, final String filePath)
      throws IOException {
    final FileReader fr = new FileReader(filePath);
    final BufferedReader bf = new BufferedReader(fr);

    final String titleStr = bf.readLine();

    String itmStr;
    while ((itmStr = bf.readLine()) != null) {
      final Course course = new Course(titleStr, itmStr);
      cntMap.put(course.getModule() + "_" + course.getPresentation(), new HashMap<>());
    }
    bf.close();
    fr.close();
  }

  /**
   * add an item to cntMap.
   * @param cntMap cntMap
   * @param csvItem csvItem obj
   */
  public void processItem(final Map<String, Map<String, Double>> cntMap, final CSVItem csvItem) {
    final StudentVle studentVle = (StudentVle) csvItem;
    final Map<String, Double> dateMap = cntMap.get(studentVle.getModule() + "_" +
        studentVle.getPresentation());
    dateMap.put(studentVle.getDate(), dateMap.getOrDefault(studentVle.getDate(),
        0d) + Double.parseDouble(studentVle.getSumClick()));
  }

  /**
   * Output the results including module_presentation files and activity-threshold.csv file.
   * @param cntMap cntMap
   * @param outPutDir outPutDir
   * @param threshold user specified threshold value
   * @param thresholdFileName thresholdFileName: activity-threshold.csv
   * @param thresholdHeaders module_presentation, date, total_clicks
   * @param modulePresentationFileHeaders date, total_clicks
   * @throws IOException IOException
   */
  public void outPut(final Map<String, Map<String, Double>> cntMap, final String outPutDir,
      final double threshold, final String thresholdFileName, final String thresholdHeaders,
      final String modulePresentationFileHeaders)
      throws IOException {

    //create output dir if not exist
    final File outputDirFile = new File(outPutDir);
    if (!outputDirFile.exists()) {
      outputDirFile.mkdirs();
    }

    //create thresholdFile output stream
    final File thresholdFile = new File(outPutDir + File.separator + thresholdFileName);

    //if file doesnt exists, then create it
    if (!thresholdFile.exists()) {
      thresholdFile.createNewFile();
    }

    final FileWriter thresholdFW = new FileWriter(thresholdFile.getPath());
    final BufferedWriter thresholdBW = new BufferedWriter(thresholdFW);
    //hold threshold file content
    final StringBuilder thresholdFileBuilder = new StringBuilder(thresholdHeaders);

    //output module_presentation.csv files
    for (final Map.Entry<String, Map<String, Double>> fileEntry: cntMap.entrySet()) {
      final String fileName = fileEntry.getKey();

      final File file = new File(outPutDir + File.separator + fileName
          + OUTPUT_FILE_EXT);

      //if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      final FileWriter fw = new FileWriter(file.getPath());
      final BufferedWriter bw = new BufferedWriter(fw);

      final StringBuilder fileContentBuilder = new StringBuilder(modulePresentationFileHeaders);
      final Map<String, Double> dateMap = fileEntry.getValue();
      for (final Map.Entry<String, Double> clickEntry: dateMap.entrySet()) {
        final String date = clickEntry.getKey();
        final double click = clickEntry.getValue();
        fileContentBuilder.append(date).append(", ").append(click).append("\n");
        //add this entry to threshold file
        if (click > threshold) {
          thresholdFileBuilder.append(fileName).append(", ").append(date).append(", ")
              .append(click).append("\n");
        }
      }
      //output module_presentation.csv
      bw.write(fileContentBuilder.toString());
      bw.close();
      fw.close();
    }

    //output threshold file
    thresholdBW.write(thresholdFileBuilder.toString());
    thresholdBW.close();
    thresholdFW.close();
  }
}
