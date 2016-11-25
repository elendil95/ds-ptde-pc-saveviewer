package illgirni.ds.ptde.pc.saveviewer.ui.slottree;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;

/**
 * Wraps an exported slot.
 * 
 * @author illgirni
 *
 */
public class ExportedSlotWrapper extends AbstractTreeNodeWrapper<ExportedSlot> {
    
    /**
     * @param wrappedNode The wrapped slot.
     */
    public ExportedSlotWrapper(final ExportedSlot wrappedNode) {
        super(wrappedNode);
    }
    
    /**
     * The name of the wrapped slot.
     */
    @Override
    protected String doGetName(final ExportedSlot wrappedNode) {
        return wrappedNode.getName();
    }
    
    /**
     * An empty list.
     */
    @Override
    protected List<?> getChildrenToWrap() {
        return new ArrayList<>();
    }
    
    /**
     * @throws UnsupportedOperationException
     */
    @Override
    protected AbstractTreeNodeWrapper<?> wrapChild(final Object childToWrap, final boolean groupsOnly) {
        throw new UnsupportedOperationException("This node type does not have children!");
    }
}
