package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import java.text.DecimalFormat;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;

/**
 * Base for a panel listing a number of details of a character in a save slot. Designs the basic
 * design and has convenience methods to add detail values to the panel.
 * <p/>
 * The panel itself is a grid. The convenience methods allow to add cells in a specific style to the
 * grid.
 * <p/>
 * The details shown in this panel are intended to be lists consisting of label-value-pairs. The
 * convenience methods allow to add cells for the labels and pairs shown in the panel.
 * 
 * @author illgirni
 *
 */
public abstract class AbstractDetailsPanel extends GridPane {

  /**
   * Style definition for a white background.
   */
  private static final String WHITE_BACKGROUND_COLOR = "-fx-background-color: #fff;";

  /**
   * Style definition for the background of a value label.
   */
  private static final String LABEL_BACKGROUND_COLOR = "-fx-background-color: #ddd;";

  /**
   * Style definition for cell border colors.
   */
  private static final String BORDER_COLOR = "-fx-border-color: #999;";

  /**
   * The grid for the detail values shown in this panel.
   */
  private final GridPane contentContainer;

  /**
   * The label acting as this panel's title.
   */
  private final Label title;

  /**
   * Sets up this panel by building and adding this panel's child elements. Allows sub-classes to
   * define their content contributions with {@link #fillContentGrid(GridPane)}.
   */
  public AbstractDetailsPanel() {
    setStyle("-fx-border-width: 1 1 1 1;" + WHITE_BACKGROUND_COLOR + BORDER_COLOR);
    setPadding(new Insets(10, 0, 10, 20));

    title = new Label(getTitle());
    title.setStyle("-fx-font-size: 1.2em; -fx-font-weight: bold;");
    title.setPadding(new Insets(0, 0, 10, 0)); // top, right, bottom, left

    contentContainer = new GridPane();
    contentContainer.setAlignment(Pos.TOP_CENTER);
    contentContainer.setStyle(WHITE_BACKGROUND_COLOR);

    fillContentGrid(contentContainer);

    GridPane.setHalignment(title, HPos.CENTER);
    GridPane.setColumnSpan(title, 3);
    add(title, 0, 0);

    final Label bufferLeft = new Label("");
    GridPane.setHgrow(bufferLeft, Priority.ALWAYS);
    add(bufferLeft, 0, 1);

    GridPane.setHalignment(contentContainer, HPos.CENTER);
    GridPane.setHgrow(contentContainer, Priority.NEVER);
    add(contentContainer, 1, 1);

    final Label bufferRight = new Label("");
    GridPane.setHgrow(bufferRight, Priority.ALWAYS);
    add(bufferRight, 2, 1);

    // setGridLinesVisible(true);
  }

  /**
   * Sets a new title text for the panel.
   */
  protected final void setTitle(final String title) {
    this.title.setText(title);
  }

  /**
   * The initial title text of the panel.
   */
  protected abstract String getTitle();

  /**
   * Ads the details shown in the panel to the given grid.
   * 
   * @param contentContainer This panel's content.
   */
  protected abstract void fillContentGrid(final GridPane contentContainer);

  /**
   * Removes the node from this panel's content.
   */
  protected final void removeFromContent(final Node node) {
    if (node != null) {
      contentContainer.getChildren().remove(node);
    }
  }

  /**
   * Ads the node to this panel's content.
   * 
   * @param node The node to add.
   * @param column The column in the content grid for the node.
   * @param row The row in the content grid for the node.
   */
  protected final void addToContent(final Node node, final int column, final int row) {
    if (node != null) {
      contentContainer.add(node, column, row);
    }
  }

  /**
   * Adds a cell styled as a label to the container.
   * 
   * @param container The container.
   * @param cellText The label text.
   * @param position If the label is the first, middle, or last in a list of labels. This determines
   *        the border drawn around the label.
   * @param column The column of the label cell in the container.
   * @param row The row of the label cell in the container.
   */
  protected final void addLabelGridCell(final GridPane container, final String cellText,
      final VPos position, final int column, final int row) {
    container.add(createLabelGridCell(cellText, position), column, row);
  }

