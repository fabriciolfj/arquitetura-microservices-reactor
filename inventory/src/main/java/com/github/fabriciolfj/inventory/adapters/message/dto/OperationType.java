package com.github.fabriciolfj.inventory.adapters.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperationType {

    ENTRANCE("entrance"), EXIT("exit");

    private String description;

    public static OperationType toEnum(final String description) {
        return Stream.of(OperationType.values())
                .filter(p -> p.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Enum not found: " + description));
    }

}
