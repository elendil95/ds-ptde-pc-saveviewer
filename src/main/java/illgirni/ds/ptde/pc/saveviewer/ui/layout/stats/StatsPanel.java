package illgirni.ds.ptde.pc.saveviewer.ui.layout.stats;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Ending;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Gift;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Location;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.StartingClass;
import illgirni.ds.ptde.pc.saveviewer.ui.i18n.Messages;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.AbstractDetailsPanel;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * A details panel which shows a character's stats. The shown elements are grouped in thematic lists
 * (e.g. levelable stats, basic character properties), which are aligned as a grid with two columns.
 * Each list entry consists of label-value-pairs.
 * 
 * @author illgirni
 *
 */
public class StatsPanel extends AbstractDetailsPanel {

  private Text ng;

  private Text lastEnding;

  private Text playTime;

  private Text location;

  private Text level;

  private Text humanity;

  private Text hp;

  private Text stamina;

  private Text covenant;

  private Text cursed;

  private Text souls;

  private Text soulsTotal;

  private Text deaths;

  private Text coopVictories;

  private Text indictments;

  private Text startingClass;

  private Text gift;

  private Text vitality;

  private Text attunement;

  private Text endurance;

  private Text strength;

  private Text dexterity;

  private Text resistance;

  private Text intelligence;

  private Text faith;

  /** {@inheritDoc} */
  @Override
  protected String getTitle() {
    return "Stats";
  }

  /**
   * Adds four listings organized in two columns to the container:
   * <ul>
   * <li>playthrough information</li>
   * <li>stage (level, covenant, etc.) information</li>
   * <li>levelable stats</li>
   * <li>other</li>
   * </ul>
   */
  @Override
  protected void fillContentGrid(GridPane contentContainer) {
    int nextRow = createBlock1(contentContainer, 2, 0);
    int nextRow2 = createBlock2(contentContainer, 0, 0);

    nextRow = Integer.max(nextRow, nextRow2);
    contentContainer.add(new Text(""), 0, nextRow++);

    createBlock3(contentContainer, 2, nextRow);
    createBlock4(contentContainer, 0, nextRow);
  }

