package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import java.util.ArrayList;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlotContent;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CharacterStatistics;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CovenantLevel;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.BonfireStrength;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Gender;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.AttunedMagic;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.Equipment;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BonfiresState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossesProgress;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutsState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpState;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ExportSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ImportSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageButton;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageKey;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageRegistry;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.progress.BonfiresPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.progress.BossesPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.progress.TailcutsPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.progress.WarpPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.stats.EquipmentPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.stats.StatsPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceSetting;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A panel to show the details of a save slot. Supports multiple layouts: tabbed, split panel, and
 * list. Which layout is used depends on user preferences. Also defines controls to import an
 * exported slot and export a save file slot.
 * 
 * @author illgirni
 *
 */
public class SaveSlotDetailsPanel extends BorderPane {

  /**
   * The sub-panel with the character name and the export/import controls.
   */
  private final TitlePanel titlePanel = new TitlePanel();

  /**
   * The stats-sub-panel.
   */
  private final StatsPanel statsPanel = new StatsPanel();

  /**
   * The equipment sub-panel.
   */
  private final EquipmentPanel equipmentPanel = new EquipmentPanel();

  /**
   * The bosses sub-panel.
   */
  private final BossesPanel bossesPanel = new BossesPanel();

  /**
   * The bonfires sub-panel.
   */
  private final BonfiresPanel bonfiresPanel = new BonfiresPanel();

  /**
   * The warp points sub-panel.
   */
  private final WarpPanel warpPanel = new WarpPanel();

  /**
   * The tail-cuts sub-panel.
   */
  private final TailcutsPanel tailcutsPanel = new TailcutsPanel();

  /**
   * The container for all of the sub-panels.
   */
  private final Node content;

  /**
   * Node to be shown when no slot details are to be displayed at the moment.
   */
  private final Pane emptyFiller;

  /**
   * Sets up the panel content. Initially makes the panel appear as "empty".
   */
  public SaveSlotDetailsPanel() {
    this.content = createContent();

    this.emptyFiller = new Pane();
    this.emptyFiller.setStyle("-fx-background-color: #fff;");

    setCenter(emptyFiller);
  }

  /**
   * Creates the main content with all the detail sub-panels. Which kind of node is created depends
   * on user preferences.
   */
  private Node createContent() {
    final int layoutIndicator = LayoutUtils.getPreferenceValue(PreferenceSetting.CHARACTER_DETAILS);

    switch (layoutIndicator) {
      case 3:
        return createListedContent();
      case 2:
        return createSplitContent();
      default:
        return createTabContent();
    }
  }

  /**
   * Creates the content as tab panel.
   */
  private Node createTabContent() {
    final TabPane tabbedContent = new TabPane();
    tabbedContent.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    tabbedContent.setSide(Side.LEFT);

    final Tab statsTab = new Tab("Stats & Equipment");
    final Node statsTabContent = createDetailSectionsListing(statsPanel, equipmentPanel);
    statsTab.setContent(statsTabContent);

    final Tab bonfiresTab = new Tab("Bonfires");
    final Node bonfiresTabContent = createDetailSectionsListing(warpPanel, bonfiresPanel);
    bonfiresTab.setContent(bonfiresTabContent);

    final Tab bossesTab = new Tab("Bosses");
    final Node bossesTabContent = createDetailSectionsListing(bossesPanel, tailcutsPanel);
    bossesTab.setContent(bossesTabContent);

    tabbedContent.getTabs().addAll(statsTab, bossesTab, bonfiresTab);
    tabbedContent.getSelectionModel().selectFirst();

    return tabbedContent;
  }

  /**
   * Creates the content as split panel.
   */
  private Node createSplitContent() {
    final SplitPane splitContent = new SplitPane();
    splitContent.setOrientation(Orientation.HORIZONTAL);

    final Node statsList = createDetailSectionsListing(statsPanel, equipmentPanel);
    final Node progressList =
        createDetailSectionsListing(bossesPanel, tailcutsPanel, warpPanel, bonfiresPanel);

    splitContent.getItems().addAll(statsList, progressList);

    Platform.runLater(() -> splitContent.setDividerPositions(0.5));

    return splitContent;
  }

  /**
   * Creates the content as simple listing of the sub-panels.
   */
  private Node createListedContent() {
    return createDetailSectionsListing(statsPanel, equipmentPanel, bossesPanel, tailcutsPanel,
        warpPanel, bonfiresPanel);
  }

  /**
   * Creates a vertical, scrollable listing of the nodes.
   * 
   * @param detailSections The listed nodes.
   * @return The node list.
   */
  private Node createDetailSectionsListing(Node... detailSections) {
    final VBox detailsList = new VBox();
    detailsList.setSpacing(10);
    detailsList.setFillWidth(true);
    detailsList.setStyle("-fx-background-color: transparent;");

    for (Node detailSection : detailSections) {
      detailsList.getChildren().add(detailSection);
    }

    GridPane.setHgrow(detailsList, Priority.NEVER);

    GridPane contentBackgroundContainer = new GridPane();
    contentBackgroundContainer.setStyle("-fx-background-color: #ccc;");
    contentBackgroundContainer.add(detailsList, 0, 0);
    contentBackgroundContainer.setPadding(new Insets(10));

    final ScrollPane listedContent = new ScrollPane(contentBackgroundContainer);
    listedContent.setContent(contentBackgroundContainer);
    // listedContent.setPadding(new Insets(10, 10, 10, 10));
    listedContent.getStyleClass().add("slot-details");
    listedContent
        .setStyle("-fx-background-color: #ccc; -fx-border-width: 1 1 1 1; -fx-border-color: #999");

    return listedContent;
  }

