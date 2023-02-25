package illgirni.ds.ptde.pc.saveviewer.ui.controller;

import java.util.Objects;
import java.util.Optional;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savemanager.SaveManagerException;
import illgirni.ds.ptde.pc.saveviewer.savemanager.WorkspaceManager;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlots;
import illgirni.ds.ptde.pc.saveviewer.ui.event.AddGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.DeleteGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.DeleteSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ExportSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ImportSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.MenuEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.MoveGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.MoveSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ReloadSaveEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.RenameGroupEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.RenameSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ShowExportedSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ShowSaveFileSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.LayoutUtils;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.MainWindow;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.AbstractMoveExportedElementDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.AddExportedSlotGroupDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.BasicDialogs;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.ExportSlotDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.ImportSlotDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.MenuDialogs;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.MoveExportedSlotDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.MoveExportedSlotGroupDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.RenameExportedElementDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceSetting;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.AbstractTreeNodeWrapper;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * The central controller of the application. Puts the contents in the main window and handles most
 * of the events fired by the application.
 * 
 * @author illgirni
 *
 */
public class MainController {

  /**
   * The workspace manager through which all changes to the work space and save file have to be
   * directed.
   */
  private final WorkspaceManager workspaceManager;

  /**
   * The controller for showing the details of a particular save slot.
   */
  private SaveSlotDetailsController saveSlotDetailsController;

  /**
   * The controller for showing the slots in the save file.
   */
  private SaveFileSlotsController saveFileSlotsController;

  /**
   * The controller for showing the exported slots.
   */
  private ExportedSlotsController exportedSlotsController;

  /**
   * The user interface managed by this controller - the application's main window.
   */
  private MainWindow mainWindow;

  /**
   * Initializes the sub-controllers and assembles the main window contents.
   * 
   * @param workspaceManager The workspace manager.
   * 
   * @throws SaveManagerException
   */
  public MainController(final WorkspaceManager workspaceManager) throws SaveManagerException {
    this.workspaceManager = workspaceManager;

    initSaveFileSlotsController();
    initSaveSlotDetailsController();
    initExportedSlotsController();
    assembleMainWindow();
  }

