package illgirni.ds.ptde.pc.saveviewer.savemanager;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlots;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.SaveWorkspace;

/**
 * Manager for a {@link SaveWorkspace}: Loads and changes the workspace and the save file
 * and exported save slots in the workspace.
 * 
 * @author illgirni
 *
 */
@Bean
public class WorkspaceManager {
    
    /**
     * The save file and exported slot manager.
     */
    @Inject
    private SaveManager saveManager;
    
    /**
     * The path to the loaded workspace file.
     */
    private File workspacePath;
    
    /**
     * The loaded workspace.
     */
    private SaveWorkspace workspace;
    
    /**
     * Loads the save file defined by the managed workspace. The loaded data is stored 
     * in the workspace Object.
     * 
     * @throws SaveManagerException
     */
    public void loadSaveFile() throws SaveManagerException {
        getWorkspace().setSaveFile(saveManager.loadSaveFile(getWorkspace().getSaveFilePath()));
    }
    
    /**
     * Loads the save slot file defined by the exported slot. The loaded data is stored
     * in the exported slot.
     * 
     * @param exportedSlot The slot for which to load the slot data.
     * 
     * @throws SaveManagerException
     */
    public void loadSaveSlot(final ExportedSlot exportedSlot) throws SaveManagerException {
        final File saveSlotFile = new File(getWorkspaceExportedSlotsDirectory(), exportedSlot.getFile());
        final File loadScreenFile = new File(getWorkspaceExportedSlotsDirectory(), exportedSlot.getLoadScreenFile());
        
        exportedSlot.setSlot(saveManager.loadSaveSlot(saveSlotFile.getAbsolutePath(), loadScreenFile.getAbsolutePath()));
    }
    
    /**
     * Exports the given SaveSlot. Creates a new exported slot in the given group and writes the save 
     * slot data to a file. Saves the workspace after the changes.
     * 
     * @param slotToExport The slot to export.
     * @param name The name of the exported slot.
     * @param description Optional description.
     * @param group The group which should contain the new exported slot.
     * 
     * @throws SaveManagerException
     * 
     * @see SaveManager#exportSaveSlot(SaveSlot, String)
     */
    public void exportSlot(final SaveSlot slotToExport, final String name, final String description, final ExportedSlotGroup group) throws SaveManagerException {
        final String slotFileName = saveManager.exportSaveSlot(slotToExport, getWorkspaceExportedSlotsDirectory());
        final String loadScreenFileName = saveManager.exportSaveSlotLoadScreen(slotToExport, getWorkspaceExportedSlotsDirectory());
        
        final ExportedSlot exportedSlot = new ExportedSlot();
        exportedSlot.setFile(slotFileName);
        exportedSlot.setLoadScreenFile(loadScreenFileName);
        exportedSlot.setName(name);
        exportedSlot.setDescription(description);
        exportedSlot.setSlot(slotToExport);
        
        group.getSlots().add(exportedSlot);
        //Collections.sort(group.getSlots());
        
        saveWorkspace();
    }
    
    /**
     * Imports the slot to the given position in the save file of the managed workspace. Reloads the save file
     * before and after conducting the changes.
     * 
     * @param exportedSlot The exported slot to import.
     * @param slotIndex The position of the slot in the save file.
     * 
     * @throws SaveManagerException
     * 
     * @see SaveManager#importSaveSlot(String, SaveFile, SaveSlot, int)
     */
    public void importSlot(final SaveSlot exportedSlot, final int slotIndex) throws SaveManagerException {
        loadSaveFile();
        
        final SaveFile saveFile = getWorkspace().getSaveFile();
        
        if (saveFile.getSaveSlots().contains(exportedSlot)) {
            throw new SaveManagerException("The slot is already in the save file.");
        }
        
        saveManager.importSaveSlot(getWorkspace().getSaveFilePath(), saveFile, exportedSlot, slotIndex);
        
        loadSaveFile();
    }
    
    /**
     * Deletes the exported slot. Removes it from it group and also removes the associated slot file.
     * Saves after the changes.
     * 
     * @param exportedSlot The slot to delete.
     * 
     * @throws SaveManagerException
     */
    public void deleteSlot(final ExportedSlot exportedSlot) throws SaveManagerException {
        final ExportedSlotGroup parentGroup = findParentGroup(exportedSlot);
        
        saveManager.deleteSaveSlot(getWorkspaceExportedSlotsDirectory(), exportedSlot.getFile(), exportedSlot.getLoadScreenFile());
        parentGroup.getSlots().remove(exportedSlot);
        
        saveWorkspace();
    }
    
