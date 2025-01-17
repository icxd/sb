package net.icxd.user;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class User {
  public static final Map<UUID, User> USERS = new HashMap<>();
  public static final UserRepository REPOSITORY = new UserRepository();

  public final UUID uuid;
  public final long firstJoin = System.currentTimeMillis();
  public final long lastJoin = System.currentTimeMillis();

  public Rank rank = Rank.DEFAULT;
  public double coins = 0.0;

  public User(UUID uuid) {this.uuid = uuid;}

  public static User get(UUID uuid) {return USERS.computeIfAbsent(uuid, User::new);}

  public void save() {REPOSITORY.save(this);}

  public void load() {REPOSITORY.load(this);}
}
