package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * Generic definition of a character stat that is represented by an indicator ("enumerated") value
 * in the save.
 * 
 * @author illgirni
 *
 */
@Bean
public class IndicatorStatDefinition {

  /**
   * The indicator section definition, which is a one byte unsigned number.
   */
  public ByteBlockSectionDefinition<Long> getDefinition() {
    return new ByteBlockSectionDefinition<>(0, 1, JavaTypeToDataType.UINT_8);
  }

}