    /**
     * Creates a new group as sub-group in the given parent group. When no parent group is provided a new root
     * group is created. Saves the workspace after the changes.
     * 
     * @param name The name of the new group.
     * @param parentGroup The parent group.
     * 
     * @throws SaveManagerException
     */
    public void createSlotGroup(final String name, final ExportedSlotGroup parentGroup) throws SaveManagerException {
        final ExportedSlotGroup group = new ExportedSlotGroup();
        group.setName(name);
        
        if (parentGroup == null) {
            final ExportedSlots exportedSlots = getWorkspace().getExportedSlots();
            
            exportedSlots.getSlotGroups().add(group);
            //Collections.sort(exportedSlots.getSlotGroups());
            
        } else {
            parentGroup.getSlotGroups().add(group);
            //Collections.sort(parentGroup.getSlotGroups());
            
        }
        
        saveWorkspace();
    }
    
    /**
     * Deletes the slot group and all its descendants. Saves the workspace after the changes.
     * 
     * @param slotGroup The group to delete.
     * 
     * @throws SaveManagerException
     */
    public void deleteSlotGroup(final ExportedSlotGroup slotGroup) throws SaveManagerException {
        final ExportedSlotGroup parentGroup = findParentGroup(slotGroup);
        
        if (parentGroup != null) {
            parentGroup.getSlotGroups().remove(slotGroup);
            
        } else {
            getWorkspace().getExportedSlots().getSlotGroups().remove(slotGroup);
            
        }
        
        deleteSlots(slotGroup);
        
        saveWorkspace();
    }
    
    /**
     * Deletes all the files of the exported slots in the slot groups and its (indirect) sub-groups.
     * 
     * @param slotGroup The group.
     * 
     * @throws SaveManagerException
     */
    private void deleteSlots(final ExportedSlotGroup slotGroup) throws SaveManagerException {
        for (final ExportedSlotGroup slotSubGroup : slotGroup.getSlotGroups()) {
            deleteSlots(slotSubGroup);
        }
        
        for (final ExportedSlot slot : slotGroup.getSlots()) {
            saveManager.deleteSaveSlot(getWorkspaceExportedSlotsDirectory(), slot.getFile(), slot.getLoadScreenFile());
        }
    }
    
    /**
     * Moves the group to the new parent. When the new parent is not provided the group will
     * become a root group. Upholds the tree-structure of the groups. Saves the workspace after 
     * the changes. 
     * 
     * @param movedGroup The group to move.
     * @param newParentGroup The new parent group.
     * 
     * @throws SaveManagerException
     */
    public void moveSlotGroup(final ExportedSlotGroup movedGroup, final ExportedSlotGroup newParentGroup) throws SaveManagerException {
        if (newParentGroup != null && movedGroup.equals(newParentGroup)) {
            throw new IllegalArgumentException("A group cannot be a parent of itself.");
            
        } else if (newParentGroup != null && containsGroup(movedGroup, newParentGroup)) {
            throw new IllegalArgumentException("Moving the group would create a cycle.");
            
        } else {
            final ExportedSlotGroup parentGroup = findParentGroup(movedGroup);
            final ExportedSlots exportedSlots = getWorkspace().getExportedSlots();
            
            if (parentGroup != null) {
                parentGroup.getSlotGroups().remove(movedGroup);
                
            } else {
                exportedSlots.getSlotGroups().remove(movedGroup);
                
            }
            
            if (newParentGroup != null) {
                newParentGroup.getSlotGroups().add(movedGroup);
                //Collections.sort(newParentGroup.getSlotGroups());
                
            } else {
                exportedSlots.getSlotGroups().add(movedGroup);
                //Collections.sort(exportedSlots.getSlotGroups());
                
            }
            
            saveWorkspace();
            
        }
        
    }
    
    /**
     * Moves the slot to a new parent group. Saves after the changes.
     * 
     * @param movedSlot The slot to move.
     * @param newParentGroup The new parent group.
     * 
     * @throws SaveManagerException
     */
    public void moveSlot(final ExportedSlot movedSlot, final ExportedSlotGroup newParentGroup) throws SaveManagerException {
        final ExportedSlotGroup parentGroup = findParentGroup(movedSlot);
        
        if (parentGroup != null) {
            parentGroup.getSlots().remove(movedSlot);
            
        } else {
            throw new SaveManagerException("Couldn't find original parent of moved slot.");
            
        }
        
        if (newParentGroup != null) {
            newParentGroup.getSlots().add(movedSlot);
            //Collections.sort(newParentGroup.getSlotGroups());
            
        } else {
            throw new SaveManagerException("Slots require a group as parent.");
            
        }
        
        saveWorkspace();
        
    }
    
