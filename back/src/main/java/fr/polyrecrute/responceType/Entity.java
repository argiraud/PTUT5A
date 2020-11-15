package fr.polyrecrute.responceType;

import javax.persistence.Column;

public class Entity {

    private Long idEntity;
    private String name;

    public Entity(Long idEntity, String name) {
        this.idEntity = idEntity;
        this.name = name;
    }

    public Long getIdEntity() {
        return idEntity;
    }

    public String getName() {
        return name;
    }
}
