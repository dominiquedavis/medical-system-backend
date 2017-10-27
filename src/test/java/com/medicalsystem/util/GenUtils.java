package com.medicalsystem.util;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;

import java.util.*;

public final class GenUtils {

    private GenUtils() {}

    public static Field getRandomField() {
        Field field = new Field();
        field.setName(UUID.randomUUID().toString());
        field.setType(getRandomFieldType());
        field.setSection(null);
        return field;
    }

    public static FieldType getRandomFieldType() {
        List<FieldType> fieldTypes = Arrays.asList(FieldType.values());
        Collections.shuffle(fieldTypes);
        return fieldTypes.get(0);
    }

    public static List<Form> createFormStructure(int formsToCreate, int sectionsToCreate, int fieldsToCreate) {
        List<Form> forms = new ArrayList<>();

        for (int f = 0; f < formsToCreate; f++) {
            Form form = new Form();
            form.setName("Form " + f);

            for (int s = 0; s < sectionsToCreate; s++) {
                Section section = new Section();
                section.setName("Section " + (s + 1));
                //section.setForm(form); // not needed to work properly

                for (int i = 0; i < fieldsToCreate; i++) {
                    Field field = new Field();
                    field.setName("Field " + (s + 1));
                    field.setType(GenUtils.getRandomFieldType());
                    //field.setSection(section); // not needed to work properly

                    field.setColumnIndex(i);

                    if (field.getType() == FieldType.SELECT || field.getType() == FieldType.MULTIPLE_SELECT) {
                        Map<String, String> possibleValues = new HashMap<>();
                        possibleValues.put("key1_" + i, "value1_" + i);
                        possibleValues.put("key2_" + i, "value2_" + i);
                        possibleValues.put("key3_" + i, "value3_" + i);
                        field.setPossibleValues(possibleValues);
                    }

                    section.getFields().add(field);
                }

                form.getSections().add(section);
            }

            forms.add(form);
        }

        return forms;
    }

}
