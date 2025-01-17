package net.icxd.user;

import net.icxd.mongo.AbstractRepository;

public class ProfileRepository extends AbstractRepository<Profile> {
  public ProfileRepository() { super("profiles"); }
}
