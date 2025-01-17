package net.icxd.item;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum ItemRarity {
  COMMON(ChatColor.WHITE),
  UNCOMMON(ChatColor.GREEN),
  RARE(ChatColor.BLUE),
  EPIC(ChatColor.DARK_PURPLE),
  LEGENDARY(ChatColor.GOLD),
  MYTHIC(ChatColor.LIGHT_PURPLE),
  DIVINE(ChatColor.AQUA),
  SPECIAL(ChatColor.RED),
  VERY_SPECIAL(ChatColor.RED),
  ULTIMATE(ChatColor.DARK_RED),
  ADMIN(ChatColor.DARK_RED);

  private final ChatColor color;

  ItemRarity(ChatColor color) {
    this.color = color;
  }
}
