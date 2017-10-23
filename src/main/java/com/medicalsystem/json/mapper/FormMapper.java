package com.medicalsystem.json.mapper;

import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.model.Form;

public interface FormMapper {

    Form fromJSON(JSONForm jsonForm);

    JSONForm toJSON(Form form);

    JSONForm toJSON(int patientId);

}
