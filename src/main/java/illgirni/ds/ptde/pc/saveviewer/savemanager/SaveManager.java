package illgirni.ds.ptde.pc.saveviewer.savemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.UUID;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.SaveFileParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.SaveSlotParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlotDescriptor;

/**
 * Manager for file related actions on the save file and exported slots.
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveManager {
    
    /**
     * The save file parser.
     */
    @Inject
    private SaveFileParser saveFileParser;
    
    /**
     * The save slot parser.
     */
    @Inject
    private SaveSlotParser saveSlotParser;
    
    /**
     * Loads the save file at the given (full) path.
     * 
     * @param saveFilePath The path to the save file.
     * @return The loaded save file.
     * 
     * @throws SaveManagerException When an error occurs during save file loading.
     */
    public SaveFile loadSaveFile(final String saveFilePath) throws SaveManagerException {
        try {
            return saveFileParser.parse(saveFilePath);
            
        } catch (FileNotFoundException e) {
            throw new SaveManagerException("Couldn't find file: " + saveFilePath, e);
            
        } catch (IOException e) {
            throw new SaveManagerException("Something went wrong when loading the save file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Loads the exported save slot at the give (full) path.
     * 
     * @param saveSlotFilePath The path to the save slot file.
     * @return The loaded save slot.
     * 
     * @throws SaveManagerException When an error occurs during save slot loading.
     */
    public SaveSlot loadSaveSlot(final String saveSlotFilePath) throws SaveManagerException {
        try {
            return saveSlotParser.parse(saveSlotFilePath);
        } catch (IOException e) {
            throw new SaveManagerException("Something went wrong when loading the save slot: " + e.getMessage(), e);
        }
    }
    
    /**
     * Exports the data in the save slot to a file in the given directory. The file name will be
     * a randomly generated uuid with the file extends defined by {@link WorkspaceConstants#SAVE_SLOT_FILE_EXTENSION}.
     * 
     * @param exportedSlot The slot to save to file.
     * @param targetDirectoryPath The directory in which to create the file.
     * @return The name of the created file.
     * 
     * @throws SaveManagerException When an error occurs during the export.
     */
    public String exportSaveSlot(final SaveSlot exportedSlot, final String targetDirectoryPath) throws SaveManagerException {
        final File targetDirectory = new File(targetDirectoryPath);
        
        if (exportedSlot.isErroneous()) {
            throw new SaveManagerException("The slot to export is erroneous.");
            
        } else if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
            throw new SaveManagerException("This is not a directory: " + targetDirectoryPath);
            
        } else {
            try {
                if (!targetDirectory.exists()) {
                    targetDirectory.mkdir();
                    
                }
                
                final String slotFileName = UUID.randomUUID().toString() + '.' + WorkspaceConstants.SAVE_SLOT_FILE_EXTENSION;
                Files.write(new File(targetDirectory, slotFileName).toPath(), exportedSlot.getData().getBlockData());
                
                return slotFileName;
                
            } catch (IOException e) {
                throw new SaveManagerException("Couldn't write slot file: " + e.getMessage(), e);
            }
        }
        
    }
    
    /**
     * Deletes the file at the given location.
     * 
     * @param slotFileDirectoryPath The directory containing the file.
     * @param slotFileName The file name.
     * 
     * @throws SaveManagerException
     */
    public void deleteSaveSlot(final String slotFileDirectoryPath, final String slotFileName) throws SaveManagerException {
        final File slotFile = new File(slotFileDirectoryPath, slotFileName);
        
        if (slotFile.isFile()) {
            slotFile.delete();
            
        } else {
            //Do nothing. File is not there. So we treat is as already deleted. 
            //throw new IOException("Slot file does not exist: " + slotFile.getAbsolutePath());
        }
        
    }
    
    /**
     * Merges the data of the save slot into the save file data at the given slot index. I.e. the save slot will become
     * the slot at the position defined by {@code index} in the save file. The merged data is then written to the given 
     * path.
     * <p/>
     * Does not change anything in the given parameters. So, the save file should be reloaded after this method.
     * 
     * @param saveFilePath The path to which to write the merged data.
     * @param saveFile The save file.
     * @param slot The save slot to merge into the file.
     * @param slotIndex At which position to include the slot in the save file.
     * 
     * @throws SaveManagerException
     */
    public void importSaveSlot(final String saveFilePath, final SaveFile saveFile, final SaveSlot slot, final int slotIndex) throws SaveManagerException {
        final File saveFileFile = new File(saveFilePath);
        
        if (saveFile.isErroneous()) {
            throw new SaveManagerException("The save file is erroneous.");
            
        } else if (slot.isErroneous()) {
            throw new SaveManagerException("The slot to import is erroneous.");
            
        } else if (saveFileFile.isDirectory()) {
            throw new SaveManagerException("File must not be a directory: " + saveFileFile.getAbsolutePath());
            
        } else {
            final byte[] saveFileData = saveFile.getSaveFileData().getBlockData();
            
            int slotOffset = -1;
            
            for (final SaveSlotDescriptor slotDescriptor : saveFile.getSaveSlotDescriptors()) {
                if (slotDescriptor.getIndex() == slotIndex) {
                    slotOffset = slotDescriptor.getSlotOffset();
                    break;
                }
            }
            
            if (slotOffset <= 0) {
                throw new SaveManagerException("Cannot find slot descriptor with index: " + slotIndex);
                
            } else {
                try {
                    final byte[] newSaveFileData = Arrays.copyOf(saveFileData, saveFileData.length);
                    final byte[] slotData = slot.getData().getBlockData();
                    
                    for (int slotByteIndex = 0, saveFileOffset = slotOffset; slotByteIndex < slotData.length; slotByteIndex++, saveFileOffset++) {
                        newSaveFileData[saveFileOffset] = slotData[slotByteIndex];
                    }
                    
                    Files.write(saveFileFile.toPath(), newSaveFileData);
                    
                } catch (IOException e) {
                    throw new SaveManagerException("Could not write save file: " + e.getMessage(), e);
                }
            }
            
        }
        
    }
}
