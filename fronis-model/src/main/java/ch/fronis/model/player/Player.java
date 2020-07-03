package ch.fronis.model.player;

public class Player {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final Integer playerNumber;

    public Player(int id, String firstName, String lastName, Integer playerNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerNumber = playerNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }
}
