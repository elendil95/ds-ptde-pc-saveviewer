package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The contents of a save slot are kinda mixed. There is often not a particular section containing the complete
 * set of data for one "topic" (like stats, bosses, bonfires, etc.). Hence, this definition is kind of empty.
 * <p/>
 * The slot content consists of two "areas": The actual character data and a checksum over the character data. The 
 * checksum is located at the end of the slot content.
 * <p/>
 * here are some interesting sections within the save slot content and what they contain. Offsets are in relation to 
 * the offset of the whole save slot and not just the offset of the slot content. So you'll have to subtract twenty 
 * from each offset to get the offset within the slot content:
 * <ol>
 *  <li><i>20 - 366:</i> character stats</li>
 *  <li><i>536 - 615:</i> equipped items (accessories and consumables) inventory pointers</li>
 *  <li><i>644 - 723:</i> equipped item (accessories and consumables) ids</li>
 *  <li><i>736 - 58079:</i> inventory</li>
 *  <li><i>58084 - 58179:</i> attuned magics</li>
 *  <li><i>58384 - 123920:</i> bottomless box</li>
 *  <li><i>124198 - 124207:</i> NG clear counter and last chosen game ending</li>
 *  <li><i>127272 - 127275:</i> death counter</li>
 *  <li><i>127313 - 127316:</i> enabled warp points</li>
 *  <li><i>151617:</i> quest bit indicating if warping is enabled</li>
 *  <li><i>last 16:</i> checksum over the previous bytes of the slot content.</li>
 * </ol>
 * Distributed and dynamically located sections:
 * <ul>
 *  <li><i>Bosses and tailcuts:</i> The information which bosses have been beaten and which tails were cut is 
 *      distributed over the slot contents, but always in the same location. Each "boss beaten" and "tail cut" 
 *      information is a single bit in the slot content.</li>
 *   <li><i>Bonfire kindle state:</i> The section containing how far each bonfire is kindled not only is dynamically 
 *      positioned. It also has a dynamic size. Bonfires are added as the game progresses.</li>
 * </ul>
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveSlotContentDefinition {
    
    /**
     * The section with the MD5 checksum over the slot content. This are the last sixteen bytes of the whole slot content.
     * 
     * @param slotContentLength The length of the whole slot content.
     */
    public ByteBlockSectionDefinition<byte[]> getCheckSumDefinition(final int slotContentLength) {
        return new ByteBlockSectionDefinition<>(slotContentLength - 16, 16, JavaTypeToDataType.CHECKSUM);
    }
    
}
