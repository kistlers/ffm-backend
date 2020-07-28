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

    private final PlayerRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(AdminPlayerController.class);

    public AdminPlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerEntity>> all(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer perPage,
                                                  @RequestParam(required = false, defaultValue = "id") String sortField,
                                                  @RequestParam(required = false, defaultValue = "DESC") Sort.Direction sortOrder,
                                                  @RequestParam(required = false) String query) {
        PageRequest request = PageRequest.of(page, perPage, sortOrder, sortField);
        Specification<PlayerEntity> spec = new PlayerSpecification(query);
        Page<PlayerEntity> result = repository.findAll(spec, request);
        return ResponseEntity.ok()
                .header("content-range", perPage + "/" + result.getTotalElements())
                .body(result.getContent());
    }

    @GetMapping("/players/{id}")
    public PlayerEntity one(@PathVariable Integer id) throws Throwable {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("player with id=%d not found", id)));
    }

    @PutMapping("/players/{id}")
    public PlayerEntity replaceUser(@RequestBody PlayerEntity newPlayer, @PathVariable Integer id) {
        return repository.findById(id).map(player -> {
            player.setFirstName(newPlayer.getFirstName());
            player.setLastName(newPlayer.getLastName());
            player.setPlayerNumber(newPlayer.getPlayerNumber());
            return repository.save(player);
        }).orElseGet(() -> {
            logger.warn("cannot handle player put request. player with id=" + id + " not found");
            // user creation not enabled, do nothing
            return null;
        });
    }
}
