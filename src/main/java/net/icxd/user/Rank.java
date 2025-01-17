package net.icxd.user;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum Rank {
  DEFAULT(ChatColor.GRAY),
  ADMIN(ChatColor.RED);

  private final ChatColor color;

  Rank(ChatColor color) {this.color = color;}
}
