package com.medicalsystem.factory;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.properties.StringProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Supplier;

@Component
public class FieldFactory {

    private static StringProperties props;

    @Autowired
    public FieldFactory(StringProperties props) {
        FieldFactory.props = props;
    }

    public static <T extends Field> Field<T> create(Supplier<T> supplier, String nameProperty,
                                                       String excelColumnProperty, Map<T, String> options) {
        String name = props.get(nameProperty);
        int excelColumn = props.getAsInt(excelColumnProperty);
        T field = supplier.get();

        field.setName(name);
        field.setExcelColumn(excelColumn);
        if (options != null)
            field.setOptions(options);

        return field;
    }

}
