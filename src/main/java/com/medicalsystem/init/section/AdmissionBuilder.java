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
import java.util.List;
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

        Field maxAneurysmSize = new DoubleField("Maks. Wymiary tętniaka tt. biodrowych [mm]", 13, null);

        Field imageExamination = new DoubleField("Badanie obrazowe", 14, null);

        Field aneurysmLocation = new DoubleField("Lokalizacja tętniaka", 15, null);

        Field pad = new DoubleField("PAD", 16, null);

        Map<Double, String> smokingValues = MapUtils.ofDoubles(Arrays.asList(0.0, 1.0, 2.0), Arrays.asList("nie palący",
                "palący", "palący w przeszłości"));
        Field smoking = new DoubleField("Palenie tytoniu", 17, smokingValues);

        Field asaScale = new DoubleField("Skala ASA", 18, null);

        Field faint = new DoubleField("Utrata przytomności", 19, commonValues);

        Field shock = new DoubleField("Wstrząs", 20, smokingValues);


        List<Field> fields = Arrays.asList(admissionDate, operationDate, operationTypes, anesthesia, anesthetic, operationMode,
                aaSymptoms, aaSize, maxAneurysmSize, imageExamination, aneurysmLocation, pad, smoking, asaScale, faint, shock);

        fields.forEach(field -> {
            admission.addField(field);
            fieldService.saveOrUpdate(field);
        });

        sectionService.saveOrUpdate(admission);

        return admission;
    }
}
