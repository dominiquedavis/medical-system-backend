package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;
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
public class DiseaseBuilder implements SectionBuilder {

    private final FieldService fieldService;
    private final SectionService sectionService;

    @Override
    public Section build() {

        Section diseases = new Section("Diseases");

        Map<Integer, String> yesOrNoValues = MapUtils.of(Arrays.asList(0, 1),
                Arrays.asList("nie", "tak"));

        // 19: Faint
        Field faint = new IntegerField("Utrata przytomności", 19, yesOrNoValues);

        // 20: Shock
        Field shock = new IntegerField("Wstrząs", 20, yesOrNoValues);

        // 21: HT
        Field ht = new IntegerField("HT", 21, yesOrNoValues);

        // 22: CAD
        Field cad = new IntegerField("CAD", 22, yesOrNoValues);

        // 23: High CAD
        Field cadHigh = new IntegerField("CAD wysokiego ryzyka", 23, yesOrNoValues);

        // 24: MI
        Field mi = new IntegerField("MI/ACS przebyty", 24, yesOrNoValues);

        // 25: Stenosis
        Map<Integer, String> stenosisValues = MapUtils.of(Arrays.asList(0, 1, 2, 3, 4),
                Arrays.asList("brak", "łagodna", "umiarkowana", "ciężka", "sztuczna zastawka"));
        Field stenosis = new IntegerField("Stenoza aortalna", 25, stenosisValues);

        // 26: CVE
        Field cve = new IntegerField("CVE przebyty", 26, yesOrNoValues);

        // 27: CHF
        Field chf = new IntegerField("CHF", 27, yesOrNoValues);

        // 28: DM
        Map<Integer, String> dmValues = MapUtils.of(Arrays.asList(0, 1, 2),
                Arrays.asList("nie", "tak", "w trakcie insulinoterapii"));
        Field dm = new IntegerField("DM", 28, dmValues);

        // 29: COPD
        Field copd = new IntegerField("COPD", 29, yesOrNoValues);


        List<Field> fields = Arrays.asList(faint, shock, ht, cad, cadHigh, mi, stenosis, cve, chf, dm, copd);

        fields.forEach(field -> {
            diseases.addField(field);
            fieldService.saveOrUpdate(field);
        });

        sectionService.saveOrUpdate(diseases);

        return diseases;
    }
}