    /**
     * Checks if the parent group contains the other group as an (indirect) descendant.
     * 
     * @param parentGroup The parent group.
     * @param descendantGroup The potential descendant. 
     * @return {@code true} when contained as descendant.
     */
    private boolean containsGroup(final ExportedSlotGroup parentGroup, final ExportedSlotGroup descendantGroup) {
        if (parentGroup.getSlotGroups().contains(descendantGroup)) {
            return true;
            
        } else {
            for (final ExportedSlotGroup parentSubGroup : parentGroup.getSlotGroups()) {
                if (containsGroup(parentSubGroup, descendantGroup)) {
                    return true;
                }
            }
            
        }
        
        return false;
    }
    
    /**
     * Finds the parent group containing the given group.
     * 
     * @param slotGroup The group for which to find the parent.
     * @return The parent group; {@code null} when it is a root group.
     * 
     * @throws SaveManagerException
     */
    private ExportedSlotGroup findParentGroup(final ExportedSlotGroup slotGroup) throws SaveManagerException {
        final ExportedSlots exportedSlots = getWorkspace().getExportedSlots();
        
        if (exportedSlots.getSlotGroups().contains(slotGroup)) {
            return null;
            
        } else {
            for (final ExportedSlotGroup candidateGroup : exportedSlots.getSlotGroups()) {
                final ExportedSlotGroup parentGroup = findParentGroup(slotGroup, candidateGroup);
                
                if (parentGroup != null) {
                    return parentGroup;
                }
            }
            
            throw new SaveManagerException("The group is no root group. It should have a parent.");
            //return null;
        }
    }
    
    /**
     * Finds the parent of the group starting at the given candidate group. When the candidate group does not contain
     * the group directly descends into the the candidate groups children and their children.
     * 
     * @param slotGroup The group for which to find the parent.
     * @param candidateGroup The candidate parent group.
     * 
     * @return The parent group; {@code null} when not found.
     */
    private ExportedSlotGroup findParentGroup(final ExportedSlotGroup slotGroup, final ExportedSlotGroup candidateGroup) {
        if (candidateGroup.getSlotGroups().contains(slotGroup)) {
            return candidateGroup;
            
        } else {
            for (final ExportedSlotGroup candidateSubGroup : candidateGroup.getSlotGroups()) {
                final ExportedSlotGroup parentGroup = findParentGroup(slotGroup, candidateSubGroup);
                
                if (parentGroup != null) {
                    return parentGroup;
                }
                
            }
            
            return null;
        }
    }
    
    /**
     * Finds the group containing the exported slot.
     * 
     * @param exportedSlot The exported slot for which to find the parent.
     * @return The parent.
     * @throws SaveManagerException
     */
    private ExportedSlotGroup findParentGroup(final ExportedSlot exportedSlot) throws SaveManagerException {
        for (final ExportedSlotGroup candidateGroup : getWorkspace().getExportedSlots().getSlotGroups()) {
            final ExportedSlotGroup parentGroup = findParentGroup(exportedSlot, candidateGroup);
            
            if (parentGroup != null) {
                return parentGroup;
            }
        }
        
        throw new SaveManagerException("An exported slot should always be contained in a group.");
        //return null;
    }
    
    /**
     * Finds the parent of the slot starting at the given candidate group. When the candidate group does not contain
     * the slot directly descends into the the candidate groups children and their children.
     * 
     * @param exportedSlot The slot for which to find the parent.
     * @param candidateGroup The candidate parent group.
     * 
     * @return The parent group; {@code null} when not found.
     */
    private ExportedSlotGroup findParentGroup(final ExportedSlot exportedSlot, final ExportedSlotGroup candidateGroup) {
        if (candidateGroup.getSlots().contains(exportedSlot)) {
            return candidateGroup;
            
        } else {
            for (final ExportedSlotGroup candidateSubGroup : candidateGroup.getSlotGroups()) {
                final ExportedSlotGroup parentGroup = findParentGroup(exportedSlot, candidateSubGroup);
                
                if (parentGroup != null) {
                    return parentGroup;
                }
                
            }
            
            return null;
        }
    }
    
