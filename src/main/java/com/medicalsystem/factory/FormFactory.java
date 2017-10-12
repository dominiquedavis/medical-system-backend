package com.medicalsystem.factory;

import com.medicalsystem.model.Form;
import com.medicalsystem.properties.ConfigProperties;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class FormFactory {

    private static ConfigProperties props;

    @Autowired
    public FormFactory(ConfigProperties props) {
        FormFactory.props = props;
    }

    public static Form openFromConfig() {
        ConfigProperties.Form _open = props.getOpen();

        Form open = new Form(_open.getName());


        return null;
    }

}
