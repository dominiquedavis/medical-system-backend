package com.medicalsystem.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
@ConfigurationProperties
public class FormProperties {

    @Getter @Setter
    private List<Form> forms = new ArrayList<>();

    public static class Form {

        @Getter @Setter
        private String name;

        @Getter @Setter
        private List<Section> sections = new ArrayList<>();

        public static class Section {

            @Getter @Setter
            private String name;

            @Getter @Setter
            private List<Field> fields = new ArrayList<>();

            public static class Field {

                @Getter @Setter
                private String name;

                @Getter @Setter
                private int columnIndex;

                @Getter @Setter
                private String type;

                @Getter @Setter
                private List<Options> options = new ArrayList<>();

                public static class Options {

                    @Getter @Setter
                    private String key;

                    @Getter @Setter
                    private String val;

                }

            }

        }

    }

}
