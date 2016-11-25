package illgirni.ds.ptde.pc.saveviewer.ui.slottree;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlots;

/**
 * Wrapper for the container of the root groups in the workspace.
 * 
 * @author illgirni
 *
 */
public class ExportedSlotsWrapper extends AbstractTreeNodeWrapper<ExportedSlots> {
    
    /**
     * @param wrappedNode The root group container.
     */
    public ExportedSlotsWrapper(final ExportedSlots wrappedNode) {
        super(wrappedNode);
    }
    /**
     * @param wrappedNode The root group container.
     * @param groupsOnly If only "groups" will be displayed children of this node.
     */
    public ExportedSlotsWrapper(final ExportedSlots wrappedNode, final boolean groupsOnly) {
        super(wrappedNode, groupsOnly);
    }
    
    /**
     * @return {@code null}
     */
    @Override
    protected String doGetName(final ExportedSlots wrappedNode) {
        return null;
    }
    
    /**
     * The groups in the wrapped root group container.
     */
    @Override
    protected List<?> getChildrenToWrap() {
        return getWrappedNode().getSlotGroups();
    }
    
    /**
     * The child as {@link ExportedSlotGroupWrapper}.
     */
    @Override
    protected AbstractTreeNodeWrapper<?> wrapChild(final Object childToWrap, final boolean groupsOnly) {
        return new ExportedSlotGroupWrapper((ExportedSlotGroup) childToWrap, groupsOnly);
    }
    
}
