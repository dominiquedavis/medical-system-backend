package com.medicalsystem.excel.importer.impl;

import com.medicalsystem.excel.importer.CellImporter;
import com.medicalsystem.excel.importer.RowImporter;
import com.medicalsystem.exception.EntityExistsException;
import com.medicalsystem.exception.ExcelImportException;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Patient;
import com.medicalsystem.service.PatientService;
import com.medicalsystem.util.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class RowImporterImpl implements RowImporter {

    private final PatientService patientService;
    private final CellImporter cellImporter;

    @Override
    public void importToDatabase(Row row, int maxNumberOfCells, Form form, Map<Integer, Field> fields) throws ExcelImportException {
        if (maxNumberOfCells == 0) {
            log.warning("Row empty");
            return;
        }

        Iterator<Cell> cellIterator = row.iterator();

        Patient patient;
        try {
            patient = createPatient(cellIterator.next(), form);
        } catch (NumberFormatException e) {
            log.severe("Patient ID is not an integer in a row: " + row.getRowNum() + " - patient not saved");
            return;
        } catch (EntityExistsException e) {
            log.severe(e.getMessage());
            return;
        }

        for (int i = 1; i < maxNumberOfCells; i++) {
            Cell cell = (row.getCell(i) == null) ? row.createCell(i) : row.getCell(i);
            cellImporter.importToDatabase(cell, patient, fields);
        }
    }

    private Patient createPatient(Cell idCell, Form form) throws NumberFormatException, EntityExistsException {
        String stringValue = ExcelUtils.getValueAsString(idCell);
        long patientId = Long.parseLong(stringValue);

        if (patientService.existsById(patientId)) {
            throw new EntityExistsException("Patient exists with ID: " + patientId);
        } else {
            Patient patient = new Patient();
            patient.setId(patientId);
            patient.setForm(form);
            return patientService.save(patient);
        }
    }
}
