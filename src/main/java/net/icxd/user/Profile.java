package net.icxd.user;

import java.util.UUID;

public class Profile {
  public static final String[] PROFILE_NAMES = new String[] {
      "Apple", "Peach", "Pineapple", "Zucchini", "Carrot",
      "Banana", "Grape", "Blueberry", "Cucumber", "Tomato",
  };
  public static final ProfileRepository REPOSITORY = new ProfileRepository();

  public final UUID uuid, owner;
  public final String name = PROFILE_NAMES[(int) (Math.random() * PROFILE_NAMES.length)];
  public final long created = System.currentTimeMillis();

  public double coins = 0.0;

  public Profile(UUID uuid, UUID owner) {
    this.uuid = uuid;
    this.owner = owner;
  }
}
