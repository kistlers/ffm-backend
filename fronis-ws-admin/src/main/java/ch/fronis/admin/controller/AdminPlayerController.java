package ch.fronis.admin.controller;

import ch.fronis.model.player.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/player")
public class AdminPlayerController {

    public AdminPlayerController() {
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Player> player() {
        Player player = new Player(1, "vorname", "nachame", 17);
        return ResponseEntity.ok().body(player);
    }
}
