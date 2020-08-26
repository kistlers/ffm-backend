package ch.fronis.service.player;

import ch.fronis.model.player.Player;

import java.util.List;

public interface PlayerDataService {
    List<Player> getAllPlayers();
    
    Player getPlayer(Integer id);
}
