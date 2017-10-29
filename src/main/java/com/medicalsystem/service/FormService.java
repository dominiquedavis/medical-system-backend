package com.medicalsystem.service;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.json.JSONForm;
import com.medicalsystem.model.json.JSONSection;

import java.util.List;

public interface FormService extends CRUDService<Form, Long> {
    Form getByExcelSheetIndex(int excelSheetIndex);
    List<JSONForm> getAllAsJSON();
    JSONForm getJSONFormByPatientId(long patientId);
    void addSection(long formId, JSONSection jsonSection);
}
