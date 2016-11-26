package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlotDescriptor;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.SaveFileDefinition;

/**
 * Uses the {@link SaveFileDefinition} to parse a save file. Delegates to sub-parsers for 
 * the save slot parsing. Validates that the parsed file is indeed an "understandable" file 
 * byte checking the magic number and file size.
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveFileParser extends AbstractSaveElementParser {
    
    /**
     * The save file structure definition.
     */
    @Inject
    private SaveFileDefinition saveFileDefinition;
    
    /**
     * Parser for save slot definitions.
     */
    @Inject
    private SaveSlotDescriptorParser slotDescriptorParser;
    
    /**
     * Parser for save slots.
     */
    @Inject
    private SaveSlotParser slotParser;
    
    /**
     * Parses the file at the given path as a DS1 save file.
     * 
     * @throws IOException When the file cannot be read.
     */
    public SaveFile parse(final String filePath) throws IOException {
        final File file = new File(filePath);
        
        if (file.isFile()) {
            final SaveFile saveFile = new SaveFile();
            saveFile.setSaveFileData(Files.readAllBytes(file.toPath()));
            
            final ByteBlock saveFileData = saveFile.getSaveFileData();
            
            if (isDs1SaveFile(saveFileData)) {
                for (int slotIndex = 0; slotIndex < saveFileDefinition.getSlotDescriptorDefinitions().size(); slotIndex++) {
                    final ByteBlockSectionDefinition<ByteBlock> slotDescriptorDefinition = saveFileDefinition.getSlotDescriptorDefinitions().get(slotIndex);
                    final ByteBlock slotDescriptorData = blockSectionParser.parse(slotDescriptorDefinition, saveFileData);
                    final SaveSlotDescriptor slotDescriptor = slotDescriptorParser.parse(slotDescriptorData, slotIndex);
                    
                    final String slotName = blockSectionParser.parse(saveFileDefinition.getSlotNameDefinition(slotDescriptor.getSlotNameOffset()), 
                                                                     saveFileData);
                    
                    final ByteBlock slotData = blockSectionParser.parse(saveFileDefinition.getSlotDefinition(slotDescriptor.getSlotOffset(), 
                                                                                                             slotDescriptor.getSlotLength()), 
                                                                        saveFileData);
                    
                    final ByteBlock loadScreenData = blockSectionParser.parse(saveFileDefinition.getSlotLoadScreenDefinition(slotIndex), saveFileData);
                    
                    final SaveSlot saveSlot = slotParser.parse(slotData, loadScreenData, slotDescriptor.getIndex(), slotName);
                    
                    saveFile.addSaveSlotDescriptor(slotDescriptor);
                    saveFile.addSaveSlot(saveSlot);
                }
                
            } else {
                saveFile.setErroneous(true);
            }
            
            return saveFile;
            
        } else {
            throw new FileNotFoundException("Save file not found: " + file.getAbsolutePath());
        }
        
    }
    
    /**
     * Checks that the block of bytes indeed represents a save file.
     * 
     * @param saveFileData The file's bytes.
     * @return {@code true} when we identify it as valid save file.
     */
    protected boolean isDs1SaveFile(final ByteBlock saveFileData)  {
        if (saveFileData.getLength() != saveFileDefinition.getRequiredSaveFileSize()) {
            return false;
        }
        
        final long magicNumber = blockSectionParser.parse(saveFileDefinition.getMagicNumberDefinition(), saveFileData);
        
        if (magicNumber != saveFileDefinition.getRequiredMagicNumber()) {
            return false;
        }
        
        return true;
    }
    
    
}
