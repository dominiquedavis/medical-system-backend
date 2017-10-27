package com.medicalsystem.service;

import com.medicalsystem.model.Form;

public interface FormService extends CRUDService<Form, Long> {

    Form getByExcelSheetIndex(int excelSheetIndex);

}
