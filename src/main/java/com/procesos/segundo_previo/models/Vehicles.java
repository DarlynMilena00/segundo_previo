package com.procesos.segundo_previo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Vehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
