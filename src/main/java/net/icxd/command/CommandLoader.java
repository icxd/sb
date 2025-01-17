package net.icxd.command;

import lombok.NonNull;
import net.icxd.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommandLoader {
  public static void register(Main plugin) {
    CommandMap commandMap;

    try {
      Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
      f.setAccessible(true);
      commandMap = (org.bukkit.command.CommandMap) f.get(Bukkit.getServer());
    } catch (IllegalAccessException | NoSuchFieldException e) {
      Bukkit.getLogger().severe("Failed to get command map");
      return;
    }

    Reflections reflections = new Reflections("net.icxd.command.commands");
    reflections.getSubTypesOf(AbstractCommand.class).forEach(command -> {
      if (!command.isAnnotationPresent(CommandInfo.class)) {
        Bukkit.getLogger().severe("Command " + command.getSimpleName() + " is missing CommandInfo annotation");
        return;
      }

      AbstractCommand instance;
      try {
        instance = command.getDeclaredConstructor().newInstance();
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        throw new RuntimeException(e);
      }

      final CommandInfo info = command.getAnnotation(CommandInfo.class);
      Command cmd = getCommand(info, instance);

      commandMap.register(plugin.getName(), cmd);
    });
  }

  private static Command getCommand(CommandInfo info, AbstractCommand instance) {
    Command cmd = new Command(info.name()) {
      @Override
      public boolean execute(@NonNull CommandSender sender, @NonNull String label, @NonNull String[] args) {
        if (!info.supportsConsole() && !(sender instanceof Player)) {
          sender.sendMessage("§cThis command can only be executed by players.");
          return true;
        }

        if (args.length < info.minArgs()) {
          sender.sendMessage("§cUsage: " + info.usage());
          return true;
        }

        AbstractCommand.CommandContext context = new AbstractCommand.CommandContext(sender, args);
        instance.execute(context);
        return true;
      }
    };
    cmd.setDescription(info.description());
    cmd.setUsage(info.usage());
    cmd.setPermission(info.rank().name());
    cmd.setAliases(List.of(info.aliases().split(",")));
    return cmd;
  }
}
