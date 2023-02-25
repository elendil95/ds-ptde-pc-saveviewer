package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * Definition for the contents of the bonfire kindle state section in the slot content. The section
 * with the bonfires moves around in a list of dynamically growing other sections. Also the size of
 * this section is dynamic. The size probably depends on the areas loaded in the game: Once an area
 * is loaded the bonfires of that area are added to this section.
 * 
 * @author illgirni
 *
 */
@Bean
public class BonfireStatesDefinition {

  /**
   * The length of a single bonfire kindle state sub-section in the bonfire states section.
   */
  public int getBonfireStateBlockLength() {
    return 20;
  }

  /**
   * A single bonfire state sub-section at the given offset in the bonfire states section.
   * 
   * @param offset The offset in the bonfire states section.
   */
  public ByteBlockSectionDefinition<ByteBlock> getBonfireStateDefinition(final int offset) {
    return new ByteBlockSectionDefinition<>(offset, getBonfireStateBlockLength(),
        JavaTypeToDataType.BYTE_BLOCK);
  }

}
