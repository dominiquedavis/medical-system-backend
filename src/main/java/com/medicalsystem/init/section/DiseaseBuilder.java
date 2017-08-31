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

import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DiseaseBuilder implements SectionBuilder {

    private final FieldService fieldService;
    private final SectionService sectionService;

    @Override
    public Section build() {

        Section diseases = new Section("Diseases");

//        Map<String, String> values = MapUtils.of("0", "nie", "1", "tak", "bd", "bd");

        Field ht = new DoubleField("HT", 21, null);

        Field cad = new DoubleField("CAD", 22, null);

        Field cadHigh = new DoubleField("CAD wysokiego ryzyka", 23, null);

        Field mi = new DoubleField("MI/ACS przebyty", 24, null);

        Field stenosis = new DoubleField("Stenoza aortalna", 25, null);

        Field cve = new DoubleField("CVE przebyty", 26, null);

        Field chf = new DoubleField("CHF", 27, null);

        Field dm = new DoubleField("DM", 28, null);

        Field copd = new DoubleField("COPD", 29, null);


        diseases.addField(ht);
        diseases.addField(cad);
        diseases.addField(cadHigh);
        diseases.addField(mi);
        diseases.addField(stenosis);
        diseases.addField(cve);
        diseases.addField(chf);
        diseases.addField(dm);
        diseases.addField(copd);


        fieldService.saveOrUpdate(ht);
        fieldService.saveOrUpdate(cad);
        fieldService.saveOrUpdate(cadHigh);
        fieldService.saveOrUpdate(mi);
        fieldService.saveOrUpdate(stenosis);
        fieldService.saveOrUpdate(cve);
        fieldService.saveOrUpdate(chf);
        fieldService.saveOrUpdate(dm);
        fieldService.saveOrUpdate(copd);

        sectionService.saveOrUpdate(diseases);

        return diseases;
    }
}
