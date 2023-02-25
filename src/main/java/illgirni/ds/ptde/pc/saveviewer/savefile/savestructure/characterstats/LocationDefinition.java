package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The characters current location is defined by an area/world id. The id consists of a "main area"
 * id and a "sub-area" id stored within the location section of the save slot.
 * 
 * @author illgirni
 *
 */
@Bean
public class LocationDefinition {

  /**
   * The sub-section with the main area id.
   */
  public ByteBlockSectionDefinition<Long> getMainAreaDefinition() {
    return new ByteBlockSectionDefinition<>(1, 1, JavaTypeToDataType.UINT_8);
  }

  /**
   * The sub-section with the sub-area id.
   */
  public ByteBlockSectionDefinition<Long> getSubAreaDefinition() {
    return new ByteBlockSectionDefinition<>(0, 1, JavaTypeToDataType.UINT_8);
  }


}
