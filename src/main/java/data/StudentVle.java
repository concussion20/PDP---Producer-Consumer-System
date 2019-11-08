package data;

/**
 * Represents a item in StudentVle CSV file.
 */
public class StudentVle extends CSVItem {

  private static final String CODE_MODULE = "code_module";
  private static final String CODE_PRESENTATION = "code_presentation";
  private static final String ID_STUDENT = "id_student";
  private static final String ID_SITE = "id_site";
  private static final String DATE = "date";
  private static final String SUM_CLICK = "sum_click";

  /**
   * The constructor of the StudentVle.
   *
   * @param titles  the titles of this file
   * @param itemLine an item line
   */
  public StudentVle(final String titles, final String itemLine) {
    super(titles, itemLine);
  }

  /**
   * getModule.
   * @return module
   */
  public String getModule() {
    return getProperty(CODE_MODULE);
  }

  /**
   * getPresentation.
   * @return presentation
   */
  public String getPresentation() {
    return getProperty(CODE_PRESENTATION);
  }

  /**
   * getDate.
   * @return date
   */
  public String getDate() {
    return getProperty(DATE);
  }

  /**
   * getSumClick.
   * @return sumClick
   */
  public String getSumClick() {
    return getProperty(SUM_CLICK);
  }
}
