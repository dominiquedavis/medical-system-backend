package com.medicalsystem.domain.report;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medicalsystem.domain.dto.FieldDTO;
import com.medicalsystem.serializer.FieldDeserializer;
import com.medicalsystem.serializer.FieldSerializer;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReportField {

    @Id @GeneratedValue
    private Long id = 0L;

    private boolean checked = false;

    @JsonSerialize(using = FieldSerializer.class)
    @JsonDeserialize(using = FieldDeserializer.class)
    private Long formField = 0L;

    private ConditionType conditionType = null;

    @ElementCollection
    private List<String> conditionValue = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getFormField() {
        return formField;
    }

    public void setFormField(Long formField) {
        this.formField = formField;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public List<String> getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(List<String> conditionValue) {
        this.conditionValue = conditionValue;
    }
}
