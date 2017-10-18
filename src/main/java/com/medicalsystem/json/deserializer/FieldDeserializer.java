package com.medicalsystem.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.medicalsystem.model.field.Field;

import java.io.IOException;

public class FieldDeserializer extends StdDeserializer<Field<?>> {

    public FieldDeserializer() {
        this(null);
    }

    private FieldDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Field<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return null;
    }

}
