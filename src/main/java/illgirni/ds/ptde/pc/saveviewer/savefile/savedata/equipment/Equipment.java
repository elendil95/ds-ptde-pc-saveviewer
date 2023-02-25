package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * All the equipped items and magics.
 * 
 * @author illgirni
 *
 */
public class Equipment {

  /**
   * Main left hand weapon.
   */
  private EquippedItem leftWeapon1;

  /**
   * Secondary left hand weapon.
   */
  private EquippedItem leftWeapon2;

  /**
   * Main right hand weapon.
   */
  private EquippedItem rightWeapon1;

  /**
   * Secondary right hand weapon.
   */
  private EquippedItem rightWeapon2;

  /**
   * First arrow type.
   */
  private EquippedItem arrows1;

  /**
   * Second arrow type.
   */
  private EquippedItem arrows2;

  /**
   * First bolt type.
   */
  private EquippedItem bolts1;

  /**
   * Second bolt type.
   */
  private EquippedItem bolts2;

  /**
   * Head gear.
   */
  private EquippedItem head;

  /**
   * Chest gear.
   */
  private EquippedItem body;

  /**
   * Hand gear.
   */
  private EquippedItem hands;

  /**
   * Leg gear.
   */
  private EquippedItem legs;

  /**
   * First ring.
   */
  private EquippedItem ring1;

  /**
   * Second ring.
   */
  private EquippedItem ring2;

  /**
   * First consumable.
   */
  private EquippedItem consumable1;

  /**
   * Second consumable.
   */
  private EquippedItem consumable2;

  /**
   * Third consumable.
   */
  private EquippedItem consumable3;

  /**
   * Fourth consumable.
   */
  private EquippedItem consumable4;

  /**
   * Fifth consumable.
   */
  private EquippedItem consumable5;

  /**
   * The attuned magics.
   */
  private List<AttunedMagic> attunedMagics = new ArrayList<>();

  /**
   * All the currently equipped items as list. Does not contain empty equipment slots.
   */
  public List<EquippedItem> getEquippedItems() {
    final List<EquippedItem> equippedItems = new ArrayList<>();

    if (leftWeapon1 != null) {
      equippedItems.add(leftWeapon1);
    }

    if (leftWeapon2 != null) {
      equippedItems.add(leftWeapon2);
    }

    if (rightWeapon1 != null) {
      equippedItems.add(rightWeapon1);
    }

    if (rightWeapon2 != null) {
      equippedItems.add(rightWeapon2);
    }

    if (arrows1 != null) {
      equippedItems.add(arrows1);
    }

    if (arrows2 != null) {
      equippedItems.add(arrows2);
    }

    if (bolts1 != null) {
      equippedItems.add(bolts1);
    }

    if (bolts2 != null) {
      equippedItems.add(bolts2);
    }

    if (head != null) {
      equippedItems.add(head);
    }

    if (body != null) {
      equippedItems.add(body);
    }

    if (hands != null) {
      equippedItems.add(hands);
    }

    if (legs != null) {
      equippedItems.add(legs);
    }

    if (ring1 != null) {
      equippedItems.add(ring1);
    }

    if (ring2 != null) {
      equippedItems.add(ring2);
    }

    if (consumable1 != null) {
      equippedItems.add(consumable1);
    }

    if (consumable2 != null) {
      equippedItems.add(consumable2);
    }

    if (consumable3 != null) {
      equippedItems.add(consumable3);
    }

    if (consumable4 != null) {
      equippedItems.add(consumable4);
    }

    if (consumable5 != null) {
      equippedItems.add(consumable5);
    }

    return equippedItems;
  }

  /**
   * Adds an attuned magic to the equipment.
   */
  public void addAttunedMagic(final AttunedMagic attunedMagic) {
    if (attunedMagic != null) {
      attunedMagics.add(attunedMagic);
    }
  }

  /**
   * All attuned magics.
   */
  public List<AttunedMagic> getAttunedMagics() {
    return attunedMagics;
  }

  /**
   * Main left hand weapon.
   */
  public EquippedItem getLeftWeapon1() {
    return leftWeapon1;
  }

  /**
   * @see #getLeftWeapon1()
   */
  public void setLeftWeapon1(EquippedItem leftWeapon1) {
    this.leftWeapon1 = leftWeapon1;
  }

  /**
   * Secondary left hand weapon.
   */
  public EquippedItem getLeftWeapon2() {
    return leftWeapon2;
  }

