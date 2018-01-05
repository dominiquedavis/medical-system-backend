package com.medicalsystem.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medicalsystem.converter.templatetodto.FieldToDTOConverter;
import com.medicalsystem.domain.dto.FieldDTO;
import com.medicalsystem.domain.template.Field;
import com.medicalsystem.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

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

        jgen.writeObjectField("formField", fieldDTO);
    }
}
