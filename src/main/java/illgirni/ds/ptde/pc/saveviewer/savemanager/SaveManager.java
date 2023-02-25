package illgirni.ds.ptde.pc.saveviewer.savemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.SaveFileParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.SaveSlotParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlotDescriptor;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.SaveFileDefinition;

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
   * Definition of the main structure of the save file.
   */
  @Inject
  private SaveFileDefinition saveFileDefinition;

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
      throw new SaveManagerException(
          "Something went wrong when loading the save file: " + e.getMessage(), e);
    }
  }

  /**
   * Loads the exported save slot at the give (full) path.
   * 
   * @param saveSlotFilePath The path to the save slot file.
   * @param loadScreenFilePath The path to the file with the data shown on the character loading
   *        screen.
   * @return The loaded save slot.
   * 
   * @throws SaveManagerException When an error occurs during save slot loading.
   */
  public SaveSlot loadSaveSlot(final String saveSlotFilePath, final String loadScreenFilePath)
      throws SaveManagerException {
    try {
      return saveSlotParser.parse(saveSlotFilePath, loadScreenFilePath);
    } catch (IOException e) {
      throw new SaveManagerException(
          "Something went wrong when loading the save slot: " + e.getMessage(), e);
    }
  }

  /**
   * Exports the data in the save slot to a file in the given directory. The file name will be a
   * randomly generated uuid with the file extends defined by
   * {@link WorkspaceConstants#SAVE_SLOT_FILE_EXTENSION}.
   * 
   * @param exportedSlot The slot to save to file.
   * @param targetDirectoryPath The directory in which to create the file.
   * @return The name of the created file.
   * 
   * @throws SaveManagerException When an error occurs during the export.
   */
  public String exportSaveSlot(final SaveSlot exportedSlot, final String targetDirectoryPath)
      throws SaveManagerException {
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

        final String slotFileName =
            UUID.randomUUID().toString() + '.' + WorkspaceConstants.SAVE_SLOT_FILE_EXTENSION;
        Files.write(new File(targetDirectory, slotFileName).toPath(),
            exportedSlot.getData().getBlockData());

        return slotFileName;

      } catch (IOException e) {
        throw new SaveManagerException("Couldn't write slot file: " + e.getMessage(), e);
      }
    }

  }

  /**
   * Exports the load screen data in the save slot to a file in the given directory. The file name
   * will be a randomly generated uuid with the file extends defined by
   * {@link WorkspaceConstants#SAVE_SLOT_LOAD_FILE_EXTENSION}.
   * 
   * @param exportedSlot The slot to save to file.
   * @param targetDirectoryPath The directory in which to create the file.
   * @return The name of the created file.
   * 
   * @throws SaveManagerException When an error occurs during the export.
   */
  public String exportSaveSlotLoadScreen(final SaveSlot exportedSlot,
      final String targetDirectoryPath) throws SaveManagerException {
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

        final String loadScreenFileName =
            UUID.randomUUID().toString() + '.' + WorkspaceConstants.SAVE_SLOT_LOAD_FILE_EXTENSION;
        Files.write(new File(targetDirectory, loadScreenFileName).toPath(),
            exportedSlot.getLoadScreenData().getBlockData());

        return loadScreenFileName;

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
   * @param loadScreenFileName The name of the file with the load screen data.
   * 
   * @throws SaveManagerException
   */
  public void deleteSaveSlot(final String slotFileDirectoryPath, final String slotFileName,
      final String loadScreenFileName) throws SaveManagerException {
    final File slotFile = new File(slotFileDirectoryPath, slotFileName);

    if (slotFile.isFile()) {
      slotFile.delete();

    } else {
      // Do nothing. File is not there. So we treat is as already deleted.
    }

    final File loadScreenFile = new File(slotFileDirectoryPath, loadScreenFileName);

    if (loadScreenFile.isFile()) {
      loadScreenFile.delete();

    } else {
      // Do nothing. File is not there. So we treat is as already deleted.
    }

  }

  /**
   * Merges the data of the save slot into the save file data at the given slot index. I.e. the save
   * slot will become the slot at the position defined by {@code index} in the save file. The merged
   * data is then written to the given path.
   * <p/>
   * Does not change anything in the given parameters. So, the save file should be reloaded after
   * this method.
   * 
   * @param saveFilePath The path to which to write the merged data.
   * @param saveFile The save file.
   * @param slot The save slot to merge into the file.
   * @param slotIndex At which position to include the slot in the save file.
   * 
   * @throws SaveManagerException
   */
  public void importSaveSlot(final String saveFilePath, final SaveFile saveFile,
      final SaveSlot slot, final int slotIndex) throws SaveManagerException {
    final File saveFileFile = new File(saveFilePath);

    if (saveFile.isErroneous()) {
      throw new SaveManagerException("The save file is erroneous.");

    } else if (slot.isErroneous()) {
      throw new SaveManagerException("The slot to import is erroneous.");

    } else if (saveFileFile.isDirectory()) {
      throw new SaveManagerException(
          "File must not be a directory: " + saveFileFile.getAbsolutePath());

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
          insertSlotData(newSaveFileData, slot.getData(), slotOffset);

          insertLoadScreenData(newSaveFileData, slot.getLoadScreenData(), slotIndex);

          Files.write(saveFileFile.toPath(), newSaveFileData);

        } catch (IOException e) {
          throw new SaveManagerException("Could not write save file: " + e.getMessage(), e);
        }
      }

    }

  }

  /**
   * Inserts the slot data at the given offset into the save file data.
   * 
   * @param saveFileData The save file data.
   * @param slotDataBlock The slot data block
   * @param slotOffset The offset at which to insert the data.
   */
  private void insertSlotData(final byte[] saveFileData, final ByteBlock slotDataBlock,
      final int slotOffset) {
    final byte[] slotData = slotDataBlock.getBlockData();

    for (int slotByteIndex = 0, saveFileOffset =
        slotOffset; slotByteIndex < slotData.length; slotByteIndex++, saveFileOffset++) {
      saveFileData[saveFileOffset] = slotData[slotByteIndex];
    }

  }

  /**
   * Inserts the load screen data into the save file data, so that it corresponds to the slot with
   * the given index. Also need to fix the checksums of the hidden eleventh slot containing the load
   * screen data.
   * 
   * @param saveFileData The save file data.
   * @param loadScreenDataBlock The load screen data block.
   * @param slotIndex The slot index.
   */
  private void insertLoadScreenData(final byte[] saveFileData, final ByteBlock loadScreenDataBlock,
      final int slotIndex) throws SaveManagerException {
    // FIXME don't hard code numbers here
    final ByteBlockSectionDefinition<ByteBlock> loadScreenBlockDefinition =
        saveFileDefinition.getSlotLoadScreenDefinition(slotIndex);
    final int loadScreenDataOffset = loadScreenBlockDefinition.getOffset();

    final int loadScreenSlotOffset = saveFileDefinition.getLoadingScreenSlotOffset();
    final int loadScreenSlotLength = 393236;

    final byte[] loadScreenData = loadScreenDataBlock.getBlockData();

    // insert data
    for (int byteIndex = 0, saveFileOffset =
        loadScreenDataOffset; byteIndex < loadScreenData.length; byteIndex++, saveFileOffset++) {
      saveFileData[saveFileOffset] = loadScreenData[byteIndex];
    }

    // marks the slot as used
    saveFileData[loadScreenSlotOffset + 40 + slotIndex] = 1;

    try {
      // fix content checksum
      final int contentChecksumOffset = loadScreenSlotOffset + loadScreenSlotLength - 16;
      final byte[] slotContent =
          Arrays.copyOfRange(saveFileData, loadScreenSlotOffset + 20, contentChecksumOffset);
      final byte[] contentChecksum = MessageDigest.getInstance("MD5").digest(slotContent);

      for (int checksumIndex = 0; checksumIndex < contentChecksum.length; checksumIndex++) {
        saveFileData[contentChecksumOffset + checksumIndex] = contentChecksum[checksumIndex];
      }

      // fix slot checksum
      final byte[] slotData = Arrays.copyOfRange(saveFileData, loadScreenSlotOffset + 16,
          loadScreenSlotOffset + loadScreenSlotLength);
      final byte[] slotDataChecksum = MessageDigest.getInstance("MD5").digest(slotData);

      for (int checksumIndex = 0; checksumIndex < contentChecksum.length; checksumIndex++) {
        saveFileData[loadScreenSlotOffset + checksumIndex] = slotDataChecksum[checksumIndex];
      }

    } catch (NoSuchAlgorithmException e) {
      throw new SaveManagerException("Error calculating a checksum.", e);
    }
  }

}
