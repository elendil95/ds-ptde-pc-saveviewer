package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The play time is stored as a number of milliseconds in the character stats.
 * 
 * @author illgirni
 *
 */
@Bean
public class PlayTimeDefinition {

  /**
   * The play time definition in the character stats play time section.
   */
  public ByteBlockSectionDefinition<Long> getPlayTimeDefinition() {
    return new ByteBlockSectionDefinition<>(0, 4, JavaTypeToDataType.UINT_32);
  }

}
