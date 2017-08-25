package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;

@FunctionalInterface
public interface SectionBuilder {

    Section build();

}
