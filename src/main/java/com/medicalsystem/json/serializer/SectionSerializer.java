package com.medicalsystem.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.medicalsystem.model.Section;

import java.io.IOException;

public class SectionSerializer extends StdSerializer<Section> {

    public SectionSerializer() {
        this(null);
    }

    private SectionSerializer(Class<Section> t) {
        super(t);
    }

    @Override
    public void serialize(Section section, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", section.getId());
        jgen.writeStringField("name", section.getName());
        jgen.writeObjectField("fields", section.getFields());
        jgen.writeEndObject();
    }

}
