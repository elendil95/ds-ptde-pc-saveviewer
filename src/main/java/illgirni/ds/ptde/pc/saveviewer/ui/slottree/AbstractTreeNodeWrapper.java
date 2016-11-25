package illgirni.ds.ptde.pc.saveviewer.ui.slottree;

import java.util.List;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Base implementation for a node in a containing groups for exported slots
 * and exported slots. Wraps the group or node that this node represents.
 * 
 * @author illgirni
 *
 * @param <T>
 */
public abstract class AbstractTreeNodeWrapper<T> {
    
    /**
     * The children of the node.
     */
    private final ObservableList<AbstractTreeNodeWrapper<?>> children;
    
    /**
     * The name of the wrapped node.
     */
    private final SimpleStringProperty name = new SimpleStringProperty();
    
    /**
     * The wrapped node.
     */
    private final T wrappedNode;
    
    /**
     * If only "groups" will be displayed children of this node.
     */
    private final boolean groupsOnly;
    
    /**
     * @param wrappedNode The wrapped node.
     */
    public AbstractTreeNodeWrapper(final T wrappedNode) {
        this(wrappedNode, false);
    }
    
    /**
     * @param wrappedNode The wrapped node.
     * @param groupsOnly If only "groups" will be displayed children of this node.
     */
    public AbstractTreeNodeWrapper(final T wrappedNode, final boolean groupsOnly) {
        this.wrappedNode = wrappedNode;
        this.groupsOnly = groupsOnly;
        
        this.name.set(doGetName(wrappedNode));
        this.children = FXCollections.observableArrayList();
        getChildrenToWrap().forEach(child -> children.add(wrapChild(child, groupsOnly)));
    }
    
    /**
     * Refreshes this node and its children.
     */
    public final void refresh() {
        name.set(doGetName(wrappedNode));
        
        final List<?> childrenToWrap = getChildrenToWrap();
        
        removeNonReferencedChildren(childrenToWrap);
        addNewChildren(childrenToWrap);
        updateChildOrder(childrenToWrap);
        
        children.forEach(child -> child.refresh());
        
    }
    
    /**
     * Removes the wrapped children from this wrapper, which are not referenced by this nodes 
     * wrapped node anymore.
     * 
     * @param childrenToWrap The children that the wrapped node currently has.
     */
    private void removeNonReferencedChildren(final List<?> childrenToWrap) {
        children.removeIf(wrappedChild -> !childrenToWrap.contains(wrappedChild.getWrappedNode()));
    }
    
    /**
     * Creates new wrapped children that this wrapper does not yet reference.
     *  
     * @param childrenToWrap The children that the wrapped node currently has.
     */
    private void addNewChildren(final List<?> childrenToWrap) {
        for (int childIndex = 0; childIndex < childrenToWrap.size(); childIndex++) {
            final Object childToWrap = childrenToWrap.get(childIndex);
            
            if (getChildIndex(childToWrap) < 0) {
                children.add(childIndex, wrapChild(childToWrap, groupsOnly));
            }
        }
        
    }
    
    /**
     * Updates the order of this node's children to correspond to the order of the 
     * children of the wrapped node.
     * 
     * @param childrenToWrap The children that the wrapped node currently has.
     */
    private void updateChildOrder(final List<?> childrenToWrap) {
        boolean orderChanged = false;
        
        //only re-order, if at least one child is in the wrong position
        for (int childIndex = 0; !orderChanged && childIndex < childrenToWrap.size(); childIndex++) {
            final Object childToWrap = childrenToWrap.get(childIndex);
            
            if (getChildIndex(childToWrap) != childIndex) {
                orderChanged = true;
            }
        }
        
        if (orderChanged) {
            children.sort((child1, child2) -> childrenToWrap.indexOf(child1.getWrappedNode()) - childrenToWrap.indexOf(child2.getWrappedNode()));
        }
    }
    
    /**
     * Gets the index of the child of this node that wraps the given child of this node's wrapped node.
     * 
     * @param wrappedChild The wrapped child.
     * @return The index of the child.
     */
    private int getChildIndex(final Object wrappedChild) {
        for (int childIndex = 0; childIndex < children.size(); childIndex++) {
            final AbstractTreeNodeWrapper<?> childWrapper = children.get(childIndex);
            
            if (Objects.equals(wrappedChild, childWrapper.getWrappedNode())) {
                return childIndex;
            }
        }
        
        return -1;
    }
    
    /**
     * The wrapped node's name.
     */
    public StringProperty getName() {
        return name;
    }
    
    /**
     * Read the name of the node.
     * 
     * @param wrappedNode The node
     * @return The node's name.
     */
    protected abstract String doGetName(T wrappedNode);
    
    /**
     * The children of the wrapped node that should be wrapped children of this node.
     */
    protected abstract List<?> getChildrenToWrap();
    
    /**
     * Wrap the child so that it can be added to this node.
     * 
     * @param childToWrap The child.
     * @param groupsOnly If the child should only display group children.
     * @return The wrapped child.
     */
    protected abstract AbstractTreeNodeWrapper<?> wrapChild(Object childToWrap, boolean groupsOnly);
    
    /**
     * The wrapped node.
     */
    public final T getWrappedNode() {
        return wrappedNode;
    }
    
    /**
     * If only "groups" will be displayed children of this node.
     */
    public final boolean isGroupsOnly() {
        return groupsOnly;
    }
    
    /**
     * The children to display for this node.
     */
    public final ObservableList<AbstractTreeNodeWrapper<?>> getChildren() {
        return children;
    }
    
}
