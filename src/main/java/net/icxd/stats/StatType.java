package net.icxd.stats;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;

@AllArgsConstructor
public enum StatType {
  // Combat stats
  HEALTH              ("❤", ChatColor.RED),
  DEFENSE             ("❈", ChatColor.GREEN),
  STRENGTH            ("❁", ChatColor.RED),
  INTELLIGENCE        ("✎", ChatColor.AQUA),
  CRITICAL_CHANCE     ("☣", ChatColor.BLUE),
  CRITICAL_DAMAGE     ("☠", ChatColor.BLUE),
  BONUS_ATTACK_SPEED  ("⚔", ChatColor.YELLOW),
  ABILITY_DAMAGE      ("๑", ChatColor.RED),
  TRUE_DEFENSE        ("❂", ChatColor.WHITE),
  FEROCITY            ("⫽", ChatColor.RED),
  HEALTH_REGENERATION ("❣", ChatColor.RED),
  VITALITY            ("♨", ChatColor.DARK_RED),
  MENDING             ("☄", ChatColor.GREEN),
  SWING_RANGE         ("Ⓢ", ChatColor.YELLOW),

  // Gathering stats
  MINING_SPEED        ("⛏", ChatColor.GOLD),
  PRISTINE            ("✧", ChatColor.DARK_PURPLE),
  MINING_FORTUNE      ("☘", ChatColor.GOLD),
  GEMSTONE_FORTUNE    ("☘", ChatColor.GOLD),
  FORAGING_FORTUNE    ("☘", ChatColor.GOLD),
  FARMING_FORTUNE     ("☘", ChatColor.GOLD),
  ;

  public final String icon;
  public final ChatColor color;
}
