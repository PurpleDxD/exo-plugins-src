package net.runelite.client.plugins.xo.utils;

import net.runelite.api.Player;
import net.runelite.api.queries.PlayerQuery;
import net.runelite.client.plugins.xo.utils.models.GamePlayer;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerUtils extends ActorUtils<GamePlayer, Player, PlayerQuery> {
    @Override
    protected PlayerQuery getActorQuery() {
        return new PlayerQuery();
    }

    @Override
    protected GamePlayer getReturnModel(Player player) {
        return new GamePlayer(player);
    }

    @Override
    protected List<GamePlayer> getReturnModel(List<Player> players) {
        return players.stream().map(p -> new GamePlayer(p)).collect(Collectors.toList());
    }
}
