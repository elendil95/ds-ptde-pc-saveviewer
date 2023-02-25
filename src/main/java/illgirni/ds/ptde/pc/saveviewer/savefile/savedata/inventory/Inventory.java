package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;

public class Inventory {

  private final Map<ItemIdSpace, List<InventoryItem>> items = new HashMap<>();

  public void addItem(final InventoryItem item) {
    if (item != null) {
      List<InventoryItem> idSpace = items.get(item.getIdSpace());

      if (idSpace == null) {
        idSpace = new ArrayList<>();
        items.put(item.getIdSpace(), idSpace);
      }

      idSpace.add(item);

    }
  }

  public InventoryItem findItemById(final ItemIdSpace idSpace, final long itemId) {
    final List<InventoryItem> itemsInIdSpace = items.get(idSpace);

    if (itemsInIdSpace != null) {
      for (final InventoryItem item : itemsInIdSpace) {
        if (item.getId() == itemId) {
          return item;
        }
      }
    }

    return null;
  }

  public InventoryItem findItemByInventoryIndex(final ItemIdSpace idSpace,
      final long inventoryIndex) {
    final List<InventoryItem> itemsInIdSpace = items.get(idSpace);

    if (itemsInIdSpace != null) {
      for (final InventoryItem item : itemsInIdSpace) {
        if (item.getIndex() == inventoryIndex) {
          return item;
        }
      }
    }

    return null;
  }

}
