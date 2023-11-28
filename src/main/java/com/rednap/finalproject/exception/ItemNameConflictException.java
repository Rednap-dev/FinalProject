package com.rednap.finalproject.exception;

import lombok.Getter;

public class ItemNameConflictException extends RuntimeException {

    @Getter
    private final String name;

    public ItemNameConflictException(final String name) {
        super();
        this.name = name;
    }
}