  /**
   * @see #getLeftWeapon2()
   */
  public void setLeftWeapon2(EquippedItem leftWeapon2) {
    this.leftWeapon2 = leftWeapon2;
  }

  /**
   * Main right hand weapon.
   */
  public EquippedItem getRightWeapon1() {
    return rightWeapon1;
  }

  /**
   * @see #getRightWeapon1()
   */
  public void setRightWeapon1(EquippedItem rightWeapon1) {
    this.rightWeapon1 = rightWeapon1;
  }

  /**
   * Secondary right hand weapon.
   */
  public EquippedItem getRightWeapon2() {
    return rightWeapon2;
  }

  /**
   * @see #getRightWeapon2()
   */
  public void setRightWeapon2(EquippedItem rightWeapon2) {
    this.rightWeapon2 = rightWeapon2;
  }

  /**
   * First arrow type.
   */
  public EquippedItem getArrows1() {
    return arrows1;
  }

  /**
   * @see #getArrows1()
   */
  public void setArrows1(EquippedItem arrows1) {
    this.arrows1 = arrows1;
  }

  /**
   * Second arrow type.
   */
  public EquippedItem getArrows2() {
    return arrows2;
  }

  /**
   * @see #getArrows2()
   */
  public void setArrows2(EquippedItem arrows2) {
    this.arrows2 = arrows2;
  }

  /**
   * First bolt type.
   */
  public EquippedItem getBolts1() {
    return bolts1;
  }

  /**
   * @see #getBolts1()
   */
  public void setBolts1(EquippedItem bolts1) {
    this.bolts1 = bolts1;
  }

  /**
   * Second bolt type.
   */
  public EquippedItem getBolts2() {
    return bolts2;
  }

  /**
   * @see #getBolts2()
   */
  public void setBolts2(EquippedItem bolts2) {
    this.bolts2 = bolts2;
  }

  /**
   * Head gear.
   */
  public EquippedItem getHead() {
    return head;
  }

  /**
   * @see #getHead()
   */
  public void setHead(EquippedItem head) {
    this.head = head;
  }

  /**
   * Chest gear.
   */
  public EquippedItem getBody() {
    return body;
  }

  /**
   * @see #getBody()
   */
  public void setBody(EquippedItem body) {
    this.body = body;
  }

  /**
   * Hand gear.
   */
  public EquippedItem getHands() {
    return hands;
  }

  /**
   * @see #getHands()
   */
  public void setHands(EquippedItem hands) {
    this.hands = hands;
  }

  /**
   * Leg gear.
   */
  public EquippedItem getLegs() {
    return legs;
  }

  /**
   * @see #getLegs()
   */
  public void setLegs(EquippedItem legs) {
    this.legs = legs;
  }

  /**
   * First ring.
   */
  public EquippedItem getRing1() {
    return ring1;
  }

  /**
   * @see #getRing1()
   */
  public void setRing1(EquippedItem ring1) {
    this.ring1 = ring1;
  }

  /**
   * Second ring.
   */
  public EquippedItem getRing2() {
    return ring2;
  }

  /**
   * @see #getRing2()
   */
  public void setRing2(EquippedItem ring2) {
    this.ring2 = ring2;
  }

  /**
   * First consumable.
   */
  public EquippedItem getConsumable1() {
    return consumable1;
  }

  /**
   * @see #getConsumable1()
   */
  public void setConsumable1(EquippedItem consumable1) {
    this.consumable1 = consumable1;
  }

  /**
   * Second consumable.
   */
  public EquippedItem getConsumable2() {
    return consumable2;
  }

  /**
   * @see #getConsumable2()
   */
  public void setConsumable2(EquippedItem consumable2) {
    this.consumable2 = consumable2;
  }

  /**
   * Third consumable.
   */
  public EquippedItem getConsumable3() {
    return consumable3;
  }

  /**
   * @see #getConsumable3()
   */
  public void setConsumable3(EquippedItem consumable3) {
    this.consumable3 = consumable3;
  }

  /**
   * Fourth consumable.
   */
  public EquippedItem getConsumable4() {
    return consumable4;
  }

  /**
   * @see #getConsumable4()
   */
  public void setConsumable4(EquippedItem consumable4) {
    this.consumable4 = consumable4;
  }

  /**
   * Fifth consumable.
   */
  public EquippedItem getConsumable5() {
    return consumable5;
  }

  /**
   * @see #getConsumable5()
   */
  public void setConsumable5(EquippedItem consumable5) {
    this.consumable5 = consumable5;
  }

}
