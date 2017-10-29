package com.medicalsystem.service;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.json.JSONField;

public interface SectionService extends CRUDService<Section, Long> {
    void addField(long sectionId, JSONField jsonField);
}
