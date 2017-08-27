package com.medicalsystem.init.form;

import com.medicalsystem.init.section.SectionBuilder;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OpenFormBuilder implements FormBuilder {

    private final List<? extends SectionBuilder> sectionBuilders;

    private final FormService formService;

    @Override
    public Form build() {
        Form open = new Form("OPEN");

        sectionBuilders.forEach(builder -> {
            Section section = builder.build();
            section.addForm(open);
            open.addSection(section);
        });

        formService.saveOrUpdate(open);

        return open;
    }
}
