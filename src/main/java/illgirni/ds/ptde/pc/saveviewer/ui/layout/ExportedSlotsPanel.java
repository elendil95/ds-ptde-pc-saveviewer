package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import java.util.List;
import java.util.Objects;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.ui.event.AddGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.DeleteGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.DeleteSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.MoveGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.MoveSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.RenameGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.RenameSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ShowExportedSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageButton;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageKey;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageRegistry;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.AbstractTreeNodeWrapper;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotGroupWrapper;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotWrapper;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * The panel showing the exported slots in their groups. The exported slots and their groups are
 * shown as a tree-table. The panel also has controls to modify the tree. It fires an event every
 * time an exported slot has been selected.
 * 
 * @author illgirni
 *
 */
public class ExportedSlotsPanel extends BorderPane {

  /**
   * The tree-table with the exported slots.
   */
  private TreeTableView<AbstractTreeNodeWrapper<?>> exportedSlotsTree;

  /**
   * The currently selected exported slot.
   */
  private final SimpleObjectProperty<ExportedSlotWrapper> selectedSlotProperty =
      new SimpleObjectProperty<>();

  /**
   * Sets up the contents of the panel.
   */
  public ExportedSlotsPanel() {
    final GridPane titleBar = new GridPane();
    titleBar.setPadding(new Insets(5));
    titleBar
        .setStyle("-fx-background-color: #fff; -fx-border-width: 0 0 1 0; -fx-border-color: #999;");
    setTop(titleBar);

    final Text title = new Text("Exported Characters");
    title.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em");
    GridPane.setHgrow(title, Priority.ALWAYS);
    GridPane.setValignment(title, VPos.CENTER);
    titleBar.add(title, 0, 0);

    final Button addGroupButton = new ImageButton(ImageKey.ADD_FOLDER);
    addGroupButton.setTooltip(new Tooltip("Add Group..."));
    addGroupButton.setOnAction(event -> addGroupButton.fireEvent(new AddGroupEvent()));
    GridPane.setHgrow(addGroupButton, Priority.NEVER);
    GridPane.setValignment(addGroupButton, VPos.CENTER);
    titleBar.add(addGroupButton, 1, 0);

    setTop(titleBar);

    exportedSlotsTree = setupSlotTree();

    final ScrollPane content = new ScrollPane(exportedSlotsTree);
    content.setFitToHeight(true);
    content.setFitToWidth(true);

    setCenter(content);

  }

  /**
   * Clears the selection of the tree-table.
   */
  public void clearSelection() {
    exportedSlotsTree.getSelectionModel().clearSelection();
  }

  /**
   * Sets the contents of the tree-table to the given tree data. Wraps the tree nodes in
   * {@link TreeItems TreeItem}.
   * 
   * @see #createTreeItem(AbstractTreeNodeWrapper)
   */
  public void setSlotTree(final ExportedSlotsWrapper slotsTree) {
    clearSelection();

    final TreeItem<AbstractTreeNodeWrapper<?>> root = createTreeItem(slotsTree);
    root.setExpanded(true);
    exportedSlotsTree.setRoot(root);
  }

