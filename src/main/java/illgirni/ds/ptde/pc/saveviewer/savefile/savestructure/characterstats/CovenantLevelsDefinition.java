package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The level of a covenant is determined by the number of "items" that have been offered for the
 * covenant. The progress of all the "levelable" covenants is stored in one section within the
 * character stats. The progress of a particular covenant takes up one byte in this section.
 * 
 * @author illgirni
 *
 */
@Bean
public class CovenantLevelsDefinition {

  /**
   * For the given covenant provides the sub-section with the covenant's progress within the
   * character stats covenants progress section.
   * 
   * @param covenant The requested covenant.
   * @return The progress section definition; {@code null} when the covenant is not "levelable".
   */
  public ByteBlockSectionDefinition<Long> getCovenantLevelDefinition(final Covenant covenant) {
    switch (covenant) {
      case WARRIOR_OF_SUNLIGHT:
        return new ByteBlockSectionDefinition<>(0, 1, JavaTypeToDataType.UINT_8);
      case DARKWRAITH:
        return new ByteBlockSectionDefinition<>(1, 1, JavaTypeToDataType.UINT_8);
      case PATH_OF_THE_DRAGON:
        return new ByteBlockSectionDefinition<>(2, 1, JavaTypeToDataType.UINT_8);
      case GRAVELORD_SERVANT:
        return new ByteBlockSectionDefinition<>(3, 1, JavaTypeToDataType.UINT_8);
      case FOREST_HUNTER:
        return new ByteBlockSectionDefinition<>(5, 1, JavaTypeToDataType.UINT_8);
      case BLADE_OF_THE_DARKMOON:
        return new ByteBlockSectionDefinition<>(6, 1, JavaTypeToDataType.UINT_8);
      case CHAOS_SERVANT:
        return new ByteBlockSectionDefinition<>(7, 1, JavaTypeToDataType.UINT_8);
      case PRINESS_S_GUARD:
        return null;
      case WAY_OF_WHITE:
        return null;
      case NONE:
        return null;
      default:
        throw new IllegalArgumentException("Unsupported convenant: " + covenant);

    }
  }

}
