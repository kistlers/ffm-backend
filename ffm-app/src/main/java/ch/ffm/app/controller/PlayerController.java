package ch.ffm.app.controller;

import ch.ffm.app.config.HeaderHelper;
import ch.ffm.data.repository.PlayerRepository;
import ch.ffm.model.entity.Player;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * @return players ordered by number ascending and nulls last
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<Player>> players() {
        var players = playerRepository.findAllByOrderByPlayerNumberAsc().stream()
                .sorted(Comparator.comparing(Player::getPlayerNumber, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        return HeaderHelper.createOKResponseEntityDefaultCacheControl(players);
    }
}
