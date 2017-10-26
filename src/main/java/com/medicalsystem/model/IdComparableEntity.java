package com.medicalsystem.model;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class IdComparableEntity {

    public abstract long getId();

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClass());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof IdComparableEntity) {
            IdComparableEntity entity = (IdComparableEntity) obj;
            return Objects.equals(getClass(), entity.getClass()) && Objects.equals(getId(), entity.getId());
        }

        return false;
    }
}
