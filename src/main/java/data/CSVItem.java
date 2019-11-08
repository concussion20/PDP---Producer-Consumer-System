package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a CSV item.
 */
public class CSVItem {

  private Map<String, String> properties;

  /**
   * The constructor of CVSItem.
   *
   * @param titleStr the keys for values
   * @param itemLine the String line of item values
   */
  public CSVItem(final String titleStr, final String itemLine) {
    this.properties = new HashMap<>();

    loadItem(titleStr, itemLine);
  }

  /**
   * check if two CSVItem are equal.
   * @param o another CSVItem obj
   * @return true/false
   */
  @Override
  public boolean equals(Object o) {
    return this == o;
  }

  /**
   * Calculate the hashcode of this obj.
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(properties);
  }

  /**
   * load properties.
   * @param titleStr the titles of this file
   * @param itemLine an item line
   */
  private void loadItem(final String titleStr, final String itemLine) {
    if (titleStr == null || itemLine == null) {
      return;
    }
    final List<String> titles = parse(titleStr);
    final List<String> fields = parse(itemLine);
    for (int i = 0; i < titles.size(); i++) {
      this.properties.put(titles.get(i), fields.get(i));
    }
  }

  /**
   * Gets the property.
   *
   * @param key the property key
   * @return the property value
   */
  public String getProperty(final String key) {
    return this.properties.get(key);
  }

  /**
   * get the properties map size.
   * @return size fo properties
   */
  public int getPropertiesSize() {
    return this.properties.size();
  }

  /**
   * Parses a line.
   *
   * @param line a String line in CSV file
   * @return the parsed String list
   */
  private List<String> parse(final String line) {
    final Matcher matcher = Pattern.compile("\"[^\"]+\"").matcher(line);  // like "XXX"
    final List<String> items = new ArrayList<>();
    while (matcher.find()) {
      final String field = matcher.group();
      final String[] temp = field.split("\"");  // remove "
      items.add(temp[1]);
    }

    return items;
  }
}
