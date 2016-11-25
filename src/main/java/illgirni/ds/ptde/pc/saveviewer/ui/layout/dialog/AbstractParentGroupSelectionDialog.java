package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import java.util.function.Function;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageKey;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageRegistry;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.AbstractTreeNodeWrapper;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotGroupWrapper;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A dialog which provides convenience methods to create a tree show exported slot groups, so that
 * one of them can be chosen as parent of another group or container of an exported slot.
 * 
 * @author illgirni
 *
 * @param <T> The dialog return type.
 */
public abstract class AbstractParentGroupSelectionDialog<T> extends Dialog<T> {
    
    /**
     * The currently selected tree item.
     */
    private final SimpleObjectProperty<TreeItem<AbstractTreeNodeWrapper<?>>> selectedParentTreeNode = new SimpleObjectProperty<>();
    
    /**
     * The currently selected slot group.
     */
    private final TransitiveObjectProperty<TreeItem<AbstractTreeNodeWrapper<?>>, ExportedSlotGroup> selectedParent = new TransitiveObjectProperty<>();
    
    /**
     * Applies the styles to the dialog and links the {@link #getSelectedParent()} to
     * the tree selection.
     */
    public AbstractParentGroupSelectionDialog() {
        BasicDialogs.applySaveViewerStyle(this);
        
        this.selectedParent.bind(selectedParentTreeNode, treeItem -> treeItem.getValue() instanceof ExportedSlotGroupWrapper 
                                                                            ? ((ExportedSlotGroupWrapper) treeItem.getValue()).getWrappedNode() 
                                                                            : null);
    }
    
    /**
     * The currently selected group.
     */
    protected SimpleObjectProperty<ExportedSlotGroup> getSelectedParent() {
        return selectedParent;
    }
    
    /**
     * Creates a tree in a scroll panel with the given node as root of he tree.
     * 
     * @param parentGroups The root of the tree.
     * @return The tree wrapped in a scroll panel.
     */
    protected final ScrollPane createScrollableTree(final ExportedSlotsWrapper parentGroups) {
        final TreeView<AbstractTreeNodeWrapper<?>> parentTree = createParentSelectionTree(parentGroups);
        selectedParentTreeNode.bind(parentTree.getSelectionModel().selectedItemProperty());
        
        final ScrollPane treeScroller = new ScrollPane(parentTree);
        treeScroller.setFitToHeight(true);
        treeScroller.setFitToWidth(true);
        treeScroller.setPrefHeight(300);
        
        return treeScroller;
    }
    
    /**
     * Creates the actual group tree with the given node as value of the tree's root node.
     * 
     * @param wrappedParents The root value of the tree.
     * @return The tree.
     */
    private TreeView<AbstractTreeNodeWrapper<?>> createParentSelectionTree(final ExportedSlotsWrapper wrappedParents) {
        final TreeItem<AbstractTreeNodeWrapper<?>> treeData = createTreeNode(wrappedParents);
        treeData.setExpanded(true);
        
        TreeView<AbstractTreeNodeWrapper<?>> tree = new TreeView<>(treeData);
        tree.setShowRoot(false);
        //custom cell factory for correct display of tree nodes and better disclosure node behavior
        tree.setCellFactory((treeView) -> new ParentTreeCell());
        
        //deselect on ESC
        tree.setTooltip(new Tooltip("To deselect press the 'ESC' key."));
        tree.setOnKeyPressed(keyEvent -> {if (keyEvent.getCode() == KeyCode.ESCAPE) {tree.getSelectionModel().clearSelection();}});
        
        return tree;
    }
    
    /**
     * Creates a tree item wrapping the given node. Also creates tree items for the children of the node
     * and adds them as children to the created tree item.
     * 
     * @param treeNodeValue The tree item's value.
     * @return The tree item.
     */
    private TreeItem<AbstractTreeNodeWrapper<?>> createTreeNode(final AbstractTreeNodeWrapper<?> treeNodeValue) {
        final TreeItem<AbstractTreeNodeWrapper<?>> treeNode = new TreeItem<>(treeNodeValue);
        
        for (final AbstractTreeNodeWrapper<?> child : treeNodeValue.getChildren()) {
            final TreeItem<AbstractTreeNodeWrapper<?>> childNode = createTreeNode(child);
            treeNode.getChildren().add(childNode);
        }
        
        return treeNode;
    }
    
