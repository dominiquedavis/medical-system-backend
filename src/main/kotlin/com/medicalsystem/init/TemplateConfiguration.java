package com.medicalsystem.init;

import com.medicalsystem.domain.template.FieldType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value = "classpath:template_config.properties", encoding = "Windows-1250")
@ConfigurationProperties
public class TemplateConfiguration {
    private List<ConfigForm> forms = new ArrayList<>();

    public static class ConfigForm {
        private String name;
        private String type;
        private int index;
        private List<ConfigSection> sections = new ArrayList<>();

        public static class ConfigSection {
            private String name;
            private List<ConfigField> fields = new ArrayList<>();

            public static class ConfigField {
                private String name;
                private int excelColumnIndex;
                private FieldType type;
                private List<ConfigOption> options = new ArrayList<>();

                public static class ConfigOption {
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

                public List<ConfigOption> getOptions() {
                    return options;
                }

                public void setOptions(List<ConfigOption> options) {
                    this.options = options;
                }
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ConfigField> getFields() {
                return fields;
            }

            public void setFields(List<ConfigField> fields) {
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

        public List<ConfigSection> getSections() {
            return sections;
        }

        public void setSections(List<ConfigSection> sections) {
            this.sections = sections;
        }
    }

    public List<ConfigForm> getForms() {
        return forms;
    }

    public void setForms(List<ConfigForm> forms) {
        this.forms = forms;
    }
}
