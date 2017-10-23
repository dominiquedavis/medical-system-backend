package com.medicalsystem.service;

import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.model.Form;

public interface FormService extends CRUDService<Form, Integer> {

    Form findByPatientId(int patientId);

    JSONForm getForm(int patientId);

    JSONForm updateForm(int patientId, JSONForm jsonForm);

}
