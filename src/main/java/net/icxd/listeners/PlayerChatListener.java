package net.icxd.listeners;

import net.icxd.user.Rank;
import net.icxd.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    final Player player = event.getPlayer();
    final User user = User.get(player.getUniqueId());
    final Rank rank = user.getRank();

    event.setCancelled(true);

    for (Player online : event.getRecipients()) {
      online.sendMessage(
          rank.getColor() + (rank != Rank.DEFAULT ? "[" + rank.name() + "] " : "") +
          player.getName() + (rank == Rank.DEFAULT ? ChatColor.GRAY : ChatColor.WHITE) +
          ": " + event.getMessage()
      );
    }
  }
}
