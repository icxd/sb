package net.icxd.item;

import lombok.NonNull;
import org.bukkit.Material;

import java.util.List;

public interface IBasicItem {
  @NonNull
  String id();

  @NonNull
  String name();

  @NonNull
  Material material();

  @NonNull
  ItemRarity rarity();

  @NonNull
  ItemCategory category();

  default List<String> description() {return List.of();}

  default boolean stackable() {return true;}
}
