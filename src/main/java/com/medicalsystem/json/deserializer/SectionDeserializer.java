package com.medicalsystem.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.medicalsystem.model.Section;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SectionDeserializer extends StdDeserializer<Section> {

    public SectionDeserializer() {
        this(null);
    }

    private SectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Section deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Section section = new Section();

        // Set name
        String name = node.get("name").asText();
        section.setName(name);

        return section;
    }

}
