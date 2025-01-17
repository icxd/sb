package net.icxd.listeners;

import net.icxd.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    final Player player = event.getPlayer();
    event.setQuitMessage(null);

    new Thread(() -> {
      User user = User.get(player.getUniqueId());
      user.save();
    }).start();
  }
}
