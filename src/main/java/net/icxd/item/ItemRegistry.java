package net.icxd.item;

import net.icxd.item.items.TestItem;

import java.util.Map;

public class ItemRegistry {
  public static final Map<String, IBasicItem> ITEMS = Map.ofEntries(
      register(new TestItem())
  );

  private static Map.Entry<String, IBasicItem> register(IBasicItem item) {return Map.entry(item.id(), item);}
}
