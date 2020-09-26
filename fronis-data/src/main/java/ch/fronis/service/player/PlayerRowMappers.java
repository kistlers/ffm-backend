package ch.fronis.service.player;

import ch.fronis.model.player.Player;
import ch.fronis.model.player.PlayerPosition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerRowMappers {
    static class PlayerRowMapper implements RowMapper<Player> {

        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("pk_player_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Integer playerNumber = resultSet.getObject("player_number", Integer.class);
            PlayerPosition position = PlayerPosition.fromString(resultSet.getString("position"));
            Integer yearOfBirth = resultSet.getObject("year_of_birth", Integer.class);
            String image = resultSet.getString("image");
            return new Player(id, firstName, lastName, playerNumber, position, yearOfBirth, image);
        }
    }
}
