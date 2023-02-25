package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Location;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats.LocationDefinition;

/**
 * Uses the {@link LocationDefinition} to parse the current location from the byte block with the
 * location information.
 * 
 * @author illgirni
 *
 */
@Bean
public class LocationParser extends AbstractSaveElementParser {

  /**
   * The location value definition.
   */
  @Inject
  private LocationDefinition locationDefinition;

  /**
   * Parses the location from the byte block.
   * 
   * @param locationBlock The bytes.
   * @return The parsed location.
   */
  public Location parse(final ByteBlock locationBlock) {
    return Location.getLocationFor(
        blockSectionParser.parse(locationDefinition.getMainAreaDefinition(), locationBlock),
        blockSectionParser.parse(locationDefinition.getSubAreaDefinition(), locationBlock));
  }

}
