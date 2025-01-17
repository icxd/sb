package net.icxd.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
  private static final String CONNECTION_STRING = "mongodb://localhost:27017";
  private static final String DATABASE = "sb";
  private static MongoDatabase database;

  public static MongoDatabase getDatabase() {
    if (database == null)
      database = new MongoClient(new MongoClientURI(CONNECTION_STRING)).getDatabase(DATABASE);
    return database;
  }
}
