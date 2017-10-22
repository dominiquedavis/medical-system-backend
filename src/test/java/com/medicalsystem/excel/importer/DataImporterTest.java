package com.medicalsystem.excel.importer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataImporterTest {

    private final String filePath = "data/baza2.xlsx";

    @Autowired
    private DataImporter dataImporter;

    @Test
    public void shouldImportCorretly() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(filePath);
        dataImporter.importToDatabase(inputStream);
    }

}
