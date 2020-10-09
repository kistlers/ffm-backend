package ch.fronis.app.controller;

import ch.fronis.app.config.HeaderHelper;
import ch.fronis.data.repository.PlayerRepository;
import ch.fronis.model.entity.Player;
import java.util.List;
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

    @GetMapping({"/players"})
    public ResponseEntity<List<Player>> players() {
        var players = playerRepository.findAll();
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
