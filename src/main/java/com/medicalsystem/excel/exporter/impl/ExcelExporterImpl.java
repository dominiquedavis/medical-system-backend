package com.medicalsystem.excel.exporter.impl;

import com.medicalsystem.excel.exporter.ExcelExporter;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Patient;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelExporterImpl implements ExcelExporter {

    private final FormService formService;
    private final PatientService patientService;
    private final FieldValueService fieldValueService;

    @Override
    public void exportToFile(String excelFilePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        formService.getAll().stream()
                .sorted(Comparator.comparingInt(Form::getExcelSheetIndex))
                .forEach(form -> {
                    Sheet sheet = workbook.createSheet(form.getName());
                    exportForm(form, sheet);
                });

        saveWorkbook(workbook, excelFilePath);

        log.info("Exported completed: " + excelFilePath);
    }

    private void exportForm(Form form, Sheet sheet) {
        prepareHeaders(form, sheet);
        exportData(form, sheet);
    }

    private void prepareHeaders(Form form, Sheet sheet) {
        // TODO: Create first header row
        Row firstHeader = sheet.createRow(0);

        // Create second header row
        Row secondHeader = sheet.createRow(1);

        // Create ID column header
        Cell idHeader = secondHeader.createCell(0);
        idHeader.setCellValue("ID");

        form.getSections().stream()
                .flatMap(section -> section.getFields().stream())
                .forEach(field -> {
            int excelColumnIndex = field.getExcelColumnIndex();
            Cell cell = secondHeader.createCell(excelColumnIndex);
            cell.setCellValue(field.getName());
        });
    }

    private void exportData(Form form, Sheet sheet) {
        List<Patient> patients = patientService.getAllByForm(form);
        patients.sort(Comparator.comparingLong(Patient::getId));

        int rowIndex = 2;
        for (Patient patient : patients) {
            Row row = sheet.createRow(rowIndex++);
            exportPatient(row, patient);
        }
    }

    private void exportPatient(Row row, Patient patient) {
        row.createCell(0).setCellValue(patient.getId());
        fieldValueService.getAllByPatient(patient)
                .forEach(fieldValue -> fieldValue.createCell(row));
    }

    private void saveWorkbook(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream output = new FileOutputStream(excelFilePath)) {
            workbook.write(output);
        }
    }
}
