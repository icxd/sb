package net.icxd.command;

import net.icxd.Main;
import org.reflections.Reflections;

public class CommandMap {
  public static void register(Main plugin) {
    new Reflections("net.icxd.command.commands").getSubTypesOf(AbstractCommand.class).forEach(command -> {
      try {
        var instance = command.getDeclaredConstructor().newInstance();
        plugin.getCommand();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
