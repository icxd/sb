package net.icxd;

import net.icxd.command.CommandLoader;
import net.icxd.listeners.PlayerChatListener;
import net.icxd.listeners.PlayerJoinListener;
import net.icxd.listeners.PlayerLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static Main INSTANCE;

  @Override
  public void onEnable() {
    INSTANCE = this;

    CommandLoader.register(this);

    getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
    getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
  }

  @Override
  public void onDisable() {
  }
}