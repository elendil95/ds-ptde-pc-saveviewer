package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * Definition of a section for the kindle state of a bonfire.
 * 
 * @author illgirni
 *
 */
@Bean
public class BonfireStateDefinition {

  /**
   * The id of the bonfire.
   */
  public ByteBlockSectionDefinition<Long> getBonfireDefinition() {
    return new ByteBlockSectionDefinition<>(8, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The strength (kindle state) of the bonfire. This is a simple one byte indicator value.
   */
  public ByteBlockSectionDefinition<ByteBlock> getBonfireStrengthDefinition() {
    return new ByteBlockSectionDefinition<>(12, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

}