  /**
   * Adds the playthrough cells to the container.
   * 
   * @param statsContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock1(final GridPane statsContainer, final int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(statsContainer, "Location", VPos.TOP, labelColumn, row);
    location = addValueCell(statsContainer, "", VPos.TOP, valueColumn, row++);

    addLabelGridCell(statsContainer, "Playtime", VPos.CENTER, labelColumn, row);
    playTime = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "NG", VPos.CENTER, labelColumn, row);
    ng = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Last Ending", VPos.BOTTOM, labelColumn, row);
    lastEnding = addValueCell(statsContainer, "", VPos.BOTTOM, valueColumn, row++);

    return row;
  }

  /**
   * Adds the stage cells to the container.
   * 
   * @param statsContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock2(GridPane statsContainer, int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(statsContainer, "Starting Class", VPos.TOP, labelColumn, row);
    startingClass = addValueCell(statsContainer, "", VPos.TOP, valueColumn, row++);

    addLabelGridCell(statsContainer, "Covenant", VPos.CENTER, labelColumn, row);
    covenant = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Level", VPos.CENTER, labelColumn, row);
    level = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Humanity", VPos.BOTTOM, labelColumn, row);
    humanity = addValueCell(statsContainer, "", VPos.BOTTOM, valueColumn, row++);

    return row;
  }

  /**
   * Adds the "other" cells to the container.
   * 
   * @param statsContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock3(GridPane statsContainer, int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(statsContainer, "Souls Total", VPos.TOP, labelColumn, row);
    soulsTotal = addValueCell(statsContainer, "", VPos.TOP, valueColumn, row++);

    addLabelGridCell(statsContainer, "Souls", VPos.CENTER, labelColumn, row);
    souls = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Health", VPos.CENTER, labelColumn, row);
    hp = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Stamina", VPos.CENTER, labelColumn, row);
    stamina = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Cursed", VPos.CENTER, labelColumn, row);
    cursed = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Deaths", VPos.CENTER, labelColumn, row);
    deaths = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Co-Op Victories", VPos.CENTER, labelColumn, row);
    coopVictories = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Indictments", VPos.CENTER, labelColumn, row);
    indictments = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Gift", VPos.BOTTOM, labelColumn, row);
    gift = addValueCell(statsContainer, "", VPos.BOTTOM, valueColumn, row++);

    return row;
  }

  /**
   * Adds the levelable stats cells to the container.
   * 
   * @param statsContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock4(GridPane statsContainer, int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(statsContainer, "Vitality", VPos.TOP, labelColumn, row);
    vitality = addValueCell(statsContainer, "", VPos.TOP, valueColumn, row++);

    addLabelGridCell(statsContainer, "Attunement", VPos.CENTER, labelColumn, row);
    attunement = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Endurance", VPos.CENTER, labelColumn, row);
    endurance = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Strength", VPos.CENTER, labelColumn, row);
    strength = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Dexterity", VPos.CENTER, labelColumn, row);
    dexterity = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Resistance", VPos.CENTER, labelColumn, row);
    resistance = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Intelligence", VPos.CENTER, labelColumn, row);
    intelligence = addValueCell(statsContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(statsContainer, "Faith", VPos.BOTTOM, labelColumn, row);
    faith = addValueCell(statsContainer, "", VPos.BOTTOM, valueColumn, row++);

    return row;
  }

  public void setNG(final long ng) {
    this.ng.setText((ng + 1) + " (NG+" + ng + ")");
  }

  public void setLastEnding(final Ending lastEnding) {
    if (lastEnding == null) {
      this.lastEnding.setText("");
    } else {
      this.lastEnding.setText(Messages.getMessage(lastEnding));
    }
  }

  public void setPlayTime(final String playTime) {
    this.playTime.setText(playTime);
  }

  public void setLocation(final Location location) {
    if (location == null) {
      this.location.setText("");
    } else {
      this.location.setText(Messages.getMessage(location));
    }
  }

  public void setLevel(final long level) {
    this.level.setText(format(level));
  }

  public void setHumanity(final long humanity) {
    this.humanity.setText(format(humanity));
  }

  public void setHP(final long hpModified, final long hpUnmodified) {
    this.hp.setText(format(hpModified) + " (" + format(hpUnmodified) + ")");
  }

  public void setStamina(final long staminaModified, final long staminaUnmodified) {
    this.stamina.setText(format(staminaModified) + " (" + format(staminaUnmodified) + ")");
  }

  public void setCursed(final boolean cursed) {
    if (cursed) {
      this.cursed.setText("yes");
    } else {
      this.cursed.setText("no");
    }
  }

  public void setSouls(final long souls) {
    this.souls.setText(format(souls));
  }

  public void setSoulsTotal(final long soulsTotal) {
    this.soulsTotal.setText(format(soulsTotal));
  }

  public void setDeaths(final long deaths) {
    this.deaths.setText(format(deaths));
  }

  public void setCoopVictories(final long coopVictories) {
    this.coopVictories.setText(format(coopVictories));
  }

  public void setIndictments(final long indictments) {
    this.indictments.setText(format(indictments));
  }

  public void setStartingClass(final StartingClass startingClass) {
    if (startingClass == null) {
      this.startingClass.setText("");
    } else {
      this.startingClass.setText(Messages.getMessage(startingClass));
    }
  }

  public void setGift(final Gift gift) {
    if (gift == null) {
      this.gift.setText("");
    } else {
      this.gift.setText(Messages.getMessage(gift));
    }
  }

  public void setCovenant(final Covenant covenant, final long rank) {
    if (covenant == null) {
      this.covenant.setText("");
    } else if (rank > 0) {
      this.covenant.setText(Messages.getMessage(covenant) + " +" + rank);
    } else {
      this.covenant.setText(Messages.getMessage(covenant));
    }
  }

  public void setVitality(final long vitality) {
    this.vitality.setText(format(vitality));
  }

  public void setAttunement(final long attunement) {
    this.attunement.setText(format(attunement));
  }

  public void setEndurance(final long endurance) {
    this.endurance.setText(format(endurance));
  }

  public void setStrength(final long strength) {
    this.strength.setText(format(strength));
  }

  public void setDexterity(final long dexterity) {
    this.dexterity.setText(format(dexterity));
  }

  public void setResistance(final long resistance) {
    this.resistance.setText(format(resistance));
  }

  public void setIntelligence(final long intelligence) {
    this.intelligence.setText(format(intelligence));
  }

  public void setFaith(final long faith) {
    this.faith.setText(format(faith));
  }

}
