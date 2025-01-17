package net.icxd.item.items;

import lombok.NonNull;
import net.icxd.item.IBasicItem;
import net.icxd.item.IDungeonItem;
import net.icxd.item.ItemCategory;
import net.icxd.item.ItemRarity;
import org.bukkit.Material;

import java.util.List;

public class TestItem implements IBasicItem, IDungeonItem {
  @Override
  public @NonNull String id() {return "TEST_ITEM";}

  @Override
  public @NonNull String name() {return "Test Item";}

  @Override
  public @NonNull Material material() {return Material.BLAZE_ROD;}

  @Override
  public @NonNull ItemRarity rarity() {return ItemRarity.ADMIN;}

  @Override
  public @NonNull ItemCategory category() {return ItemCategory.SWORD;}

  @Override
  public List<String> description() {
    return List.of("This is a test item.", "It is used for testing purposes.");
  }

  @Override
  public boolean stackable() {return false;}
}
