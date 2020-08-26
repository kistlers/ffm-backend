package ch.fronis.service.player;

import ch.fronis.model.player.Player;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JDBCPlayerDataService implements PlayerDataService {

    private final NamedParameterJdbcTemplate jt;

    public JDBCPlayerDataService(DataSource dataSource) {
        this.jt = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Player> getAllPlayers() {
        return jt.query("select * from t_player", new PlayerRowMappers.PlayerRowMapper());
    }

    @Override
    public Player getPlayer(Integer id) {
        return jt.queryForObject(
                "select * from t_player where pk_player_id = :id",
                new MapSqlParameterSource("id", id),
                new PlayerRowMappers.PlayerRowMapper()
        );
    }
}
