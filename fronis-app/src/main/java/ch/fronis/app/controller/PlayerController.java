package ch.fronis.app.controller;

import ch.fronis.app.config.HeaderHelper;
import ch.fronis.model.player.Player;
import ch.fronis.model.player.PlayerPosition;
import ch.fronis.service.player.PlayerDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class PlayerController {

    private final PlayerDataService playerDataService;

    public PlayerController(PlayerDataService playerDataService) {
        this.playerDataService = playerDataService;
    }

    @GetMapping({"/players"})
    public ResponseEntity<List<Player>> players() {
        List<Player> players = playerDataService.getAllPlayers();
        return HeaderHelper
                .createOKResponseEntity(players, HeaderHelper.NEXT_REFRESH_SECONDS, HeaderHelper.MAX_AGE_SECONDS);
    }

    @GetMapping({"/players/{id}"})
    public ResponseEntity<Player> player(@PathVariable Integer id) {
        Player player = playerDataService.getPlayer(id);
        return HeaderHelper
                .createOKResponseEntity(player, HeaderHelper.NEXT_REFRESH_SECONDS, HeaderHelper.MAX_AGE_SECONDS);
    }
}
