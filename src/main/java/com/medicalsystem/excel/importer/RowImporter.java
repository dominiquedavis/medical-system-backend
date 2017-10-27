package com.medicalsystem.excel.importer;

import com.medicalsystem.exception.ExcelImportException;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import org.apache.poi.ss.usermodel.Row;

import java.util.Map;

public interface RowImporter {

    void importToDatabase(Row row, int maxNumberOfCells, Form form, Map<Integer, Field> fields) throws ExcelImportException;

}
