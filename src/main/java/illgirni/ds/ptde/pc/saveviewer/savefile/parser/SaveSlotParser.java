package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.CheckSumConflictException;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.SaveSlotDefinition;
import illgirni.ds.ptde.pc.saveviewer.utils.ByteUtils;

/**
 * Parses a save slot in a save file or a standalone slot (exported from save file to own file).
 * Uses the {@link SaveSlotDefinition} to parse the chunks of the slot data. Validates the slot data 
 * against the checksum and required size.
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveSlotParser extends AbstractSaveElementParser {
    
    /**
     * The save slot structure definition.
     */
    @Inject
    private SaveSlotDefinition slotDefinition;
    
    /**
     * The parser for the actual slot content.
     */
    @Inject
    private SaveSlotContentParser slotContentParser;
    
    /**
     * The checksum validator.
     */
    @Inject
    private CheckSumValidator checkSumValidator;
    
    /**
     * Parses a save slot from the file at the given path.
     * 
     * @param saveSlotFilePath The file containing <i>only</i> the save slot.
     * @return The parsed save slot.
     * 
     * @throws IOException
     */
    public SaveSlot parse(final String saveSlotFilePath) throws IOException {
        final File saveSlotFile = new File(saveSlotFilePath);
        
        if (saveSlotFile.isFile()) {
            final ByteBlock saveSlotFileData = new ByteBlock(Files.readAllBytes(saveSlotFile.toPath()), 0);
            
            return parse(saveSlotFileData, -1, null);
            
        } else {
            throw new FileNotFoundException("Save slot file not found: " + saveSlotFile.getAbsolutePath());
        }
    }
    
    /**
     * Parses a save slot from the given block of bytes.
     * 
     * @param slotData The bytes for the slot.
     * @param slotIndex The index of the slot in the save file.
     * @param slotName The name of the slot in the save file.
     * @return The parsed save slot.
     */
    public SaveSlot parse(final ByteBlock slotData, final int slotIndex, final String slotName) {
        final int slotContentLength = blockSectionParser.parse(slotDefinition.getSlotContentLengthDefinition(), slotData).intValue();
        final ByteBlock slotContentData = blockSectionParser.parse(slotDefinition.getSlotContentDefinition(), slotData);
        final SaveSlot saveSlot = new SaveSlot();
        
        if (isDs1SaveSlot(slotData) && slotContentLength == slotContentData.getLength()) {
            try {
                saveSlot.setCheckSum(parseCheckSum(slotData));
                
                saveSlot.setData(slotData);
                saveSlot.setIndex(slotIndex);
                saveSlot.setName(slotName);
                saveSlot.setContentLength(slotContentLength);
                
                saveSlot.setContent(slotContentParser.parse(slotContentData));
                
            } catch (ParserException e) {
                saveSlot.setErroneous(true);
            }
            
        } else {
            saveSlot.setErroneous(true);
            
        }
        
        return saveSlot;
    }
    
    /**
     * Parses the checksum from the byte block representing a save slot and validates
     * the data against the checksum.
     * 
     * @param slotData The slot data. 
     * @return The parsed checksum.
     * 
     * @throws CheckSumConflictException
     */
    protected byte[] parseCheckSum(final ByteBlock slotData) throws CheckSumConflictException {
        final ByteBlockSectionDefinition<byte[]> checkSumDefinition = slotDefinition.getCheckSumDefinition();
        final byte[] checkSum = blockSectionParser.parse(checkSumDefinition, slotData);
        
        final ByteBlock checkSummedData = ByteUtils.readBlock(slotData.getBlockData(), checkSum.length);
        checkSumValidator.validateCheckSum(checkSum, checkSummedData);
        
        return checkSum;
    }
    
    /**
     * Checks that the byte block size corresponds to the size required for a save slot.
     * 
     * @param slotData The save slot bytes.
     * @return {@code true} when the size matches.
     */
    protected boolean isDs1SaveSlot(final ByteBlock slotData) {
        if (slotData.getLength() != slotDefinition.getRequiredSaveSlotSize()) {
            return false;
        }
        
        return true;
    }

}
