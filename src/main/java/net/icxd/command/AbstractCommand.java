package net.icxd.command;

import com.mojang.brigadier.context.CommandContext;
import org.bukkit.command.CommandSender;

public interface ICommand {
  void execute(CommandContext context);

  public static record CommandContext(CommandSender sender, String[] args) {
  }
}
