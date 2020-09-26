package ch.fronis.admin.controller.hibernate;

import ch.fronis.admin.entity.PlayerEntity;
import ch.fronis.admin.repository.PlayerRepository;
import ch.fronis.admin.repository.PlayerSpecification;
import ch.fronis.model.reactadmin.DeletedResponse;
import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AdminPlayerController {

    private static final Logger logger = LoggerFactory.getLogger(AdminPlayerController.class);

    private final PlayerRepository playerRepository;

    public AdminPlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerEntity>> all(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer perPage,
        @RequestParam(required = false, defaultValue = "id") String field,
        @RequestParam(required = false, defaultValue = "DESC") Sort.Direction order,
        @RequestParam(required = false, value = "q") String query
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
            .findById(id).orElseThrow(() -> new RuntimeException(String
                .format("Cannot handle player GET request. Player with id=%d not found", id)));
    }

    @PostMapping("/players")
    public PlayerEntity newPushRegion(@RequestBody PlayerEntity newPlayer) {
        return playerRepository.save(newPlayer);
    }

    @PutMapping("/players/{id}")
    public PlayerEntity replaceUser(@RequestBody PlayerEntity newPlayer, @PathVariable Integer id)
        throws PacketTooBigException {
        try {
            return playerRepository.findById(id).map(player -> {
                player.setFirstName(newPlayer.getFirstName());
                player.setLastName(newPlayer.getLastName());
                player.setPlayerNumber(newPlayer.getPlayerNumber());
                player.setPosition(newPlayer.getPosition());
                player.setImage(newPlayer.getImage());
                return playerRepository.save(player);
            }).orElseThrow(() -> new RuntimeException(String
                .format("Cannot handle player PUT request. Player with id=%d not found", id)));
        } catch (JpaSystemException e) {
            if (PacketTooBigException.class.equals(e.getCause().getClass())) {
                logger
                    .info("Max image size exceeded. Upload a smaller image. (playerId=" + id + ")");
                throw new PacketTooBigException("Max image size exceeded. Upload a smaller image.");
            }
            throw e;
        }
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<DeletedResponse> deleteAlert(@PathVariable Integer id) {
        playerRepository.deleteById(id);
        logger.info("deleted player with id: " + id);
        return ResponseEntity.ok().body(new DeletedResponse(id));
    }
}
