package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.TailcutStateDefinition;

/**
 * Parses the tail cut information for a single tail owner from a "tail cut bit block" containing
 * information for possibly multiple tail owners.
 * 
 * @author illgirni
 *
 */
@Bean
public class TailcutStateParser extends AbstractSaveElementParser {

  /**
   * Parses the tail cut information for the tail cut definition from the bit block.
   * 
   * @param tailcutDefinition The tail cut definition.
   * @param tailcutBitBlock The "tail cut bit block".
   * @return The tail cut information for the tail owner.
   */
  public TailcutState parse(TailcutStateDefinition tailcutDefinition,
      TailcutBitBlock tailcutBitBlock) {
    final TailcutState tailcut = new TailcutState();

    tailcut.setTailOwner(tailcutDefinition.getRepresentor());
    tailcut.setCut(tailcutBitBlock.getBlockData()[tailcutDefinition.getBitOffset()]);

    return tailcut;
  }

}
