package ch.fronis.app.controller;

import ch.fronis.app.config.HeaderHelper;
import ch.fronis.model.player.Player;
import ch.fronis.model.player.PlayerPosition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost")
public class PlayerController {

    public PlayerController() {
    }

    @GetMapping({"/players"})
    public ResponseEntity<List<Player>> players() {
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Die", "Mauer", "Wand", "1", PlayerPosition.GOAL, "1985"));
        players.add(new Player(2, "Shala", "Barrera", "Barry", "65", PlayerPosition.FIELD, "1997"));
        players.add(new Player(3, "Jeanna", "Schmal", "Jeanny", "27", PlayerPosition.FIELD, "1990"));
        players.add(new Player(4, "Leon", "Rogol", "Leo", "21", PlayerPosition.FIELD, "1990"));
        players.add(new Player(5, "Jeanna", "Schmal", "Jeanny", "27", PlayerPosition.FIELD, "1990"));
        players.add(new Player(6, "Shala", "Barrera", "Barry", "65", PlayerPosition.FIELD, "1997"));
        players.add(new Player(7, "Jeanna", "Schmal", "Jeanny", "27", PlayerPosition.FIELD, "1990"));
        players.add(new Player(8, "Caren", "Rials", "Carry", "27", PlayerPosition.FIELD, "1990"));
        players.add(new Player(9, "Coach", "Steve", "Steve", "", PlayerPosition.STAFF, "1990"));

        return HeaderHelper
                .createOKResponseEntity(players, HeaderHelper.NEXT_REFRESH_SECONDS, HeaderHelper.MAX_AGE_SECONDS);
    }
}
