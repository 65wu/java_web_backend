package com.java_web.backend.model.po;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class NewsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer typeId;
    @Column(unique=true)
    @NotNull
    private String type;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
