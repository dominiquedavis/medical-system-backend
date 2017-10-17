package com.medicalsystem.importer;

import com.medicalsystem.model.FormType;
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

    private final CellImporter cellImporter;

    /**
     * Processes a single row and imports its content to the database
     *
     * @param row an excel row
     */
    public void importRow(Row row, FormType formType) {
        Iterator<Cell> iterator = row.cellIterator();

        // Retrieve patient ID from the first cell
        String patientIdStr = CellUtils.getStringValue(iterator.next());
        int patientId;
        try {
            patientId = Integer.parseInt(patientIdStr);
        } catch (NumberFormatException e) {
            log.warning(String.format("Patient ID is not an integer in row: '%d', value: '%s' - PATIENT NOT SAVED", row.getRowNum(), patientIdStr));
            return;
        }

        // Process the rest of the cells
        iterator.forEachRemaining(cell -> cellImporter.importCell(cell, patientId, formType));
    }

}