  /**
   * Builds the tree-table shown in the panel. Defines the structure of the tree-table:
   * <ul>
   * <li>Adds a custom row factory: {@link TreeTableViewRowFactory}</li>
   * <li>Defines the columns of the table: {@link #createColumns(TreeTableView)}</li>
   * <li>Registers an internal selection listener on the tree: {@link TreeSelectionListener}</li>
   * </ul>
   * The table won't have any contents yet. They have to be defined with
   * {@link #setSlotTree(ExportedSlotsWrapper)}.
   * 
   * @return The tree-table.
   */
  private TreeTableView<AbstractTreeNodeWrapper<?>> setupSlotTree() {
    TreeTableView<AbstractTreeNodeWrapper<?>> slotTree = new TreeTableView<>();
    slotTree.setColumnResizePolicy(TreeTableView.UNCONSTRAINED_RESIZE_POLICY);
    slotTree.setShowRoot(false);
    slotTree.setRowFactory(new TreeTableViewRowFactory());
    slotTree.setTableMenuButtonVisible(true);

    createColumns(slotTree);

    // deselect on ESC
    slotTree.setTooltip(new Tooltip("To deselect press the 'ESC' key."));
    slotTree.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.ESCAPE) {
        clearSelection();
      }
    });

    final TreeTableViewSelectionModel<AbstractTreeNodeWrapper<?>> treeSelectionModel =
        slotTree.getSelectionModel();
    treeSelectionModel.setSelectionMode(SelectionMode.SINGLE);
    treeSelectionModel.selectedItemProperty().addListener(new TreeSelectionListener());

    return slotTree;
  }

  /**
   * Wraps the node in a tree item. The children of the node are also wrapped and added as children
   * to the tree item. Also adds a listener to the children list of the node to wrap, which will
   * update the tree item when the children list changes.
   * 
   * @param node The node to wrap.
   * @return The tree item wrapping the node.
   * 
   * @see TreeItemChildrenChangedListener
   */
  private TreeItem<AbstractTreeNodeWrapper<?>> createTreeItem(
      final AbstractTreeNodeWrapper<?> node) {
    final TreeItem<AbstractTreeNodeWrapper<?>> treeNode =
        new TreeItem<AbstractTreeNodeWrapper<?>>(node);
    // treeNode.setExpanded(true);

    for (final AbstractTreeNodeWrapper<?> childNode : node.getChildren()) {
      treeNode.getChildren().add(createTreeItem(childNode));
    }

    node.getChildren().addListener(new TreeItemChildrenChangedListener(treeNode));

    return treeNode;
  }

  /**
   * Creates and adds the columns for the table-tree. There are four columns:
   * <ul>
   * <li>Delete tree node</li>
   * <li>Move tree node</li>
   * <li>Rename tree node</li>
   * <li>Tree node icon and name</li>
   * </ul>
   * 
   */
  private void createColumns(TreeTableView<AbstractTreeNodeWrapper<?>> slotTree) {
    final TreeTableColumn<AbstractTreeNodeWrapper<?>, Node> deleteColumn = new TreeTableColumn<>();
    deleteColumn.setText("D");
    deleteColumn.setCellValueFactory(new DeleteColumnCellValueFactory());
    deleteColumn.setSortable(false);
    deleteColumn.setResizable(false);
    deleteColumn.setVisible(false);

    final TreeTableColumn<AbstractTreeNodeWrapper<?>, Node> moveColumn = new TreeTableColumn<>();
    moveColumn.setText("M");
    moveColumn.setCellValueFactory(new MoveColumnCellValueFactory());
    moveColumn.setSortable(false);
    moveColumn.setResizable(false);
    moveColumn.setVisible(false);

    final TreeTableColumn<AbstractTreeNodeWrapper<?>, Node> editColumn = new TreeTableColumn<>();
    editColumn.setText("E");
    editColumn.setCellValueFactory(new EditColumnCellValueFactory());
    editColumn.setSortable(false);
    editColumn.setResizable(false);
    editColumn.setVisible(false);

    final TreeTableColumn<AbstractTreeNodeWrapper<?>, Node> nameColumn = new TreeTableColumn<>();
    nameColumn.setText("Characters");
    nameColumn.setStyle("-fx-alignment: center-left;");
    nameColumn.setCellValueFactory(new NameColumnCellValueFactory());
    nameColumn.setMinWidth(200);
    nameColumn.setSortable(false);
    // column takes as much width as it can
    nameColumn.prefWidthProperty()
        .bind(slotTree.widthProperty()
            .subtract(Bindings.when(deleteColumn.visibleProperty())
                .then(deleteColumn.widthProperty()).otherwise(0))
            .subtract(Bindings.when(moveColumn.visibleProperty()).then(moveColumn.widthProperty())
                .otherwise(0))
            .subtract(editColumn.widthProperty()).subtract(25));

    slotTree.getColumns().add(deleteColumn);
    slotTree.getColumns().add(moveColumn);
    slotTree.getColumns().add(editColumn);
    slotTree.getColumns().add(nameColumn);

    slotTree.setTreeColumn(nameColumn);
  }

  /**
   * A list change listener which can adapt the the list of children of a tree item when the list
   * listened on changes. Will change the list of tree item children to correspond to the changed
   * list.
   */
  // http://myjavafx.blogspot.de/2012/03/treeview-with-data-source.html
  private class TreeItemChildrenChangedListener
      implements ListChangeListener<AbstractTreeNodeWrapper<?>> {
    /**
     * The tree item to modify.
     */
    private final TreeItem<AbstractTreeNodeWrapper<?>> treeNode;

    /**
     * @param treeNode The tree item to modify.
     */
    public TreeItemChildrenChangedListener(TreeItem<AbstractTreeNodeWrapper<?>> treeNode) {
      this.treeNode = treeNode;
    }

    @Override
    public void onChanged(Change<? extends AbstractTreeNodeWrapper<?>> change) {
      final ObservableList<TreeItem<AbstractTreeNodeWrapper<?>>> treeItemChildren =
          treeNode.getChildren();
      final ObservableList<? extends AbstractTreeNodeWrapper<?>> changedList = change.getList();

      while (change.next()) {
        if (!change.wasUpdated()) {
          if (change.wasRemoved()) {
            // Clear tree selection if we remove something. DoesnÄt matter, if this node was
            // affected.
            clearSelection();

            final List<? extends AbstractTreeNodeWrapper<?>> removedNodes = change.getRemoved();
            treeItemChildren
                .removeIf(treeItemChild -> removedNodes.contains(treeItemChild.getValue()));
          }

          if (change.wasAdded()) {
            final List<? extends AbstractTreeNodeWrapper<?>> addedNodes = change.getAddedSubList();

            for (final AbstractTreeNodeWrapper<?> addedNode : addedNodes) {
              treeItemChildren.add(changedList.indexOf(addedNode), createTreeItem(addedNode));
            }
          }

          if (change.wasPermutated()) {
            clearSelection();

            treeItemChildren.sort(
                (treeItemChild1, treeItemChild2) -> changedList.indexOf(treeItemChild1.getValue())
                    - changedList.indexOf(treeItemChild2.getValue()));

          }

        }
      }
    }

  }

  /**
   * Custom listener for tree-table node selections. When the newly selected node is an exported
   * slot and the exported slot does not correspond to the last selected one
   * ({@link ExportedSlotsPanel#selectedSlotProperty}) fires an "selected slot changed" event. In
   * case of de-selection always fires the event.
   */
  private class TreeSelectionListener
      implements ChangeListener<TreeItem<AbstractTreeNodeWrapper<?>>> {
    @Override
    public void changed(
        final ObservableValue<? extends TreeItem<AbstractTreeNodeWrapper<?>>> observable,
        final TreeItem<AbstractTreeNodeWrapper<?>> oldSelection,
        final TreeItem<AbstractTreeNodeWrapper<?>> newSelection) {
      if (newSelection != null) {
        if (newSelection.getValue() instanceof ExportedSlotWrapper) {
          final ExportedSlotWrapper newSelectedSlot = (ExportedSlotWrapper) newSelection.getValue();

          if (!Objects.equals(newSelectedSlot, selectedSlotProperty.get())) {
            selectedSlotProperty.set(newSelectedSlot);
            exportedSlotsTree
                .fireEvent(new ShowExportedSlotEvent(newSelectedSlot.getWrappedNode()));
          }
        }

      } else if (selectedSlotProperty.isNotNull().get()) {
        selectedSlotProperty.set(null);
        exportedSlotsTree.fireEvent(new ShowExportedSlotEvent(null));
      }
    }
  }

  /**
   * Factory for the values shown in the name column of the tree-table. The value is a node
   * consisting of an icon and the cell value's name.
   */
  private class NameColumnCellValueFactory implements
      Callback<CellDataFeatures<AbstractTreeNodeWrapper<?>, Node>, ObservableValue<Node>> {
    @Override
    public ObservableValue<Node> call(CellDataFeatures<AbstractTreeNodeWrapper<?>, Node> cellData) {
      final HBox nodeValue = new HBox();
      nodeValue.setAlignment(Pos.CENTER_LEFT);
      nodeValue.setSpacing(3);

      final Text nameLabel = new Text();
      final ImageView icon = new ImageView();
      icon.setFitWidth(18);
      icon.setFitWidth(18);
      icon.setPreserveRatio(true);

      final TreeItem<AbstractTreeNodeWrapper<?>> treeItem = cellData.getValue();
      final AbstractTreeNodeWrapper<?> treeItemValue = treeItem.getValue();

      if (treeItemValue instanceof ExportedSlotGroupWrapper) {
        final ExportedSlotGroup group = (ExportedSlotGroup) treeItemValue.getWrappedNode();
        nameLabel.setText(group.getName());
        icon.setImage(ImageRegistry.getImage(ImageKey.FOLDER));

      } else if (treeItemValue instanceof ExportedSlotWrapper) {
        final ExportedSlot slot = (ExportedSlot) treeItemValue.getWrappedNode();
        nameLabel.setText(slot.getName());
        icon.setImage(ImageRegistry.getImage(ImageKey.FILE));

      } else {
        throw new IllegalArgumentException("Unsupported node value: " + treeItemValue);
      }

      nameLabel.textProperty().bind(treeItemValue.getName());

      nodeValue.getChildren().addAll(icon, nameLabel);

      return new ReadOnlyObjectWrapper<Node>(nodeValue);
    }
  }

  /**
   * Factory for the values shown in the edit name column of the tree-table. The value is an icon
   * button which fires a rename event matching the node's value.
   */
  private class EditColumnCellValueFactory implements
      Callback<CellDataFeatures<AbstractTreeNodeWrapper<?>, Node>, ObservableValue<Node>> {
    @Override
    public ObservableValue<Node> call(CellDataFeatures<AbstractTreeNodeWrapper<?>, Node> cellData) {

      final TreeItem<AbstractTreeNodeWrapper<?>> treeItem = cellData.getValue();
      final AbstractTreeNodeWrapper<?> treeItemValue = treeItem.getValue();

      if (treeItemValue instanceof ExportedSlotGroupWrapper) {
        final Button editButton = new ImageButton(ImageKey.EDIT_FOLDER, 20);
        editButton.setTooltip(new Tooltip("Edit name..."));

        final ExportedSlotGroup renamedGroup = (ExportedSlotGroup) treeItemValue.getWrappedNode();
        editButton.setOnAction(event -> editButton.fireEvent(new RenameGroupEvent(renamedGroup)));

        final HBox nodeValue = new HBox(editButton);
        nodeValue.setAlignment(Pos.CENTER);

        return new ReadOnlyObjectWrapper<Node>(nodeValue);

      } else if (treeItemValue instanceof ExportedSlotWrapper) {
        final Button editButton = new ImageButton(ImageKey.EDIT_FILE, 20);
        editButton.setTooltip(new Tooltip("Edit name..."));

        final ExportedSlot renamedSlot = (ExportedSlot) treeItemValue.getWrappedNode();
        editButton.setOnAction(event -> editButton.fireEvent(new RenameSlotEvent(renamedSlot)));

        final HBox nodeValue = new HBox(editButton);
        nodeValue.setAlignment(Pos.CENTER);

        return new ReadOnlyObjectWrapper<Node>(nodeValue);

      } else {
        return new ReadOnlyObjectWrapper<>();
      }

    }
  }

  /**
   * Factory for the values shown in the move node column of the tree-table. The value is an icon
   * button which fires a move event matching the node's value.
   */
  private class MoveColumnCellValueFactory implements
      Callback<CellDataFeatures<AbstractTreeNodeWrapper<?>, Node>, ObservableValue<Node>> {
    @Override
    public ObservableValue<Node> call(CellDataFeatures<AbstractTreeNodeWrapper<?>, Node> cellData) {
      final TreeItem<AbstractTreeNodeWrapper<?>> treeItem = cellData.getValue();
      final AbstractTreeNodeWrapper<?> treeItemValue = treeItem.getValue();

      if (treeItemValue instanceof ExportedSlotGroupWrapper) {
        final Button moveButton = new ImageButton(ImageKey.MOVE_FOLDER, 20);
        moveButton.setTooltip(new Tooltip("Move group..."));

        final ExportedSlotGroup movedGroup = (ExportedSlotGroup) treeItemValue.getWrappedNode();
        moveButton.setOnAction(event -> moveButton.fireEvent(new MoveGroupEvent(movedGroup)));

        final HBox nodeValue = new HBox(moveButton);
        nodeValue.setAlignment(Pos.CENTER);

        return new ReadOnlyObjectWrapper<Node>(nodeValue);

      } else if (treeItemValue instanceof ExportedSlotWrapper) {
        final Button moveButton = new ImageButton(ImageKey.MOVE_FILE, 20);
        moveButton.setTooltip(new Tooltip("Move character..."));

        final ExportedSlot movedSlot = (ExportedSlot) treeItemValue.getWrappedNode();
        moveButton.setOnAction(event -> moveButton.fireEvent(new MoveSlotEvent(movedSlot)));

        final HBox nodeValue = new HBox(moveButton);
        nodeValue.setAlignment(Pos.CENTER);

        return new ReadOnlyObjectWrapper<Node>(nodeValue);

      } else {
        return new ReadOnlyObjectWrapper<>();
      }

    }
  }

  /**
   * Factory for the values shown in the delete node column of the tree-table. The value is an icon
   * button which fires a delete event matching the node's value.
   */
  private class DeleteColumnCellValueFactory implements
      Callback<CellDataFeatures<AbstractTreeNodeWrapper<?>, Node>, ObservableValue<Node>> {
    @Override
    public ObservableValue<Node> call(CellDataFeatures<AbstractTreeNodeWrapper<?>, Node> cellData) {

      final TreeItem<AbstractTreeNodeWrapper<?>> treeItem = cellData.getValue();
      final AbstractTreeNodeWrapper<?> treeItemValue = treeItem.getValue();

      if (treeItemValue instanceof ExportedSlotGroupWrapper) {
        final Button deleteButton = new ImageButton(ImageKey.REMOVE_FOLDER, 20);
        deleteButton.setTooltip(new Tooltip("Delete group and its contents"));

        final ExportedSlotGroup deletedGroup = (ExportedSlotGroup) treeItemValue.getWrappedNode();
        deleteButton
            .setOnAction(event -> deleteButton.fireEvent(new DeleteGroupEvent(deletedGroup)));

        final HBox nodeValue = new HBox(deleteButton);
        nodeValue.setAlignment(Pos.CENTER);

        return new ReadOnlyObjectWrapper<Node>(nodeValue);

      } else if (treeItemValue instanceof ExportedSlotWrapper) {
        final Button deleteButton = new ImageButton(ImageKey.REMOVE_FILE, 20);
        deleteButton.setTooltip(new Tooltip("Delete character"));

        final ExportedSlot deletedSlot = (ExportedSlot) treeItemValue.getWrappedNode();
        deleteButton.setOnAction(event -> deleteButton.fireEvent(new DeleteSlotEvent(deletedSlot)));

        final HBox nodeValue = new HBox(deleteButton);
        nodeValue.setAlignment(Pos.CENTER);

        return new ReadOnlyObjectWrapper<Node>(nodeValue);

      } else {
        return new ReadOnlyObjectWrapper<>();
      }

    }
  }

  /**
   * Custom tree-table row factory, which fixes the alignment of the disclosure nodes of the tree.
   * The disclosure node is vertically aligned at the center.. Also fixes the issue that the
   * disclosure node is not clickable (i.e. the children of the node expandable or collapsible) when
   * the row is currently selected.
   */
  // for vertical alignment of disclosure (expand) icon.
  // also fixes disclosure clickable when node is selected
  private class TreeTableViewRowFactory implements
      Callback<TreeTableView<AbstractTreeNodeWrapper<?>>, TreeTableRow<AbstractTreeNodeWrapper<?>>> {

    @Override
    public TreeTableRow<AbstractTreeNodeWrapper<?>> call(
        TreeTableView<AbstractTreeNodeWrapper<?>> param) {
      final TreeTableRow<AbstractTreeNodeWrapper<?>> row = new TreeTableRow<>();

      final VBox disclosureWrapper = new VBox();
      disclosureWrapper.setAlignment(Pos.CENTER_LEFT);
      disclosureWrapper.prefHeightProperty().bind(row.heightProperty().subtract(7));
      disclosureWrapper.getStyleClass().setAll("tree-disclosure-node-wrapper");

      final StackPane disclosureNode = new StackPane();
      disclosureNode.getStyleClass().setAll("tree-disclosure-node");

      final StackPane disclosureNodeArrow = new StackPane();
      disclosureNodeArrow.getStyleClass().setAll("arrow");
      disclosureNode.getChildren().add(disclosureNodeArrow);

      disclosureWrapper.getChildren().add(disclosureNode);
      disclosureWrapper.setOnMouseClicked(
          event -> row.getTreeItem().setExpanded(!row.getTreeItem().isExpanded()));

      row.setDisclosureNode(disclosureWrapper);

      return row;
    }
  }
}
