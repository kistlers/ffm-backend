package ch.fronis.app.controller;

import ch.fronis.app.config.HeaderHelper;
import ch.fronis.model.player.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/player")
public class PlayerController {

    public PlayerController() {
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Player> player() {
        Player player = new Player(1, "vorname", "nachame", 17);
        return HeaderHelper.createOKResponseEntity(player, HeaderHelper.NEXT_REFRESH_SECONDS, HeaderHelper.MAX_AGE_SECONDS);
    }
}
