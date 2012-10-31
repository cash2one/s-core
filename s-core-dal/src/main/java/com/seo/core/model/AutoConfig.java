package com.seo.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auto_config")
public class AutoConfig extends BaseModel {
    @Basic
    private String name;

    @Lob
    private String config;

    public AutoConfig() {
    }

    public AutoConfig(String name, String config) {
        this.name = name;
        this.config = config;
    }

    public AutoConfig(Long id, String name, String config) {
        this.id = id;
        this.name = name;
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
