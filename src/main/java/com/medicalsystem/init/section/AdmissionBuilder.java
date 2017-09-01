package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.DateField;
import com.medicalsystem.model.field.DoubleField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.SectionService;
import com.medicalsystem.util.MapUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;


@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdmissionBuilder implements SectionBuilder {

    private final FieldService fieldService;
    private final SectionService sectionService;

    @Override
    public Section build() {

        Section admission = new Section("Parametry przyjęciowe");

        Map<Double, String> commonValues = MapUtils.ofDoubles(Arrays.asList(0.0, 1.0, 2.0),
                Arrays.asList("nie", "tak", "brak danych"));

        Field admissionDate = new DateField("Data przyjęcia", 5, null);

        Field operationDate = new DateField("Data Zabiegu", 6, null);

        //TO-DO
        Field operationTypes = new DoubleField("Rodzaj zabiegu", 7, null);


        Map<Double, String> anesthesiaValues = MapUtils.ofDoubles(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0), Arrays.asList("ogólne",
                "miejscowe", "podpajęczynówkowe", "ogólne plus zewnątrzoponowe", "ogólne plus podpajęczynówkowe"));
        Field anesthesia = new DoubleField("Znieczulenie", 8, anesthesiaValues);


        Map<Double, String> anesthesicValues = MapUtils.ofDoubles(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0), Arrays.asList("propofol",
                "etomidat", "tiopental", "ketamina", "brak danych/nic"));
        Field anesthetic = new DoubleField("Lek znieczulający", 9, anesthesicValues);


        Map<Double, String> operationModeValues = MapUtils.ofDoubles(Arrays.asList(1.0, 2.0), Arrays.asList("planowy",
                "pilny/naglący"));
        Field operationMode = new DoubleField("Tryb zabiegu", 10, operationModeValues);

        Field aaSymptoms = new DoubleField("AA-objawy", 11, commonValues);

        Field aaSize = new DoubleField("Wymiary AA [mm]", 12, null);



        admission.addField(admissionDate);
        admission.addField(operationDate);
        admission.addField(operationTypes);
        admission.addField(anesthesia);
        admission.addField(anesthetic);
        admission.addField(operationMode);
        admission.addField(aaSymptoms);
        admission.addField(aaSize);


        fieldService.saveOrUpdate(admissionDate);
        fieldService.saveOrUpdate(operationDate);
        fieldService.saveOrUpdate(operationTypes);
        fieldService.saveOrUpdate(anesthesia);
        fieldService.saveOrUpdate(anesthetic);
        fieldService.saveOrUpdate(operationMode);
        fieldService.saveOrUpdate(aaSymptoms);
        fieldService.saveOrUpdate(aaSize);

        sectionService.saveOrUpdate(admission);

        return admission;
    }
}
