package net.icxd.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.SneakyThrows;
import org.bson.Document;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.UUID;

public abstract class AbstractRepository<T> {
  protected final MongoCollection<Document> collection;

  public AbstractRepository(final String collectionName) {
    MongoDatabase db = MongoDB.getDatabase();
    this.collection = db.getCollection(collectionName);
  }

  @SneakyThrows
  @SuppressWarnings("unchecked")
  public void load(T object) {
    Field uuidField = object.getClass().getDeclaredField("uuid");
    uuidField.setAccessible(true);

    final UUID uuid = (UUID) uuidField.get(object);
    System.out.println("Loading " + object.getClass().getSimpleName() + " with UUID " + uuid);

    Document document = collection.find(new Document("uuid", uuid.toString())).first();
    if (document == null) return;

    for (String key : document.keySet()) {
      if (key.equals("uuid") || key.equals("_id")) continue;

      Object value = document.get(key);
      Field field = object.getClass().getDeclaredField(key);
      field.setAccessible(true);
      if (field.getType().accessFlags().contains(AccessFlag.ENUM)) {
        Enum<?> enumValue = Enum.valueOf((Class<? extends Enum>) field.getType(), value.toString());
        field.set(object, enumValue);
      } else if (field.getType().getName().equals("java.util.UUID")) {
        field.set(object, UUID.fromString(value.toString()));
      } else {
        field.set(object, value);
      }

      System.out.println("Set " + key + " to " + value);
    }
  }

  @SneakyThrows
  public void save(T object) {
    Field uuidField = object.getClass().getDeclaredField("uuid");
    uuidField.setAccessible(true);

    final UUID uuid = (UUID) uuidField.get(object);
    Document document = new Document("uuid", uuid.toString());
    for (Field field : object.getClass().getDeclaredFields()) {
      if (field.accessFlags().contains(AccessFlag.STATIC)) continue;

      field.setAccessible(true);
      if (field.getType().accessFlags().contains(AccessFlag.ENUM)) {
        Enum<?> value = (Enum<?>) field.get(object);
        document.append(field.getName(), value.name());
      } else if (field.getType().getName().equals("java.util.UUID")) {
        document.append(field.getName(), field.get(object).toString());
      } else {
        document.append(field.getName(), field.get(object));
      }
    }

    if (this.find(uuid) == null) collection.insertOne(document);
    else collection.replaceOne(new Document("uuid", uuid.toString()), document);
  }

  @SneakyThrows
  @SuppressWarnings("unchecked")
  public T find(UUID uuid) {
    Document document = collection.find(new Document("uuid", uuid.toString())).first();
    if (document == null) return null;

    Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    T object = clazz.getConstructor(UUID.class).newInstance(uuid);

    this.load(object);
    return (T) object;
  }
}
