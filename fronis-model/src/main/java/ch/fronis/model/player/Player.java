package ch.fronis.model.player;

public class Player {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String shortName;
    private final String playerNumber;
    private final PlayerPosition position;
    private final String yearOfBirth;
    private final byte[] image;

    public Player(
            int id, String firstName, String lastName, String shortName, String playerNumber, PlayerPosition position,
            String yearOfBirth, byte[] image
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

    public String getPlayerNumber() {
        return playerNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public byte[] getImage() {
        return image;
    }
}
