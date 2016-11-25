package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;

/**
 * The dialog in which to choose a new group containing an already exported slot. Does not allow
 * to choose the root as container (because it is not a group).
 * 
 * @author illgirni
 *
 */
public class MoveExportedSlotDialog extends AbstractMoveExportedElementDialog {
    
    /**
     * @param movedSlotName Name of the slot for which to choose a new group.
     * @param parentGroups The choosable groups.
     */
    public MoveExportedSlotDialog(final String movedSlotName, final ExportedSlotsWrapper parentGroups) {
        super(true, parentGroups);
        
        setTitle("Move Exported Character");
        setHeaderText("Select new group for exported character '" + movedSlotName + "'.");
    }

}
