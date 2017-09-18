package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;
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
public class ExaminationBuilder implements SectionBuilder {

    private final FieldService fieldService;
    private final SectionService sectionService;

    @Override
    public Section build() {
        // Examination section
        Section examination = new Section("Wyniki badań przy przyjęciu do szpitala");

        // 30: EKG
        Map<Integer, String> ekgValues = MapUtils.of(Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList("rytm zatokowy", "AF", "rytm zatokowy plus obecność VE", "AF plus obecność VE",
                        "rytm z rozrusznika", "AF plus rytm z rozrusznika"));
        Field ekg = new IntegerField("EKG przyjeciowe", 30, ekgValues);

        // 31: Lee RCRI
        Field leeRcri = new IntegerField("Wskaźnik Lee RCRI pkt.", 31, null);

        // 32: P-possum
        Field ppossum = new DoubleField("P-POSSUM %", 32, null);

        // 33: PChN
        Field pchn = new IntegerField("PChN w stadium 5 (dializoterapia)", 33, null);

        // 34: Creatinine
        Field creatinine = new DoubleField("Kreatynina [umol/l]", 34, null);

        // 35: eGFR
        Field egfr = new DoubleField("eGFR (MDRD)", 35, null);

        // 36: Hb
        Field hb = new DoubleField("Hb [g/dl]", 36, null);

        // 37: WBC
        Field wbc = new DoubleField("WBC [tys/ul]", 37, null);

        // 38: fibrinogen
        Field fibrinogen = new DoubleField("fibrynogen [g/l]", 38, null);


        List<Field> fields = Arrays.asList(ekg, leeRcri, ppossum, pchn, creatinine, egfr, hb, wbc, fibrinogen);

        fields.forEach(field -> {
            examination.addField(field);
            fieldService.saveOrUpdate(field);
        });

        sectionService.saveOrUpdate(examination);

        return examination;
    }

}
