package net.icxd.user;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class User {
  public static final Map<UUID, User> USERS = new HashMap<>();
  public static final UserRepository REPOSITORY = new UserRepository();

  public final UUID uuid;
  public final Optional<Profile>[] profiles = new Optional[5];
  public final long firstJoin = System.currentTimeMillis();
  public final long lastJoin = System.currentTimeMillis();

  public Rank rank = Rank.DEFAULT;
  public UUID selectedProfile = null;

  public User(UUID uuid) {
    this.uuid = uuid;

    for (int i = 0; i < this.profiles.length; i++)
      this.profiles[i] = Optional.empty();
  }

  public static User get(UUID uuid) {
    return USERS.computeIfAbsent(uuid, User::new);
  }

  public void save() {
    for (Optional<Profile> profile : this.profiles) {
      if (profile.isEmpty()) continue;
      Profile.REPOSITORY.save(profile.get());
    }
    REPOSITORY.save(this);
  }

  public void load() {
    REPOSITORY.load(this);
    for (Optional<Profile> profile : this.profiles) {
      if (profile.isEmpty()) continue;
      Profile.REPOSITORY.load(profile.get());
    }
  }
}
