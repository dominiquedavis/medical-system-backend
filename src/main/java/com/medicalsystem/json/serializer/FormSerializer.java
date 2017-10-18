package com.medicalsystem.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.medicalsystem.model.Form;

import java.io.IOException;

public class FormSerializer extends StdSerializer<Form> {

    public FormSerializer() {
        this(null);
    }

    private FormSerializer(Class<Form> t) {
        super(t);
    }

    @Override
    public void serialize(Form form, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", form.getId());
        jgen.writeStringField("type", form.getType().name());
        jgen.writeObjectField("sections", form.getSections());
        jgen.writeEndObject();
    }

}
