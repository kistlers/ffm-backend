package ch.fronis.model.player;

public class Player {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String shortName;
    private final Integer playerNumber;
    private final PlayerPosition position;
    private final Integer yearOfBirth;
    private final String image;

    public Player(
            int id, String firstName, String lastName, String shortName, Integer playerNumber, PlayerPosition position,
            Integer yearOfBirth, String image
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortName = shortName;
        this.playerNumber = playerNumber;
        this.position = position;
        this.yearOfBirth = yearOfBirth;
        this.image = image;
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

    public String getShortName() {
        return shortName;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public String getImage() {
        return image;
    }
}
