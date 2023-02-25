package illgirni.ds.ptde.pc.saveviewer.ui.slottree;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;

/**
 * Wraps a group of exported slots and its sub-groups.
 * 
 * @author illgirni
 *
 */
public class ExportedSlotGroupWrapper extends AbstractTreeNodeWrapper<ExportedSlotGroup> {

  /**
   * @param wrappedNode The group of exported slots.
   */
  public ExportedSlotGroupWrapper(final ExportedSlotGroup wrappedNode) {
    super(wrappedNode);
  }

  /**
   * @param wrappedNode The group of exported slots.
   * @param groupsOnly If only "groups" will be displayed children of this node.
   */
  public ExportedSlotGroupWrapper(final ExportedSlotGroup wrappedNode, final boolean groupsOnly) {
    super(wrappedNode, groupsOnly);
  }

  /**
   * The name of the wrapped group.
   */
  @Override
  protected String doGetName(final ExportedSlotGroup wrappedNode) {
    return wrappedNode.getName();
  }

  /**
   * The sub-groups and, if not {@link #isGroupsOnly()}, also the exported slots. The sub-groups
   * come first in the children list.
   */
  @Override
  protected List<?> getChildrenToWrap() {
    List<Object> childrenToWrap = new ArrayList<>();

    childrenToWrap.addAll(getWrappedNode().getSlotGroups());

    if (!isGroupsOnly()) {
      childrenToWrap.addAll(getWrappedNode().getSlots());
    }

    return childrenToWrap;
  }

  /**
   * Bases on the child type an {@link ExportedSlotGroupWrapper} or an {@link ExportedSlotWrapper}.
   */
  @Override
  protected AbstractTreeNodeWrapper<?> wrapChild(final Object childToWrap,
      final boolean groupsOnly) {
    if (childToWrap instanceof ExportedSlotGroup) {
      return new ExportedSlotGroupWrapper((ExportedSlotGroup) childToWrap, groupsOnly);

    } else if (childToWrap instanceof ExportedSlot) {
      return new ExportedSlotWrapper((ExportedSlot) childToWrap);

    } else {
      throw new IllegalArgumentException("Unsupported child type: " + childToWrap);
    }
  }

}
