package ch.fronis.admin.entity;

import ch.fronis.admin.entity.convert.ImageConverter;
import ch.fronis.model.image.Image;
import ch.fronis.model.player.PlayerPosition;

import javax.persistence.*;

@Entity(name = "t_player")
public class PlayerEntity {

    @Id
    @Column(name = "pk_player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "player_number")
    private Integer playerNumber;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @Column(name = "year_of_birth")
    private String yearOfBirth;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    @Convert(converter = ImageConverter.class)
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition playerPosition) {
        this.position = playerPosition;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
