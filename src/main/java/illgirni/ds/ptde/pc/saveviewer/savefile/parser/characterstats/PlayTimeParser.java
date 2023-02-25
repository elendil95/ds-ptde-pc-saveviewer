package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import java.text.MessageFormat;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats.PlayTimeDefinition;

/**
 * Parses the play time from the byte block. The play time is stored in milliseconds, which are
 * converted to a time string.
 * 
 * @author illgirni
 *
 */
@Bean
public class PlayTimeParser extends AbstractSaveElementParser {

  /**
   * The play time (milliseconds) definition.
   */
  @Inject
  private PlayTimeDefinition playTimeDefinition;

  /**
   * Parses the play time from the byte block.
   * 
   * @param playTimeBlock The bytes.
   * @return The play time as formatted String.
   */
  public String parse(ByteBlock playTimeBlock) {
    final long totalMillis =
        blockSectionParser.parse(playTimeDefinition.getPlayTimeDefinition(), playTimeBlock);

    final long totalSeconds = totalMillis / 1000;
    final long totalMinutes = totalSeconds / 60;

    // long extraMillis = totalMillis % 1000; //not used

    final long hours = totalMinutes / 60;
    final long minutes = totalMinutes - (hours * 60);
    final long seconds = totalSeconds - (totalMinutes * 60);

    return MessageFormat.format("{0,number,00}:{1,number,00}:{2,number,00}", hours, minutes,
        seconds);
  }

}
