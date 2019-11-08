package data;

/**
 * Course data.
 * Hold each item in courses.csv.
 */
public class Course extends CSVItem {

  private static final String CODE_MODULE = "code_module";
  private static final String CODE_PRESENTATION = "code_presentation";
  private static final String CODE_PRESENTATION_LEN = "code_presentation_length";

  /**
   * The constructor of Course.
   *
   * @param titles   the keys for values
   * @param itemLine the String line of item values
   */
  public Course(final String titles, final String itemLine) {
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
   * getPresentationLen.
   * @return presentation length
   */
  public String getPresentationLen() {
    return getProperty(CODE_PRESENTATION_LEN);
  }
}
