package ch.fronis.model.player;

public enum PlayerPosition {
    STAFF, GOAL, FIELD;

    public static PlayerPosition fromString(String value) {
        if (value.equalsIgnoreCase("STAFF")) {
            return PlayerPosition.STAFF;
        }
        if (value.equalsIgnoreCase("GOAL")) {
            return PlayerPosition.GOAL;
        }
        if (value.equalsIgnoreCase("FIELD")) {
            return PlayerPosition.FIELD;
        }
        throw new IllegalArgumentException("Cannot convert " + value + " to PlayerPosition enum");
    }
}
