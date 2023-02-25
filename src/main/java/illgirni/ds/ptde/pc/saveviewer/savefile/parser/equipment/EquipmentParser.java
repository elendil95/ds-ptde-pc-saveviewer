package illgirni.ds.ptde.pc.saveviewer.savefile.parser.equipment;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.Equipment;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment.AttunedMagicDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment.EquipmentDefinition;

@Bean
public class EquipmentParser extends AbstractSaveElementParser {

  @Inject
  private EquipmentDefinition equipmentDefinition;

  @Inject
  private EquippedItemParser equippedItemParser;

  @Inject
  private AttunedMagicParser attunedMagicParser;

  public Equipment parse(final ByteBlock slotContentData) {
    final ByteBlock equipmentItemIdsBlock = blockSectionParser
        .parse(equipmentDefinition.getEquipmentItemIdsDefinition(), slotContentData);

    final ByteBlock equipmentInventoryIndexesBlock = blockSectionParser
        .parse(equipmentDefinition.getEquipmentInventoryIndexesDefinition(), slotContentData);

    final ByteBlock attunedMagicsBlock =
        blockSectionParser.parse(equipmentDefinition.getAttunedMagicDefinition(), slotContentData);

    return parse(equipmentItemIdsBlock, equipmentInventoryIndexesBlock, attunedMagicsBlock);
  }

  public Equipment parse(final ByteBlock itemIdsBlock, final ByteBlock inventoryIndexesBlock,
      final ByteBlock attunedMagicsBlock) {
    final Equipment equipment = new Equipment();

    equipment.setLeftWeapon1(equippedItemParser.parse(
        equipmentDefinition.getLeftWeapon1Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setLeftWeapon2(equippedItemParser.parse(
        equipmentDefinition.getLeftWeapon2Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setRightWeapon1(equippedItemParser.parse(
        equipmentDefinition.getRightWeapon1Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setRightWeapon2(equippedItemParser.parse(
        equipmentDefinition.getRightWeapon2Definition(), itemIdsBlock, inventoryIndexesBlock));

    equipment.setArrows1(equippedItemParser.parse(equipmentDefinition.getArrows1Definition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setArrows2(equippedItemParser.parse(equipmentDefinition.getArrows2Definition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setBolts1(equippedItemParser.parse(equipmentDefinition.getBolts1Definition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setBolts2(equippedItemParser.parse(equipmentDefinition.getBolts2Definition(),
        itemIdsBlock, inventoryIndexesBlock));

    equipment.setHead(equippedItemParser.parse(equipmentDefinition.getHeadDefinition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setBody(equippedItemParser.parse(equipmentDefinition.getBodyDefinition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setHands(equippedItemParser.parse(equipmentDefinition.getHandsDefinition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setLegs(equippedItemParser.parse(equipmentDefinition.getLegsDefinition(),
        itemIdsBlock, inventoryIndexesBlock));

    equipment.setRing1(equippedItemParser.parse(equipmentDefinition.getRing1Definition(),
        itemIdsBlock, inventoryIndexesBlock));
    equipment.setRing2(equippedItemParser.parse(equipmentDefinition.getRing2Definition(),
        itemIdsBlock, inventoryIndexesBlock));

    equipment.setConsumable1(equippedItemParser.parse(
        equipmentDefinition.getConsumable1Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setConsumable2(equippedItemParser.parse(
        equipmentDefinition.getConsumable2Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setConsumable3(equippedItemParser.parse(
        equipmentDefinition.getConsumable3Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setConsumable4(equippedItemParser.parse(
        equipmentDefinition.getConsumable4Definition(), itemIdsBlock, inventoryIndexesBlock));
    equipment.setConsumable5(equippedItemParser.parse(
        equipmentDefinition.getConsumable5Definition(), itemIdsBlock, inventoryIndexesBlock));

    for (final AttunedMagicDefinition attunedMagicDefinition : equipmentDefinition
        .getAttunedMagicDefinitions()) {
      equipment
          .addAttunedMagic(attunedMagicParser.parse(attunedMagicDefinition, attunedMagicsBlock));
    }

    return equipment;
  }

}
