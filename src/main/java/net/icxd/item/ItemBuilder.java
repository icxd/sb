package net.icxd.item;

import net.icxd.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
  public static ItemStack build(IBasicItem item) {
    final List<Class<?>> interfaces = Arrays.asList(item.getClass().getInterfaces());

    ItemStack stack = new ItemStack(item.material());
    ItemMeta meta = stack.getItemMeta();
    assert meta != null;

    meta.setDisplayName(item.rarity().getColor() + item.name());

    List<String> lore = new ArrayList<>();
    if (!item.description().isEmpty()) {
      lore.add("");
      for (String line : item.description())
        lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + line);
    }

    lore.add("");
    lore.add("" + item.rarity().getColor() + ChatColor.BOLD + item.rarity().name() + " " + (interfaces.contains(IDungeonItem.class) ? "DUNGEON " : "") + (item.category() == ItemCategory.NONE ? "" : item.category().name()));

    meta.setLore(lore);

    for (ItemFlag flag : ItemFlag.values())
      meta.addItemFlags(flag);

    PersistentDataContainer container = meta.getPersistentDataContainer();
    if (!item.stackable())
      container.set(new NamespacedKey(Main.INSTANCE, "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());

    stack.setItemMeta(meta);
    return stack;
  }
}