  /**
   * Updates the content of this panel to the values defined by the slot. When no slot is provided
   * switched to "empty content". Otherwise shows the "detail content" with the slot's values.
   * 
   * @param slot The slot to display.
   * @param standalone If the import ({@code true}) or export ({@code false}) controls have to be
   *        shown.
   */
  public void update(SaveSlot slot, boolean standalone) {
    if (slot == null) {
      setTop(null);
      setCenter(emptyFiller);
      titlePanel.setExport(false, null);

    } else {
      setTop(titlePanel);
      setCenter(content);

      // not pretty, but well...
      if (content instanceof TabPane) {
        ((TabPane) content).getSelectionModel().selectFirst();
      }

      final SaveSlotContent slotContent = slot.getContent();
      final CharacterStatistics stats = slotContent.getCharacterStatistics();

      titlePanel.setName(stats.getName());
      titlePanel.setGender(stats.getGender());

      titlePanel.setExport(!standalone, slot);

      // TODO delegate update to the different panels
      statsPanel.setNG(stats.getClearCount());
      statsPanel.setLastEnding(stats.getLastEnding());
      statsPanel.setPlayTime(stats.getPlayTime());
      statsPanel.setLocation(stats.getLocation());
      statsPanel.setLevel(stats.getLevel());

      statsPanel.setHumanity(stats.getHumanity());
      statsPanel.setHP(stats.getHpMaxModified(), stats.getHpMaxUnmodified());
      statsPanel.setStamina(stats.getStaminaMaxModified(), stats.getStaminaMaxUnmodified());
      statsPanel.setCursed(stats.isCursed());
      statsPanel.setSouls(stats.getSouls());

      statsPanel.setSoulsTotal(stats.getSoulsTotal());
      statsPanel.setDeaths(stats.getDeaths());
      statsPanel.setCoopVictories(stats.getCoopVictories());
      statsPanel.setIndictments(stats.getIndictments());
      statsPanel.setStartingClass(stats.getStartingClass());

      final Covenant covenant = stats.getCovenant();
      long covenantRank = 0;

      for (final CovenantLevel covenantLevel : stats.getCovenantLevels()) {
        if (covenantLevel.getCovenant() == covenant) {
          covenantRank = covenantLevel.getRank();
          break;
        }
      }

      statsPanel.setCovenant(covenant, covenantRank);

      statsPanel.setGift(stats.getGift());
      statsPanel.setVitality(stats.getVitality());
      statsPanel.setAttunement(stats.getAttunement());
      statsPanel.setEndurance(stats.getEndurance());
      statsPanel.setStrength(stats.getStrength());

      statsPanel.setDexterity(stats.getDexterity());
      statsPanel.setResistance(stats.getResistance());
      statsPanel.setIntelligence(stats.getIntelligence());
      statsPanel.setFaith(stats.getFaith());

      final Equipment equipment = slotContent.getEquipment();

      equipmentPanel.setHead(equipment.getHead());
      equipmentPanel.setBody(equipment.getBody());
      equipmentPanel.setHands(equipment.getHands());
      equipmentPanel.setLegs(equipment.getLegs());

      equipmentPanel.setRightWeapon1(equipment.getRightWeapon1());
      equipmentPanel.setRightWeapon2(equipment.getRightWeapon2());
      equipmentPanel.setLeftWeapon1(equipment.getLeftWeapon1());
      equipmentPanel.setLeftWeapon2(equipment.getLeftWeapon2());

      equipmentPanel.setRing1(equipment.getRing1());
      equipmentPanel.setRing2(equipment.getRing2());

      equipmentPanel.setArrows1(equipment.getArrows1());
      equipmentPanel.setArrows2(equipment.getArrows2());
      equipmentPanel.setBolts1(equipment.getBolts1());
      equipmentPanel.setBolts2(equipment.getBolts2());

      equipmentPanel.setConsumable1(equipment.getConsumable1());
      equipmentPanel.setConsumable2(equipment.getConsumable2());
      equipmentPanel.setConsumable3(equipment.getConsumable3());
      equipmentPanel.setConsumable4(equipment.getConsumable4());
      equipmentPanel.setConsumable5(equipment.getConsumable5());

      equipmentPanel.setAttunedMagics(new ArrayList<AttunedMagic>(equipment.getAttunedMagics()));

      final BossesProgress bossesProgress = slotContent.getProgress().getBossesProgress();
      int bossesDefeatedCount = 0;

      for (final Boss boss : Boss.values()) {
        final boolean defeated = bossesProgress.isDefeated(boss);
        bossesPanel.setBoss(boss, defeated);

        if (defeated) {
          bossesDefeatedCount++;
        }
      }

      bossesPanel.setBossesProgress(bossesDefeatedCount);

      final BonfiresState bonfiresState = slotContent.getProgress().getBonfiresState();
      int bonfiresLitCount = 0;

      for (final Bonfire bonfire : Bonfire.values()) {
        final BonfireStrength bonfireStrength = bonfiresState.getStrength(bonfire);
        bonfiresPanel.setBonfireStrength(bonfire, bonfireStrength);

        if (bonfireStrength != BonfireStrength.OFF) {
          bonfiresLitCount++;
        }
      }

      bonfiresPanel.setBonfiresProgress(bonfiresLitCount);

      final WarpState warpState = slotContent.getProgress().getWarpState();
      int warpPointsUnlockCount = 0;

      for (final Bonfire bonfire : WarpState.ALL_WARP_POINTS) {
        final boolean unlocked = warpState.isUnlocked(bonfire);
        warpPanel.setWarpPoint(bonfire, unlocked);

        if (unlocked) {
          warpPointsUnlockCount++;
        }
      }

      warpPanel.setWarpPointsProgress(warpPointsUnlockCount);
      warpPanel.setWarpUnlocked(warpState.isWarpingUnlocked());

      final TailcutsState tailcutsState = slotContent.getProgress().getTailcutsState();
      int tailcutCount = 0;

      for (final TailOwner tailOwner : TailOwner.values()) {
        final boolean cut = tailcutsState.isCut(tailOwner);
        tailcutsPanel.setTailcut(tailOwner, cut);

        if (cut) {
          tailcutCount++;
        }
      }

      tailcutsPanel.setTailcutsProgress(tailcutCount);

    }

  }

