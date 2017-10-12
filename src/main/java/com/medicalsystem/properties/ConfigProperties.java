package com.medicalsystem.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A class reflecting config.properties file structure.
 * Loads automaticaly on startup.
 */
@Component
@ConfigurationProperties("")
public class ConfigProperties {

    @Getter @Setter
    private Form open;

    @Getter @Setter
    private Form evar;

    public static class Form {

        @Getter @Setter
        private String name;

        @Getter @Setter
        private List<Section> sections = new ArrayList<>();

        public static class Section {

            @Getter @Setter
            private List<Field> fields = new ArrayList<>();

            public static class Field {

                @Getter @Setter
                private String name;

                @Getter @Setter
                private int index;

                @Getter @Setter
                private String type;

                @Getter @Setter
                private List<Option> options = new ArrayList<>();

                public static class Option {

                    @Getter @Setter
                    private String key;

                    @Getter @Setter
                    private String val;
                }
            }
        }
    }
}