    /**
     * Initializes the workspace path. First looks in the system properties for the custom location definition.
     * If defined uses that. if not uses the default location. May create the workspace directory in that  case 
     * (when it doesn't exist yet).
     * 
     * @throws SaveManagerException
     */
    private void initWorkspacePath() throws SaveManagerException {
        if (System.getProperties().containsKey(WorkspaceConstants.SYSTEM_PROPERTY__CUSTOM_WORKSPACE_LOCATION)) {
            workspacePath = new File(System.getProperty(WorkspaceConstants.SYSTEM_PROPERTY__CUSTOM_WORKSPACE_LOCATION));
            
            if (!workspacePath.isDirectory()) {
                throw new SaveManagerException("Workspace path does not point to a directory: " + workspacePath.getAbsolutePath());
            }
            
        } else {
            workspacePath = new File(System.getProperty(WorkspaceConstants.SYSTEM_PROPERTY__DEFAULT_WORKSPACE_LOCATION), 
                                     WorkspaceConstants.DEFAULT_WORKSPACE_DIRECTORY_NAME);
            
            if (!workspacePath.isDirectory()) {
                if (workspacePath.isFile()) {
                    throw new SaveManagerException("Workspace already exists and is not a directory: " + workspacePath.getAbsolutePath());
                    
                } else if (!workspacePath.mkdir()) {
                    throw new SaveManagerException("Could not create workspace directory: " + workspacePath.getAbsolutePath());
                    
                }
            }
            
        }
    }
    
    /**
     * Loads the workspace from the workspace file defined by {@link #getWorkspaceFile()}. If the file doesn't 
     * exist yet it is created.
     * 
     * @throws SaveManagerException
     */
    private void initWorkspace() throws SaveManagerException {
        final File workspaceFile = getWorkspaceFile();
        
        if (workspaceFile.isDirectory()) {
            throw new SaveManagerException("Not expecting a directory to be here: " + workspaceFile.getAbsolutePath());
            
        } else if (!workspaceFile.isFile()) {
            //workspace is empty. setup new one.
            workspace = new SaveWorkspace();
            workspace.setSaveFilePath(new File(WorkspaceConstants.DEFAULT_SAVE_FILE_PATH).getAbsolutePath());
            
            saveWorkspace();
            
        } else {
            loadWorkspace();
            
        }
    }
    
    /**
     * Saves the current workspace state ({@link #getWorkspace()}) to XML 
     * at {@link #getWorkspaceFile()}.
     * 
     * @throws SaveManagerException
     */
    public void saveWorkspace() throws SaveManagerException {
        try {
            final File workspaceFile = getWorkspaceFile();
            
            final JAXBContext jaxbContext = JAXBContext.newInstance(SaveWorkspace.class);
            final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(getWorkspace(), workspaceFile);
            
        } catch (JAXBException e) {
            throw new SaveManagerException("Error saving workspace.", e);
        }
        
    }
    
    /**
     * Loads the workspace from the XML file at {@link #getWorkspaceFile()}.
     * 
     * @throws SaveManagerException
     */
    private void loadWorkspace() throws SaveManagerException {
        try {
            final File workspaceFile = getWorkspaceFile();
            
            final JAXBContext jaxbContext = JAXBContext.newInstance(SaveWorkspace.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            
            workspace = (SaveWorkspace) unmarshaller.unmarshal(workspaceFile);
            
        } catch (JAXBException e) {
            throw new SaveManagerException("Error loading workspace.", e);
        }
        
    }
    
    /**
     * The path to the workspace XML file.
     * 
     * @throws SaveManagerException
     */
    private File getWorkspaceFile() throws SaveManagerException {
        if (workspacePath == null) {
            initWorkspacePath();
        }
        
        return new File(workspacePath, WorkspaceConstants.WORKSPACE_FILE_NAME);
    }
    
    /**
     * The path to the workspace directory.
     * 
     * @throws SaveManagerException
     */
    public File getWorkspacePath() throws SaveManagerException {
        if (workspacePath == null) {
            initWorkspacePath();
        }
        
        return workspacePath;
    }
    
    /**
     * The (full) path to the directory containing the exported slots.
     * 
     * @throws SaveManagerException
     */
    private String getWorkspaceExportedSlotsDirectory() throws SaveManagerException {
        if (workspacePath == null) {
            initWorkspacePath();
        }
        
        return new File(workspacePath, WorkspaceConstants.WORKSPACE_EXPORTED_SLOTS_DIRECTORY).getAbsolutePath();
    }
    
    /**
     * The managed workspace. All changes on this object should be conducted by this manager.
     * 
     * @throws SaveManagerException
     */
    public SaveWorkspace getWorkspace() throws SaveManagerException {
        if (workspace == null) {
            initWorkspace();
        }
        
        return workspace;
    }
    
}
