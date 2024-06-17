package com.pnu.lab.control.labcontrol.exception;


import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private String id;
    private Class<?> type;

    public EntityNotFoundException(String id, Class<?> type) {
        super("Не знайдено сутності %s за ідентифікатором %s".formatted(type.getSimpleName(), id));
        this.id = id;
        this.type = type;
    }
}
