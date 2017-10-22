package com.medicalsystem.excel.exporter;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.*;
import com.medicalsystem.properties.ConfigProperties;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
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
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelDataExporter implements DataExporter {

    private final ConfigProperties props;
    private final FieldService fieldService;
    private final PatientService patientService;
    private final FieldValueService fieldValueService;

    @Override
    public void exportToFile(String excelFilePath) {
        Workbook workbook = new XSSFWorkbook();

        Sheet openSheet = workbook.createSheet(props.getOpenName());
        Sheet evarSheet = workbook.createSheet(props.getEvarName());

        prepareHeaders(openSheet, evarSheet);
        exportData(openSheet, evarSheet);

        saveWorkbook(workbook, excelFilePath);
    }

    private void prepareHeaders(Sheet openSheet, Sheet evarSheet) {
        prepareHeaders(openSheet, FormType.OPEN);
        prepareHeaders(evarSheet, FormType.EVAR);
    }

    private void prepareHeaders(Sheet sheet, FormType formType) {
        // TODO: Create first header row
        Row firstHeader;

        // Create second header row
        Row secondHeader = sheet.createRow(1);

        // Create ID header
        Cell idHeader = secondHeader.createCell(0);
        idHeader.setCellValue("ID");

        for (Field<?> field : fieldService.findAllByFormType(formType)) {
            int columnIndex = field.getExcelColumnByType(formType);
            Cell cell = secondHeader.createCell(columnIndex);
            cell.setCellValue(field.getName());
        }
    }

    private void exportData(Sheet openSheet, Sheet evarSheet) {
        exportData(openSheet, FormType.OPEN);
        exportData(evarSheet, FormType.EVAR);
    }

    private void exportData(Sheet sheet, FormType formType) {
        int i = 2;
        for (Patient patient : patientService.findAllByFormType(formType)) {
            Row row = sheet.createRow(i++);
            exportPatient(row, patient, formType);
        }
    }

    private void exportPatient(Row row, Patient patient, FormType formType) {
        List<FieldValue<?>> fieldValues = fieldValueService.findAllByPatientId(patient.getId());

        for (FieldValue<?> fieldValue : fieldValues) {
            fieldValue.createValueCell(row, formType);
        }
    }

    private void saveWorkbook(Workbook workbook, String excelFilePath) {
        try (OutputStream output = new FileOutputStream(excelFilePath)) {
            workbook.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
