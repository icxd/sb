package net.icxd.listeners;

import net.icxd.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    event.setJoinMessage(null);

    new Thread(() -> {
      User user = User.get(player.getUniqueId());
      User.REPOSITORY.load(user);
    }).start();
  }
}
