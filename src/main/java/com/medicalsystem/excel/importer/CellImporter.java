package com.medicalsystem.excel.importer;

import com.medicalsystem.exception.ExcelImportException;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Patient;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Map;

public interface CellImporter {

    void importToDatabase(Cell cell, Patient patient, Map<Integer, Field> fields) throws ExcelImportException;

}