  /**
   * Creates a cell styled as a label. Does not add it to this panel!
   * 
   * @param cellText The label text.
   * @param position If the label is the first, middle, or last in a list of labels. This determines
   *        the border drawn around the label.
   */
  protected final Node createLabelGridCell(final String cellText, final VPos position) {
    final Text label = new Text(cellText + ":");

    final StringBuilder style = new StringBuilder();

    style.append("-fx-font-weight: bold;").append(BORDER_COLOR).append(LABEL_BACKGROUND_COLOR);

    switch (position) {
      case BOTTOM:
        style.append("-fx-border-width: 0 0 1 1;");
        break;
      case CENTER:
        style.append("-fx-border-width: 0 0 0 1;");
        break;
      case TOP:
        style.append("-fx-border-width: 1 0 0 1;");
        break;
      case BASELINE:
        style.append("-fx-border-width: 1 0 1 1;");
        break;
      default:
        throw new IllegalArgumentException("Unsupported position: " + position);

    }

    final TextFlow cell = new TextFlow(label);
    cell.setTextAlignment(TextAlignment.LEFT);
    cell.setStyle(style.toString());
    cell.setPadding(new Insets(1, 10, 1, 2)); // top, right, bottom, left

    GridPane.setFillHeight(cell, true);
    GridPane.setFillWidth(cell, true);
    GridPane.setHalignment(cell, HPos.LEFT);
    GridPane.setValignment(cell, VPos.CENTER);
    GridPane.setHgrow(cell, Priority.NEVER);

    return cell;

  }

  /**
   * Adds a cell styled for a detail value to the container.
   * 
   * @param container The container.
   * @param cellText The value as text.
   * @param position If the label is the first, middle, or last in a list of values. This determines
   *        the border drawn around the value.
   * @param column The column of the label cell in the container.
   * @param row The row of the label cell in the container.
   * 
   * @return The node containing the value text.
   */
  protected final Text addValueCell(final GridPane container, final String cellText,
      final VPos position, final int column, final int row) {
    final Pair<TextFlow, Text> valueCell = createValueCell(cellText, position);
    container.add(valueCell.getKey(), column, row);

    return valueCell.getValue();
  }

  /**
   * Creates a cell styled for a detail value. Does not add it to this panel!
   * 
   * @param cellText The value as text.
   * @param position If the label is the first, middle, or last in a list of labels. This determines
   *        the border drawn around the value.
   * 
   * @return A pair of the styled node wrapping the the value text node and the wrapped value text
   *         node.
   */
  protected final Pair<TextFlow, Text> createValueCell(final String cellText, final VPos position) {
    final Text text = new Text(cellText);

    final StringBuilder style = new StringBuilder();

    style.append(BORDER_COLOR).append(WHITE_BACKGROUND_COLOR);

    switch (position) {
      case BOTTOM:
        style.append("-fx-border-width: 0 1 1 0;");
        break;
      case CENTER:
        style.append("-fx-border-width: 0 1 0 0;");
        break;
      case TOP:
        style.append("-fx-border-width: 1 1 0 0;");
        break;
      case BASELINE:
        style.append("-fx-border-width: 1 1 1 0;");
        break;
      default:
        throw new IllegalArgumentException("Unsupported position: " + position);

    }

    final TextFlow cell = new TextFlow(text);
    cell.setTextAlignment(TextAlignment.RIGHT);
    cell.setStyle(style.toString());
    cell.setPadding(new Insets(1, 2, 1, 10)); // top, right, bottom, left

    GridPane.setFillHeight(cell, true);
    GridPane.setFillWidth(cell, true);
    GridPane.setHalignment(cell, HPos.RIGHT);
    GridPane.setValignment(cell, VPos.CENTER);
    GridPane.setHgrow(cell, Priority.NEVER);
    GridPane.setMargin(cell, new Insets(0, 20, 0, 0)); // top, right, bottom, left

    return new Pair<TextFlow, Text>(cell, text);

  }

  /**
   * Formats the number for display.
   */
  protected final String format(final long number) {
    return new DecimalFormat("#,##0").format(number);
  }
}
