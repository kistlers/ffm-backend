package ch.fronis.admin.controller.hibernate;

import ch.fronis.admin.entity.PlayerEntity;
import ch.fronis.admin.repository.PlayerRepository;
import ch.fronis.admin.repository.PlayerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AdminPlayerController {

    private final PlayerRepository playerRepository;
    private static final Logger logger = LoggerFactory.getLogger(AdminPlayerController.class);

    public AdminPlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerEntity>> all(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer perPage,
            @RequestParam(required = false, defaultValue = "id") String field,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction order,
            @RequestParam(required = false) String query
    ) {
        PageRequest request = PageRequest.of(page, perPage, Sort.by(order, field));
        Specification<PlayerEntity> spec = new PlayerSpecification(query);
        Page<PlayerEntity> result = playerRepository.findAll(spec, request);
        return ResponseEntity.ok()
                .header("Content-Range", perPage + "/" + result.getTotalElements())
                .body(result.getContent());
    }

    @GetMapping("/players/{id}")
    public PlayerEntity one(@PathVariable Integer id) throws RuntimeException {
        return playerRepository
                .findById(id).orElseThrow(() -> new RuntimeException(String.format("player with id=%d not found", id)));
    }

    @PutMapping("/players/{id}")
    public PlayerEntity replaceUser(@RequestBody PlayerEntity newPlayer, @PathVariable Integer id) {
        return playerRepository.findById(id).map(player -> {
            player.setFirstName(newPlayer.getFirstName());
            player.setLastName(newPlayer.getLastName());
            player.setShortName(newPlayer.getShortName());
            player.setPlayerNumber(newPlayer.getPlayerNumber());
            player.setPosition(newPlayer.getPosition());
            player.setImage(newPlayer.getImage());
            return playerRepository.save(player);
        }).orElseGet(() -> {
            logger.warn("cannot handle player put request. player with id=" + id + " not found");
            // user creation on put not enabled, do nothing
            return null;
        });
    }
}
