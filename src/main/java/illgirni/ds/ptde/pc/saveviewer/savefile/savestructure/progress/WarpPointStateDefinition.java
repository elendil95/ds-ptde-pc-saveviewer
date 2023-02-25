package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;

/**
 * Enabled ("yes/no") definition for a single warp point in a "warp points byte".
 * 
 * @author illgirni
 *
 */
public class WarpPointStateDefinition extends AbstractBitDefinition<Bonfire> {

  /**
   * @param warpPoint The warp point for which to determine if enabled or not.
   * @param unlockedBitOffset The bit offset in the byte.
   */
  public WarpPointStateDefinition(final Bonfire warpPoint, final int unlockedBitOffset) {
    super(warpPoint, unlockedBitOffset);
  }

}