    /**
     * A tree cell showing the cell's value as name of the value. Also shows an icon in front of the name.
     * Corrects the vertical alignment and click-behavior of the cell's disclosure node. 
     *
     */
    //for custom disclosure node that reacts when row is selected 
    private class ParentTreeCell extends TreeCell<AbstractTreeNodeWrapper<?>> {
        
        public ParentTreeCell() {
            setDisclosureNode(createDisclosureNode());
        }
        
        /**
         * Sets the name of the node and an icon as the cell's content.
         */
        @Override
        protected void updateItem(final AbstractTreeNodeWrapper<?> item, final boolean empty) {
            super.updateItem(item, empty);
            
            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                
            } else {
                final HBox content = new HBox();
                content.setAlignment(Pos.CENTER_LEFT);
                content.setSpacing(3);
                
                final Node icon = createFolderIcon();
                final Text name = new Text(item.getName().get());
                
                content.getChildren().addAll(icon, name);
                
                setText(null);
                setGraphic(content);
            }
            
        }
        
        /**
         * The icon shown for the cell.
         */
        private Node createFolderIcon() {
            ImageView folderIcon = new ImageView(ImageRegistry.getImage(ImageKey.FOLDER));
            folderIcon.setPreserveRatio(true);
            folderIcon.setFitHeight(18);
            folderIcon.setFitWidth(18);
            
            return folderIcon;
        }
        
        /**
         * Custom disclosure node implementation that allows to expand or collapse
         * the tree node even when it is currently selected. Also fixes the alignment
         * of the node to be vertically centered.
         */
        private Node createDisclosureNode() {
            final VBox disclosureWrapper = new VBox();
            disclosureWrapper.setAlignment(Pos.CENTER_LEFT);
            disclosureWrapper.prefHeightProperty().bind(heightProperty().subtract(7));
            disclosureWrapper.getStyleClass().setAll("tree-disclosure-node-wrapper");
            
            final StackPane disclosureNode = new StackPane();
            disclosureNode.getStyleClass().setAll("tree-disclosure-node");
            
            final StackPane disclosureNodeArrow = new StackPane();
            disclosureNodeArrow.getStyleClass().setAll("arrow");
            disclosureNode.getChildren().add(disclosureNodeArrow);
            
            disclosureWrapper.getChildren().add(disclosureNode);
            disclosureWrapper.setOnMouseClicked(event -> getTreeItem().setExpanded(!getTreeItem().isExpanded()));
            
            return disclosureWrapper;
        }
    }
    
    /**
     * Custom property, which takes the value from a property (attribute) of an observed object value.
     *
     * @param <S> The type of the observed object.
     * @param <R> The type of this property's value.
     */
    private class TransitiveObjectProperty<S, R> extends SimpleObjectProperty<R> {
        
        /**
         * The function selecting the value from the observed object.
         */
        private Function<S, R> selector;
        
        /**
         * The observed object holder.
         */
        private ObservableObjectValue<S> observed;
        
        /**
         * @param newObservable The observed object holder.
         * @param selector The function selecting the value from the observed object.
         */
        public void bind(ObservableObjectValue<S> newObservable, Function<S, R> selector) {
            this.selector = selector;
            this.observed = newObservable;
            
            observed.addListener((observable, oldValue, newValue) -> fireValueChangedEvent());
            
        }
        
        /**
         * Gets the value from the observed object by using the selection function when this property 
         * is bound to the other observable with {@link #bind(ObservableObjectValue, Function)}.
         * Otherwise behaves like the base implementation.
         */
        @Override
        public R get() {
            if (observed != null && selector != null) {
                if (observed.get() == null) {
                    return null;
                } else {
                    return selector.apply(observed.get());
                }
            }
            
            return super.get();
        }
        
    }
    
}
