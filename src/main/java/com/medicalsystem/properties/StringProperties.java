package com.medicalsystem.properties;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource(value = "classpath:strings.properties", encoding = "UTF-8")
public class StringProperties {

    private final Environment env;

    public String get(String key) {
        return env.getProperty(key);
    }

    public int getAsInt(String key) {
        return Integer.parseInt(get(key));
    }

}
