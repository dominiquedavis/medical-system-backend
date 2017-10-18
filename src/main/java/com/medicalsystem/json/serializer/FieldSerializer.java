package com.medicalsystem.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.medicalsystem.model.field.Field;

import java.io.IOException;

public class FieldSerializer extends StdSerializer<Field<?>> {

    public FieldSerializer() {
        this(null);
    }

    private FieldSerializer(Class<Field<?>> t) {
        super(t);
    }

    @Override
    public void serialize(Field<?> field, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", field.getId());
        jgen.writeStringField("name", field.getName());
        jgen.writeStringField("type", field.getType());
        jgen.writeObjectField("values", null);
        jgen.writeObjectField("possibleValues", null);
        jgen.writeEndObject();
    }

}
