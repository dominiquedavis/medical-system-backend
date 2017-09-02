package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.DoubleField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.TextField;
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

//        Map<String, String> values = MapUtils.ofStrings("0", "nie", "1", "tak", "bd", "bd");

        Field ht = new DoubleField("HT", 21, null);

        Field cad = new DoubleField("CAD", 22, null);

        Field cadHigh = new DoubleField("CAD wysokiego ryzyka", 23, null);

        Field mi = new DoubleField("MI/ACS przebyty", 24, null);

        Field stenosis = new DoubleField("Stenoza aortalna", 25, null);

        Field cve = new DoubleField("CVE przebyty", 26, null);

        Field chf = new DoubleField("CHF", 27, null);

        Field dm = new DoubleField("DM", 28, null);

        Field copd = new DoubleField("COPD", 29, null);


        List<Field> fields = Arrays.asList(ht, cad, cadHigh, mi, stenosis, cve, chf, dm, copd);

        fields.forEach(field -> {
            diseases.addField(field);
            fieldService.saveOrUpdate(field);
        });

        sectionService.saveOrUpdate(diseases);

        return diseases;
    }
}
