package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;

/**
 * Provides the definitions for all the "boss defeats" within their respective groups.
 * 
 * @see BossGroupBitBlockDefinition
 * @see BossProgressDefinition
 * 
 * @author illgirni
 *
 */
@Bean
public class BossesProgressDefinition {

  /**
   * For each boss the "defeated yes/no" bit definition in the respective boss group byte.
   */
  public List<BossProgressDefinition> getBossProgressDefinitions() {
    List<BossProgressDefinition> bossDefinitions = new ArrayList<>();

    bossDefinitions.add(new BossProgressDefinition(Boss.MANUS, 1));
    bossDefinitions.add(new BossProgressDefinition(Boss.ASYLUM_DEMON, 0));

    bossDefinitions.add(new BossProgressDefinition(Boss.QUELAAG, 1));
    bossDefinitions.add(new BossProgressDefinition(Boss.BED_OF_CHAOS, 2));
    bossDefinitions.add(new BossProgressDefinition(Boss.IRON_GOLEM, 3));
    bossDefinitions.add(new BossProgressDefinition(Boss.ORNSTEIN_AND_SMOUGH, 4));
    bossDefinitions.add(new BossProgressDefinition(Boss.FOUR_KINGS, 5));
    bossDefinitions.add(new BossProgressDefinition(Boss.SEATH, 6));
    bossDefinitions.add(new BossProgressDefinition(Boss.GWYN, 7));

    bossDefinitions.add(new BossProgressDefinition(Boss.GAPING_DRAGON, 2));
    bossDefinitions.add(new BossProgressDefinition(Boss.BELL_GARGOYLES, 3));
    bossDefinitions.add(new BossProgressDefinition(Boss.PRISCILLA, 4));
    bossDefinitions.add(new BossProgressDefinition(Boss.SIF, 5));
    bossDefinitions.add(new BossProgressDefinition(Boss.PINWHEEL, 6));
    bossDefinitions.add(new BossProgressDefinition(Boss.NITO, 7));

    bossDefinitions.add(new BossProgressDefinition(Boss.TAURUS, 5));
    bossDefinitions.add(new BossProgressDefinition(Boss.CAPRA_DEMON, 6));

    bossDefinitions.add(new BossProgressDefinition(Boss.MOONLIGHT_BUTTERFLY, 4));

    bossDefinitions.add(new BossProgressDefinition(Boss.SANCTUARY_GUARDIAN, 0));
    bossDefinitions.add(new BossProgressDefinition(Boss.ARTORIAS, 1));
    bossDefinitions.add(new BossProgressDefinition(Boss.KALAMEET, 4));


    bossDefinitions.add(new BossProgressDefinition(Boss.DEMON_FIRESAGE, 2));

    bossDefinitions.add(new BossProgressDefinition(Boss.CEASELESS_DISCHARGE, 4));
    bossDefinitions.add(new BossProgressDefinition(Boss.CENTIPEDE_DEMON, 5));

    bossDefinitions.add(new BossProgressDefinition(Boss.GWYNDLOLIN, 4));

    bossDefinitions.add(new BossProgressDefinition(Boss.STRAY_DEMON, 4));

    if (bossDefinitions.size() != Boss.values().length) {
      throw new RuntimeException("Not all bosses defined.");
    }

    return bossDefinitions;
  }

}
