package com.medicalsystem.json.mapper;

import com.medicalsystem.json.model.JSONSection;
import com.medicalsystem.model.Section;

public interface SectionMapper {

    Section fromJSON(JSONSection jsonSection);

    JSONSection toJSON(Section section);

    JSONSection toJSON(Section section, int patientId);

}
