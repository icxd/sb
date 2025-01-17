package net.icxd.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand {
  public abstract void execute(CommandContext context);

  public record CommandContext(CommandSender sender, String[] args) {
    public boolean isPlayer() {return sender instanceof Player;}
    public Player player() {return (Player) sender;}
    public String arg(int index) {return args[index];}
  }
}
