package com.example.tenant.model;

import java.io.Serializable;

public class TenantModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public TenantModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
