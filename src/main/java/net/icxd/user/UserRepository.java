package net.icxd.user;

import net.icxd.mongo.AbstractRepository;

public class UserRepository extends AbstractRepository<User> {
  public UserRepository() { super("users"); }
}
