package net.icxd.command.commands;

import net.icxd.command.AbstractCommand;
import net.icxd.command.CommandInfo;
import net.icxd.item.IBasicItem;
import net.icxd.item.ItemBuilder;
import net.icxd.item.ItemRegistry;
import net.icxd.user.Rank;
import org.bukkit.entity.Player;

@CommandInfo(name = "item", minArgs = 1, rank = Rank.ADMIN, usage = "/item <ITEM_ID>")
public class ItemCommand extends AbstractCommand {
  @Override
  public void execute(CommandContext context) {
    final Player player = context.player();
    final String itemId = context.arg(0);

    final IBasicItem item = ItemRegistry.ITEMS.get(itemId);
    if (item == null) {
      player.sendMessage("§cItem not found.");
      return;
    }

    player.getInventory().addItem(ItemBuilder.build(item));
    player.sendMessage("§aItem given.");
  }
}
