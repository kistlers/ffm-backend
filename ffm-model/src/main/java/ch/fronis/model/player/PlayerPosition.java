package ch.fronis.model.player;

public enum PlayerPosition {
    STAFF,
    GOAL,
    FIELD;

    public static PlayerPosition fromString(String value) {
        if ("STAFF".equalsIgnoreCase(value)) {
            return PlayerPosition.STAFF;
        }
        if ("GOAL".equalsIgnoreCase(value)) {
            return PlayerPosition.GOAL;
        }
        if ("FIELD".equalsIgnoreCase(value)) {
            return PlayerPosition.FIELD;
        }
        throw new IllegalArgumentException("Cannot convert " + value + " to PlayerPosition enum");
    }
}
