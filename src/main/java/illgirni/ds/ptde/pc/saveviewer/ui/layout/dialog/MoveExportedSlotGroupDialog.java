package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;

/**
 * The dialog to choose a new parent for an exported slot group. It is allowed to choose the
 * "nothing" to make the group a root group.
 * 
 * @author illgirni
 *
 */
public class MoveExportedSlotGroupDialog extends AbstractMoveExportedElementDialog {

  /**
   * @param movedGroupName The name of the group for which to choose a new parent.
   * @param parentGroups The available parent groups.
   */
  public MoveExportedSlotGroupDialog(final String movedGroupName,
      final ExportedSlotsWrapper parentGroups) {
    super(false, parentGroups);

    setTitle("Move Exported Character Group");
    setHeaderText("Select new parent group for character group '" + movedGroupName + "'.");
  }

}
