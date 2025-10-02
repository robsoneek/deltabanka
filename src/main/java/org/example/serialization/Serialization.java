package org.example.serialization;

public interface Serialization {
    public String serialize(Object object);
    public Object deserialize(String serializedData);
}
