package illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception;

/**
 * Exception to be thrown when a value is discovered during parsing that does not correspond to any
 * of our internal enumerated values.
 * 
 * @author illgirni
 *
 */
public class UnknownIndicatorException extends ParserException {

  private static final long serialVersionUID = 1L;

  /**
   * The type which provides the internal value.
   */
  private final Class<?> indicatorType;

  /**
   * The value in the save.
   */
  private final long indicator;

  /**
   * @param indicatorType The type which provides the internal value.
   * @param indicator The value in the save.
   */
  public UnknownIndicatorException(Class<?> indicatorType, final long indicator) {
    this.indicatorType = indicatorType;
    this.indicator = indicator;
  }

  /**
   * The type which provides the internal value.
   */
  public Class<?> getIndicatorType() {
    return indicatorType;
  }

  /**
   * The value in the save.
   */
  public long getIndicator() {
    return indicator;
  }

}
