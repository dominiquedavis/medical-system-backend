package com.medicalsystem.init;

import com.medicalsystem.domain.template.FieldType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value = "classpath:initialConfig.properties", encoding = "UTF-8")
@ConfigurationProperties
public class InitialConfig {
    private List<PropForm> forms = new ArrayList<>();

    public static class PropForm {
        private String name;
        private String type;
        private int index;
        private List<PropSection> sections = new ArrayList<>();

        public static class PropSection {
            private String name;
            private List<PropField> fields = new ArrayList<>();

            public static class PropField {
                private String name;
                private int excelColumnIndex;
                private FieldType type;
                private List<PropOption> options = new ArrayList<>();

                public static class PropOption {
                    private String key;
                    private String val;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getVal() {
                        return val;
                    }

                    public void setVal(String val) {
                        this.val = val;
                    }
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getExcelColumnIndex() {
                    return excelColumnIndex;
                }

                public void setExcelColumnIndex(int excelColumnIndex) {
                    this.excelColumnIndex = excelColumnIndex;
                }

                public FieldType getType() {
                    return type;
                }

                public void setType(FieldType type) {
                    this.type = type;
                }

                public List<PropOption> getOptions() {
                    return options;
                }

                public void setOptions(List<PropOption> options) {
                    this.options = options;
                }
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<PropField> getFields() {
                return fields;
            }

            public void setFields(List<PropField> fields) {
                this.fields = fields;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<PropSection> getSections() {
            return sections;
        }

        public void setSections(List<PropSection> sections) {
            this.sections = sections;
        }
    }

    public List<PropForm> getForms() {
        return forms;
    }

    public void setForms(List<PropForm> forms) {
        this.forms = forms;
    }
}
