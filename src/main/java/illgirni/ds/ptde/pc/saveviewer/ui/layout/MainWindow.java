package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import illgirni.ds.ptde.pc.saveviewer.ui.event.MenuEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.MenuEvent.MenuElement;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceSetting;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 * The main window of the application. It is split into four parts:
 * <ul>
 * <li>Menu bar (at the top border)</li>
 * <li>Space for save file slots (top left corner)</li>
 * <li>Space for the exported slots (bottom left corner)</li>
 * <li>Space for the slot details (right "half")</li>
 * </ul>
 */
public class MainWindow extends Scene {

  /**
   * The window content.
   */
  private final WindowContent content;

  /**
   * Sets up the content of the window and applies style sheets to the window and all its children.
   */
  public MainWindow() {
    super(new BorderPane());

    this.content = new WindowContent();
    getStylesheets().addAll(LayoutUtils.getStylesheets());

    final BorderPane contentRoot = (BorderPane) getRoot();
    contentRoot.setTop(createMenu());
    contentRoot.setCenter(content);

  }

  /**
   * Creates the application menu. The menu elements fire events corresponding to the clicked menu
   * entry.
   */
  private MenuBar createMenu() {
    final MenuBar menu = new MenuBar();

    final MenuItem fileInfoItem = new MenuItem("File info...");
    fileInfoItem.setOnAction(event -> menu.fireEvent(new MenuEvent(MenuElement.FILE_INFO)));

    final SeparatorMenuItem separator1 = new SeparatorMenuItem();

    final MenuItem exitItem = new MenuItem("Exit");
    exitItem.setOnAction(event -> System.exit(0));

    final Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(fileInfoItem, separator1, exitItem);

    final MenuItem creditsItem = new MenuItem("Credits");
    creditsItem.setOnAction(event -> menu.fireEvent(new MenuEvent(MenuElement.CREDITS)));

    final MenuItem aboutItem = new MenuItem("About");
    aboutItem.setOnAction(event -> menu.fireEvent(new MenuEvent(MenuElement.ABOUT)));

    final SeparatorMenuItem separator2 = new SeparatorMenuItem();

    final MenuItem settingsItem = new MenuItem("Preferences...");
    settingsItem.setOnAction(event -> menu.fireEvent(new MenuEvent(MenuElement.SETTINGS)));

    final Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(creditsItem, aboutItem, separator2, settingsItem);

    menu.getMenus().addAll(fileMenu, helpMenu);

    return menu;
  }

  /**
   * Sets the save file slot panel in the window content.
   */
  public void setSaveFileSlotList(final Node saveFileSlotList) {
    content.setLeftColumnTopContent(saveFileSlotList);
  }

  /**
   * Sets the exported slot panel in the window content.
   */
  public void setExportedSlotsList(final Node exportedSlotsList) {
    content.setLeftColumnBottomContent(exportedSlotsList);
  }

  /**
   * Sets the "selected slot details" panel in the window content.
   */
  public void setSelectedSlotInfo(final Node selectedSlotInfo) {
    content.setRightColumnContent(selectedSlotInfo);
  }

  /**
   * The actual window content. It is a split pane defining two "columns". The left column is split
   * into two rows. The "spaces" created in that manner have scroll panes as their root elements.
   */
  private static class WindowContent extends SplitPane {

    private ScrollPane leftColumnTop;

    private ScrollPane leftColumnBottom;

    private ScrollPane rightColumn;

    public WindowContent() {
      setOrientation(Orientation.HORIZONTAL);

      if (LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_MAXIMIZED) == 1) {
        Platform.runLater(() -> setDividerPositions(
            LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_DIVIDER)
                / getScene().getWindow().getWidth()));

      } else {
        Platform.runLater(() -> setDividerPositions(
            (1.0 * LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_DIVIDER))
                / LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_WIDTH)));

      }

      SplitPane leftColumn = new SplitPane();
      leftColumn.setOrientation(Orientation.VERTICAL);

      leftColumnTop = new ScrollPane();
      leftColumnTop.setFitToHeight(true);
      leftColumnTop.setFitToWidth(true);
      leftColumnTop.setPadding(new Insets(5));
      leftColumnTop.setStyle("-fx-background-color: #fff");

      leftColumnBottom = new ScrollPane();
      leftColumnBottom.setFitToHeight(true);
      leftColumnBottom.setFitToWidth(true);
      leftColumnBottom.setPadding(new Insets(5));
      leftColumnBottom.setStyle("-fx-background-color: #fff");

      leftColumn.getItems().addAll(leftColumnTop, leftColumnBottom);
      leftColumn.setStyle("-fx-background-color: #fff");

      rightColumn = new ScrollPane();
      rightColumn.setFitToHeight(true);
      rightColumn.setFitToWidth(true);
      rightColumn.setPadding(new Insets(5));
      rightColumn.setStyle("-fx-background-color: #fff");

      getItems().addAll(leftColumn, rightColumn);
    }

    public void setLeftColumnTopContent(final Node content) {
      leftColumnTop.setContent(content);
    }

    public void setLeftColumnBottomContent(final Node content) {
      leftColumnBottom.setContent(content);
    }

    public void setRightColumnContent(final Node content) {
      rightColumn.setContent(content);
    }

  }

}
