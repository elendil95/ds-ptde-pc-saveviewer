package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype;

/**
 * Main game locations.
 * 
 * @author illgirni
 *
 */
public enum Location {
  // TODO Be more precise?

  DEPTHS(10, 0),

  UNDEAD_BURG_PARISH(10, 1),

  FIRELINK_SHRINE(10, 2),

  PAINTED_WORLD(11, 0),

  DARKROOT_GARDEN(12, 0),

  OOLACILE(12, 1),

  CATACOMBS(13, 0),

  TOMB_OF_THE_GIANTS(13, 1),

  GREAT_HOLLOW_ASH_LAKE(13, 2),

  BLIGHTTOWN(14, 0),

  DEMON_RUINS(14, 1),

  SENS_FORTRESS(15, 0),

  ANOR_LONDO(15, 1),

  NEW_LONDO_RUINS(16, 0),

  DUKES_ARCHIVES_CAVES(17, 0),

  KILN(18, 0),

  UNDEAD_ASYLUM(18, 1);

  /**
   * The first part of the location's id.
   */
  private final long mainAreaId;

  /**
   * The second part of the location's id.
   */
  private final long subAreaId;

  /**
   * @param mainAreaId The first part of the location's id.
   * @param subAreaId The second part of the location's id.
   */
  private Location(final long mainAreaId, final long subAreaId) {
    this.mainAreaId = mainAreaId;
    this.subAreaId = subAreaId;
  }

  /**
   * Gets the location for the provided id. {@code null} when no such locaiton exists.
   */
  public static Location getLocationFor(final long mainAreaId, final long subAreaId) {
    for (final Location location : Location.values()) {
      if (location.mainAreaId == mainAreaId && location.subAreaId == subAreaId) {
        return location;
      }
    }

    return null;

  }

}
