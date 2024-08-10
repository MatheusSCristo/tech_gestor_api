package com.gestaotech.api.enums;

public enum StructureEnum {

    TI(0),
    TI_N(1),
    TI_SOFTWARE_DEVELOPMENT(2),
    TI_COMPUTER_SCIENCE(3);

    private final int value;

    StructureEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
