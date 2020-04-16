package mif.vu.ikeea.Enums;

public enum ERestrictionDays {
    DEFAULT(3);

    private final int value;

    ERestrictionDays(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
