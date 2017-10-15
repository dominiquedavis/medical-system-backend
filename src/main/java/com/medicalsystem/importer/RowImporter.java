package com.medicalsystem.importer;

import com.medicalsystem.factory.FieldValueFactory;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.util.CellUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class RowImporter {

    private final FieldService fieldService;
    private final FieldValueService fieldValueService;

    public void importRow(Row row) {
        Iterator<Cell> iterator = row.cellIterator();

        // Get patient ID from the first cell
        int patientId = getPatientId(iterator.next());
        if (patientId == -1) {
            log.severe(String.format("Patient ID is not an integer in row: '%d' - patient not saved", row.getRowNum()));
            return;
        }

        // Process the rest of the cells
        iterator.forEachRemaining(cell -> {
            // TODO: Optimize with prequeried map: index -> field object
            int excelColumn = cell.getColumnIndex();
            Field<?> field = fieldService.findByExcelColumn(excelColumn);

            if (field == null) {
                log.severe("Field not found with column index: " + excelColumn);
                return;
            }

            FieldValue<?> fieldValue = FieldValueFactory.createFieldValue(field, cell, patientId);

            // Persist created field value
            fieldValueService.saveOrUpdate(fieldValue);

            log.info(String.format("Field value created for field '%s' and patient id '%d'", field.getName(), patientId));
        });
    }

    private int getPatientId(Cell idCell) {
        String patientIdStr = CellUtils.getStringValue(idCell);
        int patientId;
        try {
            patientId = Integer.parseInt(patientIdStr);
        } catch (NumberFormatException e) {
            patientId = -1;
        }
        return patientId;
    }

}