  /**
   * The title panel. Shows the character gender, name, and import/export controls. Only one of the
   * import and export controls is shown at a time.
   */
  private class TitlePanel extends GridPane {

    /**
     * Gender icon.
     */
    private final ImageView gender;

    /**
     * Character name.
     */
    private final Text name;

    /**
     * Import button.
     */
    private final Button importButton;

    /**
     * Export button.
     */
    private final Button exportButton;

    /**
     * Fills the panel.
     */
    public TitlePanel() {
      setStyle("-fx-background-color: #fff; -fx-border-width: 0 0 1 0; -fx-border-color: #999;");
      setPadding(new Insets(5));
      setHgap(1);

      gender = new ImageView();
      gender.setFitHeight(20);
      gender.setFitWidth(20);

      GridPane.setValignment(gender, VPos.CENTER);
      GridPane.setHgrow(gender, Priority.NEVER);

      name = new Text("");
      name.setStyle("-fx-font-size: 1.5em; -fx-font-weight: bold;");
      GridPane.setValignment(name, VPos.CENTER);
      GridPane.setHgrow(name, Priority.ALWAYS);

      exportButton = new ImageButton(ImageKey.EXPORT_FILE);
      exportButton.setTooltip(new Tooltip("Export character from save file..."));
      GridPane.setValignment(exportButton, VPos.CENTER);
      GridPane.setHgrow(exportButton, Priority.NEVER);

      importButton = new ImageButton(ImageKey.IMPORT_FILE);
      importButton.setTooltip(new Tooltip("Import character into save file..."));
      importButton.visibleProperty().bind(exportButton.visibleProperty().not());
      importButton.managedProperty().bind(exportButton.managedProperty().not());
      GridPane.setValignment(importButton, VPos.CENTER);
      GridPane.setHgrow(importButton, Priority.NEVER);

      add(importButton, 0, 0);
      add(exportButton, 0, 0);
      add(gender, 1, 0);
      add(name, 2, 0);
    }

    /**
     * Sets which of the import/export controls is shown. Adapts the controls to fire the correct
     * events for the slot.
     * 
     * @param export If the export ({@code true}) or import ({@code false}) control is shown.
     * @param slot The slot for which to fire the import/export event.
     */
    public void setExport(final boolean export, final SaveSlot slot) {
      exportButton.setVisible(export);
      exportButton.setManaged(export);

      if (slot == null) {
        exportButton.setOnAction(null);
        importButton.setOnAction(null);

      } else if (export) {
        exportButton.setOnAction(event -> exportButton.fireEvent(new ExportSlotEvent(slot)));
        importButton.setOnAction(null);

      } else {
        importButton.setOnAction(event -> importButton.fireEvent(new ImportSlotEvent(slot)));
        exportButton.setOnAction(null);

      }
    }

    /**
     * Sets the displayed name.
     */
    public void setName(final String name) {
      this.name.setText(name);
    }

    /**
     * Sets the displayed gender.
     */
    public void setGender(final Gender gender) {
      switch (gender) {
        case FEMALE:
          this.gender.setImage(ImageRegistry.getImage(ImageKey.FEMALE));
          break;
        case MALE:
          this.gender.setImage(ImageRegistry.getImage(ImageKey.MALE));
          break;
        default:
          throw new IllegalArgumentException("Unsupported gender: " + gender);

      }

    }

  }

}
