package ch.fronis.model.entity;

import ch.fronis.model.entity.convert.Base64ImageConverter;
import ch.fronis.model.image.Image;
import ch.fronis.model.player.PlayerPosition;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "t_player")
public class Player {

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
    @Convert(converter = Base64ImageConverter.class)
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
