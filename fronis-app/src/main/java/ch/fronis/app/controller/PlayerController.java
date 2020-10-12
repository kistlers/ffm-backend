package ch.fronis.app.controller;

import ch.fronis.app.config.HeaderHelper;
import ch.fronis.data.repository.PlayerRepository;
import ch.fronis.model.entity.Player;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * @return players ordered by number ascending and nulls last
     */
    @GetMapping({"/players"})
    public ResponseEntity<List<Player>> players() {
        var players =
                playerRepository.findAllByOrderByPlayerNumberAsc().stream()
                        .sorted(
                                (o1, o2) -> {
                                    var o1Number = o1.getPlayerNumber();
                                    var o2Number = o2.getPlayerNumber();

                                    if (o1Number == null && o2Number == null) {
                                        return 0;
                                    }
                                    if (o2Number == null) {
                                        return -1;
                                    }
                                    if (o1Number == null) {
                                        return 1;
                                    }
                                    return 0;
                                })
                        .collect(Collectors.toList());
        return HeaderHelper.createOKResponseEntity(
                players, HeaderHelper.NEXT_REFRESH_SECONDS, HeaderHelper.MAX_AGE_SECONDS);
    }

    @GetMapping({"/players/{id}"})
    public ResponseEntity<Player> player(@PathVariable Integer id) throws NotFoundException {
        var player = playerRepository.findById(id).orElseThrow(NotFoundException::new);
        return HeaderHelper.createOKResponseEntity(
                player, HeaderHelper.NEXT_REFRESH_SECONDS, HeaderHelper.MAX_AGE_SECONDS);
    }
}
