package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.DateField;
import com.medicalsystem.model.field.DoubleField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.IntegerField;
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

        Map<Integer, String> commonValues = MapUtils.of(Arrays.asList(0, 1, 2),
                Arrays.asList("nie", "tak", "brak danych"));

        // 5: Admission date
        Field admissionDate = new DateField("Data przyjęcia", 5, null);

        // 6: Operation date
        Field operationDate = new DateField("Data Zabiegu", 6, null);

        // 7: Operation type
        Map<Integer, String> operationTypesValues = MapUtils.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(
                "Otwarta operacja wycięcia tętniaka aorty brzusznej","Endowaskularne zaopatrzenie tętniaka aorty (EVAR)",
                "Przęsło aortalno dwuudowe (ABF)","Przęsło aortalno-udowe","Przęsło aortalno-biodrowe",
                "Przęsło aortalno-dwubiodrowe","Plastyka aorty"));
        Field operationTypes = new IntegerField("Rodzaj zabiegu", 7, operationTypesValues);

        // 8: Anesthesia
        Map<Integer, String> anesthesiaValues = MapUtils.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList("Ogólne",
                "Miejscowe", "Podpajęczynówkowe", "Ogólne plus zewnątrzoponowe", "Ogólne plus podpajęczynówkowe"));
        Field anesthesia = new IntegerField("Znieczulenie", 8, anesthesiaValues);

        // 9: Anesthestic
        Map<Integer, String> anesthesicValues = MapUtils.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList("Propofol",
                "Etomidat", "Tiopental", "Ketamina", "brak danych/nic"));
        Field anesthetic = new IntegerField("Lek znieczulający", 9, anesthesicValues);

        // 10: Operation mode
        Map<Integer, String> operationModeValues = MapUtils.of(Arrays.asList(1, 2), Arrays.asList("Planowy",
                "Pilny/Naglący"));
        Field operationMode = new IntegerField("Tryb zabiegu", 10, operationModeValues);

        // 11: AA symptoms
        Field aaSymptoms = new IntegerField("AA-objawy", 11, commonValues);

        // 12: AA size
        Field aaSize = new DoubleField("Wymiary AA [mm]", 12, null);

        // 13: Max aneurysm size
        Field maxAneurysmSize = new DoubleField("Maks. Wymiary tętniaka tt. biodrowych [mm]", 13, null);

        // 14: Image examination
        Map<Integer, String> imageExaminationValues = MapUtils.of(Arrays.asList(1, 2, 3), Arrays.asList("angio-TK",
                "USG", "z protokołu operacyjnego"));
        Field imageExamination = new IntegerField("Badanie obrazowe", 14, imageExaminationValues);

        // 15: Aneurysm location
        Map<Integer, String> aneurysmLocationValues = MapUtils.of(Arrays.asList(1, 2, 3, 4), Arrays.asList("Suprarenal",
                "Pararenal", "Juxtarenal", "Infrarenal"));
        Field aneurysmLocation = new IntegerField("Lokalizacja tętniaka", 15, aneurysmLocationValues);

        // 16: PAD
        Field pad = new IntegerField("PAD", 16, commonValues);

        // 17: Smoking
        Map<Integer, String> smokingValues = MapUtils.of(Arrays.asList(0, 1, 2), Arrays.asList("nie palący",
                "palący", "palący w przeszłości"));
        Field smoking = new IntegerField("Palenie tytoniu", 17, smokingValues);

        // 18: ASA Scale
        Map<Integer, String> asaValues = MapUtils.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList("ASA 1",
                "ASA 2", "ASA 3", "ASA 4", "ASA 5"));
        Field asaScale = new IntegerField("Skala ASA", 18, asaValues);


        List<Field> fields = Arrays.asList(admissionDate, operationDate, operationTypes, anesthesia, anesthetic, operationMode,
                aaSymptoms, aaSize, maxAneurysmSize, imageExamination, aneurysmLocation, pad, smoking, asaScale);

        fields.forEach(field -> {
            admission.addField(field);
            fieldService.saveOrUpdate(field);
        });

        sectionService.saveOrUpdate(admission);

        return admission;
    }
}
