package com.medicalsystem.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medicalsystem.converter.templatetodto.FieldToDTOConverter;
import com.medicalsystem.domain.dto.FieldDTO;
import com.medicalsystem.domain.template.Field;
import com.medicalsystem.domain.template.Option;
import com.medicalsystem.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FieldSerializer extends JsonSerializer<Long> {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldToDTOConverter fieldConverter;

    public FieldSerializer() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void serialize(Long fieldId, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        Field field = fieldService.findByID(fieldId);

        if (field == null) {
            throw new RuntimeException("Field not found with ID: " + fieldId);
        }

        FieldDTO fieldDTO = fieldConverter.convert(field);

        jgen.writeStartObject();
        jgen.writeNumberField("id", field.getId());
        jgen.writeObjectField("name", field.getName());
        jgen.writeObjectField("type", field.getType().name());
        jgen.writeObjectField("values", new ArrayList<>());
        jgen.writeObjectField("possibleValues", field.getPossibleValues().stream()
                .map(Option::getValue)
                .collect(Collectors.toList()));
        jgen.writeEndObject();

        System.out.println("wykonało się do chuja");
    }
}