  /**
   * Shows the main window. Before showing it though, it applies the user preference values to the
   * main window.
   */
  public void show() {
    final Stage window = LayoutUtils.getNewWindowRoot();

    window.setTitle("DS:PTDE [PC] Save Viewer");

    window.setWidth(LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_WIDTH));
    window.setHeight(LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_HEIGHT));
    window.setScene(mainWindow);
    window.setMaximized(LayoutUtils.getPreferenceValue(PreferenceSetting.WINDOW_MAXIMIZED) == 1);

    window.show();

  }

  /**
   * Initializes the main window and sets the window contents (retrieved from the sub-controllers).
   * Also registers the menu event handler at the main window.
   */
  private void assembleMainWindow() {
    mainWindow = new MainWindow();
    mainWindow.setSaveFileSlotList(saveFileSlotsController.getPanel());
    mainWindow.setSelectedSlotInfo(saveSlotDetailsController.getPanel());
    mainWindow.setExportedSlotsList(exportedSlotsController.getPanel());

    mainWindow.addEventHandler(MenuEvent.MENU, new MenuHandler());
  }

  /**
   * Initializes the sub-controller for the display of the slots in the save file. Registers the
   * save file (slot) related event handlers at the panel managed by the sub-controller.
   * 
   * @throws SaveManagerException
   */
  private void initSaveFileSlotsController() throws SaveManagerException {
    if (saveFileSlotsController == null) {
      saveFileSlotsController =
          new SaveFileSlotsController(workspaceManager.getWorkspace().getSaveFile());

      final Node slotsPanel = saveFileSlotsController.getPanel();
      slotsPanel.addEventHandler(ReloadSaveEvent.RELOAD_SAVE, new ReloadHandler());
      slotsPanel.addEventHandler(ShowSaveFileSlotEvent.SHOW_SAVE_FILE_SLOT,
          new ShowSaveFileSlotHandler());

    }
  }

  /**
   * Initializes the sub-controller for the display of the exported slots. Registers the exported
   * slot (group) related event handlers at the panel managed by the sub-controller.
   * 
   * @throws SaveManagerException
   */
  private void initExportedSlotsController() throws SaveManagerException {
    if (exportedSlotsController == null) {
      exportedSlotsController =
          new ExportedSlotsController(workspaceManager.getWorkspace().getExportedSlots());

      final Node slotsPanel = exportedSlotsController.getPanel();

      slotsPanel.addEventHandler(AddGroupEvent.ADD_GROUP, new AddExportedSlotGroupHandler());

      slotsPanel.addEventHandler(RenameGroupEvent.RENAME_GROUP,
          new RenameExportedSlotGroupHandler());
      slotsPanel.addEventHandler(RenameSlotEvent.RENAME_SLOT, new RenameExportedSlotHandler());

      slotsPanel.addEventHandler(DeleteGroupEvent.DELETE_GROUP,
          new DeleteExportedSlotGroupHandler());
      slotsPanel.addEventHandler(DeleteSlotEvent.DELETE_SLOT, new DeleteExportedSlotHandler());

      slotsPanel.addEventHandler(MoveGroupEvent.MOVE_GROUP, new MoveExportedSlotGroupHandler());
      slotsPanel.addEventHandler(MoveSlotEvent.MOVE_SLOT, new MoveExportedSlotHandler());

      slotsPanel.addEventHandler(ShowExportedSlotEvent.SHOW_EXPORTED_SLOT,
          new ShowExportedSlotHandler());

    }
  }

  /**
   * Initializes the sub-controller for the display of the details of a selected save slot.
   * Registers the event handlers for importing and exporting slots at the panel managed by the
   * sub-controller.
   */
  private void initSaveSlotDetailsController() {
    if (saveSlotDetailsController == null) {
      saveSlotDetailsController = new SaveSlotDetailsController();

      final Node detailsPanel = saveSlotDetailsController.getPanel();

      detailsPanel.addEventHandler(ExportSlotEvent.EXPORT_SLOT, new ExportSlotHandler());
      detailsPanel.addEventHandler(ImportSlotEvent.IMPORT_SLOT, new ImportSlotHandler());
    }
  }

  /**
   * EventHandler to reload the save file in the workspace. Also triggers the corresponding refresh
   * in the save file slot listing and save slot details display.
   */
  private class ReloadHandler implements EventHandler<ReloadSaveEvent> {

    @Override
    public void handle(final ReloadSaveEvent event) {
      try {
        workspaceManager.loadSaveFile();
        saveSlotDetailsController.updateSaveSlot(null, false);
        saveFileSlotsController.refreshSaveSlots(workspaceManager.getWorkspace().getSaveFile());

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while reloading the save file.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Manages exporting a save slot from the save file. Shows the export dialog to select the group
   * in which to put the exported slot and the custom name of the exported slot. When the dialog is
   * confirmed actually exports the slot and triggers the required UI refreshes.
   */
  private class ExportSlotHandler implements EventHandler<ExportSlotEvent> {
    @Override
    public void handle(final ExportSlotEvent event) {
      try {
        final SaveSlot slotToExport = event.getPayload();
        final String characterName = slotToExport.getContent().getCharacterStatistics().getName();
        final ExportSlotDialog exportDialog =
            new ExportSlotDialog(characterName, workspaceManager.getWorkspace().getExportedSlots());

        final Optional<Pair<String, ExportedSlotGroup>> exportedSlotData =
            exportDialog.showAndWait();

        if (exportedSlotData.isPresent()) {
          workspaceManager.exportSlot(slotToExport, exportedSlotData.get().getKey(), null,
              exportedSlotData.get().getValue());
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to export slot.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Manages importing a save slot into the save file. Shows the import dialog to select the slot
   * position at which to put the slot in the save file. When the dialog is confirmed actually
   * imports the slot and triggers the required UI refreshes.
   */
  private class ImportSlotHandler implements EventHandler<ImportSlotEvent> {
    @Override
    public void handle(final ImportSlotEvent event) {
      try {
        final SaveSlot slotToImport = event.getPayload();

        final String characterName = slotToImport.getContent().getCharacterStatistics().getName();
        final ImportSlotDialog exportDialog = new ImportSlotDialog(characterName,
            workspaceManager.getWorkspace().getSaveFile().getSaveSlots());

        final Optional<Integer> exportedSlotData = exportDialog.showAndWait();

        if (exportedSlotData.isPresent()) {
          workspaceManager.importSlot(slotToImport, exportedSlotData.get());
          saveFileSlotsController.refreshSaveSlots(workspaceManager.getWorkspace().getSaveFile());

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to export slot.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Handler managing the creation of a new group of exported slots. Shows the dialog to select the
   * parent group and define the group name. When the dialog is confirmed actually creates the group
   * and triggers the required UI refreshes.
   */
  private class AddExportedSlotGroupHandler implements EventHandler<AddGroupEvent> {
    @Override
    public void handle(final AddGroupEvent event) {
      try {
        final ExportedSlots exportedSlots = workspaceManager.getWorkspace().getExportedSlots();
        final AddExportedSlotGroupDialog addDialog = new AddExportedSlotGroupDialog(exportedSlots);

        final Optional<Pair<String, ExportedSlotGroup>> newGroupValues = addDialog.showAndWait();

        if (newGroupValues.isPresent()) {
          workspaceManager.createSlotGroup(newGroupValues.get().getKey(),
              newGroupValues.get().getValue());
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to add group.", e.getMessage(),
            e);
      }

    }

  }

  /**
   * Handler managing the renaming of a group of exported slots. Shows the dialog to define the new
   * group name. When the dialog is confirmed actually renames the group and triggers the required
   * UI refreshes.
   */
  private class RenameExportedSlotGroupHandler implements EventHandler<RenameGroupEvent> {
    @Override
    public void handle(final RenameGroupEvent event) {
      try {
        final ExportedSlotGroup renamedGroup = event.getPayload();
        final RenameExportedElementDialog renameDialog =
            new RenameExportedElementDialog(renamedGroup.getName());

        final Optional<String> newName = renameDialog.showAndWait();

        if (newName.isPresent() && !Objects.equals(renamedGroup.getName(), newName.get())) {
          renamedGroup.setName(newName.get());
          workspaceManager.saveWorkspace();
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to renaming group.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Handler managing the renaming of an exported slot. Shows the dialog to define the new slot
   * name. When the dialog is confirmed actually renames the slot and triggers the required UI
   * refreshes.
   */
  private class RenameExportedSlotHandler implements EventHandler<RenameSlotEvent> {
    @Override
    public void handle(final RenameSlotEvent event) {
      try {
        final ExportedSlot renamedSlot = event.getPayload();
        final RenameExportedElementDialog renameDialog =
            new RenameExportedElementDialog(renamedSlot.getName());

        final Optional<String> newName = renameDialog.showAndWait();

        if (newName.isPresent() && !Objects.equals(renamedSlot.getName(), newName.get())) {
          renamedSlot.setName(newName.get());
          workspaceManager.saveWorkspace();
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to renaming slot.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Handler managing the deletion of an exported slot group. Shows a confirmation dialog before
   * actually deleting the group and its contents. Triggers the required UI refreshes afterwards.
   */
  private class DeleteExportedSlotGroupHandler implements EventHandler<DeleteGroupEvent> {
    @Override
    public void handle(final DeleteGroupEvent event) {
      try {
        final ExportedSlotGroup deletedGroup = event.getPayload();
        final boolean deletionConfirmed = BasicDialogs.showConfirmDialog("Delete Character Group",
            "Really delete character group '" + deletedGroup.getName() + "' and all its contents?");

        if (deletionConfirmed) {
          workspaceManager.deleteSlotGroup(deletedGroup);
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to delete group.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Handler managing the deletion of an exported slot. Shows a confirmation dialog before actually
   * deleting the slot and its contents. Triggers the required UI refreshes afterwards.
   */
  private class DeleteExportedSlotHandler implements EventHandler<DeleteSlotEvent> {
    @Override
    public void handle(final DeleteSlotEvent event) {
      try {
        final ExportedSlot deletedSlot = event.getPayload();
        final boolean deletionConfirmed = BasicDialogs.showConfirmDialog("Delete Character",
            "Really delete character '" + deletedSlot.getName() + "'?");

        if (deletionConfirmed) {
          workspaceManager.deleteSlot(deletedSlot);
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to delete character.",
            e.getMessage(), e);
      }
    }

  }

  /**
   * Handler managing moving an exported slot to a different group. Shows the move dialog in which
   * the new group has to be chosen. When the dialog is confirmed actually moves the slot and
   * triggers the required UI updates.
   */
  private class MoveExportedSlotHandler implements EventHandler<MoveSlotEvent> {
    @Override
    public void handle(final MoveSlotEvent event) {
      try {
        final ExportedSlot movedSlot = event.getPayload();
        final ExportedSlotsWrapper selectableParentGroups =
            new ExportedSlotsWrapper(workspaceManager.getWorkspace().getExportedSlots(), true);
        final AbstractMoveExportedElementDialog moveDialog =
            new MoveExportedSlotDialog(movedSlot.getName(), selectableParentGroups);

        final Optional<Pair<Boolean, ExportedSlotGroup>> newParent = moveDialog.showAndWait();

        if (newParent.isPresent()) {
          workspaceManager.moveSlot(movedSlot, newParent.get().getValue());
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to move slot.", e.getMessage(),
            e);
      }
    }

  }

  /**
   * Handler managing moving an exported slot group to a different parent group. Shows the move
   * dialog in which the new parent group has to be chosen. When the dialog is confirmed actually
   * moves the group and triggers the required UI updates.
   */
  private class MoveExportedSlotGroupHandler implements EventHandler<MoveGroupEvent> {
    @Override
    public void handle(final MoveGroupEvent event) {
      try {
        final ExportedSlotGroup movedGroup = event.getPayload();
        final ExportedSlotsWrapper selectableParentGroups =
            new ExportedSlotsWrapper(workspaceManager.getWorkspace().getExportedSlots(), true);
        removeFromParentWrapper(movedGroup, selectableParentGroups);
        final AbstractMoveExportedElementDialog moveDialog =
            new MoveExportedSlotGroupDialog(movedGroup.getName(), selectableParentGroups);

        final Optional<Pair<Boolean, ExportedSlotGroup>> newParent = moveDialog.showAndWait();

        if (newParent.isPresent()) {
          workspaceManager.moveSlotGroup(movedGroup, newParent.get().getValue());
          exportedSlotsController.refreshSlotTree();

        }

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("An error occurred while trying to move slot group.",
            e.getMessage(), e);
      }
    }

    /**
     * Removes the group to move from from the parents selectable in the move dialog. This avoids
     * cycles and self references.
     * 
     * @param movedGroup The group to move.
     * @param parentCandidate The candidate parent group of the group to move.
     * @return {@code true} when the group was removed from the tree.
     */
    private boolean removeFromParentWrapper(final ExportedSlotGroup movedGroup,
        final AbstractTreeNodeWrapper<?> parentCandidate) {
      boolean removed = parentCandidate.getChildren()
          .removeIf(group -> Objects.equals(movedGroup, group.getWrappedNode()));

      if (!removed) {
        for (final AbstractTreeNodeWrapper<?> parentGroup : parentCandidate.getChildren()) {
          removed = removeFromParentWrapper(movedGroup, parentGroup);

          if (removed) {
            return true;
          }
        }

        return false;

      } else {
        return true;
      }

    }

  }

  /**
   * Handler to manage showing the details of a slot in the save file. Also clears the selection in
   * the exported slots panel.
   */
  private class ShowSaveFileSlotHandler implements EventHandler<ShowSaveFileSlotEvent> {
    @Override
    public void handle(ShowSaveFileSlotEvent event) {
      // clear selection first and update slots controller second (because selection clear may
      // trigger another listener)
      final SaveSlot newSlot = event.getPayload();

      if (newSlot != null) {
        exportedSlotsController.clearSelection();
      }

      saveSlotDetailsController.updateSaveSlot(newSlot, false);

    }
  }

  /**
   * Handler to manage showing the details of an exported slot. Also clears the selection in the
   * save file slots panel.
   */
  private class ShowExportedSlotHandler implements EventHandler<ShowExportedSlotEvent> {

    @Override
    public void handle(ShowExportedSlotEvent event) {
      final ExportedSlot newSlot = event.getPayload();
      final SaveSlot selectedSlot;

      try {
        if (newSlot != null) {
          if (newSlot.getSlot() == null) {
            // "lazy loading" the slot data. we saved application startup time by not loading that
            // earlier.
            workspaceManager.loadSaveSlot(newSlot);
          }

          saveFileSlotsController.clearSelection();
          selectedSlot = newSlot.getSlot();

        } else {
          selectedSlot = null;
        }

        saveSlotDetailsController.updateSaveSlot(selectedSlot, true);

      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("Could not load character data!", e.getMessage(), e);
      }
    }

  }

  /**
   * Handler to capture menu events and showing the dialogs corresponding to the menu element.
   */
  private class MenuHandler implements EventHandler<MenuEvent> {
    @Override
    public void handle(MenuEvent event) {
      try {
        switch (event.getPayload()) {
          case ABOUT:
            MenuDialogs.showAboutDialog();
            break;
          case CREDITS:
            MenuDialogs.showCreditsDialog();
            break;
          case FILE_INFO:
            MenuDialogs.showFileInfoDialog(workspaceManager.getWorkspace().getSaveFilePath(),
                workspaceManager.getWorkspacePath().getAbsolutePath());
            break;
          case SETTINGS:
            new PreferencesEditController().show();
            break;
          default:
            throw new IllegalArgumentException("Unsupported menu action: " + event.getPayload());

        }
      } catch (SaveManagerException e) {
        BasicDialogs.showErrorDialog("Uuups...", "This should not have happened: " + e.getMessage(),
            e);
      }
    }

  }

}
