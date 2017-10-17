package com.medicalsystem.importer;

import com.medicalsystem.factory.FieldValueFactory;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class CellImporter {

    private final FieldService fieldService;
    private final FieldValueService fieldValueService;

    public void importCell(Cell cell, int patientId, FormType formType) {
        // TODO: Optimize with prequeried map: index -> field object
        int excelColumn = cell.getColumnIndex();
        Field<?> field = fieldService.findByExcelColumn(excelColumn, formType);

        if (field == null) {
            log.severe(String.format("Field not found with column index '%d' and form type '%s'", excelColumn, formType));
            return;
        }

        FieldValue<?> fieldValue = FieldValueFactory.createFieldValue(field, cell, patientId);

        // Persist created field value
        fieldValueService.saveOrUpdate(fieldValue);

        log.info(String.format("Value created for field: '%s'\tpatient id: '%d'\tform type: '%s' - %s",
                field.getName(), patientId, formType, fieldValue.getValue()));
    }

}
