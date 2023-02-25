package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;

/**
 * The number of "offerings" provided to a "levelable" covenant.
 * 
 * @author illgirni
 *
 */
public class CovenantLevel {

  /**
   * The covenant.
   */
  private final Covenant covenant;

  /**
   * The number of offerings.
   */
  private final long offerings;

  /**
   * @param covenant The covenant.
   * @param offerings The number of offerings.
   */
  public CovenantLevel(final Covenant covenant, final long offerings) {
    this.covenant = covenant;
    this.offerings = offerings;
  }

  /**
   * The covenant.
   */
  public Covenant getCovenant() {
    return covenant;
  }

  /**
   * The number of offerings.
   */
  public long getOfferings() {
    return offerings;
  }

  /**
   * The rank in the covenant - based on the number of offerings.
   */
  public long getRank() {
    return covenant.calculateRank(offerings);
  }

}
